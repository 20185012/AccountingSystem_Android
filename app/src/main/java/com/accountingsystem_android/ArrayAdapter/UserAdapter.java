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
import com.accountingsystem_android.Response.UserResponse;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends ArrayAdapter<UserResponse> {

    Context applicationContext;
    List<UserResponse> usersList = new ArrayList<>();

    public UserAdapter(@NonNull Context context, @NonNull List<UserResponse> objects) {
        super(context, 0, objects);
        applicationContext = context;
        usersList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;

        if(listItem == null)
        {
            listItem = LayoutInflater.from(applicationContext).inflate(R.layout.activity_userrow, parent, false);
        }

        UserResponse user = usersList.get(position);

        TextView userUsernameText = listItem.findViewById(R.id.userUsername);
        TextView userUserType = listItem.findViewById(R.id.userUserType);

        userUsernameText.setText(user.getUsername());
        userUserType.setText(user.getUserType());

        return listItem;
    }
}
