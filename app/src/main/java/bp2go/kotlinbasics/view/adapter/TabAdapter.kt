package bp2go.kotlinbasics.view.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.ViewGroup

class TabAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val mFragmentList: ArrayList<Fragment>
    private val mFragmentTitleList: ArrayList<String>

            init{
                mFragmentTitleList = ArrayList()
                mFragmentList = ArrayList()
            }

    override fun getItem(position: Int): Fragment = mFragmentList.get(position)

    override fun getCount(): Int = mFragmentList.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val ret = super.instantiateItem(container, position)
        mFragmentList[position] = ret as Fragment
        return ret
    }

    fun addFragment(fragment: Fragment, title: String){
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence? = mFragmentTitleList.get(position)
}