package bp2go.kotlinbasics.injection

import bp2go.kotlinbasics.App
import bp2go.kotlinbasics.injection.module.AppModule
import bp2go.kotlinbasics.injection.module.NetworkModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AndroidInjectionModule::class,
        AppModule::class,
        ActivityBuilder::class,
        NetworkModule::class
))
interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>() {}
}