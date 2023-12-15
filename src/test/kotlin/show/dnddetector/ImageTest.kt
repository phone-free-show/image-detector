package show.dnddetector

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.awt.image.BufferedImage
import java.awt.image.BufferedImage.TYPE_INT_ARGB
import java.io.File
import javax.imageio.ImageIO


class ImageTest {

    @Test
    fun `test something`() {
        val input = Util.getLocalResource("./phone-1-images.jpeg")
        val taker = Taker()
        assertNotNull(input)
        val image: BufferedImage = taker.take(input)
        val pix1 = image.getRGB(0, 0)
        assertEquals(-6579301, pix1)
        assertEquals(183, image.height)
        assertEquals(275, image.width)
        //assertEquals( TYPE_3BYTE_BGR, image.type)
        assertEquals(TYPE_INT_ARGB, image.type)

        val result = ImageIO.write(image, "TIFF", File("./target/out-111.tiff"))
        assertTrue(result)
    }
}
