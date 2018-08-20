package bp2go.kotlinbasics.view.home.userlist


import android.arch.lifecycle.Observer
import android.arch.paging.PagedList
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.LinearLayout
import android.widget.Toast

import bp2go.kotlinbasics.R
import bp2go.kotlinbasics.model.NetworkState
import bp2go.kotlinbasics.model.User2
import bp2go.kotlinbasics.model.datasource.UsersDataSource
import bp2go.kotlinbasics.utils.toast
import bp2go.kotlinbasics.view.adapter.ListUserAdapter
import bp2go.kotlinbasics.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_user_list.view.*
import javax.inject.Inject


class UserListFragment : BaseFragment<UserListViewModel>() {

    override fun getViewModel(): Class<UserListViewModel> {
        return UserListViewModel::class.java
    }

    val users = ArrayList<String>()
    private lateinit var userAdapter: ListUserAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_user_list, container, false)
        addUsers()
        initAdapter(view)

        return view
    }

    private fun initAdapter(view: View){
        /*
        val listUserAdapter = ListUserAdapter {
            toast(it, view.context)
        }*/
        userAdapter = ListUserAdapter { viewModel.retry()}

       // view.recy_userList.layoutManager = GridLayoutManager(view.context, 2)
        view.recy_userList.layoutManager = LinearLayoutManager(view.context, LinearLayout.VERTICAL, false)
        view.recy_userList.adapter = userAdapter
        //userAdapter.addUsers(users)
        viewModel.userList.observe(this, Observer<PagedList<User2>> { userAdapter.submitList(it) })
        viewModel.getNetworkState().observe(this, Observer<NetworkState> { userAdapter.setNetworkState(it)})

    }



    fun addUsers(){
        users.add("Pit")
        users.add("Olaf")
        users.add("Jürgen")
        users.add("Steve")
        users.add("Alfred")
    }


}
