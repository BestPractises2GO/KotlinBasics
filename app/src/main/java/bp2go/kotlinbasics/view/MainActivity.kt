package bp2go.kotlinbasics.view

import android.app.FragmentManager
import android.app.FragmentTransaction
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import bp2go.kotlinbasics.R
import bp2go.kotlinbasics.injection.module.GlideApp
import bp2go.kotlinbasics.utils.addFragment
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

import bp2go.kotlinbasics.view.base.BaseActivity
import bp2go.kotlinbasics.view.user.ShowUserFragment


class MainActivity : BaseActivity() {




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

    //Beispiel Lambda Funktionen
    // (Int) = Eingabewert für die Funktion als Intwert
    // -> Int = Rückgabewert eines Int für die Funktion func(i), wobei i ein int sein muss
    fun t1(i: Int, func: (Int) -> Int){
        println(func(i))
    }
    fun t2(i: Int, func: (Int) -> Int) = func(i)
    //Lambda Ausdruck auf der rechten Seite, dadurch ist t3 eine anonymefunktion, t3 ist ein Funktionsobjekt
    //damit ist folgendes möglich :  val otherFunction = t3
    //bei statischen Funktionen müsste man ansonsten : val otherFunction = ::t1
    val t3 = {i: Int, func: (Int) -> Int -> func(i)}


    private fun observable(){
        val emitter = PublishSubject.create<View>()
   
        searchButton.setOnClickListener({ v -> emitter.onNext(v)  })

        val subscribe = emitter
                .map { inc1() }
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribe({ _ -> inc2() },
                        { e -> println(e.message) },
                        { println("complete")})

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        observable()
        addFragment(PartaFragment(), R.id.frame1)

        //Beispiel Lambda Aufruf
        t1(10) { i -> i+2 }
        println(t2(10) {i -> i+3 })
        println(t3(10) {i -> i+4 })

/*
        ob.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { t: Int? -> println(t) }
*/
        button_goto_service.setOnClickListener{
            addFragment(ShowUserFragment(), R.id.frame1 )
        }

        /*
        GlideApp.with(this)
                .load()
                .into()
                */
    }



}




