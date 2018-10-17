package com.igor.hamsteratlas.network;

import com.igor.hamsteratlas.model.Hamster;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ApiService {
    @GET("test3")
    Call<List<Hamster>> getHamsters(@Header("X-Homo-Client-OS") String clientOs, @Header("X-Homo-Client-Version") String clientVersion, @Header("X-Homo-Client-Model") String clientDeviceInfo );

}