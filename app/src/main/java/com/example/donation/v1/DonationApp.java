package com.example.donation.v1;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DonationApp extends Application {
    public static final int MAX_DONATED_AMOUNT = 10000;
    public static final int MIN_PICKER_AMOUNT = 0;
    public static final int MAX_PICKER_AMOUNT = 1000;
    public static int totalDonated = 0;
    public static List<Donation> donations = new ArrayList<>();

    public DbManager dbManager;

    public boolean newDonation(Donation donation) {
        boolean targetAchieved = totalDonated > MAX_DONATED_AMOUNT;
        if (!targetAchieved)
        {
            dbManager.add(donation);
            totalDonated += donation.getAmount();
        } else {
            Toast.makeText(this, "Target Exceeded!", Toast.LENGTH_SHORT).show();
        }
        return targetAchieved;
    }

    @Override
    public void onCreate() {
        super .onCreate();
        Log.v("Donation", "Donation App Started");
        dbManager = new DbManager(this);
        Log.v("Donate", "Database Created");
    }
}
