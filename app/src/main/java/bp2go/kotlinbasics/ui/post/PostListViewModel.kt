package bp2go.kotlinbasics.ui.post

import bp2go.kotlinbasics.view.base.BaseViewModel
import bp2go.kotlinbasics.model.network.PostApi
import javax.inject.Inject

class  PostListViewModel: BaseViewModel(){
    @Inject
    lateinit var postApi: PostApi

}