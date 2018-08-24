package bp2go.kotlinbasics

import org.junit.Assert
import org.junit.Test
import kotlin.test.assertEquals

class ModelTest {
    @Test
    fun isUserComplete(){
        val user3 = User3("Fred", "Bier")

        assertEquals("Fred",user3.firstName)
        assertEquals("Bier",user3.lastName)
        assertEquals("Fred Bier2",user3.fullname(),"FullName nicht richtig")
    }
}