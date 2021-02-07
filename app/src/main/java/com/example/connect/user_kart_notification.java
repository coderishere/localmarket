package com.example.connect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

public class user_kart_notification extends AppCompatActivity {
TextView textView;
Button returns,replaces;
ArrayList<String> product_name , product_price, product_policy, shop;
TextView name_product, price_product, policy_product, from_shop;
ListView listView;
Button returned , replaced,cancel;
adaptive csadapter;
    ParseQuery<ParseObject>newparse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_user_kart_notification );

        listView = findViewById ( R.id.user_kart_listview );

        product_name = new ArrayList<> (  );
        product_price = new ArrayList<> (  );
        product_policy = new ArrayList<> (  );
        shop = new ArrayList<> (  );
        csadapter = new adaptive ();

        newparse = new ParseQuery<ParseObject> ( "Past_Notification" );
        newparse.whereEqualTo ( "parent", ParseUser.getCurrentUser () );
        newparse.findInBackground ( new FindCallback<ParseObject> ( ) {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e==null&objects.size ()>0){
                    for(ParseObject object : objects){
                        product_name.add ( object.getString ( "name" ) );
                        product_price.add ( object.getString ( "price" ) );
                        product_policy.add ( object.getString ( "offer" ) );
                        shop.add ( object.getString ( "Buyfrom" ) );
                    }
                    listView.setAdapter ( csadapter );
                }

            }
        } );



    }
    class adaptive extends BaseAdapter{

        @Override
        public int getCount() {
            return product_name.size ();
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
            convertView = getLayoutInflater ().inflate ( R.layout.user_kart_notification,null );

            name_product = convertView.findViewById ( R.id.product_name );
            price_product = convertView.findViewById ( R.id.prices_product );
            policy_product = convertView.findViewById ( R.id.policy_shop );
            from_shop = convertView.findViewById ( R.id.from_shop );
            returned = convertView.findViewById ( R.id.returned_button );
            replaced = convertView.findViewById ( R.id.replaced_button );
            cancel = convertView.findViewById ( R.id.cancel_button );

            returned.setVisibility ( View.INVISIBLE );
            replaced.setVisibility ( View.INVISIBLE );

            name_product.setText ( product_name.get ( position ) );
            price_product.setText ("Rs. :"+ product_price.get ( position ) );
            policy_product.setText ( "Product : "+product_policy.get ( position ) );
            from_shop.setText ( "Buy From :"+shop.get ( position ) );
            if(product_policy.get ( position ).equals ( "Returnable" ))
            {
                returned.setVisibility ( View.VISIBLE );
            }else {
                if (product_policy.get ( position ).equals ( "Replaceable" )) {
                    replaced.setVisibility ( View.VISIBLE );
                }else {
                    if (product_policy.get ( position ).equals ( "none" )) {
                        returned.setVisibility ( View.INVISIBLE );
                        replaced.setVisibility ( View.INVISIBLE );
                        cancel.setVisibility ( View.INVISIBLE );
                    }else {
                        returned.setVisibility ( View.VISIBLE );
                        replaced.setVisibility ( View.VISIBLE );
                    }
                }
            }
             returned.setOnClickListener ( new View.OnClickListener ( ) {
                 @Override
                 public void onClick(View v) {
                     ParseObject parseObject = new ParseObject ( "Returned_product" );
                     parseObject.put ( "productName", product_name.get ( position ) );
                     parseObject.put ( "username", ParseUser.getCurrentUser ().getString ( "fullname" ) );
                     parseObject.put ( "phone", ParseUser.getCurrentUser ().getString ( "user_phone" ) );
                     parseObject.put ( "address", ParseUser.getCurrentUser ().getString ( "address1" ) );
                     parseObject.put ( "landmark", ParseUser.getCurrentUser ().getString ( "landmark" ) );
                     parseObject.put ( "shop_user", shop.get ( position ) );
                     parseObject.saveInBackground ();

                     newparse.whereEqualTo ( "parent", ParseUser.getCurrentUser () );
                     newparse.whereEqualTo ( "name", product_name.get ( position ) );
                     newparse.findInBackground ( new FindCallback<ParseObject> ( ) {
                         @Override
                         public void done(List<ParseObject> objects, ParseException e) {
                             if(e==null&&objects.size ()>0){
                                 for(ParseObject object:objects){
                                     object.deleteInBackground ();
                                 }
                             }
                         }
                     } );
                 }
             } );

            replaced.setOnClickListener ( new View.OnClickListener ( ) {
                @Override
                public void onClick(View v) {
                    ParseObject parseObject = new ParseObject ( "Replaced_product" );
                    parseObject.put ( "productName", product_name.get ( position ) );
                    parseObject.put ( "username", ParseUser.getCurrentUser ().getString ( "fullname" ) );
                    parseObject.put ( "phone", ParseUser.getCurrentUser ().getString ( "user_phone" ) );
                    parseObject.put ( "address", ParseUser.getCurrentUser ().getString ( "address1" ) );
                    parseObject.put ( "landmark", ParseUser.getCurrentUser ().getString ( "landmark" ) );
                    parseObject.put ( "shop_user", shop.get ( position ) );
                    parseObject.saveInBackground ();
                }
            } );
            return convertView;
        }
    }
}
//  ParseQuery<ParseObject>parseQuery = new ParseQuery<ParseObject> ( "delivered_product" );
//            parseQuery.findInBackground ( new FindCallback<ParseObject> ( ) {
//                @Override
//                public void done(List<ParseObject> objects, ParseException e) {
//                    if(e==null&&objects.size ()>0){
//                        for(ParseObject object : objects){
//                            if(object.getString ( "user_username" ).equals ( ParseUser.getCurrentUser ().getUsername () )){
//
//
//                            }else{
//
//                            }
//                        }
//                    }
//                }
//            } );