package bp2go.kotlinbasics.view.user

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import bp2go.kotlinbasics.model.User
import bp2go.kotlinbasics.model.UserRepository
import javax.inject.Inject

class UserProfileViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    private var user: LiveData<User>? = null

    fun init(userId: String)  = if(this.user != null) println("user bereits init."); else user = userRepository.getUser(userId)

    fun getUser()  = user

}