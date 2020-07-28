package com.example.petagram.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.petagram.R;
import com.example.petagram.model.Animal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.example.petagram.activity.MainActivity.favorites;
import static com.example.petagram.activity.MainActivity.numberFavorites;
import static com.example.petagram.activity.MainActivity.fav;


public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<Animal> data;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    public HomeRecyclerViewAdapter(Context context, ArrayList<Animal> data) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.data = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.nameDog.setText(data.get(position).getNameDog());
        holder.rateDog.setText(data.get(position).getRateDog() + "");
        holder.imageDog.setImageResource(data.get(position).getImageDog());

        holder.imageDog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(data.get(position).isLike()) {
                    holder.myLike.setImageResource(R.drawable.white_bone);
                    data.get(position).setLike(false);
                    favorites.remove(data.get(position));
                    Toast.makeText(context, data.get(position).getNameDog() + "  se ha quitado de favoritos ", Toast.LENGTH_SHORT).show();
                    numberFavorites--;

                    int numberRate = Integer.parseInt(holder.rateDog.getText().toString())-1;
                    holder.rateDog.setText(numberRate + "");

                }else {
                    holder.myLike.setImageResource(R.drawable.golden_bone);
                    data.get(position).setLike(true);
                    data.get(position).setLike(true);
                    favorites.add(data.get(position));
                    Toast.makeText(context, data.get(position).getNameDog() + " añadido a favoritos ", Toast.LENGTH_SHORT).show();
                    numberFavorites++;

                    int numberRate = Integer.parseInt(holder.rateDog.getText().toString())+1;
                    holder.rateDog.setText(numberRate + "");
                }

                fav.setText(numberFavorites + "");

            }
        });

        holder.myLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(data.get(position).isLike()) {
                    holder.myLike.setImageResource(R.drawable.white_bone);
                    data.get(position).setLike(false);
                    favorites.remove(data.get(position));
                    Toast.makeText(context, data.get(position).getNameDog() + "  se ha quitado de favoritos ", Toast.LENGTH_SHORT).show();
                    numberFavorites--;

                    int numberRate = Integer.parseInt(holder.rateDog.getText().toString())-1;
                    holder.rateDog.setText(numberRate + "");

                }else {
                    holder.myLike.setImageResource(R.drawable.golden_bone);
                    data.get(position).setLike(true);
                    data.get(position).setLike(true);
                    favorites.add(data.get(position));
                    Toast.makeText(context, data.get(position).getNameDog() + " añadido a favoritos ", Toast.LENGTH_SHORT).show();
                    numberFavorites++;

                    int numberRate = Integer.parseInt(holder.rateDog.getText().toString())+1;
                    holder.rateDog.setText(numberRate + "");
                }

                fav.setText(numberFavorites + "");
            }
        });


    }

    // total number of rows
    @Override
    public int getItemCount() {
        return data.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView nameDog;
        TextView rateDog;
        ImageView imageDog;
        ImageView myLike;
        Random random = new Random();

        ViewHolder(View itemView) {
            super(itemView);
            nameDog = itemView.findViewById(R.id.dog_name);
            rateDog = itemView.findViewById(R.id.rate_dog);
            imageDog = itemView.findViewById(R.id.image_dog);
            myLike = itemView.findViewById(R.id.white_bone);
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