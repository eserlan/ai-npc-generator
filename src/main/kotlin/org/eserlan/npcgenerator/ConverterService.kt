package org.eserlan.npcgenerator

import com.fasterxml.jackson.databind.ObjectMapper
import com.vladsch.flexmark.html.HtmlRenderer
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter
import com.vladsch.flexmark.parser.Parser
import com.vladsch.flexmark.util.ast.Node
import org.springframework.stereotype.Service


@Service
class ConverterService () {


    fun convertHtmlToMarkdown(resultFromScabard: String): String {
        val value = ObjectMapper().readValue(resultFromScabard, HashMap::class.java)
        val desc: String = findValueForKey(value, "description")!!
        return FlexmarkHtmlConverter.builder().build().convert(desc)
    }

    fun findValueForKey(map: HashMap<*, *>, targetKey: String): String? {
        for ((key, value) in map) {
            if (key == targetKey) {
                return value as String?
            } else if (value is HashMap<*, *>) {
                val result = findValueForKey(value, targetKey)
                if (result != null) {
                    return result
                }
            }
        }
        return null
    }

    fun convertMarkdownToHtml(resultFromScabard: String): String {
        val parser: Parser = Parser.builder().build()
        val renderer = HtmlRenderer.builder().build()

        val document: Node = parser.parse(resultFromScabard)
        val html = renderer.render(document)

        println(html)
        return html
    }
}