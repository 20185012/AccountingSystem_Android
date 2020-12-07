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
import com.accountingsystem_android.Response.SubCategoryResponse;

import java.util.List;

public class SubCategoryAdapter extends ArrayAdapter<SubCategoryResponse> {
    Context applicationContext;
    List<SubCategoryResponse> subCategoriesList;

    public SubCategoryAdapter(@NonNull Context context, @NonNull List<SubCategoryResponse> objects) {
        super(context, 0, objects);
        applicationContext = context;
        subCategoriesList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;

        if (listItem == null) {
            listItem = LayoutInflater.from(applicationContext).inflate(R.layout.activity_categoryrow, parent, false);
        }

        SubCategoryResponse category = subCategoriesList.get(position);
        TextView subCategoryName = listItem.findViewById(R.id.categoryName);

        subCategoryName.setText(category.getName());

        return listItem;
    }
}
