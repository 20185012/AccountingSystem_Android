package com.accountingsystem_android.Service;

import com.accountingsystem_android.Response.CategoryResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryService {

    @GET("/categories/0")
    Call<List<CategoryResponse>> getRootCategories();
}
