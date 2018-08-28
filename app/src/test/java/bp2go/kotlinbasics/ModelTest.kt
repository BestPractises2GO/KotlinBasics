package bp2go.kotlinbasics

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals

class ModelTest {
    @Mock
    lateinit var user3: User3
    lateinit var tested: Greeter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)

        tested = Greeter(user3)
    }

    @Test
    fun englishGreetIsCorrect(){
        whenever(user3.fullname()).thenReturn("Fred Biere") //sobbald user3.fullname() jemals aufgerufen wird, gib "Fred Biere" zurück
        assertEquals("Hello Fred Bier", tested.getEnglishGreeting())
        verify(user3).fullname() //Überprüft, ob Methode fullname() von dem MockObjekt User3 tatsächlich aufgerufen wird, wenn tested.getEnglishGreeting() aufgerufen wird
    }

    @Test
    fun isUserComplete(){
        val user3 = User3("Fred", "Bier")
       // val user3 = mock<User3>()
        // whenever(user3.fullname()).thenReturn("Fred", "Bier")
        // verify(user3).fullname()

        assertEquals("Fred", user3.firstName)
        assertEquals("Bier", user3.lastName)
        assertEquals("Fred Bier", user3.fullname())
/*
        assertEquals("Fred",user3.firstName)
        assertEquals("Bier",user3.lastName)
        assertEquals("Fred Bier2",user3.fullname(),"FullName nicht richtig")
        */
    }
}