package bp2go.kotlinbasics.view.home.repoview

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import bp2go.kotlinbasics.model.User
import bp2go.kotlinbasics.view.base.BaseViewModel
import javax.inject.Inject


class RepoViewViewModel @Inject constructor(app: Application, val userRepository2: UserRepository2) : BaseViewModel(app) {
    /**
     * A switchMap uses a MediatorLiveData internally,
     * so it’s important to be familiar with it because you need to use it when you want to combine multiple sources of LiveData:
     * https://medium.com/androiddevelopers/livedata-beyond-the-viewmodel-reactive-patterns-using-transformations-and-mediatorlivedata-fda520ba00b7
     */

    //works with Repo
    private val organizationIdLiveData = MutableLiveData<String>()

    //works with Repo
    private val user = Transformations.switchMap(organizationIdLiveData) { userid -> userRepository2.getUser(userid) }

    private var user2: LiveData<User> = MutableLiveData()

    //works with Repo
    fun getUser() : LiveData<User> {
        return user
    }

    fun fetchUser2(userId: String) {
        user2 = Transformations.map(userRepository2.getUser("userId")) { userres -> userres}

    }

    //works with Repo
    fun fetchUser(userId: String) {
        if (organizationIdLiveData.value == null) {
            organizationIdLiveData.value = userId
        }
    }
}