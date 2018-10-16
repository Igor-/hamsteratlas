package com.igor.hamsteratlas.network;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ApiService {
    @GET("test3")
    List<Hamster> getHamsters(@Header("X-Homo-Client-OS") String clientOs, @Header("X-Homo-Client-Version") String clientVersion, @Header("X-Homo-Client-Model") String clientModel );

}