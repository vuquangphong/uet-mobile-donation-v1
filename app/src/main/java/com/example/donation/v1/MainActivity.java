package com.example.donation.v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.donation.v1.databinding.ActivityMainBinding;

public class MainActivity extends Base {

    private ActivityMainBinding binding;
    private ProgressBar progressBar;
    private TextView amountTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Button donateButton = binding.donateButton;
        RadioGroup paymentMethod = binding.paymentMethod;
        progressBar = binding.progressBar;
        amountTotal = binding.totalAmount;
        NumberPicker amountPicker = binding.amountPicker;

        binding.progressBar.setMax(DonationApp.MAX_DONATED_AMOUNT);

        binding.amountPicker.setMinValue(DonationApp.MIN_PICKER_AMOUNT);
        binding.amountPicker.setMaxValue(DonationApp.MAX_PICKER_AMOUNT);

        binding.progressBar.setProgress(DonationApp.totalDonated);
        binding.totalAmount.setText("$0");
    }

    public void donateButtonPressed(View view) {
        int amount = binding.amountPicker.getValue();
        int radioId = binding.paymentMethod.getCheckedRadioButtonId();

        String method = radioId == R.id.radioPayPal ? "PayPal" : "Direct";

        if (DonationApp.totalDonated + amount >= DonationApp.MAX_DONATED_AMOUNT) {
            DonationApp.totalDonated = DonationApp.MAX_DONATED_AMOUNT;
        } else {
            DonationApp.totalDonated += amount;

            Donation donation = new Donation(amount, method);
            DonationApp.donations.add(donation);
        }
        binding.progressBar.setProgress(DonationApp.totalDonated);

        String totalAmountStr = "$" + DonationApp.totalDonated;
        binding.totalAmount.setText(totalAmountStr);

        Log.v("Donate", "Donate Pressed " + amount + ", method: " + method);
        Log.v("Donate", "Current donated amount: " + DonationApp.totalDonated);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuReport:
                Toast toast1 = Toast.makeText(this, "Report Selected", Toast.LENGTH_SHORT);
                toast1.show();
                startActivity(new Intent(this, ReportActivity.class));
                break;

            case R.id.menuDonate:
                Toast toast2 = Toast.makeText(this, "Donate Selected", Toast.LENGTH_SHORT);
                toast2.show();
                startActivity(new Intent(this, MainActivity.class));
                break;

            default:
                Toast toast = Toast.makeText(this, "Setting click", Toast.LENGTH_SHORT);
                toast.show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void reset(MenuItem item) {
        app.dbManager.reset();
        app.totalDonated = 0;
        progressBar.setProgress(0);
        amountTotal.setText("$" + app.totalDonated);
    }
}