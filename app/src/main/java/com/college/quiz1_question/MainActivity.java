package com.college.quiz1_question;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.graphics.drawable.ColorDrawable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Current count.
    private int mCount = 0;
    // Current background color.
    private int mColor;
    // Text view to display both count and color.
    private TextView mShowCountTextView;

    // Key for current count
    private final String COUNT_KEY = "count";
    // Key for current color
    private final String COLOR_KEY = "color";

    // Shared preferences object
    private SharedPreferences mPreferences;
    // Name of shared preferences file
    private static final String mSharedPrefFile = "mysharedprefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views, color, preferences
        mShowCountTextView = findViewById(R.id.count_textview);
        mColor = ContextCompat.getColor(this, R.color.default_background);
        mPreferences = getSharedPreferences(mSharedPrefFile, MODE_PRIVATE);

    }

    /**
     * Handles the onClick for the background color buttons.
     * Gets background color of the button
     * that was clicked and sets the textview background to that color.
     * Saves the current background
     *
     * @param view The view (Button) that was clicked.
     */
    public void changeBackground(View view) {
        int color = ((ColorDrawable) view.getBackground()).getColor();
        mShowCountTextView.setBackgroundColor(color);
        mColor = color;
    }

    //TODO 1

    /**
     * Handles the onClick for the Count button.  Increments the value of the mCount global and
     * updates the textview.
     *
     * @param view The view (Button) that was clicked.
     */
    public void countUp(View view) {
        mShowCountTextView = findViewById(R.id.count_textview);
        mCount = Integer.parseInt(mShowCountTextView.getText().toString()) + 1;
        mShowCountTextView.setText(mCount + "");
    }

    //TODO 2

    /**
     * Handles the onClick for the Reset button.
     * Resets the global count and background
     * variables to the defaults, resets the views to those values,
     * and clears the shared preferences
     *
     * @param view The view (Button) that was clicked.
     */
    public void reset(View view) {
        mCount = 0;
        mShowCountTextView.setText(mCount + "");
        mColor = ContextCompat.getColor(this, R.color.default_background);
        mShowCountTextView.setBackgroundColor(mColor);

        mPreferences = getSharedPreferences(mSharedPrefFile, MODE_PRIVATE);
        SharedPreferences.Editor mPreferencesEdit = mPreferences.edit();
        mPreferencesEdit.clear();
        mPreferencesEdit.apply();
    }

    //TODO 3

    /**
     * Handles the onClick for the 'Save Prefs' button.
     * Saves the Color and Counter to shared preferences
     *
     * @param view The view (Button) that was clicked.
     */
    public void savePrefs(View view) {
        String colorSave = (mColor) + "";
        String numberSave = mCount + "";
        if (mPreferences != null) {
            mPreferences.edit().putString("colorSaved", colorSave).apply();
            mPreferences.edit().putString("numberSaved", numberSave).apply();
            Toast toast = Toast.makeText(this, "Preferences saved correct!!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

        //TODO 4
        /**
         * Handles the onClick for the 'Restaure Prefs' button.
         * Reads the Color and Counter from the Preferences
         * Updates the color and counter textviews.
         *
         * @param view The view (Button) that was clicked.
         */
    public void restaurePrefs (View view){
        if (mPreferences != null) {
            String colorPut = mPreferences.getString("colorSaved", null);
            mShowCountTextView.setBackgroundColor(Integer.parseInt(colorPut));
            String numberPut = mPreferences.getString("numberSaved", null);
            mShowCountTextView.setText(numberPut + "");
            Toast toast = Toast.makeText(this, "Preferences restaured!!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
