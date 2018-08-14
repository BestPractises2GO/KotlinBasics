package bp2go.kotlinbasics.view.base

import android.app.Activity
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Build

import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import javax.inject.Inject
import android.icu.lang.UCharacter.GraphemeClusterBreak.V


//V: BaseViewModel = Wird mit vererbung benötigt, damit man die ViewModelProvider.Factory Methoden auf "V" mit dem Supertyp ViewModel()
//anwenden kann
 abstract class BaseFragment<V: BaseViewModel> : DaggerFragment() {
    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    protected lateinit var viewModel: V
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
        viewModel = ViewModelProviders.of(this, mViewModelFactory).get(getViewModel())
    }


    /**
     * Override for set view model
     *
     * @return view model instance
     */
    abstract fun getViewModel(): Class<V>

    /*
         fun getViewModel(): Class<out Int>{
        return V::class.java
    }
     */

    /*
    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return childFragmentInjector;
    }*/

}