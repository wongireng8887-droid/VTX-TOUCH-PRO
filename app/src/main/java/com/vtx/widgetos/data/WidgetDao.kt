package com.vtx.widgetos.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WidgetDao {
    @Query("SELECT * FROM widget_configs ORDER BY updatedAt DESC")
    fun observeAll(): Flow<List<WidgetEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(widget: WidgetEntity)

    @Query("DELETE FROM widget_configs WHERE id = :id")
    suspend fun deleteById(id: String)

    @Query("SELECT COUNT(*) FROM widget_configs")
    fun observeCount(): Flow<Int>
}
