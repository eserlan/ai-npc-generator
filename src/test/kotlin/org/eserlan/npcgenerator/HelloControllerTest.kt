package org.eserlan.npcgenerator

import ChatGPTService
import org.junit.jupiter.api.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

@WebMvcTest
class HelloControllerTest (@Autowired val mockMvc: MockMvc){

    @MockBean
    lateinit var chad: ChatGPTService
    @Test
    fun helloKotlin() {
        mockMvc.perform(get("/hello")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
    }
}