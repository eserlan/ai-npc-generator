package org.eserlan.npcgenerator

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class NPCApplication {

    fun main(args: Array<String>) {
        runApplication<NPCApplication>(*args)
    }
}