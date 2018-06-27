package com.example.android.recipes.utilities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;
import com.example.android.recipes.R;
import com.example.android.recipes.models.Recipe;

import java.util.ArrayList;

/**
 * Created by Baraa Hesham on 6/9/2018.
 */
public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipesViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<Recipe> data;
    private itemClickListener itemClickListener;

    public interface itemClickListener {
        void onItemClickListener(int clickedItemIndex, ViewGroup imageCard);
    }

    public RecipesAdapter(Context context, ArrayList<Recipe> data, itemClickListener listener) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.data = data;
        itemClickListener = listener;
    }

    @NonNull
    @Override
    public RecipesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.recipes_list_items, parent, false);
        return new RecipesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecipesViewHolder holder, int position) {
        Recipe recipe = data.get(position);
        if (recipe.getImage().equals("")){
            switch (recipe.getName()) {
                case ("Nutella Pie"):
                    GlideApp.with(context).applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.loading).error(R.drawable.error)).load(R.drawable.nutella_pie).into(holder.recipepicture);
                    break;
                case ("Brownies"):
                    GlideApp.with(context).applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.loading).error(R.drawable.error)).load(R.drawable.brownies).into(holder.recipepicture);
                    break;
                case ("Yellow Cake"):
                    GlideApp.with(context).applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.loading).error(R.drawable.error)).load(R.drawable.yellow_cake).into(holder.recipepicture);
                    break;
                case ("Cheesecake"):
                    GlideApp.with(context).applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.loading).error(R.drawable.error)).load(R.drawable.cheesecake).into(holder.recipepicture);
                    break;
        }

        }else {
            GlideApp.with(context).applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.loading).error(R.drawable.error)).load(recipe.getImage()).into(holder.recipepicture);

        }
        holder.recipeName.setText(recipe.getName());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onItemClickListener(holder.getAdapterPosition(), holder.linearLayout);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class RecipesViewHolder extends RecyclerView.ViewHolder {
        private TextView recipeName;
        private ImageView recipepicture;
        private LinearLayout linearLayout;

        public RecipesViewHolder(View itemView) {
            super(itemView);
            recipeName = itemView.findViewById(R.id.recipe_name_tv);
            recipepicture = itemView.findViewById(R.id.recipe_image_iv);
            linearLayout = itemView.findViewById(R.id.recipe_card);
        }
    }
}