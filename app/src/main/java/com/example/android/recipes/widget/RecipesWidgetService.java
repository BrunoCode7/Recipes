package com.example.android.recipes.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by Baraa Hesham on 6/22/2018.
 */
public class RecipesWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {

        return(new RecipesRemoteViewsFactory(this.getApplicationContext()));

    }
}
