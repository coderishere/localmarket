package com.example.connect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class user_kart_null extends AppCompatActivity {
MenuItem menuItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_user_kart_null );
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater ( ).inflate ( R.menu.past_menu, menu );
        menuItem = menu.findItem ( R.id.buton_home );
        menuItem.setOnMenuItemClickListener ( new MenuItem.OnMenuItemClickListener ( ) {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId ( ) == R.id.buton_home) {
                    Intent intent = new Intent ( user_kart_null.this, mainpage_activity.class );
                    startActivity ( intent );

                }
                return false;
            }
        } );
        return super.onCreateOptionsMenu ( menu );
    }

}