package com.college.quiz1_question;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int mCounter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // This EditText contains the user input
        // for the number of burgers
        EditText numberStr = findViewById(R.id.numberId);

        // This textview displays the result of multiplying
        // the content of the numberId EditText by 5
        TextView resultTv = findViewById(R.id.resultId);

        // TODO
        // Implement the 2 listeners for buttons 'clear' and 'pay'
        // One burger is $5
        // Result should contain 5 multiplied by number chosen
        // Display should be prefixed with the '$' sign such as $15
        // Reset should clear all the fields

        Button clearBtn = findViewById(R.id.btnResetId);
        Button payBtn = findViewById(R.id.btnPayId);

        payBtn.setOnClickListener((click)->{
            mCounter = Integer.parseInt(numberStr.getText().toString());
            mCounter = mCounter * 5;
            resultTv.setText("$" + String.valueOf(mCounter));
        });

        clearBtn.setOnClickListener((click) ->{
            numberStr.setText("");
            resultTv.setText("0");
        });

    }
}