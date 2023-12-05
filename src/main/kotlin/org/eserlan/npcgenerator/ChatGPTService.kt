package org.eserlan.npcgenerator

import com.cjcrafter.openai.chat.ChatMessage.Companion.toSystemMessage
import com.cjcrafter.openai.chat.ChatMessage.Companion.toUserMessage
import com.cjcrafter.openai.chat.chatRequest
import com.cjcrafter.openai.openAI
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import okhttp3.OkHttpClient
import java.io.File
import java.util.concurrent.TimeUnit.SECONDS


/**
 * In this Java example, we will be using the Chat API to create a simple chatbot.
 */
@Service
class ChatGPTService(@Qualifier("chatGPTConfiguration") var conf: ChatGPTConfiguration) {
    fun callChad(args: Array<String>): String? {

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
            addMessage("Pls also add a oneliner for Midjourney".toSystemMessage())
        }

        val input = args[0]
        println(input)
//        messages.add(input.toUserMessage())
        request.messages.add(input.toUserMessage())
        val response = openai.createChatCompletion(request)
        println("Generating Response...")
        val npc = response[0].message.content
        println(npc)

        writeNpcToFile(npc)

        return npc

    }

    private fun writeNpcToFile(npc: String?) {
        val name = extractName(npc)
        val file = File("$name.txt")
        npc?.let { file.writeText(it) }
    }

    fun extractName(npc: String?): String {
        val lines = npc?.lines()
        var nameLine = ""

        for (line in lines!!) {

            line.lowercase().trim().contains("name").let {
                if (it) {
                    nameLine = line.replace("*", "")
                }
            }
        }

        return nameLine.substringAfter(":").trim()
    }
}