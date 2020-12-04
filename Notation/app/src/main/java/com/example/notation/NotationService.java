package com.example.notation;

import android.content.Intent;
import android.os.IBinder;
import android.app.IntentService;

import java.util.ArrayList;

public class NotationService extends IntentService {
    public static final String ACTION_NOTATIONSERVICE = "NotationService.RESPONSE";

    public NotationService() {
        super("NotationService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        ArrayList<Integer> infoList = intent.getIntegerArrayListExtra("INFO");

        Integer enteredNumber = infoList.get(0);
        String resultNumber = "0";
        int type = infoList.get(1);
        switch(type) {
            case 1:
                resultNumber = Integer.toBinaryString(enteredNumber);
                break;
            case 2:
                resultNumber = Integer.toOctalString(enteredNumber);
                break;
            case 3:
                resultNumber = Integer.toHexString(enteredNumber);
                break;
        }

        Intent responseIntent = new Intent();
        responseIntent.setAction(ACTION_NOTATIONSERVICE);
        responseIntent.addCategory(Intent.CATEGORY_DEFAULT);
        responseIntent.putExtra("RESULT", resultNumber);
        sendBroadcast(responseIntent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}