package com.igor.hamsteratlas.adapter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.igor.hamsteratlas.R;
import com.igor.hamsteratlas.activity.MainActivity;
import com.igor.hamsteratlas.model.Hamster;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HamsterListAdapter extends RecyclerView.Adapter {
    private List<Hamster> mHamsters;
    private MainActivity mActivity;
    private RecyclerView mRv;

    public static int VIEW_TYPE_PINNED = 0;
    public static int VIEW_TYPE_ALL = 1;

    public static String TAG = "HamsterListAdapter";

    public HamsterListAdapter(List<Hamster> hamsters, MainActivity activity, RecyclerView rv) {
        mHamsters = hamsters;
        mActivity = activity;
        mRv = rv;
    }

    @Override
    public int getItemViewType(int position) {
        Hamster hamster = mHamsters.get(position);

        if (hamster.isPinned()) {
            return VIEW_TYPE_PINNED;
        } else {
            return VIEW_TYPE_ALL;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ConstraintLayout v;
        if (viewType == VIEW_TYPE_PINNED) {
            v = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pinned_hamster, parent, false);
        } else {
            v = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hamster, parent, false);
        }
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Hamster hamster = mHamsters.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.mTvHamsterName.setText(hamster.getName());

    }

    @Override
    public int getItemCount() {
        return mHamsters.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @BindView(R.id.tv_hamster_name)
        TextView mTvHamsterName;


        @Override
        public void onClick(View view) {
            int itemPosition = mRv.getChildLayoutPosition(view);
            Hamster hamster = mHamsters.get(itemPosition);
            Bundle bundle = new Bundle();
            bundle.putParcelable("hamster", hamster);
            mActivity.getNavController().navigate(R.id.action_hamstersFragment_to_hamsterFragment, bundle);
        }
    }
}