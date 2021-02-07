package com.example.connect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class Past_Notification extends AppCompatActivity {
ListView listView;
ArrayList<String> arrayList_name, arrayList_price, arrayList_from, arrayList_stock;
TextView textView, textView1, textView2, textView3,textView4;
adapters csadapter;
Button past_availability;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_past__notification );

        arrayList_name = new ArrayList<> (  );
        arrayList_price = new ArrayList<> (  );
        arrayList_from= new ArrayList<> (  );
        arrayList_stock = new ArrayList<> (  );
        csadapter = new adapters ();

        listView = findViewById ( R.id.past_listview );
        ParseQuery<ParseObject>parseQuery = new ParseQuery<ParseObject> ( "Past_Notification" );
        parseQuery.whereEqualTo ( "parent", ParseUser.getCurrentUser () );
        parseQuery.findInBackground ( new FindCallback<ParseObject> ( ) {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e==null){
                    for(ParseObject object : objects){
                        arrayList_name.add ( object.getString ( "name" ) );
                        arrayList_price.add ( object.getString ( "price" ) );
                        arrayList_from.add ( object.getString ( "Buyfrom" ) );
                        arrayList_stock.add ( object.getString ( "stock" ) );
                    }
                    listView.setAdapter ( csadapter );
                }
            }
        } );
    }
    class adapters extends BaseAdapter{

        @Override
        public int getCount() {
            return arrayList_name.size ();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
          convertView=  getLayoutInflater ().inflate ( R.layout.past_notification_layout,null );
            textView = convertView.findViewById ( R.id.past_product );
            textView1= convertView.findViewById ( R.id.past_price );
            textView2 = convertView.findViewById ( R.id.past_stock );
            textView3= convertView.findViewById ( R.id.past_shopkeeper );
            textView4 = convertView.findViewById ( R.id.Note );
            past_availability = convertView.findViewById ( R.id.check_avail_button );

            textView.setText ( arrayList_name.get ( position ) );
            textView1.setText ("Rs. : "+ arrayList_price.get ( position ) );
            textView2.setText ( "ShopKeeper : "+arrayList_from.get ( position ) );
            textView3.setText ( "Was in Stock : "+arrayList_stock.get ( position ) );
            textView4.setText ( "Note: To Get recent updates of product Do Quick search" );
            past_availability.setOnClickListener ( new View.OnClickListener ( ) {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent ( Past_Notification.this, mainpage_activity.class );
                    startActivity ( intent );

                }
            } );
            return convertView;
        }
    }
}