package com.example.ph26725.api;

import com.example.ph26725.model.ReceThongTin;
import com.example.ph26725.model.ThongTin;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiTT {

    Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyy").create();

    ApiTT apiTt = new Retrofit.Builder()
            .baseUrl("http://192.168.1.8:3000/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiTT.class);

    @GET("users")
    Call<ReceThongTin> getData();

    @POST("users")
    Call<ThongTin> postData(@Body ThongTin thongTin);

    @PUT("users/update/{id}")
    Call<ThongTin> putData( @Path("id") String id ,@Body ThongTin thongTin);

    @DELETE("users/delete/{id}")
    Call<ThongTin> deleteData(@Path("id") String id);

}
