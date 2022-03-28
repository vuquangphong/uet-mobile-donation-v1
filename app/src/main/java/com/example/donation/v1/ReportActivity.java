package com.example.donation.v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.donation.v1.databinding.ActivityReportBinding;

public class ReportActivity extends Base {

    private ActivityReportBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityReportBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ListView listView = binding.reportList;
        DonationAdapter adapter = new DonationAdapter(this, DonationApp.donations);
        listView.setAdapter(adapter);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}