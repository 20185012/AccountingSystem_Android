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
import com.accountingsystem_android.Response.PaymentResponse;

import java.util.List;

public class PaymentAdapter extends ArrayAdapter<PaymentResponse> {

    Context applicationContext;
    List<PaymentResponse> paymentsList;

    public PaymentAdapter(@NonNull Context context, @NonNull List<PaymentResponse> objects) {
        super(context, 0, objects);
        applicationContext = context;
        paymentsList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;

        if(listItem == null)
        {
            listItem = LayoutInflater.from(applicationContext).inflate(R.layout.activity_paymentrow, parent, false);
        }

        PaymentResponse payment = paymentsList.get(position);

        TextView paymentDateTxt = listItem.findViewById(R.id.paymentDate);
        TextView paymentSumTxt = listItem.findViewById(R.id.paymentSum);

        paymentDateTxt.setText(payment.getPaymentDate());
        paymentSumTxt.setText(Float.toString(payment.getPaymentSum()));

        return listItem;

    }
}
