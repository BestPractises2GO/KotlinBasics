package bp2go.kotlinbasics.view.home.user


import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import bp2go.kotlinbasics.R
import bp2go.kotlinbasics.injection.module.GlideApp
import bp2go.kotlinbasics.model.User
import bp2go.kotlinbasics.view.base.BaseFragment
import com.bumptech.glide.request.RequestOptions
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_show_user.*


class ShowUserFragment : BaseFragment<UserProfileViewModel>() {

    private fun dataStoreMethod(){
        Realm.getDefaultInstance().use{realmTransaction ->

        }
    }


    companion object UidKey{
     val UID_KEY ="uid"
        private val USER_LOGIN ="JakeWharton"
    }


    //Gib ViewModel Referenz zurück, damit sie im BaseFragment initialisiert wird
    override fun getViewModel(): Class<UserProfileViewModel> {
        return UserProfileViewModel::class.java
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val userLogin = arguments?.getString(UID_KEY) ?: USER_LOGIN
        viewModel.init(userLogin)
        viewModel.getUser()?.observe(this,  Observer<User>{ user -> updateUi(user) })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun updateUi(user: User?){
        name_user.text = user?.name
        company_user.text = user?.company
        blog_user.text = user?.blog
        GlideApp.with(this)
                .load(user?.avatar_url)
                .apply(RequestOptions().circleCrop())
                .into(avatar_user)
    }


}
