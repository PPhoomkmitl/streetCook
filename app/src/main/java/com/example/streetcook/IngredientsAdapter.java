package com.example.streetcook;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ViewHolder> {
    private List<String> ingredients;
    private Context context;

    public IngredientsAdapter(List<String> ingredients) {
        this.ingredients = ingredients;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String ingredient = ingredients.get(position);
        holder.ingredientTextView.setText(ingredient);
    }

    @Override
    public int getItemCount() {
        int maxItemCount = 4; // จำนวนรายการที่ต้องการแสดงเพียง 4 รายการ
        return Math.min(ingredients.size(), maxItemCount);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView ingredientTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredientTextView = itemView.findViewById(android.R.id.text1);
        }
    }
}
