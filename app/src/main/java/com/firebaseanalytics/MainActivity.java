package com.firebaseanalytics;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private FirebaseAnalytics firebaseAnalytics;
    String[] names = new String[]{"Adam", "Binny", "Germish", "Mick", "Oren"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtain the Firebase Analytics instance.
        firebaseAnalytics = FirebaseAnalytics.getInstance(this);

    }


    public void onNotificationClick(View view) {
        Intent iHome = new Intent(this, NotificationActivity.class);
        startActivity(iHome);

    }

    public void onCustomEventClick(View view) {

        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "ID 1");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Custom Name");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

    }


    public void onAddUserPropertyClick(View view) {
        Person name = new Person();
        name.setId(1);
        // choose random food name from the list
        name.setName(names[randomIndex()]);

        Bundle bundle = new Bundle();
        bundle.putInt(FirebaseAnalytics.Param.ITEM_ID, name.getId());
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name.getName());

        //Logs an app event.
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

        //Sets whether analytics collection is enabled for this app on this device.
        firebaseAnalytics.setAnalyticsCollectionEnabled(true);

        //Sets the minimum engagement time required before starting a session. The default value is 10000 (10 seconds). Let's make it 20 seconds just for the fun
        firebaseAnalytics.setMinimumSessionDuration(20000);

        //Sets the duration of inactivity that terminates the current session. The default value is 1800000 (30 minutes).
        firebaseAnalytics.setSessionTimeoutDuration(500);

        //Sets the user ID property.
        firebaseAnalytics.setUserId(String.valueOf(name.getId()));

        //Sets a user property to a given value.
        firebaseAnalytics.setUserProperty("Person", name.getName());
    }


    private int randomIndex() {
        int min = 0;
        int max = names.length - 1;
        Random rand = new Random();
        return min + rand.nextInt((max - min) + 1);
    }

}
