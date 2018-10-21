package bp2go.kotlinbasics.injection.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import bp2go.kotlinbasics.injection.ViewModelFactory
import bp2go.kotlinbasics.injection.ViewModelKey
import bp2go.kotlinbasics.view.home.repoview.RepoViewViewModel
import bp2go.kotlinbasics.view.home.rxjava.RxJavaExamplesViewModel
import bp2go.kotlinbasics.view.home.user.UserProfileViewModel
import bp2go.kotlinbasics.view.home.userlist.UserListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(UserProfileViewModel::class)
    abstract fun bindsUserProfileViewModel(repoViewModel: UserProfileViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RxJavaExamplesViewModel::class)
    abstract fun bindsRxJavaExamplesViewModel(rxJavaViewModel: RxJavaExamplesViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UserListViewModel::class)
    abstract fun provideUserListViewModel(userListViewModel: UserListViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RepoViewViewModel::class)
    abstract fun provideRepoViewViewModel(repoViewModel: RepoViewViewModel) : ViewModel

    //Factory binden
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory) : ViewModelProvider.Factory
}