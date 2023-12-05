package org.eserlan.npcgenerator

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "scabard")
data class ScabardConfiguration (
    var key: String = "",
    var url: String = "",
    var username: String = "",
    var campaignId: Long = 0,
    var timeout: Long = 30
)