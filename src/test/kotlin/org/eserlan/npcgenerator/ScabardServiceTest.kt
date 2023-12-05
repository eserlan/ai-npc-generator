package org.eserlan.npcgenerator

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.EnabledIf

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource

@SpringBootTest
@TestPropertySource(locations = ["classpath:application.yml"])
class ScabardServiceTest {


    @Autowired
    lateinit var service: ScabardService

    @Test
    @EnabledIf("org.eserlan.npcgenerator.isFlagSet")
    fun updateScabard() {

        val characterDesc = service.get(arrayOf("character", "2991708"))
        println(characterDesc)

    }

    fun isFlagSet(): Boolean {
        val flag = System.getProperty("scabard-integration")
        return flag != null && flag == "true"
    }
}