package com.example.connect;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
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
import com.parse.ParsePush;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Notification_shop extends AppCompatActivity implements View.OnClickListener {
    ListView listView;
    ProgressDialog progressDialog;
    String gets,objectid,sat,policy;
    ArrayList<String> prices,product_name_array,Notification_id,parentid,links_array,newdata;
    String[] stockArr;
    String[] paths = {"Returnable", "Replaceable", "None"};
    EditText shop_product_price, shop_product_offer,quantity ;
    TextView username, product_name, product_price,links;
    Spinner spinner;
    Button submit, cancel;
    ParseQuery<ParseObject> query,query1;
    ParseQuery<ParseUser> parseQuery,parseQuery1;
    ParseObject user_notifications;
    adapter csadapter;
    int i,j;
    MenuItem menuItem;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater ().inflate ( R.menu.home_button,menu );
        menuItem = menu.findItem ( R.id.home_button );
        menuItem.setOnMenuItemClickListener ( new MenuItem.OnMenuItemClickListener ( ) {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId ()==R.id.home_button){
                   Intent intent = new Intent ( Notification_shop.this, Shopkeeper_mainpage.class );
                   startActivity ( intent );
                }
                return false;
            }
        } );
        return super.onCreateOptionsMenu ( menu );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_notification_shop );
        prices = new ArrayList<> ( );
        product_name_array = new ArrayList<> ( );
        Notification_id = new ArrayList<> ( );
        parentid = new ArrayList<> ( );
        links_array = new ArrayList<> ( );
        newdata = new ArrayList<> (  );

        listView = findViewById ( R.id.listview );
        csadapter = new adapter ( );
         progressDialog = new ProgressDialog ( Notification_shop.this );
        progressDialog.show ();

        if (ParseUser.getCurrentUser ( ).getString ( "cateogory" ).equals ( "Book" )) {
            parseQuery = ParseUser.getQuery ( );
            //parseQuery.selectKeys ( Arrays.asList ( "list" ) );
            parseQuery.whereExists ( "code" );
            parseQuery.whereExists ( "list" );
            parseQuery.whereEqualTo ( "cateogory", "Book" );
            parseQuery.getInBackground ( ParseUser.getCurrentUser ( ).getObjectId ( ), new GetCallback<ParseUser> ( ) {
                @Override
                public void done(ParseUser object, ParseException e) {
                    if (e == null) {
                        ArrayList<String> userTastesGot = (ArrayList<String>) object.get ( "list" );
                        stockArr = new String[userTastesGot.size ( )];

                        stockArr = userTastesGot.toArray ( stockArr );
                        for (String s : stockArr) {
                            sat = s;
                            newdata.add ( sat );
                        }
                        Log.i ( "newdatasds", String.valueOf ( newdata ) );


                            query = new ParseQuery<ParseObject> ( "Notification" );
                            query.include ( "parent" );
                            query.addAscendingOrder ( "price" );
                            query.whereContainedIn ( "objectId", newdata );
                            query.findInBackground ( new FindCallback<ParseObject> ( ) {
                                @Override
                                public void done(List<ParseObject> objects, ParseException e) {
                                    if (e == null) {

                                        for (ParseObject object : objects) {
                                            prices.add ( object.getString ( "price" ) );
                                            product_name_array.add ( object.getString ( "name" ) );
                                            links_array.add ( object.getString ( "link" ) );
                                            String user_id = String.valueOf ( object.getParseObject ( "parent" ).get ( "username" ) );
                                            parentid.add ( user_id );
                                            gets = object.getObjectId ( );

                                            Notification_id.add ( gets );
                                            Log.i ( "mess", gets );
                                        }


                                        listView.setAdapter ( csadapter );

                                    } else {
                                        e.printStackTrace ( );
                                    }
                                }
                            } );





                        Log.i ( "User", "Retrieved " + userTastesGot );
                        Log.i ( "name", ParseUser.getCurrentUser ( ).getUsername ( ) );
                    }
                }
            } );
        } if(ParseUser.getCurrentUser ().getString ( "cateogory" ).equals ( "Electronics" )) {

            parseQuery = ParseUser.getQuery ( );
            parseQuery.whereExists ( "code" );
            parseQuery.selectKeys ( Arrays.asList ( "list" ) );
            parseQuery.whereEqualTo ( "cateogory", "Electronics" );
            parseQuery.getInBackground ( ParseUser.getCurrentUser ( ).getObjectId ( ), new GetCallback<ParseUser> ( ) {
                @Override
                public void done(ParseUser object, ParseException e) {
                    if (e == null) {
                        ArrayList<String> userTastesGot = (ArrayList<String>) object.get ( "list" );
                        stockArr = new String[userTastesGot.size ( )];

                        stockArr = userTastesGot.toArray ( stockArr );
                        ArrayList<String> arrayList = new ArrayList<> (  );
                        for (String s : stockArr) {


                            arrayList.add ( s );

                        }

                            query = new ParseQuery<ParseObject> ( "Ele_Notification" );
                            query.include ( "parent" );
                            query.addAscendingOrder ( "price" );
                            query.whereContainedIn ( "objectId", arrayList );
                            query.findInBackground ( new FindCallback<ParseObject> ( ) {
                                @Override
                                public void done(List<ParseObject> objects, ParseException e) {
                                    if (e == null) {

                                        for (ParseObject object : objects) {
                                            prices.add ( object.getString ( "price" ) );
                                            product_name_array.add ( object.getString ( "name" ) );
                                            links_array.add ( object.getString ( "link" ) );
                                            String user_id = String.valueOf ( object.getParseObject ( "parent" ).get ( "username" ) );
                                            parentid.add ( user_id );
                                            gets = object.getObjectId ( );

                                            Notification_id.add ( gets );
                                            Log.i ( "mess", gets );
                                        }


                                        listView.setAdapter ( csadapter );

                                    } else {
                                        e.printStackTrace ( );
                                    }
                                }
                            } );





                    }
                }
            } );


        }
    }

    @Override
    public void onClick(View v) {

    }

    class adapter extends BaseAdapter {

        @Override
        public int getCount() {
            return links_array.size ();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View view, final ViewGroup parent) {
            view = getLayoutInflater ( ).inflate ( R.layout.custom1_layout, null);
            shop_product_price = (EditText) view.findViewById ( R.id.shop_product_price1 );
            shop_product_offer = (EditText) view.findViewById ( R.id.shop_product_offer1 );
            spinner = view.findViewById ( R.id.policy );
            quantity = (EditText) view.findViewById ( R.id.quantity1 );
            product_name = (TextView) view.findViewById ( R.id.name );
            product_price = (TextView) view.findViewById ( R.id.prices );
            links= view.findViewById ( R.id.link );
            submit = (Button) view.findViewById ( R.id.button3 );
            cancel = (Button) view.findViewById ( R.id.cancel1 );
            submit.setTag ( position );
            cancel.setTag ( position );
            shop_product_price.setTag ( position );
            shop_product_offer.setTag ( position );
            quantity.setTag ( position );
            Linkify.addLinks(links, Linkify.WEB_URLS);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(Notification_shop.this,android.R.layout.simple_spinner_item,paths);

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



            product_name.setText ( product_name_array.get ( position ) );
           product_price.setText ( prices.get ( position ) );
           links.setText ( links_array.get ( position ) );
           progressDialog.dismiss ();
            user_notifications = new ParseObject ( "User_Notifications" );
            submit.setOnClickListener (  new View.OnClickListener ( ) {

                @Override
                public void onClick(View v) {
                    SharedPreferences sharedPreferencescheck = PreferenceManager.getDefaultSharedPreferences ( Notification_shop.this );
                    sharedPreferencescheck.edit ( ).putString ( "Storing", "checking" ).commit ( );


                    if (shop_product_offer.getText ( ).toString ( ).matches ( "" ) || shop_product_price.getText ( ).toString ( ).matches ( "" )) {
                        Toast.makeText ( Notification_shop.this, "details missing", Toast.LENGTH_SHORT ).show ( );
                    } else{
                        user_notifications.put ( "Shop_price", shop_product_price.getText ( ).toString ( ) );
                    user_notifications.put ( "shop_offer", shop_product_offer.getText ( ).toString ( ) );
                    user_notifications.put ( "product_quantity", quantity.getText ( ).toString ( ) );
                    user_notifications.put ( "parent", ParseUser.getCurrentUser ( ) );
                    user_notifications.put ( "usernames", ParseUser.getCurrentUser ( ).getUsername ( ) );
                    user_notifications.put ( "policies", policy );
                    user_notifications.put ( "names", product_name_array.get ( position ) );
                    user_notifications.put ( "queryfrom", parentid.get ( position ) );
                    user_notifications.put ( "links", links_array.get ( position ) );
                    user_notifications.saveInBackground ( new SaveCallback ( ) {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                Toast.makeText ( Notification_shop.this, "details sent ", Toast.LENGTH_LONG ).show ( );

                            }
                        }
                    } );
                        JSONObject data = new JSONObject();
// Put data in the JSON object
                        try {
                            data.put("alert", "check the details!!");
                            data.put("title", "Product is available");
                        } catch ( JSONException e) {
                            // should not happen
                            throw new IllegalArgumentException("unexpected parsing error", e);
                        }
// Configure the push
                        ParsePush push = new ParsePush();
                        push.setChannel(links_array.get ( position ));
                        push.setData(data);
                        push.sendInBackground();

                        ParsePush.subscribeInBackground ( ParseUser.getCurrentUser ().getUsername () );

                  /*  gets = product_name_array.get( position );
                    Log.i ( "position",gets );
                    query.whereEqualTo ( "name",gets );
                    query.findInBackground ( new FindCallback<ParseObject> ( ) {
                        @Override
                        public void done(List<ParseObject> objects, ParseException e) {
                            if(e==null ){
                                for (ParseObject obj: objects){
                                    obj.deleteInBackground ();
                                }
                            }else {

                            }
                        }
                    } );*/

                    // Toast.makeText ( Notification_shop.this, "button is clicked", Toast.LENGTH_SHORT ).show ( );


                    final int positionToRemove = (int) v.getTag ( );
                    product_name_array.remove ( positionToRemove );
                    prices.remove ( positionToRemove );
                    links_array.remove ( positionToRemove );
                    if (product_name_array.size ( ) == 0) {
                        Intent intent = new Intent ( Notification_shop.this, Shopkeeper_mainpage.class );
                        startActivity ( intent );
                    }

                    objectid = Notification_id.get ( position );
                    for (String t : stockArr) {
                        if (objectid.matches ( t )) {
                            ParseUser.getCurrentUser ( ).getList ( "list" ).remove ( objectid );
                            List newlist = ParseUser.getCurrentUser ( ).getList ( "list" );
                            ParseUser.getCurrentUser ( ).remove ( "list" );
                            ParseUser.getCurrentUser ( ).put ( "list", newlist );
                            ParseUser.getCurrentUser ( ).saveInBackground ( );
                            Notification_id.remove ( positionToRemove );
                        } else {
                            //do nothing
                        }
                    }


                    v.setVisibility ( View.GONE );
                    notifyDataSetChanged ( );
                }
                    }


            } );

            cancel.setOnClickListener ( new View.OnClickListener ( ) {
                @Override
                public void onClick(View v){
                        int positionToRemove1 = (int) v.getTag ( );
                        product_name_array.remove ( positionToRemove1 );
                        prices.remove ( positionToRemove1 );
                        parentid.remove ( positionToRemove1 );
                        links_array.remove ( positionToRemove1 );
                        objectid = Notification_id.get ( position );
                        for (String t : stockArr) {
                            if (objectid.matches ( t )) {
                                Log.i ( "drive", "well done" );
                                ParseUser.getCurrentUser ( ).getList ( "list" ).remove ( objectid );
                                List newlist = ParseUser.getCurrentUser ( ).getList ( "list" );
                                ParseUser.getCurrentUser ( ).remove ( "list" );
                                ParseUser.getCurrentUser ( ).put ( "list", newlist );
                                ParseUser.getCurrentUser ( ).saveInBackground ( );
                                Notification_id.remove ( positionToRemove1 );
                            }

                        }
                        if (product_name_array.size ( ) == 0) {
                            Intent intent = new Intent ( Notification_shop.this, Shopkeeper_mainpage.class );
                            startActivity ( intent );
                        }
                        v.setVisibility ( View.GONE );
                        notifyDataSetChanged ( );

                }
            } );


            return view;

        }

    }

}