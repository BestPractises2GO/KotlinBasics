package bp2go.kotlinbasics.view.base

import android.app.Activity
import android.content.Context
import android.os.Build

import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment

open class BaseFragment : DaggerFragment() {

    /*
    @Inject
    DispatchingAndroidInjector<Fragment> childFragmentInjector;
    */
    @SuppressWarnings("deprecation")
    @Override
    override fun onAttach(activity: Activity?) {
        // Perform injection here for versions before M as onAttach(*Context*) did not yet exist
        // This fixes DaggerFragment issue: https://github.com/google/dagger/issues/777
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            AndroidSupportInjection.inject(this);
        }
        super.onAttach(activity)
    }

    override fun onAttach(context: Context?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Perform injection here for M (API 23) due to deprecation of onAttach(*Activity*)
            AndroidSupportInjection.inject(this);
        }
        super.onAttach(context)
    }

    /*
    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return childFragmentInjector;
    }*/

}