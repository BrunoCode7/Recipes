package com.example.android.recipes.widget;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.android.recipes.R;

import java.util.ArrayList;
import java.util.Arrays;



/**
 * Created by Baraa Hesham on 6/22/2018.
 */

public class RecipesRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private ArrayList<String> listItemList;
    private Context context;
    private int recipeWidgetId;


    public RecipesRemoteViewsFactory(Context context) {
        this.context = context;


    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDataSetChanged() {
        SharedPreferences sharedPref = context.getSharedPreferences(
                "widget_shared_preference", Context.MODE_PRIVATE);
        sharedPref.getString("widget_shared_preference_list", "No chosen Recipe");
        listItemList = new ArrayList<String>(Arrays.asList(sharedPref.getString("widget_shared_preference_list", "No chosen Recipe").split(", ")));
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return listItemList.size();

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    /*
     *Similar to getView of Adapter where instead of View
     *we return RemoteViews
     *
     */
    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews remoteView = new RemoteViews(
                context.getPackageName(), R.layout.widget_row);
        remoteView.setTextViewText(R.id.widget_row_tv, listItemList.get(position));
        Intent clickIntent = new Intent();
        remoteView.setOnClickFillInIntent(R.id.widget_row_tv, clickIntent);
        return remoteView;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }
}
