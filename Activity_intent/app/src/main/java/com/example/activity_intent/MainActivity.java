package com.example.activity_intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Integer previousResultNumber = getIntent().getIntExtra("previousResultNumber", 0);
        TextView viewResultNumber = findViewById(R.id.viewResultNumber);
        viewResultNumber.setText(previousResultNumber.toString());

        Button buttonOk = findViewById(R.id.buttonOk);

        buttonOk.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        EditText viewEnterNumber = findViewById(R.id.viewEnterNumber);
        Integer enteredNumber = (viewEnterNumber.getText() == null)?0:Integer.parseInt(viewEnterNumber.getText().toString());
        TextView viewResultNumber = findViewById(R.id.viewResultNumber);
        Integer previousResultNumber = (viewResultNumber.getText() == null)?0:Integer.parseInt(viewResultNumber.getText().toString());
        Integer resultNumber = previousResultNumber + enteredNumber;

        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("previousResultNumber", resultNumber);

        startActivity(intent);
    }
}