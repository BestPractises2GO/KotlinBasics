package bp2go.kotlinbasics.view.home.userlist

import android.app.Application
import android.arch.core.util.Function
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import bp2go.kotlinbasics.model.NetworkState
import bp2go.kotlinbasics.model.User2
import bp2go.kotlinbasics.model.datasource.UsersDataSource
import bp2go.kotlinbasics.model.datasource.UsersDataSourceFactory
import bp2go.kotlinbasics.model.network.GithubService
import bp2go.kotlinbasics.view.base.BaseViewModel
import javax.inject.Inject

class UserListViewModel @Inject constructor(app: Application, private val githubService: GithubService) : BaseViewModel(app) {

    var userList: LiveData<PagedList<User2>>
    private val pageSize = 15

    private val sourceFactory: UsersDataSourceFactory

    init {
        sourceFactory = UsersDataSourceFactory(mCompositeDisposable, githubService)
        val config = PagedList.Config.Builder()
                .setPageSize(pageSize)
                .setInitialLoadSizeHint(pageSize*2)
                .setEnablePlaceholders(false)
                .build()
        userList = LivePagedListBuilder<Long, User2>(sourceFactory,config).build()
    }

    fun getNetworkState(): LiveData<NetworkState> = Transformations.switchMap<UsersDataSource, NetworkState>(
            sourceFactory.userDataSourceLiveData, {it.networkState})

    fun retry(){
        sourceFactory.userDataSourceLiveData.value!!.retry()
    }

}