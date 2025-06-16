package com.example.myapplication.chapter8.one;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class SmsReceiver extends BroadcastReceiver {

    public interface SmsListener {
        void onMessageReceived(String sender, String message);
    }

    public static SmsListener smsListener;

    public static void bindListener(SmsListener listener) {
        smsListener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Object[] pdus = (Object[]) bundle.get("pdus");
            if (pdus != null) {
                for (Object obj : pdus) {
                    SmsMessage message = SmsMessage.createFromPdu((byte[]) obj, bundle.getString("format"));
                    String sender = message.getDisplayOriginatingAddress();
                    String body = message.getMessageBody();

                    if (smsListener != null) {
                        smsListener.onMessageReceived(sender, body);
                    }
                }
            }
        }
    }
}
