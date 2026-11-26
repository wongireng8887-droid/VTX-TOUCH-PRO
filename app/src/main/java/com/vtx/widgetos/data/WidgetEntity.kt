package com.vtx.widgetos.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "widget_configs")
data class WidgetEntity(
    @PrimaryKey val id: String,
    val name: String,
    val type: String,
    val width: Int = 2,
    val height: Int = 2,
    val configJson: String = "{}",
    val updatedAt: Long = System.currentTimeMillis()
)
