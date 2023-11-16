package org.eserlan.npcgenerator

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(ChatGPTConfiguration::class)
class NPCApplication {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<NPCApplication>(*args)
        }
    }
}