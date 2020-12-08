package com.accountingsystem_android.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.accountingsystem_android.ApiClient;
import com.accountingsystem_android.ArrayAdapter.CategoryAdapter;
import com.accountingsystem_android.ArrayAdapter.UserAdapter;
import com.accountingsystem_android.R;
import com.accountingsystem_android.Response.CategoryResponse;
import com.accountingsystem_android.Response.UserResponse;

import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    UserResponse currentUser;
    List<UserResponse> allUsers;
    List<CategoryResponse> rootCategories;
    ListView allUsersListView;
    ListView rootCategoriesListView;
    Button refreshBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        allUsersListView = findViewById(R.id.allUsersListView);
        rootCategoriesListView = findViewById(R.id.rootCategoriesListView);

        setCurrentUser();
        setUsers();
        setRootCategories();

        refreshBtn = findViewById(R.id.refreshBtn);
        refreshBtn.setOnClickListener(this);

        allUsersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getApplicationContext(), UserReviewActivity.class).putExtra("userData", allUsers.get(position)));
            }
        });

        rootCategoriesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getApplicationContext(), CategoryReviewActivity.class).putExtra("categoryData", rootCategories.get(position)));
            }
        });
    }

    private void setRootCategories() {
        Call<List<CategoryResponse>> call = ApiClient.getCategoryService().getRootCategories();

        call.enqueue(new Callback<List<CategoryResponse>>() {
            @Override
            public void onResponse(Call<List<CategoryResponse>> call, Response<List<CategoryResponse>> response) {

                rootCategories = response.body();

                if (!currentUser.getUserType().equals("ADMIN")) {
                    rootCategories = filterOutUnaccessibleCategories(rootCategories);
                }
                CategoryAdapter categoryAdapter = new CategoryAdapter(getApplicationContext(), rootCategories);

                rootCategoriesListView.setAdapter(categoryAdapter);
            }

            @Override
            public void onFailure(Call<List<CategoryResponse>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private List<CategoryResponse> filterOutUnaccessibleCategories(List<CategoryResponse> categories) {

        return categories.stream().filter(category -> {
            for (UserResponse user : category.getResponsibleUsers()) {
                if (user.getUserID() == currentUser.getUserID()) return true;
            }
            return false;
        }).collect(Collectors.toList());
    }

    private void setUsers() {
        Call<List<UserResponse>> call = ApiClient.getUserService().getUsers();

        call.enqueue(new Callback<List<UserResponse>>() {
            @Override
            public void onResponse(Call<List<UserResponse>> call, Response<List<UserResponse>> response) {
                allUsers = response.body();

                if(!currentUser.getUserType().equals("ADMIN"))
                {
                    allUsers = filterOutOtherUsers(allUsers);
                }

                UserAdapter userAdapter = new UserAdapter(getApplicationContext(), allUsers);

                allUsersListView.setAdapter(userAdapter);
            }

            @Override
            public void onFailure(Call<List<UserResponse>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private List<UserResponse> filterOutOtherUsers(List<UserResponse> allUsers) {
        return allUsers.stream().filter(user -> user.getUserID() == currentUser.getUserID()).collect(Collectors.toList());
    }

    private void setCurrentUser() {
        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            currentUser = (UserResponse) intent.getSerializableExtra("userData");

            Log.e("TAG", " ---> " + currentUser.getName());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.refreshBtn:
                startActivity(new Intent(MainActivity.this, MainActivity.class).putExtra("userData", currentUser));
                break;

            default:
                break;
        }
    }
}