package bp2go.kotlinbasics.view


import android.os.Bundle
import bp2go.kotlinbasics.R
import bp2go.kotlinbasics.utils.addFragment
import bp2go.kotlinbasics.view.base.BaseActivity
import bp2go.kotlinbasics.view.user.ShowUserFragment
import bp2go.kotlinbasics.view.user.TabsHomeFragment


class MainActivity : BaseActivity() {

    private val USER_LOGIN ="JakeWharton"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addFragment(TabsHomeFragment(), R.id.frame1)
        setContentView(R.layout.activity_main)


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



}




