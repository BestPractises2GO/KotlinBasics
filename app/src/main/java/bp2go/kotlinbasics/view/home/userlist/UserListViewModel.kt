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
import java.util.concurrent.Executors
import javax.inject.Inject

class UserListViewModel @Inject constructor(app: Application, private val githubService: GithubService) : BaseViewModel(app) {

    var userList: LiveData<PagedList<User2>>
    private val pageSize = 15

    private val sourceFactory: UsersDataSourceFactory

    init {
       //Wenn man mit Executor Backgroudn Threads arbeiten möchte:  val executor = Executors.newFixedThreadPool(5)
        sourceFactory = UsersDataSourceFactory(mCompositeDisposable, githubService)
        val config = PagedList.Config.Builder()
                .setPageSize(pageSize) // The number of items to load in the PagedList.
                .setInitialLoadSizeHint(pageSize*2) //The number of items to load initially.
                .setEnablePlaceholders(false) //Placeholder (Image) when Images are not fully loaded
                .build()
        userList = LivePagedListBuilder<Long, User2>(sourceFactory,config).build()  //.setFetchExecutor(executor) wenn man executor benutzen möchte
    }


    fun retry(){
        sourceFactory.userDataSourceLiveData.value!!.retry()
    }
    fun refresh(){
        sourceFactory.userDataSourceLiveData.value!!.invalidate()
    }

    //Everytime sourceFactory.userDataSourceLiveData changes, UserDataSource.networkState will be called and returns a LiveData.
    //Everytime UserDataSource.networkState changes, the value of getNetworkState() will change too.
    //Value of getNetworkState() depend on changes of sourceFactory.userDataSourceLiveData and changes of UserDataSource.NetworkSTate
    //Bei loadInitial(), loadAfter() werden networkState und InitialState immer gesetzt
    fun getNetworkState(): LiveData<NetworkState> = Transformations.switchMap<UsersDataSource, NetworkState>(
            sourceFactory.userDataSourceLiveData, {it.networkState})
    //initialLoad ist wichtig, wenn z.B. das erste Laden der Daten nicht geklappt hat (z.B. Verbindung verloren gegangen)
    fun getRefreshState(): LiveData<NetworkState> = Transformations.switchMap<UsersDataSource, NetworkState>(
            sourceFactory.userDataSourceLiveData, {it.initialLoad}
    )

}