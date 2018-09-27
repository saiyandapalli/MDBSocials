package com.saiyandapalli.mdbsocials;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.CustomViewHolder> {

    private Context context;
    private ArrayList<Social> data;
    private int focusedItemIndex = -1;
    final int NOTEXPANDED = 0;
    final int EXPANDED = 1;
    private int adapterPosition;
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("/socials");

    public ListAdapter(Context context, ArrayList<Social> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_view, parent, false);
//        Log.d("os", ""+viewType);
//        if (viewType == EXPANDED){
//            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_view, parent, false);
//        }
        return new CustomViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final CustomViewHolder holder, final int position) {
//        holder.container.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(position == focusedItemIndex){
//                    focusedItemIndex = -1;
//                }else{
//                    focusedItemIndex = position;
//                    notifyDataSetChanged();
//                }
//            }
//        });


        final Social m = data.get(position);
        holder.emailView.setText(m.email);
        holder.nameView.setText(m.name);
        holder.interestView.setText(context.getString(R.string.interested, m.interest));
        holder.descriptionView.setText(m.description);
        holder.interestCheckView.setChecked(m.interested);
        if (position == focusedItemIndex) {
            ((TextView) holder.container.findViewById(R.id.descriptionView)).setText(m.description);
        }
        holder.interestCheckView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    m.interested = true;
                    m.interest = m.interest + 1;
                }else{
                    m.interested = false;
                    m.interest = m.interest - 1;
                }
//                ref.child("socials").child(m.firebaseImageUrl).child("interested").setValue(m.interested);
//                ref.child("socials").child(m.firebaseImageUrl).child("interest").setValue(m.interest);
                notifyDataSetChanged();
            }
        });

        //haven't taught this yet but essentially it runs separately from the UI
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child(m.firebaseImageUrl + ".png");
        Glide.with(context).using(new FirebaseImageLoader()).load(storageReference).into(holder.imageView);
    }


    @Override
    public int getItemViewType(int position) {
        return (position == focusedItemIndex) ? EXPANDED : NOTEXPANDED;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public int getAdapterPosition() {
        return adapterPosition;
    }

    /**
     * A card displayed in the RecyclerView
     */
    class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView emailView;
        TextView nameView;
        TextView interestView;
        View container;
        ImageView imageView;
        TextView descriptionView;
        CheckBox interestCheckView;
        boolean expanded = false;
        boolean start = true;


        public CustomViewHolder (View view) {
            super(view);
            this.emailView = (TextView) view.findViewById(R.id.emailView);
            this.container = view.findViewById(R.id.container);
            this.nameView = (TextView) view.findViewById(R.id.emailloginView);
            this.interestView = (TextView) view.findViewById(R.id.interestView);
            this.imageView = (ImageView) view.findViewById(R.id.imageView);
            this.descriptionView = (TextView) view.findViewById(R.id.descriptionView);
            this.interestCheckView = (CheckBox) view.findViewById(R.id.interestCheckView);
            interestCheckView.setVisibility(View.GONE);
            descriptionView.setVisibility(View.GONE);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(expanded) {
                        expanded = false;
                        start = false;
                        interestCheckView.setVisibility(View.GONE);
                        descriptionView.setVisibility(View.GONE);
                    } else {
                        expanded = true;
                        start = false;
                        interestCheckView.setVisibility(View.VISIBLE);
                        descriptionView.setVisibility(View.VISIBLE);
                    }
                    notifyDataSetChanged();
                }
            });


        }
//    }
//    // sparse boolean array for checking the state of the items
//    private SparseBooleanArray itemStateArray= new SparseBooleanArray();
//
//    @Override
//    public void onClick(View v) {
//        int adapterPosition = getAdapterPosition();
//        if (!itemStateArray.get(adapterPosition, false)) {
//            ((TextView) v.findViewById(R.id.interestCheckView)).setChecked(true);
//            itemStateArray.put(adapterPosition, true);
//        } else {
//            ((TextView) v.findViewById(R.id.interestCheckView)).setChecked(false);
//            itemStateArray.put(adapterPosition, false);
//        }
    }
}
