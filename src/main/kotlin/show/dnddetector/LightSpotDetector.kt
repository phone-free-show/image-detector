package show.dnddetector

import nu.pattern.OpenCV
import org.opencv.core.CvType
import org.opencv.core.Mat
import org.opencv.core.MatOfByte
import org.opencv.core.MatOfPoint
import org.opencv.core.Scalar
import org.opencv.imgcodecs.Imgcodecs
import org.opencv.imgproc.Imgproc
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream


class LightSpotDetector {
    init {
        OpenCV.loadLocally()
    }
    fun detect(input: InputStream, minContourArea:Double = 4.0, outputFileName: String? = null):List<MatOfPoint> {
        // Load the image

        val inputImage: Mat = readInputStreamIntoMat(input)

        // Apply thresholding
        val binaryImage: Mat = Mat()
        Imgproc.threshold(inputImage, binaryImage, 200.0, 255.0, Imgproc.THRESH_BINARY)

        // Find contours
        val contours: List<MatOfPoint> = ArrayList<MatOfPoint>()
        val hierarchy: Mat = Mat()
        Imgproc.findContours(binaryImage, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE)

        // Filter contours based on area (optional)
        val filteredContours= contours.filter { contour -> Imgproc.contourArea(contour) > minContourArea }


        // Draw contours on the original image
        val resultImage: Mat = Mat(inputImage.size(), CvType.CV_8UC3, Scalar(255.0, 255.0, 255.0))
        Imgproc.drawContours(resultImage, contours, -1, Scalar(0.0, 0.0, 255.0), -1)
//        Imgproc.drawContours(resultImage, filteredContours, -1, Scalar(0.0, 0.0, 255.0), -1)

        // Display or save the result
        outputFileName.let {  Imgcodecs.imwrite(outputFileName, resultImage) }
        return filteredContours
    }

    private fun readStream(stream: InputStream): ByteArray {
        // Copy content of the image to byte-array
        val buffer = ByteArrayOutputStream()
        var nRead: Int
        val data = ByteArray(16384)

        while ((stream.read(data, 0, data.size).also { nRead = it }) != -1) {
            buffer.write(data, 0, nRead)
        }

        buffer.flush()
        val temporaryImageInMemory = buffer.toByteArray()
        buffer.close()
        stream.close()
        return temporaryImageInMemory
    }

    @Throws(IOException::class)
    private fun readInputStreamIntoMat(inputStream: InputStream, kind: Int = Imgcodecs.IMREAD_GRAYSCALE): Mat {
        // Read into byte-array
        val temporaryImageInMemory = readStream(inputStream)

        // Decode into mat. Use any IMREAD_ option that describes your image appropriately
        val outputImage: Mat = Imgcodecs.imdecode(MatOfByte(*temporaryImageInMemory), kind)

        return outputImage
    }
}