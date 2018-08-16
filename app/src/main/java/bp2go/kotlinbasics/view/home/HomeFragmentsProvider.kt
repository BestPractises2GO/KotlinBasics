package bp2go.kotlinbasics.view.home

import bp2go.kotlinbasics.view.home.rxjava.RxJavaExamplesFragment
import bp2go.kotlinbasics.view.home.user.ShowUserFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeFragmentsProvider {

    @ContributesAndroidInjector
    abstract fun bindShowUserFragment(): ShowUserFragment

    @ContributesAndroidInjector
    abstract fun bindRxJavaExamplesFragment(): RxJavaExamplesFragment
}