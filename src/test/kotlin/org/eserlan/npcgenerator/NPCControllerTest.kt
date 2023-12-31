package org.eserlan.npcgenerator

import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

@WebMvcTest
class NPCControllerTest (@Autowired val mockMvc: MockMvc){

    @MockBean
    lateinit var chad: ChatGPTService
    @MockBean
    lateinit var scabardService: ScabardService
    @MockBean
    lateinit var converterService: ConverterService
    @Test
    fun helloKotlin() {

        `when`(chad.callChad(arrayOf("a fantasy npc"))).thenReturn(Pair("", ""))

        mockMvc.perform(get("/createNpc")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
    }
}