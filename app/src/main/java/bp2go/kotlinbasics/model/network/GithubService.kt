package bp2go.kotlinbasics.model.network


import bp2go.kotlinbasics.model.User2
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubService {
    @GET("/users")
    fun getUsers(@Query("since") userId: Long, @Query("per_page") perPage: Int): Single<List<User2>>
}