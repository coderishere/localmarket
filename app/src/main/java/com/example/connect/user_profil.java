package com.example.connect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseUser;

public class user_profil extends AppCompatActivity implements View.OnClickListener {
TextView textView, textView1,textView2,textView3,textView4;
ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_user_profil );
        textView = findViewById ( R.id.textView14 );
        textView1 = findViewById ( R.id.textView15 );
        textView2 = findViewById ( R.id.user_phone );
        textView3 = findViewById ( R.id.user_address );
        textView4 = findViewById ( R.id.user_fullname );
        imageView = findViewById ( R.id.edit_profile_user );
        imageView.setOnClickListener ( this );

        if(ParseUser.getCurrentUser ()!=null){
            if(ParseUser.getCurrentUser ().has ( "fullname" )){
                textView4.setText ( ParseUser.getCurrentUser ().getString ( "fullname" ) );
            }else{
                textView4.setText ( "" );
            }if(ParseUser.getCurrentUser ().has ( "address1" )){
                textView3.setText ( ParseUser.getCurrentUser ().getString ( "address1" ) );
            }else {
                textView3.setText ( "" );
            }
            textView2.setText ( ParseUser.getCurrentUser ().getString ( "personalPhone" ) );

            textView.setText ( ParseUser.getCurrentUser ().getUsername () );
            textView1.setText ( ParseUser.getCurrentUser ().getEmail () );
        }else {
            Toast.makeText ( this, "No Current User", Toast.LENGTH_SHORT ).show ( );
        }


    }

    @Override
    public void onClick(View v) {
        if(v.getId ()==R.id.edit_profile_user){
           // Intent intent = new Intent ( user_profil.this, profile_edit.class );
            //startActivity ( intent );
        }
    }
}