package com.issa.anime;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ParseAdapter extends RecyclerView.Adapter<ParseAdapter.ViewHolder> {
    private ArrayList<Parseltem> parseltems;
    private Context context;

    public ParseAdapter(ArrayList<Parseltem> parseltems, Context context) {
        this.parseltems = parseltems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_new,parent,false);
        final ViewHolder viewHolder = new ViewHolder(view);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            Parseltem parseltem = parseltems.get(position);
            holder.textView.setText(parseltem.getTitleNew());
            //holder.text.setText(parseltem.getAnimeDescr());
            Picasso.get().load(parseltem.getImgUrl()).into(holder.imageView);


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return parseltems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView textView , text;
        Button mFavButton;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
           // text = itemView.findViewById(R.id.anime);
            imageView = itemView.findViewById(R.id.animeImage);

            textView = itemView.findViewById(R.id.animeTilte);



            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Parseltem parseltem = parseltems.get(position);

            Intent intent = new Intent(context, DetailsActivity.class);
            //intent.putExtra("title", parseltem.getTitleNew());
           // intent.putExtra("image", parseltem.getImgUrl());
            intent.putExtra("detailUrl", parseltem.getDetailanime());


            context.startActivity(intent);

        }
    }
}
