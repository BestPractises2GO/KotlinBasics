package bp2go.kotlinbasics.model

import android.app.Application
import android.arch.lifecycle.LiveData
import android.widget.Toast
import bp2go.kotlinbasics.App
import bp2go.kotlinbasics.model.local.UserDao
import bp2go.kotlinbasics.model.network.UserWebservice
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import java.util.concurrent.Executor
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(val webservice: UserWebservice, val userDao: UserDao, val executor: Executor, val app: Application){

    private val FRESH_TIMEOUT_IN_MINUTES = 3;


    fun getUser(userLogin: String) : LiveData<User> {
        refreshUser(userLogin)
        return userDao.load(userLogin)
    }

    fun refreshUser(userLogin: String) : Unit{
        executor.execute{
            val userExists: Boolean = (userDao.hasUser(userLogin, getMaxRefreshTime(Date())) != null)

            if (!userExists) {
                webservice.getUser(userLogin).enqueue(object : Callback<User> {
                    override fun onFailure(call: Call<User>?, t: Throwable?) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onResponse(call: Call<User>?, response: Response<User>?) {
                        Toast.makeText(app, "Daten vom Netzwerk aktualisiert", Toast.LENGTH_LONG).show()
                        val user: User? = response?.body()
                        user?.lastRefresh = Date()
                        userDao.save(user)
                    }

                })
            }

        }
    }

    private fun getMaxRefreshTime(currentDate: Date) : Date{
        val cal = Calendar.getInstance()
        cal.time = currentDate
        cal.add(Calendar.MINUTE, -FRESH_TIMEOUT_IN_MINUTES)
        return cal.time
    }

}