package com.example.android.recipes.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Baraa Hesham on 6/8/2018.
 */
public class Recipe implements Parcelable{
    private ArrayList ingredients;
    private ArrayList steps;
    private String name;
    private String image;

    public Recipe(){

    }

    public Recipe(ArrayList<Ingredient> ingredients, ArrayList<Steps> steps, String name,String image) {
        this.ingredients = ingredients;
        this.steps = steps;
        this.name = name;
        this.image=image;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public ArrayList<Steps> getSteps() {
        return steps;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void setSteps(ArrayList<Steps> steps) {
        this.steps = steps;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Recipe(Parcel parcel){
        this.name= parcel.readString();
        this.steps=parcel.readArrayList(getClass().getClassLoader());
        this.ingredients=parcel.readArrayList(getClass().getClassLoader());
    }
    public static Creator<Recipe> CREATOR=new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel parcel) {
            return new Recipe(parcel);
        }

        @Override
        public Recipe[] newArray(int i) {
            return new Recipe[i];
        }
    };
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    parcel.writeString(name);
    parcel.writeList(steps);
    parcel.writeList(ingredients);

    }
}
