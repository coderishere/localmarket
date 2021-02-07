package com.example.connect;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,View.OnKeyListener {
    public void enteruser(){
        if(ParseUser.getCurrentUser ()!=null){
            Intent intent = new Intent ( getApplicationContext (), mainpage_activity.class );
            startActivity ( intent );
        }
    }
    TextView loginTextView,textView;
    EditText Email,phone;
    EditText username;
    EditText password;
    Button signup;
    ImageView logo;
    ConstraintLayout signuppagelayout;
    ParseUser user;

    boolean signupmode = true;

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {

        if(keyCode==KeyEvent.KEYCODE_ENTER && event.getAction()==KeyEvent.ACTION_DOWN){
            signupclick ( v );
        }
        return false;
    }
    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.loginTextView){
            Log.i("was","tap");
             signup = findViewById(R.id.sign_up);
            if(signupmode){
                signupmode = false;
                Intent intent = new Intent(MainActivity.this, loginactivity.class);
                startActivity(intent);
            }else{
                signupmode = true;
                loginTextView.setText("login");
            }
        }else if(view.getId()== R.id.logo2 || view.getId()==R.id.signuppagelayout){
            InputMethodManager inputMethodManager= (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);        // hide keyboard if touch anywhere
        }
    }

    public  void signupclick(View view) {                                              // login code


        if (username.getText().toString().matches("") || password.getText().toString().matches("")||Email.getText().toString().matches ( "" )) {
            Toast.makeText(this, "username and password required", Toast.LENGTH_SHORT).show();
        } else {
            if (signupmode) {
                user = new ParseUser();
                user.setEmail(Email.getText().toString());
                user.setUsername(username.getText().toString());
                user.setPassword(password.getText().toString());
                user.put ( "personalPhone", phone.getText ().toString () );
                user.signUpInBackground( new SignUpCallback () {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Log.i("yes", "sucess");

                            Intent i = new Intent(MainActivity.this,mainpage_activity.class);
                            startActivity(i);
                            finish ();
                        } else {
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

      //  enteruser ();
        loginTextView = findViewById(R.id.loginTextView);
        loginTextView.setOnClickListener ( this );
        username = (EditText) findViewById(R.id.username1 );
        password = (EditText) findViewById(R.id.password1 );
        textView = findViewById ( R.id.textView );
        signup = findViewById(R.id.sign_up);
        logo = findViewById(R.id.logo2 );
        signuppagelayout = findViewById(R.id.signuppagelayout);
        Email= findViewById(R.id.email);
        logo.setOnClickListener(this);
        signuppagelayout.setOnClickListener(this);
        phone = findViewById ( R.id.phone_usr );

    }
}