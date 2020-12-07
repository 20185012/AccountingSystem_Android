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
import com.accountingsystem_android.Response.ReceivableResponse;

import java.util.List;

public class ReceivableAdapter extends ArrayAdapter<ReceivableResponse> {

    Context applicationContext;
    List<ReceivableResponse> receivablesList;

    public ReceivableAdapter(@NonNull Context context, @NonNull List<ReceivableResponse> objects) {
        super(context, 0, objects);
        applicationContext = context;
        receivablesList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItem = convertView;

        if(listItem == null)
        {
            listItem = LayoutInflater.from(applicationContext).inflate(R.layout.activity_receivablerow, parent, false);
        }

        ReceivableResponse receivable = receivablesList.get(position);

        TextView receivableDateTxt = listItem.findViewById(R.id.receivableDate);
        TextView receivableSumTxt = listItem.findViewById(R.id.receivableSum);

        receivableDateTxt.setText(receivable.getReceivableDate());
        receivableSumTxt.setText(Float.toString(receivable.getReceivableSum()));

        return listItem;
    }
}
