package bp2go.kotlinbasics.injection

import bp2go.kotlinbasics.view.home.MainActivity
import bp2go.kotlinbasics.view.home.HomeFragmentsProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {


@ContributesAndroidInjector(modules = arrayOf(HomeFragmentsProvider::class))
abstract fun bindMainActivity(): MainActivity

}