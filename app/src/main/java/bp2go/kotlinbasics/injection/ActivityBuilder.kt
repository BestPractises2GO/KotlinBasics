package bp2go.kotlinbasics.injection

import bp2go.kotlinbasics.view.MainActivity
import bp2go.kotlinbasics.view.user.UserProfilFragmentProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {


@ContributesAndroidInjector(modules = arrayOf(UserProfilFragmentProvider::class))
abstract fun bindMainActivity(): MainActivity

}