package bp2go.kotlinbasics.view.home


import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.util.Log
import android.view.View
import bp2go.kotlinbasics.R
import bp2go.kotlinbasics.injection.module.GlideApp
import bp2go.kotlinbasics.utils.addFragment
import bp2go.kotlinbasics.utils.addFragmentTag
import bp2go.kotlinbasics.utils.toast
import bp2go.kotlinbasics.view.base.BaseActivity
import bp2go.kotlinbasics.view.home.listener.ExampleListener
import bp2go.kotlinbasics.view.home.user.ShowUserFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity(), ExampleListener {
    override fun onButtonClick(msg: String) {
        toast(msg+" "+this::class, this)
        //Hier könnte ich jetzt z.B. Daten zu einem anderen Fragment in die Methode senden
    }

    private val USER_LOGIN ="JakeWharton"

    fun onArticleSelected(position: Int){

    }

    //ToDo: Navigation Drawer implementieren

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Actionbar
        setSupportActionBar(toolbar)
        // Initialize the action bar drawer toggle instance
        val drawerToggle: ActionBarDrawerToggle = object: ActionBarDrawerToggle(
                this,
                drawer_layout,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close){
            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
            }

            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
            }
        }
        // Configure the drawer layout to add listener and show icon on toolbar
        drawerToggle.isDrawerIndicatorEnabled = true
        drawer_layout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
        // Set navigation view navigation item selected listener
        navigation_view.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.action_cut -> toast("cut clicked", this)
                R.id.action_copy -> toast("copy clicked", this)
                R.id.action_insert -> {
                    toast("insert clicked", this)
                    drawer_layout.setBackgroundColor(Color.RED)
                }
            }
            drawer_layout.closeDrawer(GravityCompat.START)
            true
        }

        //Füge das TabsHomeFragment einmal hinzu (addfragmenttag(), damit es sicher vor neu erstellung beim Rotationchange ist)
        addFragOnce(TabsHomeFragment(), "tabsHome")
        Log.i("tablayout","onCreate() Main")
        /*
        //String an Bundle hinzufügen und dann an Fragment hinzufügen
        //ToDo: mit Extension verkürzen, da wiederkehrende Situation
        val bundle:Bundle = Bundle()
        bundle.putString(ShowUserFragment.UID_KEY, USER_LOGIN)
        val showUserFragment = ShowUserFragment()
        showUserFragment.arguments = bundle
        */

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




