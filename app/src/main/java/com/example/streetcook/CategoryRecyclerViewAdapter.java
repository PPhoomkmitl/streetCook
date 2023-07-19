package com.example.streetcook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CategoryRecyclerViewAdapter extends RecyclerView.Adapter<CategoryRecyclerViewAdapter.ViewHolder> {

    private List<CategoryItemData> dataList;
    private CategoryItemClickListener listener;

    public CategoryRecyclerViewAdapter(List<CategoryItemData> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CategoryItemData categoryItemData = dataList.get(position);
        holder.foodNameTextView.setText(categoryItemData.getTitle());
        holder.foodDescriptionTextView.setText(categoryItemData.getDescription());
        Glide.with(holder.itemView.getContext()).load(categoryItemData.getImageResource()).into(holder.foodImageView);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void setOnItemClickListener(CategoryRecyclerViewAdapter.CategoryItemClickListener listener) {
        this.listener = listener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView foodNameTextView;
        TextView foodDescriptionTextView;
        ImageView foodImageView;
        Button detailButton;

        public ViewHolder(View itemView) {
            super(itemView);
            foodNameTextView = itemView.findViewById(R.id.Categoryfoodname);
            foodDescriptionTextView = itemView.findViewById(R.id.Categorytagfood);
            foodImageView = itemView.findViewById(R.id.Categoryimage_food);
            detailButton = itemView.findViewById(R.id.CategoryDetailButton);

            detailButton.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            if (listener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onCategoryItemClick(position);
                }
            }
        }
    }

    public interface CategoryItemClickListener {
        void onCategoryItemClick(int position);
    }

}
