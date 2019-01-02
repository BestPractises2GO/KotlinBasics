package bp2go.kotlinbasics.view.home


import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import bp2go.kotlinbasics.R
import bp2go.kotlinbasics.view.home.parta.PartaFragment
import bp2go.kotlinbasics.view.adapter.TabAdapter
import bp2go.kotlinbasics.view.home.maps.MapsFragment
import bp2go.kotlinbasics.view.home.repoview.RepoViewFragment
import bp2go.kotlinbasics.view.home.rxjava.RxJavaExamplesFragment
import bp2go.kotlinbasics.view.home.user.ShowUserFragment
import bp2go.kotlinbasics.view.home.userlist.UserListFragment
import kotlinx.android.synthetic.main.fragment_tabs_home.*
import kotlinx.android.synthetic.main.fragment_tabs_home.view.*


class TabsHomeFragment : Fragment() {

    private lateinit var tabAdapter: TabAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        //Layout muss in eine Variable gespeichert werden, und über diese Variable werden
        //ViewPager und TabLayout aufgerufen, ansonsten erscheint eine NPE
        val view =  inflater.inflate(R.layout.fragment_tabs_home, container, false)
        Log.i("tablayout","onCreateView()")

        //Initialisiere TabAdapter mit childFragmentManager, weil man Fragmente aus einem Fragment öffnet
        //über Activity wäre es dann der supportFragmentMAnager
        tabAdapter = TabAdapter(childFragmentManager)
        //ScreenPageLimit für höhere Performanz
        view.view_pager.offscreenPageLimit = 6
        addFragmentsToViewPager(view.view_pager)
        //Füge dem Tablayout den ViewPager zu
        view.home_tabLayout.setupWithViewPager(view.view_pager)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.i("tablayout","onActivityCreated()")

        //Bei Orientation Change wird überprüft ob savedInstanceSTate Daten enthält
        if(savedInstanceState != null){
            with(savedInstanceState){
                mPosition = getInt(SELECTED_TAB)
            }
            Log.i("tablayout", mPosition.toString())
        }
    }



    fun addFragmentsToViewPager(viewPager: ViewPager){
        tabAdapter.addFragment(ShowUserFragment(), "Room")
        tabAdapter.addFragment(PartaFragment(), "Parta")
        tabAdapter.addFragment(RxJavaExamplesFragment(), "Rx Beispiele")
        tabAdapter.addFragment(UserListFragment(), "Recycler")
        tabAdapter.addFragment(MapsFragment(), "Maps")
        tabAdapter.addFragment(RepoViewFragment(), "Repo")

        //ViewPager Adapter erhält TabAdapter mit den Fragmenten, die innerhalb des TabAdapter hinzugefügt worden sind
        viewPager.adapter = tabAdapter
    }



        private val SELECTED_TAB ="TabPosition"
        private var mPosition:Int? = 0



    override fun onSaveInstanceState(outState: Bundle) {
        mPosition?.let { outState.putInt(SELECTED_TAB, it) }
        super.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        super.onDestroy()
    }


}
