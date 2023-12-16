package show.dnddetector

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.awt.image.BufferedImage
import java.awt.image.BufferedImage.TYPE_INT_ARGB
import java.io.File
import javax.imageio.ImageIO


class LightSpotDetectorTest {

    @Test
    fun `test something`() {
        val input = Util.getLocalResource("./phone-1-images.jpeg")
        val taker = LightSpotDetector()
        assertNotNull(input)
        val n = taker.detect(input, outputFileName = "target/phone-out-images.jpeg")
        assertEquals(2, n.size)
    }
}
