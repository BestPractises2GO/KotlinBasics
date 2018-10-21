package bp2go.kotlinbasics.view.home.repoview


import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import bp2go.kotlinbasics.R
import bp2go.kotlinbasics.model.User
import bp2go.kotlinbasics.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_repo_view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class RepoViewFragment : BaseFragment<RepoViewViewModel>() {
    companion object UidKey{
        val UID_KEY ="uid"
        private val USER_LOGIN ="JakeWharton"
    }

    override fun getViewModel(): Class<RepoViewViewModel> {
        return RepoViewViewModel::class.java
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_repo_view, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getUser().observe(this,  Observer<User>{
            showResult(it)
         })
        viewModel.fetchUser(USER_LOGIN)

    }

    fun showResult(user: User?){
        Log.d("RETRO"," DATEN SIND DA ${user?.name}")
        text_repoview.text = user!!.name
    }

    override fun onResume() {
        super.onResume()
    }


}
