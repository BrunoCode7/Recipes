package com.example.android.recipes.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

import com.example.android.recipes.MainActivity;
import com.example.android.recipes.R;
import com.example.android.recipes.RecipesSteps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Implementation of App Widget functionality.
 */
public class RecipeWidgetProvider extends AppWidgetProvider {


    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_widget_provider);

        SharedPreferences sharedPref = context.getSharedPreferences(
                "widget_shared_preference", Context.MODE_PRIVATE);
        views.setTextViewText(R.id.widget_title,sharedPref.getString("name",""));


        Intent intent = context.getPackageManager().getLaunchIntentForPackage("com.example.android.recipes");

        PendingIntent pendingIntent=PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        views.setPendingIntentTemplate(R.id.widget_body,pendingIntent);

        Intent intent1=new Intent(context,RecipesWidgetService.class);
        views.setRemoteAdapter(R.id.widget_body,intent1);
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }



    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }


}

