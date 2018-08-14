package bp2go.kotlinbasics.view.home.user

import bp2go.kotlinbasics.view.home.user.ShowUserFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UserProfilFragmentProvider {


    @ContributesAndroidInjector
    abstract fun bindShowUserFragment(): ShowUserFragment
}