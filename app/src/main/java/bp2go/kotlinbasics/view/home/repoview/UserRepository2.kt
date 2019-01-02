package bp2go.kotlinbasics.view.home.repoview

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import bp2go.kotlinbasics.model.User
import bp2go.kotlinbasics.model.network.UserWebservice
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject


class UserRepository2 @Inject constructor(val service: UserWebservice) {
    var data: MutableLiveData<User>? =null

    fun getUser(user: String): LiveData<User> {
        if(data==null) {
            data = MutableLiveData()
        }
         service.getUser(user).enqueue(object : retrofit2.Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                Log.d("ReTro", response.body().toString())
                data!!.setValue(response.body())
            }
        })

        return data as MutableLiveData<User>
    }



}