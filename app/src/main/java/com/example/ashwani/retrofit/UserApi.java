package com.example.ashwani.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public class UserApi {
    private static final String baseUrl="http://45.55.53.57:3000/";

    public static UserService userService = null;
    //to implement singleton pattern
    public static UserService getUserService(){

        if(userService==null){
            Retrofit retrofit=new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            userService = retrofit.create(UserService.class);
        }
        return userService;
    }

    public interface UserService{
        @GET("data/user")
        Call<List<UserListPojo>> getUserList();

        @POST("data/signup")
        @Headers("Content-type: application/json")
        Call<Resp> postUser(@Body UserListPojo user);
    }
}
