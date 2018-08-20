package bp2go.kotlinbasics.model.datasource

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.ItemKeyedDataSource
import android.util.Log
import bp2go.kotlinbasics.model.NetworkState
import bp2go.kotlinbasics.model.User
import bp2go.kotlinbasics.model.User2
import bp2go.kotlinbasics.model.network.GithubService
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UsersDataSource(private val githubService: GithubService, private val compositeDisposable: CompositeDisposable): ItemKeyedDataSource<Long, User2>() {
    val networkState = MutableLiveData<NetworkState>()
    val initialLoad = MutableLiveData<NetworkState>()

    /**
     * Keep Completable reference for the retry event
     */
    private var retryCompletable: Completable? = null

    fun retry(){
        if(retryCompletable != null){
            compositeDisposable.add(retryCompletable!!
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({}, {throwable -> Log.d("HApp",throwable.message)}))
        }
    }




    /*
    You maybe wonder why i don’t use one of Rx most powerful features which specify the threads i want to work in or observe on.
    after some analysis i realize that the load methods called on background thread provided by Paging.
    The problem is you get the data on thread and load methods called on another thread.
     */
    override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<User2>) {
        networkState.postValue(NetworkState.LOADING)
        initialLoad.postValue(NetworkState.LOADING)

        compositeDisposable.add(githubService.getUsers(1, params.requestedLoadSize)
                .subscribe(
                        {users ->
                            setRetry(null)
                            networkState.postValue(NetworkState.LOADED)
                            initialLoad.postValue(NetworkState.LOADED)
                            callback.onResult(users)},
                        {throwable ->
                            //keep a Completable for future retry
                            setRetry(Action {loadInitial(params, callback)})
                            val error = NetworkState.error(throwable.message)
                            //publish the error
                            networkState.postValue(error)
                            initialLoad.postValue(error)
                            Log.i("apierr", throwable.message) }
                ))
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<User2>) {
        compositeDisposable.add(githubService.getUsers(params.key, params.requestedLoadSize)
                .subscribe (
                        {users -> callback.onResult(users)},
                        {throwable ->  Log.i("apierr",throwable.message)}
                ))
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<User2>) {
        //wird nicht gebraucht für den UseCase
    }

    override fun getKey(item: User2): Long {
        return item.id
    }

    private fun setRetry(action: Action?){
        if (action == null) {
            this.retryCompletable = null
        } else {
            this.retryCompletable = Completable.fromAction(action)
        }
    }




}