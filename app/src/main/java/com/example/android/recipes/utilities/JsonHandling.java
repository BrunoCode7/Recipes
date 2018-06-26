package com.example.android.recipes.utilities;

import com.example.android.recipes.models.Ingredient;
import com.example.android.recipes.models.Recipe;
import com.example.android.recipes.models.Steps;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Baraa Hesham on 6/9/2018.
 */
public class JsonHandling {


    public static ArrayList<Recipe> recipeJsonHandling(JSONArray recipeJsonArray) throws JSONException {
        ArrayList<Recipe> RecipesList=new ArrayList<>();

        String recipeName;

        for (int r=0;r<recipeJsonArray.length();r++){
             JSONObject recipeJsonObject=recipeJsonArray.getJSONObject(r);
              recipeName=recipeJsonObject.getString("name");
             JSONArray ingredientsJsonArray=recipeJsonObject.getJSONArray("ingredients");
            JSONArray stepsJsonArray=recipeJsonObject.getJSONArray("steps");
            ArrayList<Ingredient> ingredientsList =new ArrayList<>();
            ArrayList<Steps> stepsList =new ArrayList<>();

            for (int i=0;i<ingredientsJsonArray.length();i++){
                 JSONObject ingredientJsonObject = ingredientsJsonArray.getJSONObject(i);
                 float ingredientQuantity=Float.valueOf(ingredientJsonObject.getString("quantity"));
                 String ingredientMeasure=ingredientJsonObject.getString("measure");
                 String ingredientName=ingredientJsonObject.getString("ingredient");
                 Ingredient ingredient=new Ingredient(ingredientName,ingredientQuantity,ingredientMeasure);
                 ingredientsList.add(ingredient);
            }
            for (int s=0;s<stepsJsonArray.length();s++){
                JSONObject stepJsonObject=stepsJsonArray.getJSONObject(s);
                String stepShortDescription=stepJsonObject.getString("shortDescription");
                String superscription=stepJsonObject.getString("description");
                String stepVideoUrl=stepJsonObject.getString("videoURL");
                String stepThumbnailUrl=stepJsonObject.getString("thumbnailURL");
                Steps step=new Steps(stepShortDescription,superscription,stepVideoUrl,stepThumbnailUrl);
                stepsList.add(step);
            }
            Recipe recipe=new Recipe(ingredientsList,stepsList,recipeName);
            RecipesList.add(recipe);
        }
        return RecipesList;
    }
}
