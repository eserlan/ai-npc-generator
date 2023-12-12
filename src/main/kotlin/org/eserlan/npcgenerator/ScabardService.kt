package org.eserlan.npcgenerator

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.http.MediaType
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono


@Service
class ScabardService(
    val conf: ScabardConfiguration,
    val converter: ConverterService,
    val webClientBuilder: WebClient.Builder
) {

    private val logger = KotlinLogging.logger {}
    val desc = """
            <p><strong>Name</strong>: Jarek Kian
            <strong>Age</strong>: 19 (Born 621)</p>
            <p><em>&quot;Grit and grind, that's all there is to life!&quot;</em></p>
            <p>An optimistic young man with a dirty face and straw hat, who dreams of becoming the richest cattle baron in the realm.</p>
            <p><strong>Appearance</strong>:
            Jarek's face, well-tanned with sun-kissed brown eyes, always has a smile. He is well-built due to his constant farmhouse chores. His curly black hair sticks out from under his trampled straw hat.</p>
            <p><strong>Background</strong>: A hardworking farmhand on a Savannah ranch who was orphaned at a young age. He has been living and working with Old Lady Marla since the age of 7, who took him under her care after his parents untimely death due to a monstrous beast attack.</p>
            <p><strong>Clothing/Gear</strong>:
            Dressed in simple yet hardwearing clothes suitable for his chores. Jarek carries a short, blunt knife for utility purposes and leather gloves to keep his hands safe while working. Additionally, he owns a trusty old lasso, a gift from Marla.</p>
            <p><strong>Location/Home Base</strong>: Savannah Fields, a vast stretch of arid land dotted with sparse trees and the magnificent ranch dwelling of Old Lady Marla.</p>
        """.trimIndent()
    val secrets = """
            <p><strong>Personality/Temperament</strong>:
            Jarek is resilient, cheerful, and undeterred even in the face of the harshest of life's trials. His dedication to work may sometimes come off as being over-eager, but he strives for a better life.</p>
            <p><strong>Abilities/Skills, Occupation</strong>: While being an excellent cattleherder and a hardworking farmhand, Jarek also possesses an uncanny ability to tame almost any animal; horses, cattle, even the more disobedient ones.</p>
            <p><strong>Long-Term Goals</strong>:</p>
            <ul>
            <li>Become the owner of the biggest ranch in the realm</li>
            </ul>
            <p><strong>Short-Term Goals</strong>:</p>
            <ul>
            <li>Find a partner to help him with his heavy workload</li>
            <li>Discover the truth about his parents' death</li>
            </ul>
            <p><strong>Rumors</strong>:</p>
            <ul>
            <li>It is said that Jarek is the illegitimate son of a local noble.</li>
            <li>Some talk about a dark beast roaming the savannah fields at night.</li>
            </ul>
            <p><strong>Religion and Relationships</strong>:
            Jarek holds great respect for the Old Gods of the Savannah, the way his mother taught him. He holds a close bond with Old Lady Marla, the cattle on the field, and the handful of other farmhands.</p>
            <p><strong>Quests / Plot Hooks</strong>:</p>
            <ul>
            <li>Jarek has been noticing some cattle missing recently, suspecting monster activities in the area. He could seek adventurers to help solve this issue.</li>
            <li>He has a hidden desire to investigate the lore of the dark beast that haunts the Savannah, suspecting it to be the same beast that killed his parents.</li>
            </ul>
            <p><strong>Dark Secret</strong>:
            Jarek has been diverting some of his earnings secretly to a local scholar to learn more about the dark beast and how he could potentially defeat it.</p>
            <p><strong>Text based AI generator Image Prompt</strong>:
            A youthful face, dirty from the earth, grinning under a worn straw hat. Muscled arms wrapped in rough linen are draped over a wooden large fence, behind him is a vast expanse of dry grass and cattle herds eating, the setting sun casting warm hues over the scene. On his face, the light marked the lines of inspiration and aspirations that fills inside this young man's heart.</p>
        """.trimIndent()

    fun get(args: Array<String>): String {
        println("url: ${conf.url}")
        println("campaignId: ${conf.campaignId}")

        val webClient = webClientBuilder
            .baseUrl(conf.url)
            .build()


        val concept = args[0]
        val conceptId = args[1]
        val result = webClient.get()
            .uri("/${conf.campaignId}/$concept/$conceptId") //ilona
            .header("Username", conf.username)
            .header("accessKey", "239393675039405307")
            .header("Content-Type", "application/json")
            .retrieve()
            .bodyToMono(String::class.java)
            .block()


        return converter.convertHtmlToMarkdown(result!!)
    }

    data class Character(
        val name: String,
        val briefSummary: String,
        val isSecret: Boolean? = null,
        val description: String,
        val secrets: String,
        val gmSecrets: String,
        val concept: String? = null
    )
    fun post(name: String?, query: String, html: String) {
        logger.warn { "JAAAAAAA" }
        logger.debug { "NEEEEEI" }
        println("scabard user = ${conf.username}")
        println("url: ${conf.url}")
        println("campaignId: ${conf.campaignId}")

        val webClient = webClientBuilder
            .baseUrl(conf.url)
            .build()

        val descAndSecrets : Pair<String, String> = converter.parseHtml(html)


//        val character = Character(
//            name = name!!,
//            briefSummary = query,
//            description = descAndSecrets.first,
//            secrets = descAndSecrets.second,
//            gmSecrets = "even by deceit and lies"
//        )

        val character = Character(
            name = "Jarekian",
            briefSummary = "just a guy",
            description = desc,
            secrets = secrets,
            gmSecrets = "even by deceit and lies"
        )

        println("char = $character")



        val concept = "character"
        val conceptId = "3102397"

        val result = webClient.post()
            .uri("/${conf.campaignId}/$concept")
            .header("Content-Type", APPLICATION_JSON_VALUE)
            .header("username", conf.username)
            .header("accessKey", "7533274524823901661")
            .body(Mono.just(character), Character::class.java)
            .retrieve()
            .bodyToMono(String::class.java)
            .doOnNext { response -> println("HTTP response: $response") } // Log the HTTP response
            .doOnError { error -> println("HTTP POST error: $error") } // Log any HTTP error
            .block()

        println("result = $result")


    }
}