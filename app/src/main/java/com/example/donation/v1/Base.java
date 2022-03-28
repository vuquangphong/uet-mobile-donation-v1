package com.example.donation.v1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Base extends AppCompatActivity {
    public DonationApp app;
    public DbManager dbManager;
    private TextView amountTotal;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (DonationApp) getApplication();
        app.dbManager.open();
        app.dbManager.setTotalDonated(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        app.dbManager.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem report = menu.findItem(R.id.menuReport);
        MenuItem donate = menu.findItem(R.id.menuDonate);
        MenuItem reset = menu.findItem(R.id.menuReset);

        if (app.dbManager.getAll().isEmpty()) {
            report.setEnabled(false);
            reset.setEnabled(false);
        } else {
            report.setEnabled(true);
            reset.setEnabled(true);
        }
        if(this instanceof MainActivity) {
            donate.setVisible(false);
            if (!app.dbManager.getAll().isEmpty()) {
                report.setVisible(true);
                reset.setEnabled(true); }
        } else {
            report.setVisible(false);
            donate.setVisible(true);
            reset.setVisible(true);
        }
        return true;
    }

    public void settings(MenuItem item) {
        Toast.makeText(this, "Settings Selected", Toast.LENGTH_SHORT).show(); }
    public void report(MenuItem item) {
        startActivity (new Intent(this, ReportActivity.class)); }
    public void donate(MenuItem item) {
        startActivity (new Intent(this, MainActivity.class)); }
    public void reset(MenuItem item) {
        app.dbManager.reset();
        app.totalDonated = 0;
        progressBar.setProgress(0);
        amountTotal.setText("$" + app.totalDonated);
    }

}
