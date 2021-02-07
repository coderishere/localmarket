package com.example.connect;

import android.app.Application;
import android.content.Intent;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.ParseQuery;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate ( );
       // FirebaseApp.initializeApp(this);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                // if defined
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build()
        );
        ParseInstallation installation = ParseInstallation.getCurrentInstallation();
        installation.put("GCMSenderId", "251140267282");
        installation.saveInBackground();
    }

}
