package org.eserlan.npcgenerator

import org.junit.jupiter.api.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource

@SpringBootTest
@TestPropertySource(locations = ["classpath:application.yml"])
class ScabardServiceTest {


    @Autowired
    lateinit var service: ScabardService

    @Test
    fun updateScabard() {

        val characterDesc = service.get(arrayOf("character", "2991708"))
        println(characterDesc)

    }
}