package com.example.connect;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class Shop_login extends AppCompatActivity implements View.OnClickListener {
    public void enteruser1() {
        if (ParseUser.getCurrentUser ( ).has ( "code" )) {
            if (ParseUser.getCurrentUser ( ) != null) {
                Intent intent = new Intent ( getApplicationContext ( ), mainpage_activity.class );
                startActivity ( intent );
            }
        }
    }
EditText username , password;
Button login_shop;
TextView textview;
String user , user_password;
ParseObject parseObject;
String obj,size;
    String objectid,objectid1;
ParseQuery<ParseUser>  parseQuery;
ImageView logo_shoplogin;
ConstraintLayout shoplogin_layout;
ArrayList<String> arrayList,arrayList1;

String string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_shop_login );

        arrayList = new ArrayList<> (  );
        arrayList1 = new ArrayList<> (  );

       // enteruser1 ();


        username = findViewById ( R.id.login_username );
        password = findViewById ( R.id.Password_login );
        login_shop = findViewById ( R.id.shop_login );
        textview = findViewById ( R.id.signupText );
        logo_shoplogin = findViewById ( R.id.logo_shop );
        shoplogin_layout = findViewById ( R.id.login_shop_layout );
        textview.setOnClickListener ( this );
        logo_shoplogin.setOnClickListener ( this );
        shoplogin_layout.setOnClickListener ( this );
      //  if(ParseUser.getCurrentUser ().getString ( "cateogory" ).equals ( "Book" ))


        login_shop.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                if (username.getText ().toString ().matches ( "" )||password.getText ().toString ().matches ( "" )){
                    Toast.makeText ( Shop_login.this, "fill required details", Toast.LENGTH_SHORT ).show ( );
                }else {
                    ParseUser.logInInBackground ( username.getText ( ).toString ( ), password.getText ( ).toString ( ), new LogInCallback ( ) {
                        @Override
                        public void done(ParseUser user, ParseException e) {
                            if (e == null && user!=null) {

                                if(ParseUser.getCurrentUser ().has ( "code" )) {

                                    Intent intent1 = new Intent ( Shop_login.this, Shopkeeper_mainpage.class );
                                    startActivity ( intent1 );
                                    finish ();

                                    //  ParseUser.logOut ();
                                }else{
                                    Intent newintent = new Intent ( Shop_login.this, mainpage_activity.class );
                                    startActivity ( newintent );
                                    finish ();
                                }


                            } else {
                                Toast.makeText ( Shop_login.this, e.getMessage ( ), Toast.LENGTH_SHORT ).show ( );
                            }
                        }
                    } );

                }
            }
        } );



    }

    @Override
    public void onClick(View v) {
        if(v.getId ()==R.id.signupText){
            Intent intent2 = new Intent ( Shop_login.this,shopkeeper_signup.class );
            startActivity ( intent2 );
        }else if (v.getId ()==R.id.logo_shop||v.getId ()==R.id.login_shop_layout){
            InputMethodManager inputMethodManager= (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }
    }
}
/*
 parseQuery5.getInBackground ( ParseUser.getCurrentUser ( ).getObjectId ( ), new GetCallback<ParseUser> ( ) {
                                        @Override
                                        public void done(ParseUser object, ParseException e) {
                                            object.add ( "list",size );
                                            object.saveInBackground ();
                                        }
                                    } );
                                    Log.i ( "idss", size );




                                ParseQuery<ParseObject> query = new ParseQuery ( "Notification" );
                    query.findInBackground ( new FindCallback<ParseObject> ( ) {
                        @Override
                        public void done(List<ParseObject> objects, ParseException e) {
                            if (e == null) {
                                ParseQuery<ParseUser> parseQuery5 = ParseUser.getQuery ();
                                for (ParseObject object : objects) {
                                    size = object.getObjectId ( );



                                }

                            }
                        }
                    } );

 */