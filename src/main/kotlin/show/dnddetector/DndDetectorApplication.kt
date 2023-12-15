package show.dnddetector

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DndDetectorApplication

fun main(args: Array<String>) {
    runApplication<DndDetectorApplication>(*args)
}
