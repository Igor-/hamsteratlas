package com.igor.hamsteratlas.fragment;


import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.igor.hamsteratlas.R;
import com.igor.hamsteratlas.activity.MainActivity;
import com.igor.hamsteratlas.adapter.HamsterListAdapter;
import com.igor.hamsteratlas.db.AppDatabase;
import com.igor.hamsteratlas.network.ApiService;
import com.igor.hamsteratlas.model.Hamster;
import com.igor.hamsteratlas.network.RetrofitClient;
import com.igor.hamsteratlas.utils.ClientInfo;

import java.util.ArrayList;
import java.util.List;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class HamstersFragment extends Fragment implements View.OnClickListener{


    public HamstersFragment() {
        // Required empty public constructor
    }

    private ApiService mApiService;
    private MainActivity mActivity;
    private HamsterListAdapter mHamsterListAdapter;
    private AppDatabase mDb;
    private List<Hamster> mHamsters;

    @BindView(R.id.rv_hamsters)
    RecyclerView mRvHamsters;

    @BindView(R.id.et_search)
    EditText mEtSearch;

    @BindView(R.id.btn_search)
    Button mBtnSearch;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_hamsters, container, false);
        ButterKnife.bind(this, v);
        mActivity = (MainActivity)getActivity();
        mRvHamsters.setLayoutManager(new LinearLayoutManager(mActivity));
        mHamsters = new ArrayList<>();
        mHamsterListAdapter = new HamsterListAdapter(mHamsters, mActivity, mRvHamsters);
        mRvHamsters.setAdapter(mHamsterListAdapter);
        mDb = Room.databaseBuilder(mActivity.getApplicationContext(), AppDatabase.class, "hamsteratlas_db").allowMainThreadQueries().build();
        mApiService = RetrofitClient.getInstance().getApiService();
        mBtnSearch.setOnClickListener(this);
        setHasOptionsMenu(true);
        Call<List<Hamster>> hamstersCall = mApiService.getHamsters(ClientInfo.getOsVersion(), ClientInfo.getClientVersion(mActivity), ClientInfo.getDeviceInfo());

        hamstersCall.enqueue(new Callback<List<Hamster>>() {
            @Override
            public void onResponse(Call<List<Hamster>> call, Response<List<Hamster>> response) {
                List<Hamster> responseHamsters = response.body();
                if(responseHamsters != null && responseHamsters.size() > 0) {
                    //TODO: remove duplications with onFailure
                    mDb.hamsterDao().deleteAll();
                    mDb.hamsterDao().insertHamsters(responseHamsters);
                    mHamsters.clear();
                    mHamsters.addAll(mDb.hamsterDao().pinned());
                    mHamsters.addAll(mDb.hamsterDao().notPinned());
                    mHamsterListAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Hamster>> call, Throwable t) {
                mHamsters.clear();
                mHamsters.addAll(mDb.hamsterDao().pinned());
                mHamsters.addAll(mDb.hamsterDao().notPinned());
                mHamsterListAdapter.notifyDataSetChanged();

            }
        });

        return v;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.btn_search: {
                mHamsters.clear();
                String searchQuery = mEtSearch.getText().toString();
                if (searchQuery == null || searchQuery.equals("")) {
                    mHamsters.addAll(mDb.hamsterDao().pinned());
                    mHamsters.addAll(mDb.hamsterDao().notPinned());
                } else {
                    mHamsters.addAll(mDb.hamsterDao().findByString("%" + searchQuery + "%"));
                }
                mHamsterListAdapter.notifyDataSetChanged();
                break;
            }
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.hamsters_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_about: {
                mActivity.getNavController().navigate(R.id.action_global_aboutFragment);
                break;
            }

        }
        return super.onOptionsItemSelected(item);
    }
}
