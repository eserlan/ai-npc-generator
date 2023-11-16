package org.eserlan.npcgenerator

import ChatGPTService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController (val chad: ChatGPTService) {

    @GetMapping("/hello")
    fun helloKotlin(@RequestParam(required = false, defaultValue = "World") name: String): String {
        println("hello $name")

        chad.callChad(arrayOf(name))

        return "hello $name"
    }
}