package bp2go.kotlinbasics.model.network

import bp2go.kotlinbasics.model.User
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UserWebservice {

    @GET("/users/{user}")
    fun getUser(@Path("user") userId: String?): Call<User>


    @GET("/users/{user}")
    fun getUserRx(@Path("user") userId: String?): Single<User>
}