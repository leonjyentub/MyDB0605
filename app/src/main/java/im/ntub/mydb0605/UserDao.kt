package im.ntub.mydb0605

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getAll(): List<User>

    @Query("SELECT * FROM users WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Query("SELECT * FROM Users WHERE uid=:uid")
    fun getbyUID(uid: Int): User

    @Query("SELECT * FROM users WHERE name LIKE :name LIMIT 1")
    fun findByName(name: String): User??

    @Insert
    fun insert(user: User):Long
    //成功的話可以取得產生的key值
    @Insert
    fun insertAll(vararg users: User)

    @Update fun update(user: User) : Int //成功的話可以知道更新了幾筆

    @Delete fun delete(user: User)
}