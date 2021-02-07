package com.example.connect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.parse.ParseUser;

public class strtingPage extends AppCompatActivity implements View.OnClickListener {
TextView textView,textView1,textView2;
public  void   entruser(){
if(ParseUser.getCurrentUser ()!=null){
    if(ParseUser.getCurrentUser ().has ( "code" )){
        Intent intent = new Intent ( strtingPage.this, Shopkeeper_mainpage.class );
        startActivity ( intent );
    }else {
        if(ParseUser.getCurrentUser ().has ( "entercode" )){
            Intent intent = new Intent ( strtingPage.this, strtingPage.class );
            startActivity ( intent );
        }else {
            Intent intent = new Intent ( strtingPage.this,mainpage_activity.class );
            startActivity ( intent );
        }
    }
}
}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_strting_page );
        // entruser ();
        textView = findViewById ( R.id.consumer );
        textView1 = findViewById ( R.id.shopkeeper );
        textView2 = findViewById ( R.id.todelivery );
        textView1.setOnClickListener ( this );
        textView.setOnClickListener ( this );
        textView2.setOnClickListener ( this );

    }

    @Override
    public void onClick(View v) {
        if(v.getId ()==R.id.consumer){
            Intent intent = new Intent ( strtingPage.this, loginactivity.class );
            startActivity ( intent );
            finish ();
        }else {
            if(v.getId ()==R.id.shopkeeper){
                Intent intent1 = new Intent ( strtingPage.this, Shop_login.class );
                startActivity ( intent1 );
                finish ();
            }
        }
        if(v.getId ()==R.id.todelivery){
            Intent intent2 = new Intent ( strtingPage.this, delivery_user.class );
            startActivity ( intent2 );
            finish ();
        }
    }
}