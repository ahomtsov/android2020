package com.example.notation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private NotationServiceBroadcastReceiver broadcastReceiver;

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

        broadcastReceiver = new NotationServiceBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter(NotationService.ACTION_NOTATIONSERVICE);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        EditText inputEditText = findViewById(R.id.inputEditText);
        Integer enteredNumber;
        if (inputEditText.getText().toString().equals("")) enteredNumber = 0;
        else enteredNumber = Integer.parseInt(inputEditText.getText().toString());
        ArrayList<Integer> infoIntent = new ArrayList<Integer>();
        infoIntent.add(enteredNumber);

        int id = item.getItemId();
        switch(id){
            case R.id.binary_settings:
                Intent binaryIntent = new Intent(MainActivity.this, NotationService.class);
                infoIntent.add(1);
                binaryIntent.putExtra("INFO", infoIntent);
                startService(binaryIntent);
                break;
            case R.id.octal_settings:
                Intent octalIntent = new Intent(MainActivity.this, NotationService.class);
                infoIntent.add(2);
                octalIntent.putExtra("INFO", infoIntent);
                startService(octalIntent);
                break;
            case R.id.hex_settings:
                Intent hexIntent = new Intent(MainActivity.this, NotationService.class);
                infoIntent.add(3);
                hexIntent.putExtra("INFO", infoIntent);
                startService(hexIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
        Toast.makeText(getApplicationContext(), "Остановка сервиса", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, NotationService.class);
        stopService(intent);
    }

    public class NotationServiceBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String resultNumber = intent.getStringExtra("RESULT");
            TextView resultTextView = findViewById(R.id.notationResultTextView);
            resultTextView.setText(resultNumber);
        }
    }
}