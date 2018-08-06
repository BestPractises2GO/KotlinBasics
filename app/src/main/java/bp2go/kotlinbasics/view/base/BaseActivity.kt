package bp2go.kotlinbasics.view.base

import android.os.Bundle
import android.os.PersistableBundle
import dagger.android.AndroidInjection
import dagger.android.DaggerActivity
import dagger.android.support.DaggerAppCompatActivity

open class BaseActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState, persistentState)
    }
}