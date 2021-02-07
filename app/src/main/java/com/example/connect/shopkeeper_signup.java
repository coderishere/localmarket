package com.example.connect;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.ParseQuery;

import com.parse.ParseRelation;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.util.List;


public class shopkeeper_signup extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener{
    public void enteruser1() {
        if (ParseUser.getCurrentUser ( ).has ( "code" )) {
            if (ParseUser.getCurrentUser ( ) != null) {
                Intent intent = new Intent ( getApplicationContext ( ), mainpage_activity.class );
                startActivity ( intent );
            }
        }
    }
EditText shopuser, Phone , shop_email, password, code,shop_address;
Button cateogory_shop, signup_shop;
String ids;
TextView loginText;
ImageView logo;
ConstraintLayout layout;
Spinner spinner;

String obj,dropdown,checking,obj1;
int pos;
ParseUser user1;

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

         dropdown = parent.getSelectedItem ().toString ();
         Log.i ( "checking", String.valueOf ( position ) );
         checking=String.valueOf ( position );
         pos = position;
         Log.i ( "pos",String.valueOf ( pos ) );

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_shopkeeper_signup );

   // enteruser1 ();


        shopuser = findViewById ( R.id.Shopuser );
        Phone = findViewById ( R.id.Phone );
        shop_email= findViewById ( R.id.Shop_email );
        password = findViewById ( R.id.Shop_password );
        code = findViewById ( R.id.Shop_code );
        shop_address = findViewById ( R.id.Shop_address );
       // cateogory_shop = findViewById ( R.id.shopbutton );
        signup_shop = findViewById ( R.id.shop_signup );
        loginText = findViewById ( R.id.loginTextView );
        logo= findViewById ( R.id.logo2 );
        layout = findViewById ( R.id.layout1 );
        spinner = findViewById ( R.id.spinner );


        spinner.setOnItemSelectedListener ( this );
        loginText.setOnClickListener ( this );
        layout.setOnClickListener ( this );
        logo.setOnClickListener ( this );
        signup_shop.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                if(shopuser.getText ().toString ().matches ( "" )||Phone.getText ().toString ().matches ( "" )||shop_email.getText ().toString ().matches ( "" )||code.getText ().toString ().matches ( "" )||password.getText ().toString ().matches ( "" )){
                    Toast.makeText ( shopkeeper_signup.this, "Fill the required details", Toast.LENGTH_SHORT ).show ( );
                }else {

                    if(pos==0) {
                        ParseQuery<ParseObject> query = new ParseQuery<ParseObject> ( "Notification" );
                        query.findInBackground ( new FindCallback<ParseObject> ( ) {
                            @Override
                            public void done(List<ParseObject> objects, ParseException e) {
                                if (e == null) {
                                    user1 = new ParseUser ( );
                                    user1.setUsername ( shopuser.getText ( ).toString ( ) );
                                    user1.setEmail ( shop_email.getText ( ).toString ( ) );
                                    user1.setPassword ( password.getText ( ).toString ( ) );
                                    user1.put ( "PhoneNumber", Phone.getText ( ).toString ( ) );
                                    user1.put ( "code", code.getText ( ).toString ( ) );
                                    user1.put ( "address", shop_address.getText ( ).toString ( ) );
                                    user1.put ( "cateogory", dropdown );
                                    for (ParseObject object : objects) {
                                        obj = object.getObjectId ( );
                                    // user1.addUnique ( "list", obj );
                                    // user1.addUnique ( "newlists",obj );
                                    }
                                    user1.signUpInBackground ( new SignUpCallback ( ) {
                                        @Override
                                        public void done(ParseException e) {
                                            if (e == null) {
                                                ParsePush.subscribeInBackground ( "Books" );
                                                Intent intent = new Intent ( shopkeeper_signup.this, Shopkeeper_mainpage.class );
                                                startActivity ( intent );
                                                finish ();
                                            } else {
                                                Toast.makeText ( shopkeeper_signup.this, e.getMessage ( ), Toast.LENGTH_LONG ).show ( );
                                            }
                                        }
                                    } );

                                }

                            }
                        } );
                    }
                    if(pos==1) {

                        ParseQuery<ParseObject> query1 = new ParseQuery<ParseObject> ( "Ele_Notification" );
                        query1.findInBackground ( new FindCallback<ParseObject> ( ) {
                            @Override
                            public void done(List<ParseObject> objects, ParseException e) {
                                if (e == null) {
                                    user1 = new ParseUser ( );
                                    user1.setUsername ( shopuser.getText ( ).toString ( ) );
                                    user1.setEmail ( shop_email.getText ( ).toString ( ) );
                                    user1.setPassword ( password.getText ( ).toString ( ) );
                                    user1.put ( "PhoneNumber", Phone.getText ( ).toString ( ) );
                                    user1.put ( "code", code.getText ( ).toString ( ) );
                                    user1.put ( "address", shop_address.getText ( ).toString ( ) );
                                    user1.put ( "cateogory", dropdown );
                                    for (ParseObject object : objects) {
                                        obj1 = object.getObjectId ( );
                                        user1.addUnique ( "list", obj1 );

                                    }
                                    user1.signUpInBackground ( new SignUpCallback ( ) {
                                        @Override
                                        public void done(ParseException e) {
                                            if (e == null) {
                                                ParsePush.subscribeInBackground ( "Electronics" );
                                                Intent intent = new Intent ( shopkeeper_signup.this, Shopkeeper_mainpage.class );
                                                startActivity ( intent );
                                            } else {
                                                Toast.makeText ( shopkeeper_signup.this, e.getMessage ( ), Toast.LENGTH_LONG ).show ( );
                                            }
                                        }
                                    } );

                                }

                            }
                        } );

                    }

                }

            }
        } );

}

    @Override
    public void onClick(View v) {
if(v.getId ()==R.id.loginTextView){
    Intent start = new Intent ( shopkeeper_signup.this, Shop_login.class );
    startActivity ( start );
}else if(v.getId()== R.id.logo2 || v.getId()==R.id.layout1){
    InputMethodManager inputMethodManager= (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
    inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);        // hide keyboard if touch anywhere
}
    }



}
