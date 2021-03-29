package com.example.cookingapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter {

    private ArrayList<Przepisy> mPrzepisy = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private Context mContext;

    private class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView mTitle;
        public TextView mContext;
        public ImageView mImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.article_title);
            mContext = (TextView) itemView.findViewById(R.id.article_subtitle);
            mImage = (ImageView) itemView.findViewById(R.id.imageAm);

        }
    }
    public MyAdapter(Context mContext,ArrayList<Przepisy> pPrzepisy,RecyclerView pRecyclerView){
        this.mContext=mContext;
        mPrzepisy = pPrzepisy;
        mRecyclerView = pRecyclerView;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wyglad_propozycji,parent,false);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,infoOprzepisise.class);
                mContext.startActivity(intent);
            }
        });


        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        Przepisy przepisy = mPrzepisy.get(position);
        ((MyViewHolder) holder).mTitle.setText(przepisy.getTitle());
        ((MyViewHolder)holder).mContext.setText(przepisy.getContent());
        ((MyViewHolder)holder).mImage.setImageResource(przepisy.getImage());
    }

    @Override
    public int getItemCount() {
        return mPrzepisy.size();
    }
}
