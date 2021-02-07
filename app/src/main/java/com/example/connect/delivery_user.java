package com.example.connect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class delivery_user extends AppCompatActivity {
EditText username,password,code;
Button signup_delivery;
    ParseUser delivery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_delivery_user );
        delivery = new ParseUser ();

        username = findViewById ( R.id.delivery_username );
        password = findViewById ( R.id.password_delivery );
        code = findViewById ( R.id.codes );
        signup_delivery = findViewById ( R.id.signup_delivery );


        delivery.setUsername ( username.getText ().toString () );
        delivery.setPassword (password.getText ().toString ());
        delivery.put ( "entercode",  code.getText ().toString ());
        signup_delivery.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                delivery.signUpInBackground ( new SignUpCallback ( ) {
                    @Override
                    public void done(ParseException e) {
                        if(username.getText ().toString ().matches ( "localmarket" )||password.getText ().toString ().matches ( "rahul.1234" )||code.getText ().toString ().matches ( "deliveryboy" )) {
                            Intent intent = new Intent ( delivery_user.this, delivery_page.class );
                            startActivity ( intent );
                        }else{
                            Toast.makeText ( delivery_user.this, e.getMessage () , Toast.LENGTH_SHORT ).show ( );
                        }
                    }
                } );
            }
        } );

    }
}