package com.example.mysqltarea;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ClientAdapter extends ArrayAdapter<Client> {
    private Context context;
    private List<Client> clientList;

    public ClientAdapter(Context context, List<Client> clientList) {
        super(context, 0, clientList);
        this.context = context;
        this.clientList = clientList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        }

        Client currentClient = clientList.get(position);

        TextView nameTextView = listItemView.findViewById(R.id.nameTextView);
        TextView ageTextView = listItemView.findViewById(R.id.ageTextView);

        nameTextView.setText(currentClient.getName());
        ageTextView.setText(String.valueOf(currentClient.getAge()));



        return listItemView;
    }
}

