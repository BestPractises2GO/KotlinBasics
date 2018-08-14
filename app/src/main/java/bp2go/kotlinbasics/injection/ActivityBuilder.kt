package bp2go.kotlinbasics.injection

import bp2go.kotlinbasics.view.home.MainActivity
import bp2go.kotlinbasics.view.home.user.UserProfilFragmentProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {


@ContributesAndroidInjector(modules = arrayOf(UserProfilFragmentProvider::class))
abstract fun bindMainActivity(): MainActivity

}