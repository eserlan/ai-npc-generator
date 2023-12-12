package org.eserlan.npcgenerator

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import java.io.File
@SpringBootTest
@TestPropertySource(locations = ["classpath:application.yml"])
class ConverterServiceTest {

    @Autowired
    lateinit var service: ConverterService
    @Test
    fun convertMarkdownToHtml() {
        val html = service.convertMarkdownToHtml(readFile())
        println("html: $html")
    }

    @Test
    fun extractContentFromHtml() {
         service.parseHtml(readFile())


    }

    fun readFile(): String {
//        val p = "Jarek Kian.txt"
        val p = "TheBlacksmith.html"
        return File(p).readText()
    }
}