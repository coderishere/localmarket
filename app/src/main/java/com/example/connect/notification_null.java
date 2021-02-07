package com.example.connect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class notification_null extends AppCompatActivity {
MenuItem menuItem, menuItem1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_notification_null );
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater ().inflate ( R.menu.past_menu,menu );
        menuItem = menu.findItem ( R.id.buton_home );
        menuItem.setOnMenuItemClickListener ( new MenuItem.OnMenuItemClickListener ( ) {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId ()==R.id.buton_home){
                    Intent intent = new Intent ( notification_null.this, mainpage_activity.class );
                    startActivity ( intent );

                }
                return false;
            }
        } );
        menuItem1 = menu.findItem ( R.id.past_notify );
        menuItem1.setOnMenuItemClickListener ( new MenuItem.OnMenuItemClickListener ( ) {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId ()==R.id.past_notify){
                    Intent intent = new Intent ( notification_null.this, Past_Notification.class );
                    startActivity ( intent );
                }
                return false;
            }
        } );
        return super.onCreateOptionsMenu ( menu );
    }
}