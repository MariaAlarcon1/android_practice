package com.college.quiz1_question;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ShareCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private final String LOG_TAG="MainActivity";
    // EditText view for the website URI
    private EditText mWebsiteEditText;
    // EditText view for the location URI
    private EditText mLocationEditText;
    // EditText view for the share text
    private EditText mShareTextEditText;
    //EditText for Dial action
    private EditText mDialEditText;
    private String contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebsiteEditText = findViewById(R.id.website_edittext);
        mLocationEditText = findViewById(R.id.location_editext);
        mShareTextEditText = findViewById(R.id.share_edittext);
        mDialEditText = findViewById(R.id.phone_edittext);

        Button openWebsiteBtn = findViewById(R.id.open_website_button);
        Button openLocationBtn = findViewById(R.id.open_location_button);
        Button shareTextBtn = findViewById(R.id.share_text_button);
        Button buttonCall = findViewById(R.id.dial_button);


        //Open a URL in a browser.
        openWebsiteBtn.setOnClickListener((v)->{openWebsite(openWebsiteBtn);});

        openLocationBtn.setOnClickListener((v)->{openLocation(openLocationBtn);});
        shareTextBtn.setOnClickListener((v)->{ shareText(mShareTextEditText);});

        buttonCall.setOnClickListener((v)->{dial(mDialEditText);});

        /*
    It contains buttons for sending implicit intents:
     - Open a URL in a browser.
     - Find a location on a map.
     - Share a text string.
     - View the Contacts
     - Dial a number
     */

        //Find a location on a map.
    }

    // TODO


    /**
     * Handles the onClick for the "Open Website" button.  Gets the URI
     * from the edit text and sends an implicit intent for that URL.
     *
     * @param view The view (Button) that was clicked.
     */
    public void openWebsite(View view) {
        String url = mWebsiteEditText.getText().toString();
        Intent openWebsiteIntent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(url));
        startActivity(openWebsiteIntent);
    }

    // TODO
    /**
     * Handles the onClick for the "Open Location" button.  Gets the location
     * text from the edit text and sends an implicit intent for that location.
     *
     * The location text can be any searchable geographic location.
     *
     * @param view The view (Button) that was clicked.
     */
    public void openLocation(View view) {
        String location = mLocationEditText.getText().toString();
        //https://developers.google.com/maps/documentation/urls/android-intents
        String locationToSearch = "geo:0,0?q=" + location;

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(locationToSearch));
        this.startActivity(intent);
    }

    // TODO
    /**
     * Handles the onClick for the "Share This Text" button.  The
     * implicit intent here is created by the  {@link ShareCompat.IntentBuilder}
     * class.  An app chooser appears with the available options for sharing.
     *
     * ShareCompat.IntentBuilder is from the v4 Support Library.
     *
     * @param view The view (Button) that was clicked.
     */
    public void shareText(View view) {
        //https://guides.codepath.com/android/Sharing-Content-with-Intents
        String message = mShareTextEditText.getText().toString();
            Intent shareTextIntent = new Intent(Intent.ACTION_SEND);
            shareTextIntent.setType("text/plain");
            shareTextIntent.putExtra(Intent.EXTRA_TEXT, message);
            startActivity(Intent.createChooser(shareTextIntent, "Share text using: "));
    }

    // TODO
    /**
     * Handles the onClick for the "Dial" button.
     *
     * @param view The view (Button) that was clicked.
     */
    public void dial(View view) {
        String phoneNumber = findViewById(R.id.phone_edittext).toString();
        Intent dialIntent = new Intent(Intent.ACTION_DIAL);
        dialIntent.setData(Uri.parse(phoneNumber));
    }

    // TODO
    /**
     * Handles the onClick for the "View Contacts" button.
     *
     * @param view The view (Button) that was clicked.
     */
    public void viewContact(View view) {
        Intent viewContactsIntent = new Intent(Intent.ACTION_PICK);
        viewContactsIntent.setType("");
        requestContactsPermission();
    }

    private boolean hasContactsPermission()
    {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) ==
                PackageManager.PERMISSION_GRANTED;
    }

    // Request contact permission if it
    // has not been granted already
    private void requestContactsPermission()
    {
        if (!hasContactsPermission())
        {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS}, 0);
        }
    }

}