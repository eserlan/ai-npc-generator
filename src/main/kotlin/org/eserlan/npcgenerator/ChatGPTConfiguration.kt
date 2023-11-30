package org.eserlan.npcgenerator

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "chatgpt")
data class ChatGPTConfiguration (
    var key: String = "",
    var url: String = "",
    var timeout: Long = 30
)