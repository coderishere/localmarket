package com.example.connect;

import android.view.View;
import android.widget.TextView;

public class kart_counter {

    TextView counter_text;
    int  counter;
    public kart_counter(View view){
        counter_text = view.findViewById ( R.id.shopkeeper_kart );

    }
    public void increasenumber(){
        counter++;

        counter_text.setText ( String.valueOf ( counter ) );
    }
}
