package bp2go.kotlinbasics.view.user


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import bp2go.kotlinbasics.R
import bp2go.kotlinbasics.view.base.BaseFragment


class showUserFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_user, container, false)
    }


}
