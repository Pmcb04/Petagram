package com.example.petagram.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.petagram.R;
import com.example.petagram.model.Animal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class UserRecyclerViewAdapter extends RecyclerView.Adapter<UserRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<Animal> data;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    public UserRecyclerViewAdapter(Context context, ArrayList<Animal> data) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.data = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_row_fragment, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.imageDog.setImageResource(data.get(position).getImageDog());
        holder.numberLikes.setText(data.get(position).getRateDog()  + "");
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return data.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView numberLikes;
        ImageView imageDog;
        Random random = new Random();

        ViewHolder(View itemView) {
            super(itemView);
            numberLikes = itemView.findViewById(R.id.number_likes);
            imageDog = itemView.findViewById(R.id.image_dog);
            imageDog.setBackgroundColor(randomColor());
            itemView.setOnClickListener(this);
        }

        private int randomColor(){
            int color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
            return color;
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    Animal getItem(int id) {
        return data.get(id);
    }


    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}