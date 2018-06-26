package com.example.android.recipes;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.android.recipes.idlingResource.SimpleIdlingResource;
import com.example.android.recipes.models.Ingredient;
import com.example.android.recipes.models.Recipe;
import com.example.android.recipes.utilities.JsonHandling;
import com.example.android.recipes.utilities.MySingleton;
import com.example.android.recipes.utilities.RecipesAdapter;
import com.example.android.recipes.widget.RecipeWidgetProvider;


import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import static com.example.android.recipes.BuildConfig.URL_STRING;


public class MainActivity extends AppCompatActivity implements RecipesAdapter.itemClickListener {

    private RecyclerView recyclerView;
    private JsonArrayRequest arrayRequest;
    private RecipesAdapter recipesAdapter;
    private ArrayList<Recipe> recipesList;
    private final static String RECIPES_URL= URL_STRING;

    @Nullable
    private SimpleIdlingResource mIdlingResource;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getIdlingResource();
        recyclerView = findViewById(R.id.rv);
        if (!this.getResources().getBoolean(R.bool.is_tablet)) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            fetchData();
        } else {
            MainActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

            recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));

            fetchData();




        }

    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mIdlingResource != null) {
            mIdlingResource.setIdleState(false);
        }


        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mIdlingResource != null) {
                    mIdlingResource.setIdleState(true);
                }
            }
        },3000);

    }

    //Helper method to fetch the JSON data
    public void fetchData() {
                // you can change the Recipes RECIPES_URL with your Link String
        arrayRequest = new JsonArrayRequest(Request.Method.GET,RECIPES_URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            recipesList = JsonHandling.recipeJsonHandling(response);
                            recipesAdapter = new RecipesAdapter(getApplicationContext(), recipesList, MainActivity.this);
                            recyclerView.setAdapter(recipesAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse networkResponse = error.networkResponse;
                if (networkResponse != null) {
                    Toast.makeText(getApplicationContext(), "Error Code =" + networkResponse
                            .statusCode, Toast.LENGTH_SHORT).show();

                } else {
                    // can't connect to the server(internet connection error)
                    Toast.makeText(getApplicationContext(), "No Internet Connection!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(arrayRequest);
    }

    @Override
    public void onItemClickListener(int clickedItemIndex, ViewGroup imageCard) {
        Recipe chosenRecipe = recipesList.get(clickedItemIndex);
        Intent intent = new Intent(MainActivity.this, RecipesSteps.class);
        intent.putExtra("recipe", chosenRecipe);
        startActivity(intent);
        ArrayList<Ingredient> ingredients = chosenRecipe.getIngredients();
        ArrayList<String> stringIngredients = new ArrayList<>();
        for (int i = 0; i < ingredients.size(); i++) {
            float quantity = ingredients.get(i).getQuantity();
            String measure = ingredients.get(i).getMeasure();
            String ingredientName = ingredients.get(i).getIngredientName();
            String trueMeasure = null;
            switch (measure) {
                case "G":
                    trueMeasure = "gram";
                    break;
                case "CUP":
                    trueMeasure = "cup";
                    break;
                case "TBLSP":
                    trueMeasure = "tablespoon";
                    break;
                case "TSP":
                    trueMeasure = "teaspoon";
                    break;
                case "K":
                    trueMeasure = "kilo";
                    break;
                case "OZ":
                    trueMeasure = "ounce";
                    break;
                case "UNIT":
                    trueMeasure = "";
                    break;
            }
            stringIngredients.add(i + 1 + ") " + quantity + " " + trueMeasure + " " + ingredientName);
        }

        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(
                "widget_shared_preference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("name", chosenRecipe.getName());
        editor.putString("widget_shared_preference_list", TextUtils.join(", ", stringIngredients));
        editor.apply();
        updateWidgets(getApplicationContext());



        if (this.getResources().getBoolean(R.bool.is_tablet)) {
            Toast.makeText(getApplicationContext(), "Swipe video for more", Toast.LENGTH_LONG).show();
        }

    }

    public static void updateWidgets(Context context) {
        Intent intent = new Intent(context.getApplicationContext(), RecipeWidgetProvider.class);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);

        AppWidgetManager widgetManager = AppWidgetManager.getInstance(context);
        int[] ids = widgetManager.getAppWidgetIds(new ComponentName(context, RecipeWidgetProvider.class));

        widgetManager.notifyAppWidgetViewDataChanged(ids, R.id.widget_title);
        widgetManager.notifyAppWidgetViewDataChanged(ids, R.id.widget_body);

        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
        context.sendBroadcast(intent);
    }

    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (mIdlingResource == null) {
            mIdlingResource = new SimpleIdlingResource();
        }
        return mIdlingResource;
    }
}
