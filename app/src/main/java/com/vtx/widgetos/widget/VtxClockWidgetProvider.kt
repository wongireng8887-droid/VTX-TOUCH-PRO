package com.vtx.widgetos.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.vtx.widgetos.R

/**
 * Home-screen clock widget. TextClock updates its displayed time itself while visible;
 * the provider refreshes RemoteViews after system clock/date/time-zone changes.
 */
class VtxClockWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        appWidgetIds.forEach { appWidgetId ->
            appWidgetManager.updateAppWidget(appWidgetId, createViews(context))
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)

        when (intent.action) {
            Intent.ACTION_TIME_CHANGED,
            Intent.ACTION_TIMEZONE_CHANGED,
            Intent.ACTION_DATE_CHANGED -> {
                val manager = AppWidgetManager.getInstance(context)
                val component = ComponentName(context, VtxClockWidgetProvider::class.java)
                manager.getAppWidgetIds(component).forEach { appWidgetId ->
                    manager.updateAppWidget(appWidgetId, createViews(context))
                }
            }
        }
    }

    private fun createViews(context: Context): RemoteViews =
        RemoteViews(context.packageName, R.layout.widget_vtx_glass_clock)
}
