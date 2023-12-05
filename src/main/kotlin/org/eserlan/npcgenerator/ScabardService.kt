package org.eserlan.npcgenerator

import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient


@Service
class ScabardService (
    val conf: ScabardConfiguration,
    val converter: ConverterService,
    val webClientBuilder: WebClient.Builder) {


    fun get(args: Array<String>): String {
        println("url: ${conf.url}")
        println("campaignId: ${conf.campaignId}")
        println("campaignId: ${conf.key}")

        val webClient = webClientBuilder.baseUrl(conf.url).build()


        val concept = args[0]
        val conceptId = args[1]
        val result = webClient.get()
            .uri("/${conf.campaignId}/$concept/$conceptId") //ilona
            .header("Username", conf.username)
            .header("accessKey", conf.key)
            .retrieve()
            .bodyToMono(String::class.java)
            .block()


        return converter.convertHtmlToMarkdown(result!!)
    }
}