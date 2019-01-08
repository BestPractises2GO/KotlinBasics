package bp2go.kotlinbasics.view.home.user

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import bp2go.kotlinbasics.model.User
import bp2go.kotlinbasics.model.UserRepository
import bp2go.kotlinbasics.view.base.BaseViewModel
import javax.inject.Inject

class UserProfileViewModel @Inject constructor(application: Application, private val userRepository: UserRepository) : BaseViewModel(application) {

    private var user: LiveData<User>? = null

    val toTestLiveData: MutableLiveData<String> = MutableLiveData()

    init {
        toTestLiveData.value = "hallo"
    }

    fun init(userId: String?)  = if(this.user != null) println("user bereits init."); else user = userRepository.getUser(userId)

    fun getUser()  = user

}