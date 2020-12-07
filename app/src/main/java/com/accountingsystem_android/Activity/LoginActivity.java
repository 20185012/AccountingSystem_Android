package com.accountingsystem_android.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.accountingsystem_android.ApiClient;
import com.accountingsystem_android.R;
import com.accountingsystem_android.Request.LoginRequest;
import com.accountingsystem_android.Response.UserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBtn = findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.loginBtn:
                    processRegisterButtonClick();
                break;

            default:
                break;
        }
    }

    private void processRegisterButtonClick() {
        EditText usernameTextField = findViewById(R.id.loginUsernameTxt);
        EditText passwordTextField = findViewById(R.id.loginPasswordTxt);

        if (TextUtils.isEmpty(usernameTextField.getText()) || TextUtils.isEmpty(passwordTextField.getText()))
        {
            Toast.makeText(getApplicationContext(), "For fucks sake, dont leave empty inputs", Toast.LENGTH_LONG).show();
        }
        else
        {
            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setUsername(usernameTextField.getText().toString());
            loginRequest.setPassword(passwordTextField.getText().toString());

            loginUser(loginRequest);
        }


    }

    private void loginUser(LoginRequest loginRequest) {
        Call<UserResponse> loginResponseCall = ApiClient.getUserService().loginUser(loginRequest);

        loginResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if(response.isSuccessful())
                {
                    UserResponse userResponse = response.body();
                    startActivity( new Intent(LoginActivity.this, MainActivity.class).putExtra("userData",userResponse));
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Login details are incorrect",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}