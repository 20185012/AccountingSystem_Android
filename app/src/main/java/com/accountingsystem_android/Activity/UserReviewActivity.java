package com.accountingsystem_android.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.TextView;

import com.accountingsystem_android.R;
import com.accountingsystem_android.Response.UserResponse;

public class UserReviewActivity extends AppCompatActivity {

    TextView popUpNameTxt;
    TextView popUpEmailTxt;
    TextView popUpPhoneTxt;
    TextView popUpUsernameTxt;
    TextView popUpPasswordTxt;

    UserResponse userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userreview);

        makePopUpWindow();

        popUpNameTxt = findViewById(R.id.popUpName);
        popUpEmailTxt = findViewById(R.id.popUpEmail);
        popUpPhoneTxt = findViewById(R.id.popUpPhone);
        popUpUsernameTxt = findViewById(R.id.popUpUsername);
        popUpPasswordTxt = findViewById(R.id.popUpPassword);

        userData = getUserData();

        popUpNameTxt.setText(userData.getName());
        popUpEmailTxt.setText(userData.getEmail());
        popUpPhoneTxt.setText(userData.getPhone());
        popUpUsernameTxt.setText(userData.getUsername());
        popUpPasswordTxt.setText(userData.getPassword());
    }

    private UserResponse getUserData() {
        Intent intent = getIntent();
        if (intent.getExtras() != null) return (UserResponse) intent.getSerializableExtra("userData");
        return null;
    }

    private void makePopUpWindow() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        getWindow().setLayout((int)(width*.9),(int)(height*.9));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;

        params.x = 0;
        params.y = -20;

        getWindow().setAttributes(params);
    }
}