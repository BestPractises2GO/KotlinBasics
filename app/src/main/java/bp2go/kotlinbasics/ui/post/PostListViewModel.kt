package bp2go.kotlinbasics.ui.post

import android.app.Application
import bp2go.kotlinbasics.view.base.BaseViewModel
import bp2go.kotlinbasics.model.network.PostApi
import javax.inject.Inject

class  PostListViewModel(application: Application): BaseViewModel(application){
    @Inject
    lateinit var postApi: PostApi

}