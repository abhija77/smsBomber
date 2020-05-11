package com.mobile.smsbomber.activities;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.mobile.smsbomber.R;
import com.mobile.smsbomber.services.Receiver;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int RequestPermissionCode = 7;
    Uri inboxURI = Uri.parse("content://sms/inbox");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS)
                == PackageManager.PERMISSION_GRANTED) {
        } else {

            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_CONTACTS,
                    Manifest.permission.READ_SMS,
                    Manifest.permission.RECEIVE_SMS,
                    Manifest.permission.SEND_SMS}, RequestPermissionCode);

            this.setContentView(R.layout.activity_main);

            Button button = this.findViewById(R.id.call);
            button.setOnClickListener(this);


//
//        String SMS_RECEIVED = android.provider.Telephony;
//        registerReceiver(receiver,new IntentFilter(SMS_RECEIVED));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("=============","Destroy");
    }


    @Override
    public void onClick(View v) {
        Intent intent=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:911"));
        startActivity(intent);
    }

    public void goToActivity(Class nameActivity){
        startActivity(new Intent(this,nameActivity));
    }
}
