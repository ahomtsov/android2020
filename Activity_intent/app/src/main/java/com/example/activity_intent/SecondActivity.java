package com.example.activity_intent;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Integer previousResultNumber = getIntent().getIntExtra("previousResultNumber", 0);
        TextView viewResultNumber = findViewById(R.id.viewResultNumber);
        viewResultNumber.setText(previousResultNumber.toString());

        Button buttonOk = findViewById(R.id.buttonOk);

        buttonOk.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText viewEnterNumber = findViewById(R.id.viewEnterNumber);
                int enteredNumber = (viewEnterNumber.getText() == null)?0:Integer.parseInt(viewEnterNumber.getText().toString());
                TextView viewResultNumber = findViewById(R.id.viewResultNumber);
                int previousResultNumber = (viewResultNumber.getText() == null)?0:Integer.parseInt(viewResultNumber.getText().toString());
                int resultNumber = previousResultNumber + enteredNumber;

                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                intent.putExtra("previousResultNumber", resultNumber);

                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }

}