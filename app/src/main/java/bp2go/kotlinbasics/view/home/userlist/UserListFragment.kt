package bp2go.kotlinbasics.view.home.userlist


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.Toast

import bp2go.kotlinbasics.R
import bp2go.kotlinbasics.utils.toast
import bp2go.kotlinbasics.view.adapter.ListUserAdapter
import kotlinx.android.synthetic.main.fragment_user_list.view.*
import javax.inject.Inject


class UserListFragment : Fragment() {
    val users = ArrayList<String>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_user_list, container, false)
        addUsers()

        val listUserAdapter = ListUserAdapter {
            toast(it, view.context)
        }
        view.recy_userList.layoutManager = GridLayoutManager(view.context, 2)
        view.recy_userList.adapter = listUserAdapter
        listUserAdapter.addUsers(users)

        return view
    }



    fun addUsers(){
        users.add("Pit")
        users.add("Olaf")
        users.add("Jürgen")
        users.add("Steve")
        users.add("Alfred")
    }


}
