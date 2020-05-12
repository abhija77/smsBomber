package com.mobile.smsbomber.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import android.telephony.gsm.SmsManager;
import android.util.Log;

public class Receiver extends BroadcastReceiver {

    String body,number;
    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("params",Context.MODE_PRIVATE);
        Bundle extras = intent.getExtras();
        if (extras != null) {
            Object[] obj = (Object[]) extras.get("pdus");
            Log.w("MY_DEBUG_TAG", "RECEIVE");
            if (obj != null) {
                for (int i =0; i < obj.length; i++){
                    SmsMessage smsMessage = SmsMessage.createFromPdu((byte[])obj[i]);
                    body = smsMessage.getMessageBody().toString();
                    number = smsMessage.getOriginatingAddress().toString();
                }
                String sms_to_reply = "";
                if(sharedPreferences.contains(body)) {
                    sms_to_reply = sharedPreferences.getString(body,"");
                    SmsManager sms = SmsManager.getDefault();
                    sms.sendTextMessage(number, null, sms_to_reply,null,null);
                }

            }


        }
    }
}
