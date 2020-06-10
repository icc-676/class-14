package com.abs.clase14.model

import androidx.lifecycle.LiveData
import androidx.room.*
import com.abs.clase14.model.Gif.Companion.TABLE_NAME

@Dao
interface GifDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: Gif)

    @Query("SELECT * FROM ${TABLE_NAME}")
    fun getAllGifs(): LiveData<List<Gif>>

    @Query("SELECT * FROM ${TABLE_NAME} ORDER BY ID DESC LIMIT 1 ")
    fun getLastGif(): LiveData<Gif>

}

@Entity(tableName = TABLE_NAME)
data class Gif(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    val id: Int?= null,
    @ColumnInfo(name = URL)
    val url: String
) {
    companion object {
        const val TABLE_NAME = "gif"
        const val ID = "id"
        const val URL = "url"

    }
}