package com.example.android.recipes.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.recipes.R;
import com.example.android.recipes.models.Ingredient;
import com.example.android.recipes.models.Recipe;
import com.example.android.recipes.models.Steps;
import com.example.android.recipes.utilities.OnStepClickListener;
import com.example.android.recipes.utilities.RecipesDetailsAdapter;


import java.util.ArrayList;

public class ListFragment extends Fragment implements RecipesDetailsAdapter.itemClickListener {
    private RecyclerView recyclerView;
    private RecipesDetailsAdapter recipesDetailsAdapter;
    private ArrayList<Steps> steps1;
    private Recipe chosenRecipe1;
    private ArrayList<Ingredient> ingredientsList;
    TextView ingredientsTv;
    OnStepClickListener onStepClickListener;

    public ListFragment(){
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            chosenRecipe1=getArguments().getParcelable("chosenrecipe1");
            steps1=getArguments().getParcelableArrayList("steps1");

        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_recipe_details,container,false);



        recyclerView = view.findViewById(R.id.recipe_details_card_rv);
        recipesDetailsAdapter = new RecipesDetailsAdapter(getContext(), steps1, ListFragment.this);
        recyclerView.setAdapter(recipesDetailsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ingredientsTv = view.findViewById(R.id.ingredients_tv);
        ingredientsList = chosenRecipe1.getIngredients();

        for (int i = 0; i < ingredientsList.size(); i++) {
            float quantity = ingredientsList.get(i).getQuantity();
            String measure = ingredientsList.get(i).getMeasure();
            String ingredientName = ingredientsList.get(i).getIngredientName();
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
            ingredientsTv.append(i + 1 + ") " + quantity + " " + trueMeasure + " " + ingredientName + "\n");
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try { onStepClickListener=(OnStepClickListener)context;

        }catch (ClassCastException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClickListener(int clickedItemIndex, ViewGroup imageCard) {
        onStepClickListener.OnStepSelected(clickedItemIndex);
        Toast.makeText(getContext(), "Swipe screen for next step", Toast.LENGTH_SHORT).show();
    }
}