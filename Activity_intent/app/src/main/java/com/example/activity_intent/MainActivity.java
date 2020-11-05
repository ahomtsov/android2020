package com.example.activity_intent;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonOk = findViewById(R.id.buttonOk);

        buttonOk.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText viewEnterNumber = findViewById(R.id.viewEnterNumber);
                Integer enteredNumber = (viewEnterNumber.getText() == null)?0:Integer.parseInt(viewEnterNumber.getText().toString());
                TextView viewResultNumber = findViewById(R.id.viewResultNumber);
                Integer previousResultNumber = (viewResultNumber.getText() == null)?0:Integer.parseInt(viewResultNumber.getText().toString());
                Integer resultNumber = previousResultNumber + enteredNumber;

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("previousResultNumber", resultNumber);

                viewEnterNumber.setText("");

                startActivityForResult(intent, 10);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10) {
            if (resultCode == Activity.RESULT_OK) {
                Integer previousResultNumber = data.getIntExtra("previousResultNumber", 0);
                TextView viewResultNumber = findViewById(R.id.viewResultNumber);
                viewResultNumber.setText(previousResultNumber.toString());
            }
        }
    }

}