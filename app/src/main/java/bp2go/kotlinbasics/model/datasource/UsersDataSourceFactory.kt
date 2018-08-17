package bp2go.kotlinbasics.model.datasource

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import bp2go.kotlinbasics.model.User2
import bp2go.kotlinbasics.model.network.GithubService
import io.reactivex.disposables.CompositeDisposable

class UsersDataSourceFactory(private val compositeDisposable: CompositeDisposable, private val githubService: GithubService) : DataSource.Factory<Long, User2>() {

    val userDataSourceLiveData = MutableLiveData<UsersDataSource>()

    override fun create(): DataSource<Long, User2> {
        val source = UsersDataSource(githubService, compositeDisposable)
        userDataSourceLiveData.postValue(source)
        return source
    }
}