package bp2go.kotlinbasics

import bp2go.kotlinbasics.domain.Interactor
import bp2go.kotlinbasics.model.User
import bp2go.kotlinbasics.model.network.UserWebservice
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import java.util.*

class InteractorTest {
    private val apiService: UserWebservice = mock()
    private val sut = Interactor(apiService, Schedulers.trampoline(), Schedulers.trampoline())

    @Test
    fun testInteractor() {
        val response = User("1", "1", "pfad", "name", "no", "no", Date())
        whenever(apiService.getUserRx(anyString())).thenReturn(Single.just(response))

        sut.getUser(anyString())

        val testObservable = apiService.getUserRx(anyString())
        val testObserver = testObservable.test()
        testObserver.assertValue(response)
    }

    @Test
    fun testInteractor_error() {
        val response = Throwable("error msg")
        whenever(apiService.getUserRx(anyString())).thenReturn(Single.error(response))

        sut.getUser(anyString())
        val testObservable = apiService.getUserRx(anyString())
        val testObserver = testObservable.test()
        testObserver.assertError(response)
    }
}