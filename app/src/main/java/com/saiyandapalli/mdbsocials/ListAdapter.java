package com.saiyandapalli.mdbsocials;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.CustomViewHolder> {

    private Context context;
    private ArrayList<Social> data;

    public ListAdapter(Context context, ArrayList<Social> data) {
        this.context = context;
        this.data = data;
    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_view, parent, false);
        return new CustomViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final CustomViewHolder holder, int position) {
        Social m = data.get(position);
        holder.emailView.setText(m.email);
        holder.nameView.setText(m.name);
        holder.interestView.setText(m.interest);
        holder.descriptionView.setText(m.description);

        //haven't taught this yet but essentially it runs separately from the UI
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child(m.firebaseImageUrl + ".png");
//        Glide.with(context).using(new FirebaseImageLoader()).load(storageReference).into(holder.imageView);
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    /**
     * A card displayed in the RecyclerView
     */
    class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView msgView;
        ImageView imageView;

        public CustomViewHolder (View view) {
            super(view);
            this.msgView = (TextView) view.findViewById(R.id.interestView);
            this.imageView = (ImageView) view.findViewById(R.id.imageView);
        }
    }
}
