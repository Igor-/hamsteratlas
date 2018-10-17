package com.igor.hamsteratlas.adapter;

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

    public static String TAG = "HamsterListAdapter";

    public HamsterListAdapter(List<Hamster> hamsters, MainActivity activity, RecyclerView rv) {
        mHamsters = hamsters;
        mActivity = activity;
        mRv = rv;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ConstraintLayout v = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hamster, parent, false);
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
            Log.d(TAG, "click");
        }
    }
}