package org.eserlan.npcgenerator

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.EnabledIfSystemProperty

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit.jupiter.EnabledIf

@SpringBootTest
@TestPropertySource(locations = ["classpath:application.yml"])
class ScabardServiceTest {


    @Autowired
    lateinit var service: ScabardService

    @Test
    @EnabledIfSystemProperty(named = "scabard-integration", matches = "true")
    fun updateScabard() {

        val characterDesc = service.get(arrayOf("character", "2991708"))
        println(characterDesc)

    }

}