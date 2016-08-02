package com.example.bayar.usingrecycler.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bayar.usingrecycler.R;
import com.example.bayar.usingrecycler.model.Landscape;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LandscapeAdapter extends RecyclerView.Adapter<LandscapeAdapter.LandscapeViewHolder>{

    private static final String TAG = "LandscapeAdapter";
    private List<Landscape> mLandscapeList;

    public LandscapeAdapter(List<Landscape> landscapeList) {
        mLandscapeList = landscapeList;
    }

    @Override
    public LandscapeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new LandscapeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LandscapeViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder " + position);
        Landscape currentLandscape = mLandscapeList.get(position);
        holder.setData(currentLandscape, position);
        holder.setListeners();
    }

    @Override
    public int getItemCount() {
        return mLandscapeList.size();
    }

    public void removeItem(int position) {
        mLandscapeList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mLandscapeList.size());
    }

    public void addItem(int position, Landscape landscape) {
        mLandscapeList.add(position, landscape);
        notifyItemInserted(position);
        notifyItemRangeChanged(position, mLandscapeList.size());
    }

    public class LandscapeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.item_iv)
        ImageView mItemImageView;
        @BindView(R.id.item_title)
        TextView mItemTitle;
        @BindView(R.id.item_desc)
        TextView mItemDesc;
        @BindView(R.id.item_ic_delete)
        ImageView mItemIconDelete;
        @BindView(R.id.item_ic_make_copy)
        ImageView mItemIconMakeCopy;

        int currentPosition;
        Landscape currentLandscape;

        public LandscapeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(Landscape currentLandscape, int position) {
            mItemImageView.setImageResource(currentLandscape.getImageId());
            mItemTitle.setText(currentLandscape.getTitle());
            mItemDesc.setText(currentLandscape.getDescription());
            this.currentPosition = position;
            this.currentLandscape = currentLandscape;
        }

        public void setListeners() {
            mItemIconDelete.setOnClickListener(LandscapeViewHolder.this);
            mItemIconMakeCopy.setOnClickListener(LandscapeViewHolder.this);
        }

        @Override
        public void onClick(View view) {
            Log.d(TAG, "onClick before operation on position: "
                    + currentPosition + " size: " + mLandscapeList.size());
            int id = view.getId();
            switch (id) {
                case R.id.item_ic_delete:
                    removeItem(currentPosition);
                    break;
                case R.id.item_ic_make_copy:
                    addItem(currentPosition, currentLandscape);
                    break;
            }
            Log.d(TAG, "onClick after operation - size: " + mLandscapeList.size() + "\n\n" + mLandscapeList.toString());
        }
    }
}
