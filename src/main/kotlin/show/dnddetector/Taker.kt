package show.dnddetector

import java.awt.Image
import java.awt.Point
import java.awt.Rectangle
import java.awt.Toolkit
import java.awt.image.BufferedImage
import java.awt.image.FilteredImageSource
import java.awt.image.ImageFilter
import java.awt.image.ImageProducer
import java.io.InputStream
import javax.imageio.ImageIO
import javax.swing.GrayFilter

class Taker(private val shade: Int = 50) {

    fun take(input: InputStream): BufferedImage {
        val image: BufferedImage = ImageIO.read(input)

        val filter: ImageFilter = GrayFilter(true, shade)
        val producer: ImageProducer = FilteredImageSource(image.getSource(), filter)
        val image2: Image = Toolkit.getDefaultToolkit().createImage(producer)

        // Create a buffered image with transparency
        val bimage = BufferedImage(image2.getWidth(null), image2.getHeight(null), BufferedImage.TYPE_INT_ARGB)

        // Draw the image on to the buffered image
        val bGr = bimage.createGraphics()
        bGr.drawImage(image2, 0, 0, null)
        bGr.dispose()
        return bimage
    }
}