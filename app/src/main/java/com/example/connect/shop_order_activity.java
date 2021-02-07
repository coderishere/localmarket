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
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
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
import java.util.List;

public class shop_order_activity extends AppCompatActivity {
TextView Full_Name,Landmark,phone_Number,Address1;
ParseQuery<ParseUser> parseQuery,parseQuery7;
ArrayList<String> fullnames,phoneNumbers,landmarks,addresses1,user_username,user_ids,object_id,usernames;
ArrayList<String> getFullnames, getPhoneNumbers,getAddresses1,getLandmarks,getUser_username,getUser_ids,getObject_id;
Button Accept, cancel;
String[] stockArr ;
adapter csadapter;
int positionToRemove;
ListView listView;
ProgressDialog progressDialog;
String string,abc,object_idss;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_shop_order_activity );

        listView = findViewById ( R.id.shop_order_list );

        string = ParseUser.getCurrentUser ().getUsername ();
        Log.i ( "string",string );
        progressDialog = new ProgressDialog ( shop_order_activity.this );
        progressDialog.show ();
        fullnames =new ArrayList<> (  );
        phoneNumbers = new ArrayList<> (  );
        landmarks = new ArrayList<> (  );
        addresses1 = new ArrayList<> (  );
        user_username = new ArrayList<> (  );
        user_ids = new ArrayList<> (  );
        object_id = new ArrayList<> (  );
        usernames = new ArrayList<> (  );

        getFullnames =new ArrayList<> (  );
        getPhoneNumbers=new ArrayList<> (  );
        getLandmarks =new ArrayList<> (  );
        getAddresses1 =new ArrayList<> (  );
        getUser_username =new ArrayList<> (  );
        csadapter = new adapter ();

         preferences = PreferenceManager.getDefaultSharedPreferences ( this );

     /*  ParseQuery<ParseObject>parseQuery_past = new ParseQuery<ParseObject> ( "Past_Notification" );
       parseQuery_past.whereEqualTo ( "Buyfrom", ParseUser.getCurrentUser ().getUsername () );
       parseQuery_past.include ( "parent" );
       parseQuery_past.findInBackground ( new FindCallback<ParseObject> ( ) {
           @Override
           public void done(List<ParseObject> objects, ParseException e) {
               if(e==null){
                   for(ParseObject object: objects){
                    fullnames.add (   object.getParseObject ( "parent" ).getString ( "fullname" ));
                    getFullnames.add ( object.getParseObject ( "parent" ).getString ( "fullname" ) );

                       landmarks.add ( object.getParseObject ( "parent" ).getString ( "landmark" ) );
                       getLandmarks.add ( object.getParseObject ( "parent" ).getString ( "landmark" ) );

                       phoneNumbers.add ( object.getParseObject ( "parent" ).getString ( "user_phone" ) );
                       getPhoneNumbers.add ( object.getParseObject ( "parent" ).getString ( "user_phone" ) );

                       addresses1.add ( object.getParseObject ( "parent" ).getString ( "address1" ) );
                       getAddresses1.add ( object.getParseObject ( "parent" ).getString ( "address1" ) );

                       user_username.add ( object.getParseObject ( "parent" ).getString ( "username" ) );
                       getUser_username.add ( object.getParseObject ( "parent" ).getString ( "username" ) );

                       object_id.add ( object.getObjectId () );
                       usernames.add ( object.getString ( "username" ) );

                   }
                   listView.setAdapter ( csadapter );
               }
           }
       } );*/



        parseQuery = ParseUser.getQuery ();
        parseQuery.whereExists ( "ShopIdList"  );
        parseQuery.findInBackground ( new FindCallback<ParseUser> ( ) {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                if(e==null&&objects.size ()>0){
                    for(ParseObject object : objects){


                        ArrayList<String> userTastesGot = (ArrayList<String>) object.get("ShopIdList");
                        stockArr = new String[userTastesGot.size()];

                        stockArr = userTastesGot.toArray(stockArr);
                        for( String s : stockArr) {
                            abc = s;
                         if(abc.equals ( ParseUser.getCurrentUser ().getUsername () )){
                             fullnames.add ( object.getString ( "fullname" ) );
                             getFullnames.add ( object.getString ( "fullname" ) );

                             landmarks.add ( object.getString ( "landmark" ) );
                             getLandmarks.add ( object.getString ( "landmark" ) );

                             phoneNumbers.add ( object.getString ( "user_phone" ) );
                             getPhoneNumbers.add ( object.getString ( "user_phone" ) );

                             addresses1.add ( object.getString ( "address1" ) );
                             getAddresses1.add ( object.getString ( "address1" ) );

                             user_username.add ( object.getString ( "username" ) );
                             getUser_username.add ( object.getString ( "username" ) );

                             object_id.add ( object.getObjectId () );
                             usernames.add ( object.getString ( "username" ) );

                         }


                         }

                    }


                    listView.setAdapter ( csadapter );

                    }
            }
        } );

    }



    class adapter extends BaseAdapter{

        @Override
        public int getCount() {
            return fullnames.size ();
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
        public View getView(final int position, View view, final ViewGroup parent) {

           view =  getLayoutInflater ().inflate ( R.layout.shop_order_layout, null );
           Full_Name = view.findViewById ( R.id.Full_Name );
           Landmark = view.findViewById ( R.id.Landmark );
           phone_Number = view.findViewById ( R.id.Phone_Number );
           Address1 = view.findViewById ( R.id.Address1 );
           Accept = view.findViewById ( R.id.accept );
           cancel = view.findViewById ( R.id.cancel3 );
           Accept.setTag ( position );
           cancel.setTag ( position );

           Log.i ( "view", String.valueOf ( fullnames.size () ) );

           Full_Name.setText ( "NAME : "+ fullnames.get ( position ) );
           Landmark.setText ("Landmark : " +landmarks.get ( position ) );
           phone_Number.setText ( "PhoneNumber :"+phoneNumbers.get ( position ) );
           Address1.setText ( "Address : "+addresses1.get ( position ) );
           object_idss = object_id.get ( position );
           progressDialog.dismiss ();
           Log.i ( "givemeid", object_idss );
             parseQuery7 = ParseUser.getQuery ();
           Accept.setOnClickListener ( new View.OnClickListener ( ) {

               @Override
               public void onClick(View v) {

                   ParsePush.unsubscribeInBackground ( ParseUser.getCurrentUser ().getUsername () );

                   JSONObject data = new JSONObject();
// Put data in the JSON object
                   try {
                       data.put("alert", "your current order has accepted!!");
                       data.put("title", "Order accepted!!");
                   } catch ( JSONException e) {
                       // should not happen
                       throw new IllegalArgumentException("unexpected parsing error", e);
                   }
// Configure the push
                   ParsePush push = new ParsePush();
                   push.setChannel(getUser_username.get ( position ));
                   push.setData(data);
                   push.sendInBackground();

                   String users_username = getUser_username.get ( position );
                   String fullname =  getFullnames.get ( position );
                   String address = getAddresses1.get ( position );
                   String landmark = getLandmarks.get ( position );
                   String phone  = getPhoneNumbers.get ( position );
                   String shop_address = ParseUser.getCurrentUser ().getString ( "address" );
                   String shop_phone =  ParseUser.getCurrentUser ().getString ( "PhoneNumber" );

                   SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences ( shop_order_activity.this );
                   SharedPreferences.Editor editor = sharedPreferences.edit ();
                   editor.putString ( "accepted", users_username );
                   editor.commit ();
                 Log.i ( "username", users_username );



                   ParseQuery<ParseUser> parseQuery6 = ParseUser.getQuery ();
                   parseQuery6.getInBackground ( ParseUser.getCurrentUser ( ).getObjectId ( ), new GetCallback<ParseUser> ( ) {
                       @Override
                       public void done(ParseUser object, ParseException e) {
                           if(e==null){
                               object.saveInBackground ();
                           }
                       }
                   } );

                   ParseObject parseObject = new ParseObject ( "Order_Accepted" );
                   parseObject.put ( "parent", ParseUser.getCurrentUser () );
                   parseObject.put ( "order_from",  users_username);
                   parseObject.put ( "deliveryby", "localmarket" );
                   parseObject.saveInBackground ();
                 /*  parseQuery7.getInBackground (object_id.get ( position ) , new GetCallback<ParseUser> ( ) {
                       @Override
                       public void done(ParseUser object, ParseException e) {
                           if(e==null){

                                      object.getList ( "ShopIdList" ).remove ( string );
                                       List newlist = object.getList ( "ShopIdList" );
                                       object.remove ( "ShopIdList" );
                                       object.put ( "ShopIdList", newlist );
                                       Log.i ( "chckingobject", String.valueOf ( object ) );
                                       object.put ( "new","chdck" );
                                       object.saveInBackground ( new SaveCallback ( ) {
                                           @Override
                                           public void done(ParseException e) {
                                               if(e==null){
                                                   Toast.makeText ( shop_order_activity.this, "removed", Toast.LENGTH_SHORT ).show ( );
                                               }
                                           }
                                       } );


                           }
                       }
                   } );
*/

                   ParseObject parseObjectdeliver = new ParseObject ( "Delivery_data" );
                   parseObjectdeliver.put ( "name",fullname );
                   parseObjectdeliver.put ( "delivered", address );
                   parseObjectdeliver.put ( "reference", landmark );
                   parseObjectdeliver.put ( "coustmerphone", phone);
                   parseObjectdeliver.put ( "username", user_username.get ( position ) );
                   parseObjectdeliver.put ( "shopkeeper_address",shop_address  );
                   parseObjectdeliver.put ( "shopkeeper_no", shop_phone);
                   parseObjectdeliver.saveInBackground ();


                  int positionToRemove = (int) v.getTag ( );
                   fullnames.remove ( positionToRemove );
                   landmarks.remove ( positionToRemove );
                   phoneNumbers.remove ( positionToRemove );
                   addresses1.remove ( positionToRemove );
                   Log.i ( "view1", String.valueOf ( fullnames.size () ) );
                   v.setVisibility ( View.GONE );
                   notifyDataSetChanged ();
                  if(fullnames.size ()==0){

                    Intent intent1 = new Intent ( shop_order_activity.this, Shopkeeper_mainpage.class );
                    startActivity ( intent1 );
                    SharedPreferences shop_order_size = PreferenceManager.getDefaultSharedPreferences ( shop_order_activity.this );
                    shop_order_size.edit ().putString ( "oder_size", "0" ).commit ();
                  }else {
                      Intent intent1 = new Intent ( shop_order_activity.this, Shopkeeper_mainpage.class );
                      intent1.putExtra ( "order_size", "0" );
                      startActivity ( intent1 );
                  }
               }
           } );
cancel.setOnClickListener ( new View.OnClickListener ( ) {
    @Override
    public void onClick(View v) {
        String users_username = user_username.get ( position );

        ParseObject parseObject = new ParseObject ( "Cancel_Product" );
        parseObject.put ( "cousumer_name", users_username );
        parseObject.put ( "cancel_from", ParseUser.getCurrentUser ().getUsername () );
        parseObject.saveInBackground ();


        int positionToRemove = (int) v.getTag ( );
        fullnames.remove ( positionToRemove );
        landmarks.remove ( positionToRemove );
        phoneNumbers.remove ( positionToRemove );
        addresses1.remove ( positionToRemove );

        v.setVisibility ( View.GONE );
        notifyDataSetChanged ();
    }
} );

            return view;
        }
    }
}
/*

                        Log.i ( "fullname", String.valueOf (  landmarks.add ( object.getString ( "landmark" ) )));


 */
/*
 ParseQuery<ParseUser> parseQuery = ParseUser.getQuery ();
        parseQuery.whereDoesNotExist ( "code" );
        parseQuery.getInBackground ( object_id.get ( position ), new GetCallback<ParseUser> ( ) {
            @Override
            public void done(ParseUser object, ParseException e) {
                if(e==null){
                    object.getList ( "delivery" ).remove ( "localmarket" );
                    List list = object.getList ( "delivery" );
                    object.remove ( "delivery" );
                    object.put ( "delivery", list );
                    object.saveInBackground ();
                }
            }
        } );
 */