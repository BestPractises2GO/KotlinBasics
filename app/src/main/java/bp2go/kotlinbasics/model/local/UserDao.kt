package bp2go.kotlinbasics.model.local

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import bp2go.kotlinbasics.model.User
import java.util.*

@Dao
interface UserDao{
   @Query("SELECT * FROM user WHERE login = :userLogin")
    fun load(userLogin: String?) : LiveData<User>

    @get:Query("SELECt * FROM user")
    val loadAll : List<User>

    @Query("SELECT * FROM user WHERE login = :userLogin AND lastRefresh > :lastRefreshMax LIMIT 1")
    fun hasUser(userLogin: String?, lastRefreshMax: Date): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(user : User)
}