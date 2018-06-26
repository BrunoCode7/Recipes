package com.example.android.recipes.utilities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.recipes.R;
import com.example.android.recipes.models.Steps;


import java.util.ArrayList;

/**
 * Created by Baraa Hesham on 6/10/2018.
 */
public class RecipesDetailsAdapter extends android.support.v7.widget.RecyclerView.Adapter<RecipesDetailsAdapter.RecipesDetailsViewHolder> {
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<Steps> data;
    private itemClickListener itemClickListener;
    public interface itemClickListener{
        void onItemClickListener(int clickedItemIndex,ViewGroup imageCard);
    }

    public RecipesDetailsAdapter(Context context, ArrayList<Steps> data, RecipesDetailsAdapter.itemClickListener itemClickListener) {
        this.context = context;
        this.data = data;
        this.layoutInflater = LayoutInflater.from(context);
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public RecipesDetailsAdapter.RecipesDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= layoutInflater.inflate(R.layout.recipe_details_list_items,parent,false);
        return new RecipesDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecipesDetailsAdapter.RecipesDetailsViewHolder holder, int position) {
        Steps steps=data.get(position);
        if (steps==null){
            holder.detailsTextView.setText("Recipe Ingredients");
        }else {
            holder.detailsTextView.setText(steps.getShortDescription());
        }
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onItemClickListener(holder.getAdapterPosition(),holder.linearLayout);

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public class RecipesDetailsViewHolder extends RecyclerView.ViewHolder{

        private TextView detailsTextView;
        private LinearLayout linearLayout;
        public RecipesDetailsViewHolder(View itemView) {
            super(itemView);
            detailsTextView=itemView.findViewById(R.id.recipe_details_tv);
            linearLayout=itemView.findViewById(R.id.recipe_details_card);
        }
    }
}
