package com.college.quiz1_question;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    int mCounter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //counterView is the TextView that displays the value of the counter
        TextView counterView = findViewById(R.id.counterId);

        //TODO
        /*
        - the code to reset the counter when the button Reset is clicked.
        - Note that MainActivity class contains a private attribute mCounter that tracks the value of the counter.
        - You can also go ahead and implement the other buttons (i.e. Increment, Decrement, Toast etc.)
        */

        // reset button
        Button resetBtn = (Button) findViewById(R.id.buttonResetId);
        Button incrementBtn = (Button) findViewById(R.id.buttonIncId);
        Button decrementBtn = findViewById(R.id.buttonDecId);
        Button snackbarBtn = findViewById(R.id.buttonSnackbarId);
        Button toastBtn = findViewById(R.id.buttonToastId);



        //Resets counter when the button Reset is clicked
        resetBtn.setOnClickListener((click)->{
            counterView.setText("0");
        });

        incrementBtn.setOnClickListener((click)->{
           counterView.setText(String.valueOf(mCounter));
            mCounter ++;
        });


        decrementBtn.setOnClickListener((click)->{
            counterView.setText(String.valueOf(mCounter));
            mCounter --;
        });

        snackbarBtn.setOnClickListener((click)->{
            //Linear layout
            View myView = findViewById(R.id.viewId);

            //Create and instance of Snackbar
            Snackbar mySnackbar = Snackbar.make(myView, "Snackbar", Snackbar.LENGTH_INDEFINITE);

            /* Sets undo action that changes swift back to off if isChecked is true or
            back to on if isCheked is false
             */
            mySnackbar.setAction("Undo", clk -> counterView.setText("300"));
            mySnackbar.show(); //Method to show Snackbar

        });

        toastBtn.setOnClickListener((click)->{
            String toastMessage = "toast message"; //getResources().getString(R.string.toast_message);
            //
            Toast.makeText(this,toastMessage,Toast.LENGTH_LONG).show();
        });
    }
}