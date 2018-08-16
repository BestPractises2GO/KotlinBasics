package bp2go.kotlinbasics.injection.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import bp2go.kotlinbasics.injection.ViewModelFactory
import bp2go.kotlinbasics.injection.ViewModelKey
import bp2go.kotlinbasics.view.home.rxjava.RxJavaExamplesViewModel
import bp2go.kotlinbasics.view.home.user.UserProfileViewModel
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

    //Factory binden
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory) : ViewModelProvider.Factory
}