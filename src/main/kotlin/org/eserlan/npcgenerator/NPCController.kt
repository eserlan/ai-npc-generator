package org.eserlan.npcgenerator

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class NPCController(
    val chad: ChatGPTService,
    val scabard: ScabardService,
    val converter: ConverterService
) {

    @GetMapping("/createNpc")
    fun createNpc(@RequestParam(required = false, defaultValue = "a fantasy npc") query: String): String? {
        println("request: $query")

        val npcAndName = chad.callChad(arrayOf(query))
        val md = npcAndName.first
        val name = npcAndName.second

        val html = converter.convertMarkdownToHtml(md)

        chad.writeHtmlNpcToFile(name, html)

        scabard.post(name, query, html)


        return html
    }


    @GetMapping("/getFromScabard")
    fun getFromScabard(): String? {
        return scabard.get(
            arrayOf(
                "character",
                "3102397"
            )
        )
    }
}