package org.eserlan.npcgenerator

import com.cjcrafter.openai.chat.ChatMessage.Companion.toSystemMessage
import com.cjcrafter.openai.chat.ChatMessage.Companion.toUserMessage
import com.cjcrafter.openai.chat.chatRequest
import com.cjcrafter.openai.openAI
import okhttp3.OkHttpClient
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import java.io.File
import java.util.concurrent.TimeUnit.SECONDS


/**
 * In this Java example, we will be using the Chat API to create a simple chatbot.
 */
@Service
class ChatGPTService(@Qualifier("chatGPTConfiguration") var conf: ChatGPTConfiguration) {

    val guideline = """
            Name: 
            Age:    (and date of birth, when current date is 640, date of death, if its a dead person)
            <Catchphrase>
            <Short description>
            <One liner to describe the character>
            Appearance:
            Background:
            Clothing/Gear:
            Location/Home Base:
            
            --- SECRETS ---

            Personality/Temperament:
            Abilities/Skills, Occupation:
            Long-Term Goals: A pointed list, 2-4 points
            Short-Term Goals: A pointed list, 2-4 points
            Rumors: A pointed list, 2-4 points
            Religion and Relationships:
            Quests / Plot Hooks: A pointed list, 2-4 points
            Dark Secret:
            Text based ai generator Image Prompt: 
        """.trimIndent()

    fun callChad(args: Array<String>): Pair<String, String> {

        // dependency. Then you can add a .env file in your project directory.
        println("key: ${conf.key}")

        val okHttpClient = OkHttpClient.Builder().readTimeout(conf.timeout, SECONDS).build()
        val openai = openAI {
            apiKey(conf.key)
            client(okHttpClient)
        }

        // Here you can change the model's settings, add tools, and more.
        val request = chatRequest {
            model("gpt-4")
            addMessage("Help the user with creating a fantasy rpg tabletop ncp character".toSystemMessage())
            addMessage("Dont tell me the process, just provide the info. Pls style the output in markdown".toSystemMessage())
            addMessage(guideline.toSystemMessage())
        }

        val input = args[0]
        println(input)
//        messages.add(input.toUserMessage())
        request.messages.add(input.toUserMessage())
        val response = openai.createChatCompletion(request)
        println("Generating Response...")
        val npc: String = response[0].message.content!!
        println(npc)

        writeNpcToFile(npc)
        val name = extractName(npc)

        return Pair(npc, name)

    }

    private fun writeNpcToFile(npc: String?) {
        val name = extractName(npc)
        val file = File("$name.txt")
        npc?.let { file.writeText(it) }
    }


    fun writeHtmlNpcToFile(name: String, html: String) {
        val file = File("$name.html")
        html.let { file.writeText(it) }
    }

    fun extractName(npc: String?): String {
        val lines = npc?.lines()
        var nameLine = ""
        
        var firstLine = lines!![0]

        for (line in lines) {

            line.lowercase().trim().contains("name").let {
                if (it) {
                    nameLine = line.replace("*", "")
                }
            }
        }

        return nameLine.substringAfter(":").trim()
    }
}