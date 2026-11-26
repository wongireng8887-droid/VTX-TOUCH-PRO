package com.vtx.widgetos.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [WidgetEntity::class], version = 1, exportSchema = false)
abstract class VtxDatabase : RoomDatabase() {
    abstract fun widgetDao(): WidgetDao

    companion object {
        @Volatile private var INSTANCE: VtxDatabase? = null

        fun get(context: Context): VtxDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    VtxDatabase::class.java,
                    "vtx_widget_os.db"
                ).build().also { INSTANCE = it }
            }
    }
}
