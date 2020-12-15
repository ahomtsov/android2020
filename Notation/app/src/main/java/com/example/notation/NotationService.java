package com.example.notation;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.TextView;

public class NotationService extends Service {

    public class NotationServiceBinder extends Binder {
        public NotationService getService() {
            return NotationService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new NotationServiceBinder();
    }

    public void decimalToBinary(TextView textView, Integer enteredNumber) {
        textView.setText(Integer.toBinaryString(enteredNumber));
    }

    public void decimalToOctal(TextView textView, Integer enteredNumber) {
        textView.setText(Integer.toOctalString(enteredNumber));
    }

    public void decimalToHex(TextView textView, Integer enteredNumber) {
        textView.setText(Integer.toHexString(enteredNumber));
    }

}