package bp2go.kotlinbasics.view

import android.app.FragmentManager
import android.app.FragmentTransaction
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import bp2go.kotlinbasics.R
import bp2go.kotlinbasics.utils.addFragment
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

import bp2go.kotlinbasics.utils.edit3
import bp2go.kotlinbasics.utils.inTransaction3


class MainActivity : AppCompatActivity() {




    var ob = Observable.fromArray(1,2,3,4,5)
    var Ob2 = Observable.just("Hello World Ob")

    var i = 0



    private fun inc1(){
        var z1 = zahl1.text.toString().toInt()
        z1++
        zahl1.text = z1.toString()
    }

    private fun inc2(){
        var z2 = zahl2.text.toString().toInt()
        z2++
        zahl2.text = z2.toString()
    }

    private fun observable(){
        val emitter = PublishSubject.create<View>()
   
        searchButton.setOnClickListener({ v -> emitter.onNext(v)  })

        emitter
                .map { inc1() }
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribe({inc2()})
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        observable()
        addFragment(PartaFragment(), R.id.frame1)





/*
        ob.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { t: Int? -> println(t) }
*/


    }



}




