package com.example.connect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
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

public class replace_product extends AppCompatActivity {
    ListView listView;
    SharedPreferences sharedPreferences_userskart1;
    TextView textView,textView1,textView2;
    ArrayList<String> return_product_array, usernames_array, Address_array;
    adapter csadapter;
    Button done;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_return_product );

        return_product_array = new ArrayList<> (  );
        usernames_array = new ArrayList<> (  );
        Address_array = new ArrayList<> (  );
        listView = findViewById ( R.id.return_listview );
        csadapter = new adapter ();
        ParseQuery<ParseObject> parseQuery = new ParseQuery<ParseObject> ( "Replaced_product" );
        parseQuery.whereEqualTo ( "shop_user", ParseUser.getCurrentUser ().getUsername () );
        parseQuery.findInBackground ( new FindCallback<ParseObject> ( ) {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e==null&&objects.size ()>0){
                    for(ParseObject object:objects){
                        return_product_array.add ( object.getString ( "productName" ) );
                        usernames_array.add ( object.getString ( "username" ) );
                        Address_array.add ( object.getString ( "address" ) );

                    }
                    listView.setAdapter ( csadapter );
                }
            }
        } );
    }
    class  adapter extends BaseAdapter{

        @Override
        public int getCount() {
            return return_product_array.size ();
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
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater ().inflate ( R.layout.replace_layout,null );
            textView = convertView.findViewById ( R.id.replace_product );
            textView1 = convertView.findViewById ( R.id.replace_from );
            textView2 = convertView.findViewById ( R.id.address_replace );
            done = convertView.findViewById ( R.id.done1 );
            textView.setText ( return_product_array.get ( position ) );
            textView1.setText ("Name :"+usernames_array.get ( position ) );
            textView2.setText ( "Address :"+Address_array.get ( position ) );

            done.setOnClickListener ( new View.OnClickListener ( ) {
                @Override
                public void onClick(View v) {
                    sharedPreferences_userskart1.edit ().clear ().commit ();
                }
            } );

            return convertView;
        }
    }
}