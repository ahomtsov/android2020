package com.example.notation;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private NotationService notationService;
    private boolean isNotationServiceBound = false;
    private final ServiceConnection serviceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            NotationService.NotationServiceBinder binder = (NotationService.NotationServiceBinder) iBinder;
            notationService = binder.getService();
            isNotationServiceBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isNotationServiceBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView welcomeTextView = findViewById(R.id.infoInputTextView);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;
        int buttonWidth = screenWidth/2;
        android.view.ViewGroup.LayoutParams welcomeTextViewLayoutParams = welcomeTextView.getLayoutParams();
        welcomeTextViewLayoutParams.width = buttonWidth;
        welcomeTextView.setLayoutParams(welcomeTextViewLayoutParams);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, NotationService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isNotationServiceBound) {
            unbindService(serviceConnection);
            isNotationServiceBound = false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        EditText inputEditText = findViewById(R.id.inputEditText);
        Integer enteredNumber;
        if (inputEditText.getText().toString().equals("")) enteredNumber = 0;
        else enteredNumber = Integer.parseInt(inputEditText.getText().toString());

        int id = item.getItemId();
        switch(id){
            case R.id.binary_settings:
                notationService.decimalToBinary(findViewById(R.id.notationResultTextView), enteredNumber);
                break;
            case R.id.octal_settings:
                notationService.decimalToOctal(findViewById(R.id.notationResultTextView), enteredNumber);
                break;
            case R.id.hex_settings:
                notationService.decimalToHex(findViewById(R.id.notationResultTextView), enteredNumber);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}