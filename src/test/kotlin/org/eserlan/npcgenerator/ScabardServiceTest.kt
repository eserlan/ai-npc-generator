package org.eserlan.npcgenerator

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.EnabledIfSystemProperty

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource

@SpringBootTest
@TestPropertySource(locations = ["classpath:application.yml"])
class ScabardServiceTest {


    @Autowired
    lateinit var service: ScabardService

    @Test
    @EnabledIfSystemProperty(named = "scabard-integration", matches = "true")
    fun getFromScabard() {

        val characterDesc = service.get(arrayOf("character", "3102397"))
        println(characterDesc)

    }
    @Test
    @EnabledIfSystemProperty(named = "scabard-integration", matches = "true")
    fun postToScabard() {

        val characterDesc = service.post("Jarik", "silent magyar blacksmith", "something something html")
        println(characterDesc)

    }



}