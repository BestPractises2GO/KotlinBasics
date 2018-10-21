package bp2go.kotlinbasics.view.home

import bp2go.kotlinbasics.view.home.repoview.RepoViewFragment
import bp2go.kotlinbasics.view.home.rxjava.RxJavaExamplesFragment
import bp2go.kotlinbasics.view.home.user.ShowUserFragment
import bp2go.kotlinbasics.view.home.userlist.UserListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeFragmentsProvider {


    @ContributesAndroidInjector
    abstract fun bindShowUserFragment(): ShowUserFragment

    @ContributesAndroidInjector
    abstract fun bindRxJavaExamplesFragment(): RxJavaExamplesFragment

    @ContributesAndroidInjector
    abstract fun bindUserListFragment(): UserListFragment

    @ContributesAndroidInjector
    abstract fun bindRepoViewFragment(): RepoViewFragment
}