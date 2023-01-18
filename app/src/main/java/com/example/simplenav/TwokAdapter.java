package com.example.simplenav;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class  TwokAdapter extends RecyclerView.Adapter<TwokViewHolder> {
    private LayoutInflater inflater;
    private List<Twok> twoks;

    public TwokAdapter(Context context, List<Twok> twoks) {
        inflater = LayoutInflater.from(context);
        this.twoks = twoks;
    }

    @NonNull
    @Override
    public TwokViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.single_row, parent, false);
        return new TwokViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TwokViewHolder holder, int position) {
        Twok twokToShow = twoks.get(position);
        holder.updateContent(twokToShow);
    }

    @Override
    public int getItemCount() {
        return twoks.size();
    }
}
