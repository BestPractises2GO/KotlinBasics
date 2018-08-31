package bp2go.kotlinbasics

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.fail
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals

class ModelTest {
    @Mock
    lateinit var bookMock: BookService
    @Mock
    lateinit var user3: User3
    lateinit var lendObj: LendBookManager
    lateinit var tested: Greeter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)

        lendObj = LendBookManager(bookMock)

        tested = Greeter(user3)
    }

    @Test
    fun englishGreetIsCorrect(){
        whenever(user3.fullname()).thenReturn("Fred Bier") //sobbald user3.fullname() jemals aufgerufen wird, gib "Fred Biere" zurück
        assertEquals("hello Fred Bier", tested.getEnglishGreeting())
        verify(user3).fullname() //Überprüft, ob Methode fullname() von dem MockObjekt User3 tatsächlich aufgerufen wird, wenn tested.getEnglishGreeting() aufgerufen wird
    }


    @Test
    fun isUserComplete(){
        val user3 = User3("Fred", "Bier")
        // whenever(user3.fullname()).thenReturn("Fred", "Bier")
        // verify(user3).fullname()
        assertEquals("Fred", user3.firstName)
        assertEquals("Bier", user3.lastName)
        assertEquals("Fred Bier", user3.fullname())
    }

    @Test
    fun interfaceCallIsRight(){
        whenever(bookMock.inStock(100)).thenReturn(true)
        lendObj.checkout(100,5)
        verify(bookMock).lend(100,5)
    }

    @Test
    fun interfaceCallIsFalse(){
        whenever(bookMock.inStock(50)).thenReturn(false)
        try{
            lendObj.checkout(50,5)
            fail("Fehler, es sollte eigentlich eine Exception geworfen werden"); // if we got here, no exception was thrown, which is bad
        }catch (e: IllegalStateException){
           assertEquals("Book is not available", e.message, "Exception must be correct: "+e.message)
        }
        /*
        lendObj.checkout(50,5)
        verify(bookMock).lend(100,5)*/
    }

}