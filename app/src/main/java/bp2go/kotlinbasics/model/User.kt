package bp2go.kotlinbasics.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity
data class User(
        @PrimaryKey
            @SerializedName("id")
            @Expose
            val id: String,
        @SerializedName("login")
            @Expose
            val login: String,
        @SerializedName("avatar_url")
            @Expose
            val avatar_url: String,
        @SerializedName("name")
            @Expose
            val name: String,
        @SerializedName("company")
            @Expose
            val company: String,
        @SerializedName("blog")
            @Expose
            val blog: String,
        var lastRefresh: Date?


)