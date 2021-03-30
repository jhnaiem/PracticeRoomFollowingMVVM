package com.example.practiceroom.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.request.RequestOptions;
import com.example.practiceroom.R;
import com.example.practiceroom.model.Country;
import com.example.practiceroom.utils.Util;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideApp;

import java.util.ArrayList;
import java.util.List;


public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.CountryViewHolder> {

    List<Country> storeCountry;


    void updateCountire(List<Country> newCountries) {
        storeCountry.clear();
        storeCountry.addAll(newCountries);
    }

    public CountryListAdapter(ArrayList<Country> countries) {
        storeCountry = countries;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_country, parent, false);
        return new CountryViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {

        String name = storeCountry.get(position).getName();
        String capital = storeCountry.get(position).getCapital();
        String flagUrl = storeCountry.get(position).getFlag();

        CircularProgressDrawable circularProgressDrawable = Util.getProgressDrawable(holder.context);
        holder.mTextName.setText(name);
        holder.mTextCapital.setText(capital);

        RequestOptions options = new RequestOptions().placeholder(circularProgressDrawable).error(R.mipmap.ic_launcher_round);
//        Glide.with(holder.context)
//                .load(flagUrl)
//                .apply(options)
//                .into(holder.mImgFlag);
        
        GlideApp.with(holder.context)
                .load(flagUrl)
                .apply(options)
                .into(holder.mImgFlag);



    }

    @Override
    public int getItemCount() {
        return storeCountry.size();
    }

    public class CountryViewHolder extends RecyclerView.ViewHolder {


        TextView mTextName;
        TextView mTextCapital;
        ImageView mImgFlag;
        Context context;


        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextName = itemView.findViewById(R.id.name);
            mTextCapital = itemView.findViewById(R.id.capital);
            mImgFlag = itemView.findViewById(R.id.imgFlag);
            context = itemView.getContext();

        }
    }
}
