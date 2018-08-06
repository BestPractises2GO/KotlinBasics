package bp2go.kotlinbasics.injection.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import bp2go.kotlinbasics.injection.ViewModelFactory
import bp2go.kotlinbasics.injection.ViewModelKey
import bp2go.kotlinbasics.view.user.UserProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(UserProfileViewModel::class)
    abstract fun bindsUserProfileViewModel(repoViewModel: UserProfileViewModel) : ViewModel


    //Factory binden
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory) : ViewModelProvider.Factory
}