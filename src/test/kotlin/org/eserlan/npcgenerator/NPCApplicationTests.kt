package org.eserlan.npcgenerator

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit4.SpringRunner
//import kotlin.test.assertEquals

/**
 * Greeting test case.
 */
@RunWith(SpringRunner::class)
@SpringBootTest(classes = arrayOf(NPCApplication::class),
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NPCApplicationTests {

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @Test
    fun whenCalled_shouldReturnHello() {
        val result = testRestTemplate
            // ...
            .getForEntity("/hello", String::class.java)

        assertNotNull(result)
        assertEquals(result?.statusCode, HttpStatus.OK)
        assertEquals(result?.body, "hello World")
    }
}