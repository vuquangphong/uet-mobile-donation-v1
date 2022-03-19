package com.example.donation.v1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class MainActivity extends AppCompatActivity {
    private static final int MAX_DONATED_AMOUNT = 10000;
    private static final int MIN_PICKER_AMOUNT = 0;
    private static final int MAX_PICKER_AMOUNT = 1000;

    private Button donateButton;
    private RadioGroup paymentMethod;
    private ProgressBar progressBar;
    private NumberPicker amountPicker;

    private int totalDonated = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        donateButton = (Button) findViewById(R.id.donateButton);
        paymentMethod = (RadioGroup) findViewById(R.id.paymentMethod);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        amountPicker = (NumberPicker) findViewById(R.id.amountPicker);

        progressBar.setMax(MAX_DONATED_AMOUNT);

        amountPicker.setMinValue(MIN_PICKER_AMOUNT);
        amountPicker.setMaxValue(MAX_PICKER_AMOUNT);
    }

    public void donateButtonPressed(View view) {
        int amount = amountPicker.getValue();
        int radioId = paymentMethod.getCheckedRadioButtonId();

        String method = radioId == R.id.radioPayPal ? "PayPal" : "Direct";

        if (totalDonated + amount >= MAX_DONATED_AMOUNT) {
            totalDonated = MAX_DONATED_AMOUNT;
        } else {
            totalDonated += amount;
        }
        progressBar.setProgress(totalDonated);

        Log.v("Donate", "Donate Pressed " + amount + ", method: " + method);
        Log.v("Donate", "Current donated amount: " + totalDonated);
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
                Toast toast = Toast.makeText(this, "Report Selected", Toast.LENGTH_SHORT);
                toast.show();
                break;

            default:
                Toast toast1 = Toast.makeText(this, "Setting click", Toast.LENGTH_SHORT);
                toast1.show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}