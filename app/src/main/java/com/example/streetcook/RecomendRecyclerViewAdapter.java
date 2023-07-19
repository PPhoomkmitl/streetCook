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

public class RecomendRecyclerViewAdapter extends RecyclerView.Adapter<RecomendRecyclerViewAdapter.ViewHolder> {

    private List<RecomendItem> dataList;
    private RecomendItemClickListener listener;

    public RecomendRecyclerViewAdapter(List<RecomendItem> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_recomendfood, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RecomendItem recomendItemData = dataList.get(position);
        holder.foodNameTextView.setText(recomendItemData.getTitle());
        holder.foodDescriptionTextView.setText(recomendItemData.getDescription());
        Glide.with(holder.itemView.getContext()).load(recomendItemData.getImageResource()).into(holder.foodImageView);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void setOnItemClickListener(RecomendItemClickListener listener) {
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView foodNameTextView;
        TextView foodDescriptionTextView;
        ImageView foodImageView;
        Button detailButton;

        public ViewHolder(View itemView) {
            super(itemView);
            foodNameTextView = itemView.findViewById(R.id.Recomendfoodname);
            foodDescriptionTextView = itemView.findViewById(R.id.Recomendtagfood);
            foodImageView = itemView.findViewById(R.id.Recomendimage_food);
            detailButton = itemView.findViewById(R.id.RecomendDetailButton);

            detailButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onRecomendItemClick(position);
                }
            }
        }
    }


    public interface RecomendItemClickListener {
        void onRecomendItemClick(int position);
    }
}
