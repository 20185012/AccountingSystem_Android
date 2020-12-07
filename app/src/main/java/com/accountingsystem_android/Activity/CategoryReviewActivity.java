package com.accountingsystem_android.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import com.accountingsystem_android.ArrayAdapter.CategoryAdapter;
import com.accountingsystem_android.ArrayAdapter.PaymentAdapter;
import com.accountingsystem_android.ArrayAdapter.ReceivableAdapter;
import com.accountingsystem_android.ArrayAdapter.SubCategoryAdapter;
import com.accountingsystem_android.ArrayAdapter.UserAdapter;
import com.accountingsystem_android.R;
import com.accountingsystem_android.Response.CategoryResponse;
import com.accountingsystem_android.Response.ReceivableResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CategoryReviewActivity extends Activity {

    TextView categoryNameTxt;
    ListView incomesListView;
    ListView expensesListView;
    ListView responsibleUsersListView;
    ListView subCategoriesListView;

    CategoryResponse categoryData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoryreview);

        makePopUpWindow();
        categoryData = getCategoryData();

        categoryNameTxt = findViewById(R.id.popUpCategoryName);
        categoryNameTxt.setText(categoryData.getCategoryName());


        populateIncomesListView();
        populateExpensesListView();
        populateResponsibleUsersListView();
        populateCategoriesListView();

        getCategoryData();
    }

    private void populateCategoriesListView() {
        subCategoriesListView = findViewById(R.id.subCategoriesListView);

        subCategoriesListView.setAdapter( new SubCategoryAdapter(getApplicationContext(), categoryData.getSubCategories()));
    }

    private void populateResponsibleUsersListView() {
        responsibleUsersListView = findViewById(R.id.responsibleUsersListView);
        responsibleUsersListView.setAdapter( new UserAdapter(getApplicationContext(), categoryData.getResponsibleUsers()));
    }

    private void populateExpensesListView() {
        expensesListView = findViewById(R.id.expensesListView);

        expensesListView.setAdapter( new PaymentAdapter(getApplicationContext(), categoryData.getPayments()));
    }

    private void populateIncomesListView() {
        incomesListView = findViewById(R.id.incomesListView);

        incomesListView.setAdapter( new ReceivableAdapter(getApplicationContext(), categoryData.getReceivables()));
    }

    private CategoryResponse getCategoryData() {
        Intent intent = getIntent();
        if(intent.getExtras() != null) return (CategoryResponse) intent.getSerializableExtra("categoryData");
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