package com.studio.bin.kqxsvip.router;

import com.studio.bin.kqxsvip.entity.KqxsEntity;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {

    @GET("get-kqxs")
    Call<KqxsEntity> getKQXS();
}
