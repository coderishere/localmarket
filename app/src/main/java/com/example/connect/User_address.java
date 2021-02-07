package com.example.connect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class User_address extends AppCompatActivity {
EditText fullname, address , landmark, phone;
Button save , cancel;
String obj_user;
ParseQuery<ParseUser> parseQuery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_user_address );
        parseQuery = ParseUser.getQuery ();
        parseQuery.whereDoesNotExist ( "code" );
        fullname = findViewById ( R.id.fullName );
        address = findViewById ( R.id.user_address );
        landmark = findViewById ( R.id.landamark );
        phone = findViewById ( R.id.user_phone );
        save = findViewById ( R.id.save );
        cancel = findViewById ( R.id.address_cancel );
        final SharedPreferences avail_object = PreferenceManager.getDefaultSharedPreferences ( this );
        obj_user = avail_object.getString ( "id_user","" );

        save.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                if(fullname.getText ().toString ().matches ( "" )||address.getText ().toString ().matches ( "" )||landmark.getText ().toString ().matches ( "" )||phone.getText ().toString ().matches ( "" )){
                    Toast.makeText ( User_address.this, "fill the details", Toast.LENGTH_SHORT ).show ( );
                }else {
                    parseQuery.getInBackground ( ParseUser.getCurrentUser ( ).getObjectId ( ), new GetCallback<ParseUser> ( ) {
                        @Override
                        public void done(ParseUser object, ParseException e) {
                            object.put ( "fullname", fullname.getText ( ).toString ( ) );
                            object.put ( "address1", address.getText ( ).toString ( ) );
                            object.put ( "landmark", landmark.getText ( ).toString ( ) );
                            object.put ( "user_phone", phone.getText ( ).toString ( ) );
                            object.saveInBackground ( new SaveCallback ( ) {
                                @Override
                                public void done(ParseException e) {
                                    if(e==null){
                                        if(obj_user.equals ( ParseUser.getCurrentUser ().getUsername () )){
                                            Intent intent1 = new Intent ( User_address.this, buy_avialable.class );
                                            startActivity ( intent1 );
                                        }else {
                                            Intent intent = new Intent ( User_address.this, User_Notification.class );
                                            startActivity ( intent );
                                        }
                                    }
                                }
                            } );

                        }

                    } );
                }
            }
        } );

    }
}