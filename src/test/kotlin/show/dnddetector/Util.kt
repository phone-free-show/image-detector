package show.dnddetector

import java.io.BufferedInputStream
import java.io.InputStream

class Util {
    companion object {

        fun getLocalResource(theName: String): InputStream {
            val input = Thread.currentThread().getContextClassLoader().getResourceAsStream(theName)
                ?: throw RuntimeException("Can not find $theName")
            return BufferedInputStream(input)
        }
    }
}
