package com.example.donation.v1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DbManager {
    private SQLiteDatabase database;
    private DbDesigner dbHelper;

    public DbManager(Context context) {
        dbHelper = new DbDesigner(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        database.close();
    }

    public void add(Donation d) {
        ContentValues values = new ContentValues();
        values.put("amount", d.getAmount());
        values.put("method", d.getMethod());

        database.insert("donations", null, values);
    }

    public List<Donation> getAll() {
        List<Donation> donations = new ArrayList<Donation>();
        Cursor cursor = database.rawQuery("SELECT * FROM donations", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Donation d = toDonation(cursor);
            donations.add(d);
            cursor.moveToNext();
        }
        cursor.close();
        return donations;
    }

    private Donation toDonation(Cursor cursor) {
        Donation pojo = new Donation();
        pojo.setId(cursor.getInt(0));
        pojo.setAmount(cursor.getInt(1));
        pojo.setMethod(cursor.getString(2));
        return pojo;
    }

    public void setTotalDonated(Base base) {
        Cursor c = database.rawQuery("SELECT SUM(amount) FROM donations", null);
        c.moveToFirst();
        if (!c.isAfterLast())
            base.app.totalDonated = c.getInt(0);
    }

    public void reset() {
        database.delete("donations", null, null);
    }

}
