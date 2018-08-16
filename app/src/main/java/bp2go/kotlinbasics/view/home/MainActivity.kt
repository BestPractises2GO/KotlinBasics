package bp2go.kotlinbasics.view.home


import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import bp2go.kotlinbasics.R
import bp2go.kotlinbasics.utils.addFragment
import bp2go.kotlinbasics.utils.addFragmentTag
import bp2go.kotlinbasics.view.base.BaseActivity
import bp2go.kotlinbasics.view.home.user.ShowUserFragment


class MainActivity : BaseActivity() {

    private val USER_LOGIN ="JakeWharton"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addFragOnce(TabsHomeFragment(), "tabsHome")

        Log.i("tablayout","onCreate() Main")
        //String an Bundle hinzufügen und dann an Fragment hinzufügen
        //ToDo: mit Extension verkürzen, da wiederkehrende Situation
        val bundle:Bundle = Bundle()
        bundle.putString(ShowUserFragment.UID_KEY, USER_LOGIN)
        val showUserFragment = ShowUserFragment()
        showUserFragment.arguments = bundle

        /*
        GlideApp.with(this)
                .load()
                .into()
                */
    }

    fun addFragOnce(fragment: Fragment, tag: String){
       // supportFragmentManager.executePendingTransactions()
        if(supportFragmentManager.findFragmentByTag(tag) == null){
            addFragmentTag(fragment, R.id.frame1, tag)
        }
    }



}




