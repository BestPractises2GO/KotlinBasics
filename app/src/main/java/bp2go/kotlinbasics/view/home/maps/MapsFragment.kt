package bp2go.kotlinbasics.view.home.maps

import android.app.Activity
import bp2go.kotlinbasics.view.home.listener.ExampleListener
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import bp2go.kotlinbasics.R
import bp2go.kotlinbasics.view.home.MainActivity
import kotlinx.android.synthetic.main.fragment_maps.*


class MapsFragment : Fragment() {
private lateinit var mCallback: ExampleListener

    companion object {
        fun newInstance() = MapsFragment()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonMaps.setOnClickListener { mCallback.onButtonClick("Klappt") }
    }


    override fun onAttach(context: Context) {
        this.mCallback = activity as ExampleListener
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }



}
