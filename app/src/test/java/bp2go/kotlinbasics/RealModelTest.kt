package bp2go.kotlinbasics

import android.app.Application
import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import bp2go.kotlinbasics.model.User
import bp2go.kotlinbasics.model.UserRepository
import bp2go.kotlinbasics.model.local.UserDao
import bp2go.kotlinbasics.model.network.UserWebservice
import bp2go.kotlinbasics.view.home.user.UserProfileViewModel
import com.nhaarman.mockitokotlin2.*
import org.junit.Before
import org.junit.Test
import java.util.*
import kotlin.test.assertEquals
import org.junit.Rule
import java.util.concurrent.Executor
import org.mockito.Mockito.`when`
import org.junit.runner.RunWith
import org.mockito.*


class RealModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    @Mock
    lateinit var userRepository: UserRepository
    @Mock
    lateinit var userDao: UserDao
    @Mock
    lateinit var application: Application
    @Mock
    lateinit var webService: UserWebservice
    @Mock
    lateinit var executor: Executor
    @InjectMocks
    lateinit var userProfilViewModel: UserProfileViewModel
    @InjectMocks
    lateinit var  testUserRepository: UserRepository

    val observerState = mock<Observer<String>>()

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testViewModel(){
        val userLiveMock = MutableLiveData<User>()
        val user = User("1","Fred","null","K","null","null", Date())
        userLiveMock.postValue(user)
        whenever(userRepository.getUser("1")).thenReturn(userLiveMock)

        userProfilViewModel.init("1")

        assertEquals("Fred",userProfilViewModel.getUser()?.value?.login)

        verify(userRepository).getUser("1")
    }

    fun getString(t1: String) = "hi"

    @Test
    fun test_liveData_observe_and_value(){
        userProfilViewModel.toTestLiveData.observeForever(observerState)

        val argumentCaptor = ArgumentCaptor.forClass(String::class.java)
        argumentCaptor.run {
            verify(observerState).onChanged(capture())
            val done = value
            assertEquals("hallo", done)
        }
        assertEquals("hallo", userProfilViewModel.toTestLiveData.value)
    }

    @Test
    fun testRepo(){
        val userLiveMock = MutableLiveData<User>()
        val user = User("1","Fred","null","K","null","null", Date())
        userLiveMock.postValue(user)
        val spyUser = spy(testUserRepository) //Spy wenn ich innerhalb einer Methode einer andere MEthode aus der selbe CUT mit verify() prüfen möchte

        whenever(userDao.load("1")).thenReturn(userLiveMock)
     //   spyUser.getUser("1")

        assertEquals("Fred", spyUser.getUser("1").value?.login)
        verify(spyUser, times(1)).refreshUser("1")
        verify(userDao, times(1)).load("1")
    }

}