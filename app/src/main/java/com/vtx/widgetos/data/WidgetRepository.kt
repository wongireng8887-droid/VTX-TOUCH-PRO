package com.vtx.widgetos.data

class WidgetRepository(private val dao: WidgetDao) {
    val widgets = dao.observeAll()
    val count = dao.observeCount()

    suspend fun save(widget: WidgetEntity) = dao.upsert(widget)
    suspend fun delete(id: String) = dao.deleteById(id)
}
