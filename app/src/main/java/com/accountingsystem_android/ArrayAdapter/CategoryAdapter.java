package com.accountingsystem_android.ArrayAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.accountingsystem_android.R;
import com.accountingsystem_android.Response.CategoryResponse;
import com.accountingsystem_android.Response.UserResponse;

import java.util.List;

public class CategoryAdapter extends ArrayAdapter<CategoryResponse> {

    Context applicationContext;
    List<CategoryResponse> categoriesList;

    public CategoryAdapter(@NonNull Context context, @NonNull List<CategoryResponse> objects) {
        super(context, 0, objects);
        applicationContext = context;
        categoriesList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;

        if (listItem == null) {
            listItem = LayoutInflater.from(applicationContext).inflate(R.layout.activity_categoryrow, parent, false);
        }

        CategoryResponse category = categoriesList.get(position);

        TextView rootCategoryName = listItem.findViewById(R.id.categoryName);

        rootCategoryName.setText(category.getCategoryName());

        return listItem;
    }
}
