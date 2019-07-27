package com.vihari.apod.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ApodDao {
    @Query("select * from databaseapod")
    fun getApods(): LiveData<List<DatabaseApod>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll( apods: DatabaseApod)
}



@Database(entities = [DatabaseApod::class], version = 1)
abstract class ApodsDatabase: RoomDatabase() {
    abstract val apodDao: ApodDao
}

private lateinit var INSTANCE: ApodsDatabase

fun getDatabase(context: Context): ApodsDatabase {
    synchronized(ApodsDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                ApodsDatabase::class.java,
                "apods").build()
        }
    }
    return INSTANCE
}