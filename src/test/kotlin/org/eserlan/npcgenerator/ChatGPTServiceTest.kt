package org.eserlan.npcgenerator

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class ChatGPTServiceTest {

    @Test
    fun extractName() {
        val service = ChatGPTService(ChatGPTConfiguration())
        assertEquals("Bob", service.extractName("Name: Bob\nRole: Guard"))
    }

    @Test
    fun extractNameFromLine2() {
        val service = ChatGPTService(ChatGPTConfiguration())
        assertEquals("Bob", service.extractName("----\nName: Bob\nRole: Guard"))
    }
    @Test
    fun extractNameFromCorrect() {
        val service = ChatGPTService(ChatGPTConfiguration())
        assertEquals("Bob", service.extractName("----\n\n**Name**: Bob\nRole: Guard"))
    }

    @Test
    fun extractNameAndStripStars() {
        val service = ChatGPTService(ChatGPTConfiguration())
        assertEquals("Bob", service.extractName("----\n\n**Name:** Bob\nRole: Guard"))
    }

    @Test
    fun extractNameAsHeader() {
        val service = ChatGPTService(ChatGPTConfiguration())
        assertEquals("Bob", service.extractName("# Bob"))
    }
}