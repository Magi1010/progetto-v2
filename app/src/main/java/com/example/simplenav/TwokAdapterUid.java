package com.example.simplenav;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TwokAdapterUid extends RecyclerView.Adapter<TwokViewHolderUid> {
    private LayoutInflater inflater;
    private List<Twok> twoks;

    public TwokAdapterUid(Context context, List<Twok> twoks) {
        inflater = LayoutInflater.from(context);
        this.twoks = twoks;
    }

    @NonNull
    @Override
    public TwokViewHolderUid onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.single_row2, parent, false);
        return new TwokViewHolderUid(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TwokViewHolderUid holder, int position) {
        Twok twokToShow = twoks.get(position);
        holder.updateContent(twokToShow);
    }


    @Override
    public int getItemCount() {
        return twoks.size();
    }
}
