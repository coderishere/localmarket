package com.example.connect;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.util.Linkify;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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
import java.util.List;

public class quick_sarch extends AppCompatActivity {
ListView quick_listview;
EditText product_search;
ParseQuery<ParseObject>parseQuery;
ArrayList<String>PRODUCT_NAME,SHOP_PRICE,STOCK,SHOP_OFFER,SHOP_ADDRESS,POLICY,USERNAME,OBJECT_ID,LINKS,shop_number;
TextView shop_price,stock,shop_offer,shop_address,productname,policy,shop_no,linksText;
adapters csadpter;
ProgressDialog progressDialog,progressDialog1;
Button check_availability_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_quick_sarch );
        quick_listview = findViewById ( R.id.listview_quick );

        progressDialog = new ProgressDialog ( quick_sarch.this );
        progressDialog1 = new ProgressDialog ( quick_sarch.this );


        SHOP_PRICE = new ArrayList<> (  );
        STOCK = new ArrayList<> (  );
        SHOP_OFFER = new ArrayList<> (  );
        SHOP_ADDRESS = new ArrayList<> (  );
        PRODUCT_NAME = new ArrayList<> (  );
        POLICY = new ArrayList<> (  );
        USERNAME = new ArrayList<> (  );
        OBJECT_ID = new ArrayList<> (  );
        LINKS = new ArrayList<> (  );
        shop_number = new ArrayList<> (  );
        csadpter = new adapters ();

        Intent intent = getIntent ();
        final String tosearch = intent.getStringExtra ( "productForSearch" );
        Log.i ( "checking", tosearch );
        parseQuery = new ParseQuery<ParseObject> ( "User_Notifications" );
        parseQuery.whereMatches ( "names",tosearch, "i" );
        parseQuery.addAscendingOrder ( "Shop_price" );
        parseQuery.include ( "parent" );
        parseQuery.findInBackground ( new FindCallback<ParseObject> ( ) {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e==null){

                    for(ParseObject object: objects){
                        if(object.getString ( "names" ).equals ( tosearch )){
                            progressDialog.setTitle ( "receiving data" );
                            progressDialog.setMessage ( "Please wait..." );
                            progressDialog.show ();
                        }else {
                            progressDialog1.setTitle ( "receiving data" );
                            progressDialog1.setMessage ( "Please wait..." );
                            progressDialog1.show ();
                        }
                        SHOP_PRICE.add ( object.getString ( "Shop_price" ) );
                        PRODUCT_NAME.add ( object.getString ( "names" ) );
                        LINKS.add ( object.getString ( "links" ) );
                        STOCK.add ( object.getString ( "product_quantity" ) );
                        SHOP_OFFER.add (object.getString ( "shop_offer" ));
                        String objectid = object.getObjectId ();
                        OBJECT_ID.add ( objectid );
                        String addresses= String.valueOf ( object.getParseObject ( "parent" ).get ( "address" ) );
                        String usernames = String.valueOf ( object.getParseObject ( "parent" ).get ( "username" ) );
                        String shop_numbr = String.valueOf ( object.getParseObject ( "parent" ).get ( "PhoneNumber" ) );

                        SHOP_ADDRESS.add ( addresses );
                        shop_number.add ( shop_numbr );
                        USERNAME.add ( usernames );
                        POLICY.add ( object.getString ( "policies" ) );
                    }
                    quick_listview.setAdapter ( csadpter );

                }
            }
        } );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater ().inflate ( R.menu.home_button,menu );
        MenuItem menuItem = menu.findItem ( R.id.home_button );
        menuItem.setOnMenuItemClickListener ( new MenuItem.OnMenuItemClickListener ( ) {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId ()==R.id.home_button){
                    Intent intent = new Intent ( quick_sarch.this, mainpage_activity.class );
                    startActivity ( intent );
                }
                return false;
            }
        } );
        return super.onCreateOptionsMenu ( menu );
    }

    class adapters extends BaseAdapter{

        @Override
        public int getCount() {
           return SHOP_PRICE.size ();
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
            //ArrayList<String>PRODUCT_NAME,SHOP_PRICE,STOCK,SHOP_OFFER,SHOP_ADDRESS;
          progressDialog1.dismiss ();
          view = getLayoutInflater ().inflate ( R.layout.quick_button_search ,null);
          shop_price = view.findViewById ( R.id. SHOP_PRICE_quick);
          stock = view.findViewById ( R.id.STOCK_quick );
          shop_offer = view.findViewById ( R.id.SHOP_OFFER_quick );
          shop_address = view.findViewById ( R.id.SHOP_ADRRESS_quick );
          shop_no = view.findViewById ( R.id.Shop_no );
          productname = view.findViewById ( R.id.PRODUCT_NAME_quick );
          linksText = view.findViewById ( R.id.links_text );
            Linkify.addLinks(linksText, Linkify.WEB_URLS);
          policy = view.findViewById ( R.id.POLICY_quick );
            check_availability_button = view.findViewById ( R.id.check_availability );
            check_availability_button.setTag ( position );



                shop_price.setText ( "Rs. : " + SHOP_PRICE.get ( position ) );
                stock.setText ( "Quantity : " + STOCK.get ( position ) );
                shop_offer.setText ( "Offer : " + SHOP_OFFER.get ( position ) );
                shop_address.setText ( "Address : " + SHOP_ADDRESS.get ( position ) );
                productname.setText ( PRODUCT_NAME.get ( position ) );
                policy.setText ( "product : " + POLICY.get ( position ) );
                shop_no.setText ( "shopNo. :" + shop_number.get ( position ) );
                linksText.setText ( LINKS.get ( position ) );
            progressDialog.dismiss ();






            check_availability_button.setOnClickListener ( new View.OnClickListener ( ) {
              @Override
              public void onClick(View v) {


                 /* ParseQuery<ParseObject> avail_check = new ParseQuery<ParseObject> ( "User_Notifications" );
                  avail_check.getInBackground ( object, new GetCallback<ParseObject> ( ) {
                      @Override
                      public void done(ParseObject object, ParseException e) {
                          if(e==null){
                              object.put ( "queryfrom", ParseUser.getCurrentUser ().getUsername () );
                              object.saveInBackground ();
                          }
                      }
                  } );
*/
                  ParseObject parseObject = new ParseObject ( "Check_Available_product" );
                  parseObject.put ( "Shopkeeper_username", USERNAME.get ( position ) );
                  parseObject.put ( "name_of_product", PRODUCT_NAME.get ( position ) );
                  parseObject.put ( "shopkeeper_product_id", OBJECT_ID.get ( position ) );
                  parseObject.put ( "linked", LINKS.get ( position ) );
                  parseObject.put ( "consumer_name", ParseUser.getCurrentUser ().getUsername () );
                  parseObject.saveInBackground ( new SaveCallback ( ) {
                      @Override
                      public void done(ParseException e) {
                          if(e==null){
                              Toast.makeText ( quick_sarch.this, "Request Sent", Toast.LENGTH_LONG ).show ( );
                          }
                      }
                  } );

                  ParseQuery<ParseUser> parseUserParseQuery = ParseUser.getQuery ();
                  parseUserParseQuery.getInBackground ( ParseUser.getCurrentUser ( ).getObjectId ( ), new GetCallback<ParseUser> ( ) {
                      @Override
                      public void done(ParseUser object, ParseException e) {
                          if(e==null){
                              object.put ( "available", OBJECT_ID.get ( position ) );
                              object.saveInBackground ();
                          }
                      }
                  } );

                  int PostionToRemove = (int) v.getTag ();
                  SHOP_PRICE.remove ( PostionToRemove );
                  STOCK.remove ( PostionToRemove );
                  SHOP_OFFER.remove ( PostionToRemove );
                  SHOP_ADDRESS.remove ( PostionToRemove );
                  PRODUCT_NAME.remove ( PostionToRemove );
                  POLICY.remove ( PostionToRemove );

                  v.setVisibility ( View.GONE );
                  if(SHOP_PRICE.size ()==0){
                      Intent intent = new Intent ( quick_sarch.this, mainpage_activity.class );
                      startActivity ( intent );
                  }
              }
          } );

            return view;
        }
    }
}