package com.igor.hamsteratlas.fragment;


import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.igor.hamsteratlas.R;
import com.igor.hamsteratlas.activity.MainActivity;
import com.igor.hamsteratlas.db.AppDatabase;
import com.igor.hamsteratlas.model.Hamster;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class HamsterFragment extends Fragment {


    public HamsterFragment() {
        // Required empty public constructor
    }

    private MainActivity mActivity;

    @BindView(R.id.iv_hamster)
    ImageView mIvHamster;

    @BindView(R.id.tv_hamster_name)
    TextView mTvHamsterName;

    @BindView(R.id.tv_hamster_descr)
    TextView mTvHamsterDescr;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_hamster, container, false);
        ButterKnife.bind(this, v);
        mActivity = (MainActivity) getActivity();
        Bundle bundle = getArguments();
        if(bundle != null) {
            Hamster hamster = bundle.getParcelable("hamster");
            if(hamster != null) {
                Picasso.get().load(hamster.getImage()).fit().centerCrop().error(mActivity.getResources().getDrawable(R.drawable.placeholder)).into(mIvHamster);
                mTvHamsterName.setText(hamster.getName());
                mTvHamsterDescr.setText(hamster.getDescription());

            }
        }

        return v;
    }

}
