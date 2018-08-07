package bp2go.kotlinbasics.model.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import bp2go.kotlinbasics.model.DateConverter
import bp2go.kotlinbasics.model.User

@Database(entities = arrayOf(User::class), version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class UserDatabase : RoomDatabase() {

    companion object {
        // --- SINGLETON ---
        private val INSTANCE: Companion = UserDatabase
    }

    abstract fun userDao(): UserDao
}