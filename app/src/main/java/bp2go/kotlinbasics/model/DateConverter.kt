package bp2go.kotlinbasics.model

import android.arch.persistence.room.TypeConverter
import java.util.*


class DateConverter {
    @TypeConverter
    fun toDate(timestamp: Long?)  : Date? = if(timestamp == null) null else Date(timestamp)

    @TypeConverter
    fun toTimestamp(date: Date?)  : Long? = if(date == null) null else date.time


}