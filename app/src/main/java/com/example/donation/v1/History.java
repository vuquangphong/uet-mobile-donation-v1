package com.example.donation.v1;

import java.util.ArrayList;
import java.util.List;

public class History {
    public static final int MAX_DONATED_AMOUNT = 10000;
    public static final int MIN_PICKER_AMOUNT = 0;
    public static final int MAX_PICKER_AMOUNT = 1000;
    public static int totalDonated = 0;
    public static List<Donation> donations = new ArrayList<>();
}
