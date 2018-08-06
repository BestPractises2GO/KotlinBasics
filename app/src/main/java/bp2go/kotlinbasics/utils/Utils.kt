package bp2go.kotlinbasics.utils



import android.content.SharedPreferences
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity

//FragmentTransaction.() = func() kann von Fragmenttransaction Methoden aufgerufen werden
//FragmentTransaction = der Paramter hinter -> bedeutet, dass man nach func() FragmentTransaction Methoden aufrufen kann
//Mann kann mit dem FragmentManager jetzt hinzugefügte MEthode inTransaction3 aufrufen
//mit dem Aufruf der Methode inTransaction3 kann man in den Lambda Ausdruck eine Funktion für den
// Platzhalter func() hinzufügen, z.B. add() oder replace()
inline fun FragmentManager.inTransaction3(func: FragmentTransaction.() -> FragmentTransaction) {
     beginTransaction().func().commit()
    /*equivalent to
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.func()
    fragmentTransaction.commit()
    */
}

inline fun FragmentManager.inTransactionChild(func: FragmentTransaction.() -> FragmentTransaction){

}

inline fun SharedPreferences.edit3(func: SharedPreferences.Editor.() -> Unit) {
    val editor = edit()
    editor.func()
    editor.apply()
}

fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int){
    supportFragmentManager.inTransaction3 { add(frameId, fragment) }
}

fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int) {
    supportFragmentManager.inTransaction3{replace(frameId, fragment)}
}