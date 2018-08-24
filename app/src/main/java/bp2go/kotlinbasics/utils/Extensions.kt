package bp2go.kotlinbasics.utils



import android.content.Context
import android.content.SharedPreferences
import android.support.annotation.LayoutRes
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

/*
The good thing is that these functions are inline,
which means that the function is substituted by the code of the function in compilation time,
so it’s as efficient as writing the code directly into the place of the call.
 */
inline fun View.snack(message: String, length: Int = Snackbar.LENGTH_LONG, f: Snackbar.() -> Snackbar){
Snackbar.make(this, message, length).f().show()
}

/*######################################################################################################################
  FragmentTransaction.() = func() kann von FragmentTransaction Methoden aufgerufen werden
  FragmentTransaction = der Paramter hinter -> bedeutet, dass man nach func() FragmentTransaction Methoden aufrufen kann
  Mann kann mit dem FragmentManager jetzt hinzugefügte MEthode inTransaction3 aufrufen
  mit dem Aufruf der Methode inTransaction3 kann man in den Lambda Ausdruck eine Funktion für den
  Platzhalter func() hinzufügen, z.B. add() oder replace()
#######################################################################################################################*/
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

//Recyclerview LayoutInflater
fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false ): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int){
    supportFragmentManager.inTransaction3 { add(frameId, fragment) }
}

fun AppCompatActivity.addFragmentTag(fragment: Fragment, frameId: Int, tag: String){
    supportFragmentManager.inTransaction3 { add(frameId, fragment, tag) }
}

fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int) {
    supportFragmentManager.inTransaction3{replace(frameId, fragment)}
}

fun AppCompatActivity.replaceFragmentTag(fragment: Fragment, frameId: Int, tag: String) {
    supportFragmentManager.inTransaction3{replace(frameId, fragment,tag)}
}

fun Any.toast(message: String, context: Context, duration: Int = Toast.LENGTH_LONG) {
      Toast.makeText(context,message,duration).apply { show()}
}

