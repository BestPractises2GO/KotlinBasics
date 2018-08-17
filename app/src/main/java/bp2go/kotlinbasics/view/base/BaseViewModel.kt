package bp2go.kotlinbasics.view.base

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel(application: Application, val mCompositeDisposable: CompositeDisposable = CompositeDisposable()) : AndroidViewModel(application) {

    fun getCompositeDisposable() = mCompositeDisposable

    override fun onCleared() {
        super.onCleared()
        mCompositeDisposable.clear()
        super.onCleared()
    }
}