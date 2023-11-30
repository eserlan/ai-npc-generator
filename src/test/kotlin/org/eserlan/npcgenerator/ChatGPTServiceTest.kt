package org.eserlan.npcgenerator

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class ChatGPTServiceTest {

    @Test
    fun extractName() {
        val service = ChatGPTService(ChatGPTConfiguration())
        service.extractName("Name: Bob\nRole: Guard").let { assertEquals("Bob", it) }
    }
}