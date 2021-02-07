package com.example.connect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class delivery_page extends AppCompatActivity {
MenuItem menuItem;
ArrayList user_notification_array;
String[]user_notification;
String size;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_delivery_page );

        user_notification_array = new ArrayList (  );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater ().inflate ( R.menu.delivery,menu );
        menuItem = menu.findItem ( R.id.delivery_cart );
        ParseQuery<ParseObject> parseQuery = new ParseQuery<ParseObject> ( "Order_Accepted" );
        parseQuery.findInBackground ( new FindCallback<ParseObject> ( ) {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e==null){
                    for(ParseObject object:objects) {
                     if(object.getString ( "deliveryby" ).equals ( "localmarket" )){
                         menuItem.setActionView ( R.layout.delivery_layout );
                         View view = menuItem.getActionView ( );
                         TextView textView = view.findViewById ( R.id.delivery_badge );
                         textView.setText ( "1" );
                         view.setOnClickListener ( new View.OnClickListener ( ) {
                             @Override
                             public void onClick(View v) {
                                 Intent intent = new Intent ( delivery_page.this, delivery_address.class );
                                 startActivity ( intent );
                             }
                         } );
                     }else{
                         menuItem.setActionView ( null );
                     }
                    }
                }
            }
        } );

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences ( this );
        String count = preferences.getString ( "newvalue", "" );





        return super.onCreateOptionsMenu ( menu );
    }
}
