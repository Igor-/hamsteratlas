package com.igor.hamsteratlas.fragment;


import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.igor.hamsteratlas.R;
import com.igor.hamsteratlas.activity.MainActivity;
import com.igor.hamsteratlas.adapter.HamsterListAdapter;
import com.igor.hamsteratlas.db.AppDatabase;
import com.igor.hamsteratlas.network.ApiService;
import com.igor.hamsteratlas.model.Hamster;
import com.igor.hamsteratlas.network.RetrofitClient;
import com.igor.hamsteratlas.utils.ClientInfo;

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
public class HamstersFragment extends Fragment {


    public HamstersFragment() {
        // Required empty public constructor
    }

    private ApiService mApiService;
    private MainActivity mActivity;
    private HamsterListAdapter mHamsterListAdapter;
    private AppDatabase mDb;

    @BindView(R.id.rv_hamsters)
    RecyclerView mRvHamsters;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_hamsters, container, false);
        ButterKnife.bind(this, v);
        mActivity = (MainActivity)getActivity();
        mRvHamsters.setLayoutManager(new LinearLayoutManager(mActivity));

        mDb = Room.databaseBuilder(mActivity.getApplicationContext(), AppDatabase.class, "hamsteratlas_db").allowMainThreadQueries().build();
        mApiService = RetrofitClient.getInstance().getApiService();

        Call<List<Hamster>> hamstersCall = mApiService.getHamsters(ClientInfo.getOsVersion(), ClientInfo.getClientVersion(mActivity), ClientInfo.getDeviceInfo());

        hamstersCall.enqueue(new Callback<List<Hamster>>() {
            @Override
            public void onResponse(Call<List<Hamster>> call, Response<List<Hamster>> response) {
                List<Hamster> hamsters = response.body();
                if(hamsters != null && hamsters.size() > 0) {
                    mHamsterListAdapter = new HamsterListAdapter(hamsters, mActivity, mRvHamsters);
                    mRvHamsters.setAdapter(mHamsterListAdapter);
                    mDb.hamsterDao().deleteAll();
                    mDb.hamsterDao().insertHamsters(hamsters);
                }
            }

            @Override
            public void onFailure(Call<List<Hamster>> call, Throwable t) {
                List<Hamster> hamsters = mDb.hamsterDao().pinned();
                mHamsterListAdapter = new HamsterListAdapter(hamsters, mActivity, mRvHamsters);
                mRvHamsters.setAdapter(mHamsterListAdapter);

            }
        });

        return v;
    }

}
