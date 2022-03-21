package com.example.donation.v1;

import androidx.annotation.NonNull;
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
import android.widget.Toast;

import com.example.donation.v1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Button donateButton = binding.donateButton;
        RadioGroup paymentMethod = binding.paymentMethod;
        ProgressBar progressBar = binding.progressBar;
        NumberPicker amountPicker = binding.amountPicker;

        binding.progressBar.setMax(History.MAX_DONATED_AMOUNT);

        binding.amountPicker.setMinValue(History.MIN_PICKER_AMOUNT);
        binding.amountPicker.setMaxValue(History.MAX_PICKER_AMOUNT);

        binding.progressBar.setProgress(History.totalDonated);
    }

    public void donateButtonPressed(View view) {
        int amount = binding.amountPicker.getValue();
        int radioId = binding.paymentMethod.getCheckedRadioButtonId();

        String method = radioId == R.id.radioPayPal ? "PayPal" : "Direct";

        if (History.totalDonated + amount >= History.MAX_DONATED_AMOUNT) {
            History.totalDonated = History.MAX_DONATED_AMOUNT;
        } else {
            History.totalDonated += amount;

            Donation donation = new Donation(amount, method);
            History.donations.add(donation);
        }
        binding.progressBar.setProgress(History.totalDonated);

        Log.v("Donate", "Donate Pressed " + amount + ", method: " + method);
        Log.v("Donate", "Current donated amount: " + History.totalDonated);
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
}