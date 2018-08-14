package bp2go.kotlinbasics.view.user


import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import bp2go.kotlinbasics.R
import bp2go.kotlinbasics.view.PartaFragment
import bp2go.kotlinbasics.view.adapter.TabAdapter
import bp2go.kotlinbasics.view.rxjava.RxJavaExamplesFragment
import kotlinx.android.synthetic.main.fragment_tabs_home.*
import kotlinx.android.synthetic.main.fragment_tabs_home.view.*


class TabsHomeFragment : Fragment() {


    private lateinit var tabAdapter: TabAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        //Layout muss in eine Variable gespeichert werden, und über diese Variable werden
        //ViewPager und TabLayout aufgerufen, ansonsten erscheint eine NPE
        val tabView =  inflater.inflate(R.layout.fragment_tabs_home, container, false)

        return tabView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //Initialisiere TabAdapter mit childFragmentManager, weil man Fragmente aus einem Fragment öffnet
        //über Activity wäre es dann der supportFragmentMAnager
        tabAdapter = TabAdapter(childFragmentManager)
        //ScreenPageLimit für höhere Performanz
        view_pager.offscreenPageLimit = 3
        addFragmentsToViewPager(view_pager)
        //Füge dem Tablayout den ViewPager zu
        home_tabLayout.setupWithViewPager(view_pager)


    }

    fun addFragmentsToViewPager(viewPager: ViewPager){
        tabAdapter.addFragment(ShowUserFragment(), "Room")
        tabAdapter.addFragment(PartaFragment(), "Parta")
        tabAdapter.addFragment(RxJavaExamplesFragment(), "Rx Beispiele")

        //ViewPager Adapter erhält TabAdapter mit den Fragmenten, die innerhalb des TabAdapter hinzugefügt worden sind
        viewPager.adapter = tabAdapter
    }


}
