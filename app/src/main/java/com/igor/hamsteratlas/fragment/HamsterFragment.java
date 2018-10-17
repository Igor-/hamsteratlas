package com.igor.hamsteratlas.fragment;


import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    private Hamster mHamster;

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
        setHasOptionsMenu(true);
        Bundle bundle = getArguments();
        if(bundle != null) {
            mHamster = bundle.getParcelable("hamster");
            if(mHamster != null) {
                Picasso.get().load(mHamster.getImage()).fit().centerCrop().error(mActivity.getResources().getDrawable(R.drawable.placeholder)).into(mIvHamster);
                mTvHamsterName.setText(mHamster.getName());
                mTvHamsterDescr.setText(mHamster.getDescription());
            }
        }

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.hamster_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_item_share: {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = mHamster.getDescription();
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, mHamster.getName());
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, getString(R.string.share_via)));
                break;

            }
            case R.id.menu_item_about: {
                mActivity.getNavController().navigate(R.id.action_global_aboutFragment);
                break;
            }

        }
        return super.onOptionsItemSelected(item);
    }
}
