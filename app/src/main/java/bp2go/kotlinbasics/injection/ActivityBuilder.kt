package bp2go.kotlinbasics.injection

import bp2go.kotlinbasics.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {


@ContributesAndroidInjector
abstract fun bindMainActivity(): MainActivity

}