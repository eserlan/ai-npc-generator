package org.eserlan.npcgenerator

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class NPCController (val chad: ChatGPTService){

    @GetMapping("/hello")
    fun helloKotlin(@RequestParam(required = false, defaultValue = "World") name: String): String? {
        println("request: $name")

        return chad.callChad(arrayOf(name))
    }
}