package com.example.connect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class profile_edit extends AppCompatActivity {
EditText editText,editText1,editText2,editText3;
Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_profile_edit );
        editText = findViewById ( R.id. full_name );
        editText1 = findViewById ( R.id.Address_user );
        editText2 = findViewById ( R.id.Landmark );
        editText3 = findViewById ( R.id.Phone_number );
        button = findViewById ( R.id.save_edit_address );
        button.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseUser>parseQuery = ParseUser.getQuery ();
                parseQuery.getInBackground ( ParseUser.getCurrentUser ( ).getObjectId ( ), new GetCallback<ParseUser> ( ) {
                    @Override
                    public void done(ParseUser object, ParseException e) {
                        object.put ( "fullname", editText.getText ().toString () );
                        object.put ( "address1",editText1.getText ().toString () );
                        object.put ( "landmark",editText2.getText ().toString () );
                        object.put ( "user_phone",editText3.getText ().toString () );
                        object.saveInBackground ( new SaveCallback ( ) {
                            @Override
                            public void done(ParseException e) {
                                if(e==null){
                                    Intent intent = new Intent ( profile_edit.this, mainpage_activity.class );
                                    startActivity ( intent );
                                }
                            }
                        } );
                    }
                } );

            }
        } );


    }
}