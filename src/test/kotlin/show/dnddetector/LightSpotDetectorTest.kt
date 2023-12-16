package show.dnddetector

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test


class LightSpotDetectorTest {

    @Test
    fun `test something`() {
        val input = Util.getLocalResource("./phone-1-images.jpeg")
        val taker = LightSpotDetector()
        assertNotNull(input)
        val n = taker.detect(input, 40971 + 1, outputFileName = "target/phone-out-images.jpeg")
        input.close()
        assertEquals(2, n.size)
    }
}
