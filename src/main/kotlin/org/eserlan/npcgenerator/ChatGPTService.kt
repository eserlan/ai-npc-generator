import com.cjcrafter.openai.chat.ChatMessage
import com.cjcrafter.openai.chat.ChatMessage.Companion.toSystemMessage
import com.cjcrafter.openai.chat.ChatMessage.Companion.toUserMessage
import com.cjcrafter.openai.chat.chatRequest
import com.cjcrafter.openai.openAI
import org.eserlan.npcgenerator.ChatGPTConfiguration
import org.springframework.stereotype.Service
import java.util.*

/**
 * In this Java example, we will be using the Chat API to create a simple chatbot.
 */
@Service
class ChatGPTService (val conf: ChatGPTConfiguration){
    fun callChad(args: Array<String>) {

        // dependency. Then you can add a .env file in your project directory.
        val key: String = conf.key
        val openai = openAI { apiKey(key) }

        // Here you can change the model's settings, add tools, and more.
        val request = chatRequest {
            model("gpt-4")
            addMessage("Help the user with their problem.".toSystemMessage())
        }
        val messages: MutableList<ChatMessage> = ArrayList()




        while (true) {
            println("What are you having trouble with?")
            val input = args[0]
            messages.add(input.toUserMessage())
            val response = openai.createChatCompletion(request)
            println("Generating Response...")
            println(response[0].message.content)

            // Make sure to add the response to the messages list!
            messages.add(response[0].message)
        }
    }
}