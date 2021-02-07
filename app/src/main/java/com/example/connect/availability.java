package com.example.connect;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class availability extends AppCompatActivity {
ListView listView;
ParseQuery<ParseObject>parseObjectParseQuery;
ArrayList<String>NAMES,SHOP_PRICE,SHOP_OFFER,QUANTITY,object_ids,LINKED;
TextView product_name;
String policy,ifavailable,nameofproduct,objectsid,usernames,nameofproduct1,consumer_username;
String[] paths = {"Returnable", "Replaceable", "None"};
EditText shop_product_price,shop_product_offer,quantity;
Button submit,cancel;
Spinner spinner;
adapters1 csadpter;
ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_availability );

        progressDialog = new ProgressDialog ( availability.this );
        progressDialog.show ();
        NAMES = new ArrayList<> (  );
        SHOP_PRICE = new ArrayList<> (  );
        SHOP_OFFER = new ArrayList<> (  );
        QUANTITY = new ArrayList<> (  );
        object_ids = new ArrayList<> (  );
        LINKED = new ArrayList<> (  );

        csadpter = new adapters1 ();

        listView = findViewById ( R.id.available_listview );

        SharedPreferences sharedPreferences1 = PreferenceManager.getDefaultSharedPreferences ( this );
          nameofproduct = sharedPreferences1.getString ( "nameOfProduct", "" );
       SharedPreferences sharedPreferences2 = PreferenceManager.getDefaultSharedPreferences ( this );
        objectsid = sharedPreferences2.getString ( "objctid","" );
         Log.i ( "namoo",nameofproduct );
         SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences ( this );
         nameofproduct1 = sharedPreferences.getString ( "past_product_name","" );
            ParseQuery<ParseObject> newParsquery = new ParseQuery<ParseObject> ( "Check_Available_product" );
            parseObjectParseQuery = new ParseQuery<ParseObject> ( "User_Notifications" );
            parseObjectParseQuery.whereEqualTo ( "parent", ParseUser.getCurrentUser ());
            parseObjectParseQuery.whereMatchesKeyInQuery ( "names", "name_of_product", newParsquery );
            parseObjectParseQuery.whereMatchesKeyInQuery ( "objectId", "shopkeeper_product_id", newParsquery );
            parseObjectParseQuery.findInBackground ( new FindCallback<ParseObject> ( ) {
                @Override
                public void done(List<ParseObject> objects, ParseException e) {
                    if(e==null){
                        for (ParseObject object:objects){
                            NAMES.add ( object.getString ( "names" ) );
                            SHOP_OFFER.add ( object.getString ( "shop_offer" ) );
                            SHOP_PRICE.add ( object.getString ( "Shop_price" ) );
                            QUANTITY.add ( object.getString ( "product_quantity" ) );
                            object_ids.add ( object.getObjectId () );
                            LINKED.add ( object.getString ( "links" ) );
                        }
                        listView.setAdapter ( csadpter );
                    }
                }
            } );
        parseObjectParseQuery = new ParseQuery<ParseObject> ( "User_Notifications" );
        parseObjectParseQuery.whereEqualTo ( "parent", ParseUser.getCurrentUser ());
        parseObjectParseQuery.whereEqualTo ( "names", nameofproduct1 );
        parseObjectParseQuery.findInBackground ( new FindCallback<ParseObject> ( ) {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e==null){
                    for (ParseObject object:objects){
                        NAMES.add ( object.getString ( "names" ) );
                        SHOP_OFFER.add ( object.getString ( "shop_offer" ) );
                        SHOP_PRICE.add ( object.getString ( "Shop_price" ) );
                        QUANTITY.add ( object.getString ( "product_quantity" ) );

                    }
                    listView.setAdapter ( csadpter );
                }
            }
        } );

    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem menuItem = menu.findItem ( R.id.home_button );
        menuItem.setOnMenuItemClickListener ( new MenuItem.OnMenuItemClickListener ( ) {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId ()==R.id.home_button){
                    Intent intent = new Intent ( availability.this, Shopkeeper_mainpage.class );
                    startActivity ( intent );
                }
                return false;
            }
        } );
        return super.onCreateOptionsMenu ( menu );
    }*/

    class adapters1 extends BaseAdapter{

        @Override
        public int getCount() {
            return NAMES.size ();
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

            convertView = getLayoutInflater ().inflate ( R.layout.update_availability,null );
            shop_product_price = (EditText) convertView.findViewById ( R.id.shop_product_price1_avail );
            shop_product_offer = (EditText) convertView.findViewById ( R.id.shop_product_offer1_avail );
            spinner = convertView.findViewById ( R.id.policy_avail );
            quantity = (EditText) convertView.findViewById ( R.id.quantity1_avail );
            product_name = (TextView) convertView.findViewById ( R.id.name_avail );
           // product_price = (TextView) convertView.findViewById ( R.id.prices );
            submit = (Button) convertView.findViewById ( R.id.button3_avail );
            cancel = (Button) convertView.findViewById ( R.id.cancel1_avail );
            submit.setTag ( position );
            cancel.setTag ( position );

            product_name.setText ( NAMES.get ( position ) );
            shop_product_price.setText ( SHOP_PRICE.get ( position ) );
            shop_product_offer.setText ( SHOP_OFFER.get ( position ) );
            quantity.setText ( QUANTITY.get ( position ) );
             progressDialog.dismiss ();
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(availability.this,android.R.layout.simple_spinner_item,paths);

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener ( new AdapterView.OnItemSelectedListener ( ) {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    policy = parent.getSelectedItem ().toString ();

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            } );

            submit.setOnClickListener ( new View.OnClickListener ( ) {
                @Override
                public void onClick(View v) {
                    int remove_position = (int) v.getTag ();
                    NAMES.remove ( remove_position );
                    SHOP_OFFER.remove ( remove_position );
                    SHOP_PRICE.remove ( remove_position );
                    QUANTITY.remove ( remove_position );
                    v.setVisibility ( View.GONE );
                    notifyDataSetChanged ();

                    ParseQuery<ParseObject> newParsquery1 = new ParseQuery<ParseObject> ( "Check_Available_product" );
                    ParseQuery<ParseUser>parseUserParseQuery = ParseUser.getQuery ();
                    parseUserParseQuery.whereDoesNotExist ( "code" );
                    parseUserParseQuery.whereMatchesKeyInQuery ( "available","shopkeeper_product_id", newParsquery1 );
                    parseUserParseQuery.findInBackground ( new FindCallback<ParseUser> ( ) {
                        @Override
                        public void done(List<ParseUser> objects, ParseException e) {
                            if(e==null){
                                for(ParseUser user : objects){
                                    usernames = user.getString ( "username" );
                                    Log.i ( "usernamessd", usernames );

                                }
                            }
                        }
                    } );

                    ParseQuery<ParseObject> parseQuery = new ParseQuery<ParseObject> ( "User_Notifications" );
                    parseQuery.whereMatchesKeyInQuery ( "names", "name_of_product",newParsquery1 );
                    parseQuery.whereMatchesKeyInQuery ( "links","linked",newParsquery1 );
                    parseQuery.whereEqualTo ( "parent", ParseUser.getCurrentUser () );
                    parseQuery.getInBackground ( object_ids.get ( position ), new GetCallback<ParseObject> ( ) {
                        @Override
                        public void done(ParseObject object, ParseException e) {
                            if(e==null){
                                object.put ( "Shop_price", shop_product_price.getText ().toString () );
                                object.put ( "shop_offer", shop_product_offer.getText ().toString () );
                                object.put ( "product_quantity", quantity.getText ().toString ());
                                object.put ( "parent", ParseUser.getCurrentUser ( ) );
                                object.put ( "policies", policy );
                                object.add ( "queryiedfrom", usernames );
                                object.saveInBackground ( new SaveCallback ( ) {
                                    @Override
                                    public void done(ParseException e) {
                                        if (e == null) {
                                            Toast.makeText ( availability.this, "details sent ", Toast.LENGTH_LONG ).show ( );
                                        }
                                    }
                                } );
                            }
                        }
                    } );

                    if(NAMES.size ()==0){
                        Intent intent = new Intent ( availability.this, Shopkeeper_mainpage.class );
                        startActivity ( intent );
                    }

                }
            } );


            return convertView;
        }
    }
}
/*

 */