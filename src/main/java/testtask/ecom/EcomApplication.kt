package testtask.ecom

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class EcomApplication

fun main(args: Array<String>) {
    runApplication<EcomApplication>(*args)
}