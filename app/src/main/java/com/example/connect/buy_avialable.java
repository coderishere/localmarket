package com.example.connect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class buy_avialable extends AppCompatActivity {
ArrayList<String>  PRODUCT_NAMES,SHOP_PRICE,STOCK,POLICY,ADDRESS,SHOP_OFFER,shopkeeper_username_array;
ListView listView;
    TextView product_name, shop_price,stock,shop_offer,policy,shop_address;
    Button Buy,Cancel;
    String shop_username;
adapter csadptr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_buy_avialable );

        PRODUCT_NAMES = new ArrayList<> (  );
        SHOP_PRICE = new ArrayList<> (  );
        STOCK = new ArrayList<> (  );
        POLICY = new ArrayList<> (  );
        ADDRESS = new ArrayList<> (  );
        SHOP_OFFER = new ArrayList<> (  );
        shopkeeper_username_array = new ArrayList<> (  );
        csadptr = new adapter ();
        listView = findViewById ( R.id.buy_avail_list );


        ParseQuery<ParseUser>parseQuery = ParseUser.getQuery ();
        ParseQuery<ParseObject>parseQuery1 = new ParseQuery<ParseObject> ( "User_Notifications" );
        parseQuery1.whereMatchesKeyInQuery ( "objectId", "available",parseQuery );
        parseQuery1.include ( "parent" );
        parseQuery1.findInBackground ( new FindCallback<ParseObject> ( ) {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e==null){
                    for(ParseObject object:objects){
                        PRODUCT_NAMES.add ( object.getString ( "names" ) );
                        SHOP_PRICE.add ( object.getString ( "Shop_price" ) );
                        SHOP_OFFER.add ( object.getString ( "shop_offer" ) );
                        STOCK.add ( object.getString ( "product_quantity" ) );
                        POLICY.add ( object.getString ( "policies" ) );
                        String addresses= String.valueOf ( object.getParseObject ( "parent" ).get ( "address" ) );
                        ADDRESS.add ( addresses );
                        String id = String.valueOf ( object.getParseObject ( "parent" ).get ( "username" ) );
                        shopkeeper_username_array.add ( id );

                    }
                    listView.setAdapter ( csadptr );
                }
            }
        } );

    }
    class adapter extends BaseAdapter{

        @Override
        public int getCount() {
            return PRODUCT_NAMES.size ();
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
            view = getLayoutInflater ().inflate ( R.layout.buy_avail_layout,null );
            product_name = view.findViewById ( R.id.PRODUCT_NAME_buy);
            shop_price = view.findViewById ( R.id.SHOP_PRICE_buy );
            stock = view.findViewById ( R.id.STOCK_buy);
            shop_offer= view.findViewById ( R.id.SHOP_OFFER_buy );
            policy= view.findViewById ( R.id.POLICY_buy );
            shop_address = view.findViewById ( R.id.SHOP_ADRRESS_buy );
            Buy = view.findViewById ( R.id.buy_buy );
            Cancel= view.findViewById ( R.id.cancel1_buy );
            Buy.setTag ( position );
            Cancel.setTag ( position );

            product_name.setText ( PRODUCT_NAMES.get ( position ) );
            shop_price.setText ( "shop_price"+": "+ "Rs."+SHOP_PRICE.get ( position ) );
            stock.setText ( "Quantity : "+STOCK.get ( position ) );
            shop_offer.setText ( "offer on product : "+SHOP_OFFER.get ( position ) );
            shop_address.setText ( "address : "+ADDRESS.get ( position ) );
            policy.setText (  "Product is :" +POLICY.get ( position )  );

            Buy.setOnClickListener ( new View.OnClickListener ( ) {
                @Override
                public void onClick(View v) {
                    ParseQuery<ParseUser> query1 = ParseUser.getQuery ( );
                    query1.getInBackground ( ParseUser.getCurrentUser ( ).getObjectId ( ), new GetCallback<ParseUser> ( ) {
                        @Override
                        public void done(ParseUser object, ParseException e) {
                            if (e == null) {
                                shop_username = shopkeeper_username_array.get ( position );
                                object.add ( "ShopIdList", shop_username );
                              //  object.add ( "delivery", "local market" );
                                object.saveInBackground ( );
                            }
                        }
                    } );

                    ParseObject Past_Notification = new ParseObject ( "Past_Notification" );
                    Past_Notification.put ( "price", SHOP_PRICE.get ( position ));
                    Past_Notification.put ( "stock",STOCK.get ( position ) );
                    Past_Notification.put ( "offer",SHOP_OFFER.get ( position ) );
                    Past_Notification.put ( "offer",ADDRESS.get ( position ) );
                    Past_Notification.put ( "offer",POLICY.get ( position ) );
                    Past_Notification.put ( "parent",ParseUser.getCurrentUser () );
                    Past_Notification.put ( "Buyfrom", shopkeeper_username_array.get ( position ) );
                    Past_Notification.put ( "name",PRODUCT_NAMES.get ( position ) );
                    Past_Notification.saveInBackground ();



                    parent.setVisibility ( View.GONE );
                    notifyDataSetChanged ();
                    Intent newintents = new Intent ( buy_avialable.this, mainpage_activity.class );
                    startActivity ( newintents );
                }
            });
            return view;
        }
    }
}