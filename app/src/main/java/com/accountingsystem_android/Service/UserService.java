package com.accountingsystem_android.Service;

import com.accountingsystem_android.Request.LoginRequest;
import com.accountingsystem_android.Response.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;

public interface UserService {

    @POST("/users/login")
    Call<UserResponse> loginUser(@Body LoginRequest loginRequest);

    @GET("/users")
    Call<List<UserResponse>> getUsers();
}
