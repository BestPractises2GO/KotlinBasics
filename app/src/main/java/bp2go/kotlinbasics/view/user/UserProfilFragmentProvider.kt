package bp2go.kotlinbasics.view.user

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UserProfilFragmentProvider {


    @ContributesAndroidInjector
    abstract fun bindShowUserFragment(): showUserFragment
}