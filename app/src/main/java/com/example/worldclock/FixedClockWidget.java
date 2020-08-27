package com.example.worldclock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.SystemClock;
import android.text.Html;
import android.text.Spanned;
import android.text.SpannedString;
import android.util.Log;
import android.widget.RemoteViews;
import com.example.worldclock.WorldClock;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Implementation of App Widget functionality.
 */
public class FixedClockWidget extends AppWidgetProvider {
    public static int t = 0;
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        Spanned time_text = Html.fromHtml(
                "It's <span style = 'color: #ffffff'>ten in the morning</span> in"
        );

        int offset = -2 * 3600;

        CharSequence locationText = WorldClock.get_location(offset);

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.fixed_clock_widget);
        views.setTextViewText(R.id.time_text, time_text);
        views.setTextViewText(R.id.location_text, locationText);
        t++;

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(final Context context) {
        // Enter relevant functionality for when the first widget is created
        Log.d("ENABLING ALARM", "ENABLING ALARM");
        Context c = context;
        final Intent intent = new Intent("android.appwidget.action.APPWIDGET_UPDATE");
        final PendingIntent pending = PendingIntent.getBroadcast(c, 0, intent, 0);
        final AlarmManager alarm = (AlarmManager) c.getSystemService(Context.ALARM_SERVICE);
        alarm.cancel(pending);
        long interval = 1000*60*4;  // update every 4 minutes
        alarm.setRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime(), interval, pending);

    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("RECEIVED INTENT", intent.getAction());
        super.onReceive(context, intent);

        if (intent.getAction().equals("android.appwidget.action.APPWIDGET_UPDATE")) {
            AppWidgetManager manager = AppWidgetManager.getInstance(context);
            ComponentName name = new ComponentName(context, FixedClockWidget.class);
            updateAppWidget(context, manager, manager.getAppWidgetIds(name)[0]);
        }

    }
}

