package org.eserlan.npcgenerator

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "chatgpt")
data class ChatGPTConfiguration (
    val key: String = "",
    val url: String = ""
)