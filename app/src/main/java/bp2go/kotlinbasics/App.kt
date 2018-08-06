package bp2go.kotlinbasics

import android.annotation.SuppressLint
import android.content.Context
import bp2go.kotlinbasics.injection.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class App : DaggerApplication() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        @JvmStatic
        var context : Context? = null
    }



    override fun onCreate() {
        super.onCreate()

        context = applicationContext
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }
}