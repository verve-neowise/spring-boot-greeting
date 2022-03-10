package com.neowise.reactpizza

import com.neowise.reactpizza.data.model.StudentDto
import com.neowise.reactpizza.data.repository.StudentRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class ReactPizzaApplication {

    @Bean
    fun commandLineRunner(studentRepository: StudentRepository): CommandLineRunner {
        return CommandLineRunner {
            studentRepository.save(
                StudentDto(
                    lastName = "Jalol",
                    firstName = "Imomaddinov",
                    email = "verve.neowise@gmail.com",
                    age = 25
                )
            )

			println(studentRepository.findAll())
        }
    }
}

fun main(args: Array<String>) {
    runApplication<ReactPizzaApplication>(*args)
}
