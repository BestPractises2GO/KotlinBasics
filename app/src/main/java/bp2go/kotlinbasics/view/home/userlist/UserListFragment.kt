package bp2go.kotlinbasics.view.home.userlist


import android.arch.lifecycle.Observer
import android.arch.paging.PagedList
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.LinearLayout
import android.widget.Toast

import bp2go.kotlinbasics.R
import bp2go.kotlinbasics.model.NetworkState
import bp2go.kotlinbasics.model.Status
import bp2go.kotlinbasics.model.User2
import bp2go.kotlinbasics.model.datasource.UsersDataSource
import bp2go.kotlinbasics.utils.snack
import bp2go.kotlinbasics.utils.toast
import bp2go.kotlinbasics.view.adapter.ListUserAdapter
import bp2go.kotlinbasics.view.base.BaseFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_user_list.*
import kotlinx.android.synthetic.main.fragment_user_list.view.*
import kotlinx.android.synthetic.main.item_network_state.*
import kotlinx.android.synthetic.main.item_network_state.view.*
import javax.inject.Inject


class UserListFragment : BaseFragment<UserListViewModel>(),  ListUserAdapter.userListListener {
    val NETSTATUS = "networkState"
    var netStateStatus: String? = null

    //onClick-Listener für die jeweiligen Recycler Elemente
    override fun onClick(name: String) {
        activity?.let { toast(name, it, Toast.LENGTH_LONG) }
    //    activity?.coordinatorLayout_home?.let { Snackbar.make(it, name, Snackbar.LENGTH_LONG).setAction("Rückgängig") { activity?.let { it1 -> toast("Action clicked", it1) } }.show() }
     activity?.coordinatorLayout_home?.snack(name) {setAction("Rückgängig machen"){undoListener("entfernt...") {snackBarUndo()} }}
    }

    fun snackBarUndo(){
        //Logik für das Rückgäng machen
    }
    fun undoListener(message:String, f: () -> Unit){
        f()
        toast(message, activity!!)
    }

    override fun getViewModel(): Class<UserListViewModel> {
        return UserListViewModel::class.java
    }

    val users = ArrayList<String>()
    private lateinit var userAdapter: ListUserAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_user_list, container, false)
       // addUsers()


        if(savedInstanceState?.getString(NETSTATUS) != null) {
            Log.i("OkHttp","IST VISIBLE2???????????????" )
            Log.i("OkHttp", savedInstanceState.getString(NETSTATUS))
            if (savedInstanceState.getString(NETSTATUS) == Status.SUCCESS.toString()) {
                view.retryLoadingButton.visibility = View.GONE
                view.loadingProgressBar.visibility = View.GONE
            }
        }


        initAdapter(view)
        initSwipeRefresh(view)

        return view
    }

    private fun initAdapter(view: View){
        /*
        val listUserAdapter = ListUserAdapter {
            toast(it, view.context)
        }*/
        //Adapter mit Retry-Listener (zum "erneut laden")
        userAdapter = ListUserAdapter(this, { viewModel.retry()})

       // view.recy_userList.layoutManager = GridLayoutManager(view.context, 2)
        view.recy_userList.layoutManager = LinearLayoutManager(view.context, LinearLayout.VERTICAL, false)
        view.recy_userList.adapter = userAdapter
        //userAdapter.addUsers(users)

        //Beobachte UserList, bei Änderungen sende dies dem Adapter
        viewModel.userList.observe(this, Observer<PagedList<User2>> {userAdapter.submitList(it)})
        //Beobachte viewModel.networkState, bei Änderungen ändere den networkstate im Adapter
        viewModel.getNetworkState().observe(this, Observer<NetworkState> { userAdapter.setNetworkState(it)})

    }


    /**
     * Init swipe to refresh and enable pull to refresh only when there are items in the adapter
     */
    private fun initSwipeRefresh(view: View){
        viewModel.getRefreshState().observe(this, Observer { networkState ->
            netStateStatus = networkState?.status.toString()

            if(userAdapter.currentList != null){
                if(userAdapter.currentList!!.size > 0){
                    view.swipeRefreshLayout_userList.isRefreshing = networkState?.status == NetworkState.LOADING.status
                }else{
                    setInitialLoadingState(networkState, view)
                }
            }else{
                setInitialLoadingState(networkState, view)
            }
        })
        view.swipeRefreshLayout_userList.setOnRefreshListener { viewModel.refresh() }
    }

    /**
     * Show the current network state for the first load when the user list
     * in the adapter is empty and disable swipe to scroll at the first loading
     *
     * @param networkState the new network state
     */
    private fun setInitialLoadingState(networkState: NetworkState?, view: View){
        //error message
       if(networkState?.message != null){
           view.errorMessageTextView.visibility =  View.VISIBLE
           view.errorMessageTextView.text = networkState.message
       } else{
           view.errorMessageTextView.visibility = View.GONE
       }

        Log.i("OkHttp","IST VISIBLE???????????????" )
        Log.i("OkHttp",networkState?.status.toString() )
        //loading and retry + retry Listener mit retry Klick, wenn man initialload z.B. die Verbindung offline ist
        view.retryLoadingButton.visibility = if(networkState?.status == Status.FAILED) View.VISIBLE else View.GONE
        view.loadingProgressBar.visibility = if(networkState?.status == Status.RUNNING) View.VISIBLE else View.GONE
       

        view.swipeRefreshLayout_userList.isEnabled = networkState?.status == Status.SUCCESS //zeigt Refresh nur an, wenn GELADEN wurde
        view.retryLoadingButton.setOnClickListener {viewModel.retry()}
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putString(NETSTATUS, netStateStatus)
        }
        // call superclass to save any view hierarchy
        super.onSaveInstanceState(outState)
    }


    fun addUsers(){
        users.add("Pit")
        users.add("Olaf")
        users.add("Jürgen")
        users.add("Steve")
        users.add("Alfred")
    }




}
