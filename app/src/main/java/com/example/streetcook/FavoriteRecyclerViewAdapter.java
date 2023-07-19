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

public class FavoriteRecyclerViewAdapter extends RecyclerView.Adapter<FavoriteRecyclerViewAdapter.ViewHolder> {

    private List<FavoriteItemData> dataList;

    private static OnDetailClickListener detailListener;



    public FavoriteRecyclerViewAdapter(List<FavoriteItemData> dataList) {
        this.dataList = dataList;

    }

    public interface OnDeleteClickListener {
        void onDeleteClick(int position);
    }

    public interface OnDetailClickListener {
        void onDetailClick(int position);
    }

    private static OnDeleteClickListener listener;

    public void setOnDeleteClickListener(OnDeleteClickListener listener) {
        this.listener = listener;
    }

    public void setOnItemClickListener(OnDetailClickListener detailListener) {
        this.detailListener = detailListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_favorite, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FavoriteItemData favoriteItemData = dataList.get(position);
        holder.foodNameTextView.setText(favoriteItemData.getTitle());
        holder.foodDescriptionTextView.setText(favoriteItemData.getDescription());
        Glide.with(holder.itemView.getContext()).load(favoriteItemData.getImage()).into(holder.foodImageView);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView foodNameTextView;
        TextView foodDescriptionTextView;
        ImageView foodImageView;
        Button detailButton;
        ImageView deleteButton;

        public ViewHolder(View itemView) {
            super(itemView);
            foodNameTextView = itemView.findViewById(R.id.foodname);
            foodDescriptionTextView = itemView.findViewById(R.id.tagfood);
            foodImageView = itemView.findViewById(R.id.image_food);
            deleteButton = itemView.findViewById(R.id.DeleteIcon);
            detailButton = itemView.findViewById(R.id.DetailButton);

            detailButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        onDetailClick(position);
                    }
                }
            });

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        onDeleteClick(position);
                    }
                }
            });

        }

        private void onDetailClick(int position) {
            if (detailListener != null) {
                detailListener.onDetailClick(position);
            }
        }

        private void onDeleteClick(int position) {
            if (listener != null) {
                listener.onDeleteClick(position);
            }
        }
    }
}