package com.example.donation.v1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class DonationAdapter extends ArrayAdapter<Donation> {
    private Context context;
    private List<Donation> donations;

    public DonationAdapter(@NonNull Context context, List<Donation> donations) {
        super(context, R.layout.row_donation, donations);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.row_donation, parent, true);

        Donation donation = donations.get(position);
        TextView amountView = (TextView) view.findViewById(R.id.row_amount);
        TextView methodView = (TextView) view.findViewById(R.id.row_method);

        amountView.setText("$" + donation.getAmount());
        methodView.setText(donation.getMethod());

        return view;
    }
}
