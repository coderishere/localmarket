package com.example.connect;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseRelation;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;

public class loginactivity extends AppCompatActivity implements View.OnClickListener,View.OnKeyListener {
    public void enteruser(){
        if(ParseUser.getCurrentUser ()!=null){
            Intent intent = new Intent ( getApplicationContext (), mainpage_activity.class );
            startActivity ( intent );
        }
    }
    EditText username;
    EditText password;
    TextView signuptextview,Forgot_Password;
    ImageView logo;
    ConstraintLayout layout;
    String user1;
    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {

        if(keyCode==KeyEvent.KEYCODE_ENTER && event.getAction()==KeyEvent.ACTION_DOWN){
            loginbutton(v);
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        if(view.getId()== R.id.signupTextView){

            Intent intent= new Intent(loginactivity.this,MainActivity.class);
            startActivity(intent);
        }else if (view.getId()== R.id.logo2 || view.getId()== R.id.layout1){
            InputMethodManager inputMethodManager= (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }else if(view.getId ()==R.id.ForgotText){
            ParseUser.requestPasswordResetInBackground(ParseUser.getCurrentUser ().getEmail (), new RequestPasswordResetCallback () {
                public void done(ParseException e) {
                    if (e == null) {
                        // An email was successfully sent with reset instructions.
                        AlertDialog alertDialog = new AlertDialog.Builder(loginactivity.this).create();
                        alertDialog.setTitle("Email sent");
                        alertDialog.setMessage("An email was successfully sent to reset the password ");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();
                    } else {
                        // Something went wrong. Look at the ParseException to see what's up.
                        Toast.makeText ( loginactivity.this, e.getMessage (), Toast.LENGTH_SHORT ).show ( );
                    }
                }
            });
        }
    }


    public void loginbutton(View view) {
        if (username.getText ( ).toString ( ).matches ( "" ) || password.getText ( ).toString ( ).matches ( "" )) {
            Toast.makeText ( this, "username and password required", Toast.LENGTH_SHORT ).show ( );
        } else {

            ParseUser.logInInBackground ( username.getText ( ).toString ( ), password.getText ( ).toString ( ), new LogInCallback ( ) {
                @Override
                public void done(ParseUser user, ParseException e) {
                    if (user != null) {
                        if(!user.has ( "code" )) {

                            Intent mainintent = new Intent ( loginactivity.this, mainpage_activity.class );
                            startActivity ( mainintent );
                            finish ();
                            Log.i ( "sucess", "sucess" );
                        }else{
                            Toast.makeText ( loginactivity.this, "Account doesn't exist", Toast.LENGTH_SHORT ).show ( );
                        }
                    } else {
                        Toast.makeText ( loginactivity.this, e.getMessage ( ), Toast.LENGTH_SHORT ).show ( );
                    }
                }
            } );
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_loginactivity );

       // enteruser();

        username = findViewById ( R.id.username1 );
        password = findViewById ( R.id.password1 );
        logo = findViewById ( R.id.logo2 );
        layout = findViewById ( R.id.layout1 );
        signuptextview = findViewById ( R.id.signupTextView );
        Forgot_Password = findViewById ( R.id.ForgotText );
        Forgot_Password.setOnClickListener (this);
        signuptextview.setOnClickListener ( this );
        logo.setOnClickListener ( this );
        layout.setOnClickListener ( this );

       // Log.i ( "usernmase",ParseUser.getCurrentUser ().getUsername () );
    }

}