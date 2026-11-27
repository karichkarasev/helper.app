package org.buterblade.thehelperapp.retrofit;

import org.buterblade.thehelperapp.model.UserEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserEntityApi {

    @GET("/users/get-all")
    Call<List<UserEntity>> getAllUsers();

    @POST("/users/save")
    Call<UserEntity> save(@Body UserEntity userEntity);
}
