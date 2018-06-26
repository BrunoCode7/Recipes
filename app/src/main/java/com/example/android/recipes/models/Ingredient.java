package com.example.android.recipes.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Baraa Hesham on 6/8/2018.
 */
public class Ingredient implements Parcelable {
    private String ingredientName;
    private float quantity;
    private String measure;

    public Ingredient(){

    }

    public Ingredient(String ingredientName, float quantity, String measure) {
        this.ingredientName = ingredientName;
        this.quantity = quantity;
        this.measure = measure;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public float getQuantity() {
        return quantity;
    }

    public String getMeasure() {
        return measure;
        }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public Ingredient(Parcel parcel){
        this.ingredientName=parcel.readString();
        this.quantity=parcel.readFloat();
        this.measure=parcel.readString();
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(ingredientName);
        parcel.writeFloat(quantity);
        parcel.writeString(measure);
    }
    public static Creator<Ingredient> CREATOR=new Creator<Ingredient>() {
        @Override
        public Ingredient createFromParcel(Parcel parcel) {
            return new Ingredient(parcel);
        }

        @Override
        public Ingredient[] newArray(int i) {
            return new Ingredient[i];
        }
    };
}
