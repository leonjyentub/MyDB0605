package im.ntub.mydb0605

import androidx.room.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object TimestampConverter {
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")

    @TypeConverter
    fun fromTimestamp(value: String?): LocalDateTime? {
        return value?.let{ LocalDateTime.parse(it, formatter) }
    }

    @TypeConverter fun dateToTimestamp(date: LocalDateTime?): String? {
        return date?.format(formatter)
    }
}

@Entity(tableName = "Users")
@TypeConverters(TimestampConverter::class)
data class User(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name="name") var name: String?,
    @ColumnInfo(name="score") var score: Int?,
    @ColumnInfo var updatedTime: LocalDateTime?
)
