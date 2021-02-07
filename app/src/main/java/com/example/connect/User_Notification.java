package com.example.connect;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class User_Notification extends AppCompatActivity {

    ListView listView;
    ArrayList<String> product_name_array,shop_price_array,stock_array,offer_array,policy_array,address_array,shopkeeper_username_array,arraysize,product_name_array1,newarraylist,links_aaraylist;

    adapter sadapter;
    TextView product_name, shop_price,stock,shop_offer,policy,shop_address;
    ParseQuery<ParseObject> parseQuery,query,parseQuery1;
    ParseQuery<ParseUser> query1,query2;
    String shopid,names,objectids,Notifcationid;
    String[] naming;
    Button Buy,Cancel;
    Menu menu;
    ProgressDialog progressDialog;
    MenuItem menuItem,menuItem1;
   kart_counter KART_COUNTER;
    SharedPreferences preferences;
   int integer=0;
   ImageView imageView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater ().inflate ( R.menu.past_menu,menu );
       menuItem = menu.findItem ( R.id.buton_home );
       menuItem.setOnMenuItemClickListener ( new MenuItem.OnMenuItemClickListener ( ) {
           @Override
           public boolean onMenuItemClick(MenuItem item) {
               if(item.getItemId ()==R.id.buton_home){
                   Intent intent = new Intent ( User_Notification.this, mainpage_activity.class );
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
                    Intent intent = new Intent ( User_Notification.this, Past_Notification.class );
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
        setContentView ( R.layout.activity_user__notification );

        Log.i ( "integer value", String.valueOf ( integer ) );


        Log.i ( "object", ParseUser.getCurrentUser ().getObjectId () );
        progressDialog = new ProgressDialog ( User_Notification.this );
        progressDialog.show ();

        listView = findViewById ( R.id.LISTVIEW );
        product_name_array = new ArrayList<> (  );
        shop_price_array= new ArrayList<> (  );
        stock_array = new ArrayList<> (  );
        offer_array = new ArrayList<> (  );
        policy_array = new ArrayList<> (  );
        address_array = new ArrayList<> (  );
        links_aaraylist = new ArrayList<> (  );
        arraysize = new ArrayList<> (  );
        product_name_array1 = new ArrayList<> (  );
        shopkeeper_username_array = new ArrayList<> (  );
        newarraylist = new ArrayList<> (  );




       // KART_COUNTER = new kart_counter ( findViewById ( R.id.kart_layouts ));
        sadapter= new adapter ();

        parseQuery = new ParseQuery<ParseObject> ( "Notification" );
        parseQuery.whereEqualTo ( "parent", ParseUser.getCurrentUser () );
        parseQuery.findInBackground ( new FindCallback<ParseObject> ( ) {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e==null){
                    for(ParseObject object: objects){
                        product_name_array.add ( object.getString ( "name" ) );
                        objectids = object.getObjectId ();
                        Log.i ( "idobject",objectids );
                        newarraylist.add ( objectids );

                    }
                    query = new ParseQuery<ParseObject> ( "User_Notifications" );
                   query.whereMatchesKeyInQuery ( "links","link", parseQuery );
                   query.include ( "parent" );
                    query.findInBackground ( new FindCallback<ParseObject> ( ) {
                        @Override
                        public void done(List<ParseObject> objects, ParseException e) {
                            if(e==null){
                                for(ParseObject obj: objects){
                                 shop_price_array.add ( obj.getString ( "Shop_price" ) );
                                 stock_array.add ( obj.getString ( "product_quantity" ) );
                                 offer_array.add ( obj.getString ( "shop_offer" ) );
                                 product_name_array1.add ( obj.getString ( "names" ) );
                                 links_aaraylist.add ( obj.getString ( "links" ) );
                                 policy_array.add ( obj.getString ( "policies" ) );
                                 String addresses= String.valueOf ( obj.getParseObject ( "parent" ).get ( "address" ) );
                                 address_array.add ( addresses );

                                 String id = String.valueOf ( obj.getParseObject ( "parent" ).get ( "username" ) );
                                    shopkeeper_username_array.add ( id );

                                // shopkeeper_id_array.add ( obj.getString ( "parent" ) );

                                    naming = new String[shop_price_array.size ()];
                                    naming = shop_price_array.toArray (naming);
                                    for(String s : naming){
                                        names = s;
                                        Log.i ( "namess",names );
                                    }

                                }
                                listView.setAdapter ( sadapter );



                            }
                        }
                    } );

                }
            }
        } );
        parseQuery1 = new ParseQuery<ParseObject> ( "Ele_Notification" );
        parseQuery1.whereEqualTo ( "parent", ParseUser.getCurrentUser () );
        parseQuery1.findInBackground ( new FindCallback<ParseObject> ( ) {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e==null){
                    for(ParseObject object: objects){
                        product_name_array.add ( object.getString ( "name" ) );
                        objectids = object.getObjectId ();
                        Log.i ( "idobject",objectids );
                        newarraylist.add ( objectids );

                    }
                    query = new ParseQuery<ParseObject> ( "User_Notifications" );
                    query.whereMatchesKeyInQuery ( "links","link", parseQuery1 );
                    query.include ( "parent" );
                    query.findInBackground ( new FindCallback<ParseObject> ( ) {
                        @Override
                        public void done(List<ParseObject> objects, ParseException e) {
                            if(e==null){
                                for(ParseObject obj: objects){
                                    shop_price_array.add ( obj.getString ( "Shop_price" ) );
                                    stock_array.add ( obj.getString ( "product_quantity" ) );
                                    offer_array.add ( obj.getString ( "shop_offer" ) );
                                    product_name_array1.add ( obj.getString ( "names" ) );
                                    links_aaraylist.add ( obj.getString ( "links" ) );
                                    policy_array.add ( obj.getString ( "policies" ) );
                                    String addresses= String.valueOf ( obj.getParseObject ( "parent" ).get ( "address" ) );
                                    address_array.add ( addresses );

                                    String id = String.valueOf ( obj.getParseObject ( "parent" ).get ( "username" ) );
                                    shopkeeper_username_array.add ( id );

                                    // shopkeeper_id_array.add ( obj.getString ( "parent" ) );

                                    naming = new String[shop_price_array.size ()];
                                    naming = shop_price_array.toArray (naming);
                                    for(String s : naming){
                                        names = s;
                                        Log.i ( "namess",names );
                                    }

                                }
                                listView.setAdapter ( sadapter );



                            }
                        }
                    } );

                }
            }
        } );

    }

    class adapter extends BaseAdapter{

        @Override
        public int getCount() {
            return product_name_array1.size ();
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
            view = getLayoutInflater ().inflate ( R.layout.custom_layout2,null );
            product_name = view.findViewById ( R.id.PRODUCT_NAME);
            shop_price = view.findViewById ( R.id.SHOP_PRICE );
            stock = view.findViewById ( R.id.STOCK);
            shop_offer= view.findViewById ( R.id.SHOP_OFFER );
            policy= view.findViewById ( R.id.POLICY );
            shop_address = view.findViewById ( R.id.SHOP_ADRRESS );
            Buy = view.findViewById ( R.id.buy );
            Cancel= view.findViewById ( R.id.cancel1 );
            Buy.setTag ( position );
            Cancel.setTag ( position );


            product_name.setText ( product_name_array1.get ( position ) );
            shop_price.setText ( "shop_price"+": "+ "Rs."+shop_price_array.get ( position ) );
            stock.setText ( "Quantity : "+stock_array.get ( position ) );
            shop_offer.setText ( "offer on product : "+offer_array.get ( position ) );
            shop_address.setText ( "address : "+address_array.get ( position ) );
            policy.setText (  "Product is :" +policy_array.get ( position )  );
            progressDialog.dismiss ();
             preferences = PreferenceManager.getDefaultSharedPreferences(User_Notification.this);


            Buy.setOnClickListener ( new View.OnClickListener ( ) {
                @Override
                public void onClick(View v) {


                           ParsePush.unsubscribeInBackground ( links_aaraylist.get ( position ) );

                    JSONObject data = new JSONObject();
// Put data in the JSON object
                    try {
                        data.put("alert", "Ready your product!!");
                        data.put("title", "Product Purchased!!");
                    } catch ( JSONException e) {
                        // should not happen
                        throw new IllegalArgumentException("unexpected parsing error", e);
                    }
// Configure the push
                    ParsePush push = new ParsePush();
                    push.setChannel(shopkeeper_username_array.get ( position ));
                    push.setData(data);
                    push.sendInBackground();
                    ParsePush.subscribeInBackground ( ParseUser.getCurrentUser ().getUsername () );
                          query1 = ParseUser.getQuery ();
                          query1.getInBackground ( ParseUser.getCurrentUser ( ).getObjectId ( ), new GetCallback<ParseUser> ( ) {
                              @Override
                              public void done(ParseUser object, ParseException e) {
                                  if(e==null){
                                      shopid = shopkeeper_username_array.get ( position );
                                      object.add ( "ShopIdList", shopid );
                                      object.saveInBackground ();
                                  }
                               //   shopkeeper_username_array.remove ( positionToRemove );
                              }
                          } );
                          ParseObject Past_Notification = new ParseObject ( "Past_Notification" );
                          Past_Notification.put ( "price", shop_price_array.get ( position ));
                          Past_Notification.put ( "stock",stock_array.get ( position ) );
                          Past_Notification.put ( "offer",offer_array.get ( position ) );
                          Past_Notification.put ( "offer",address_array.get ( position ) );
                          Past_Notification.put ( "offer",policy_array.get ( position ) );
                          Past_Notification.put ( "parent",ParseUser.getCurrentUser () );
                          Past_Notification.put ( "Buyfrom", shopkeeper_username_array.get ( position ) );
                          Past_Notification.put ( "name", product_name_array1.get ( position ));
                          Past_Notification.saveInBackground ();



                        parseQuery.whereEqualTo ( "link", links_aaraylist.get ( position ) );
                        parseQuery.whereEqualTo ( "parent", ParseUser.getCurrentUser () );
                        parseQuery.findInBackground ( new FindCallback<ParseObject> ( ) {
                            @Override
                            public void done(List<ParseObject> objects, ParseException e) {
                                if(e==null){
                                    for(ParseObject object :objects){
                                        try {
                                            object.delete ();
                                            object.saveInBackground ();
                                        } catch (ParseException ex) {
                                            ex.printStackTrace ( );
                                        }
                                    }
                                }

                               // product_name_array1.remove ( positionToRemove );



                            }
                        } );
                    parseQuery1.whereEqualTo ( "link", links_aaraylist.get ( position ) );
                    parseQuery1.whereEqualTo ( "parent", ParseUser.getCurrentUser () );
                    parseQuery1.findInBackground ( new FindCallback<ParseObject> ( ) {
                        @Override
                        public void done(List<ParseObject> objects, ParseException e) {
                            if(e==null){
                                for(ParseObject object :objects){
                                    try {
                                        object.delete ();
                                        object.saveInBackground ();
                                    } catch (ParseException ex) {
                                        ex.printStackTrace ( );
                                    }
                                }
                            }

                            // product_name_array1.remove ( positionToRemove );



                        }
                    } );

                         Log.i ( "current",ParseUser.getCurrentUser ().getUsername () );

                        preferences.edit ().putString ( "newvalue",ParseUser.getCurrentUser ().getUsername () ).commit ();




                    Intent intent = new Intent ( User_Notification.this, mainpage_activity.class );
                    startActivity ( intent );
                     parent.setVisibility ( View.GONE );
                     notifyDataSetChanged ();

                }
            } );


         Cancel.setOnClickListener ( new View.OnClickListener ( ) {
             @Override
             public void onClick(View v) {

                 ParsePush.unsubscribeInBackground ( links_aaraylist.get ( position ) );
                 parseQuery.whereEqualTo ( "name", product_name_array1.get ( position ) );
                 parseQuery.whereEqualTo ( "parent", ParseUser.getCurrentUser () );
                 parseQuery.findInBackground ( new FindCallback<ParseObject> ( ) {
                     @Override
                     public void done(List<ParseObject> objects, ParseException e) {
                         if(e==null){
                             for(ParseObject object :objects){
                                 try {
                                     object.delete ();
                                     object.saveInBackground ();
                                 } catch (ParseException ex) {
                                     ex.printStackTrace ( );
                                 }
                             }
                         }

                         // product_name_array1.remove ( positionToRemove );



                     }
                 } );
                 parseQuery1.whereEqualTo ( "link", links_aaraylist.get ( position ) );
                 parseQuery1.whereEqualTo ( "parent", ParseUser.getCurrentUser () );
                 parseQuery1.findInBackground ( new FindCallback<ParseObject> ( ) {
                     @Override
                     public void done(List<ParseObject> objects, ParseException e) {
                         if(e==null){
                             for(ParseObject object :objects){
                                 try {
                                     object.delete ();
                                     object.saveInBackground ();
                                 } catch (ParseException ex) {
                                     ex.printStackTrace ( );
                                 }
                             }
                         }

                         // product_name_array1.remove ( positionToRemove );



                     }
                 } );

                 Intent intent = new Intent ( User_Notification.this, mainpage_activity.class );
                 startActivity ( intent );
                 parent.setVisibility ( View.GONE );
                 notifyDataSetChanged ();
             }
         } );


            return view;
        }
    }


}

/*
query1 = ParseUser.getQuery ();
                                query1.whereExists ( "code" );
                                query1.include ( "Users_Notifications" );
                                query1.findInBackground ( new FindCallback<ParseUser> ( ) {
                                    @Override
                                    public void done(List<ParseUser> objects, ParseException e) {
                                        if(e==null){
                                            for(ParseUser obj: objects){

                                                address_array.add ( obj.getString ( "address" ) );
                                            }

                                        }
                                    }
                                } );
                                                          */
/*

 v.setVisibility ( parent.GONE );
                        notifyDataSetChanged ( );
                        final int positionToRemove = (int)v.getTag ();
                        shop_price_array.remove ( positionToRemove );
                        stock_array.remove ( positionToRemove );
                        offer_array.remove ( positionToRemove );
                        address_array.remove ( positionToRemove );
                        policy_array.remove ( positionToRemove );




  ParseQuery<ParseUser> parseQuery2 = ParseUser.getQuery ();
                        parseQuery2.whereExists ( "code" );
                        parseQuery2.findInBackground ( new FindCallback<ParseUser> ( ) {
                            @Override
                            public void done(List<ParseUser> objects, ParseException e) {
                                if(e==null){
                                    for(ParseUser user : objects) {
                                        if (!user.getList ( "ids" ).isEmpty ( )) {
                                            user.getList ( "ids" ).remove ( ParseUser.getCurrentUser ( ).getUsername ( ) );
                                            List newlist = user.getList ( "ids" );
                                            user.remove ( "ids" );
                                            user.put ( "ids", newlist );
                                            user.saveInBackground ( );
                                        }
                                    }

                                }
                            }
                        } );
 */