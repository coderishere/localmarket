package com.example.connect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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
import java.util.Date;
import java.util.List;

public class delivery_address extends AppCompatActivity {

    ArrayList<String> newuserTastesGot,fullnames,landmarks,phoneNumbers,addresses1,shop_address,shop_no,user_username;
    String[] newstockArr;
    String size;
    TextView Full_Name,Landmark,phone_Number,Address1,shop_address1,shop_num;
    Button delivery;
    ListView delivery_listview;
    adapter csapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_delivery_address );

        delivery_listview= findViewById ( R.id.delivery_custom_layout );
        csapter = new adapter ();

        newuserTastesGot = new ArrayList<> (  );
        fullnames = new ArrayList<> (  );
        landmarks = new ArrayList<> (  );
        phoneNumbers = new ArrayList<> (  );
        addresses1 = new ArrayList<> (  );
        shop_address = new ArrayList<> (  );
        shop_no = new ArrayList<> (  );
        user_username = new ArrayList<> (  );

      ParseQuery<ParseObject> parseQuery = new ParseQuery<ParseObject> ( "Delivery_data" );
      parseQuery.findInBackground ( new FindCallback<ParseObject> ( ) {
          @Override
          public void done(List<ParseObject> objects, ParseException e) {
              if(e==null){
                  for(ParseObject object: objects){

                      fullnames.add ( object.getString ( "name" ) );
                      landmarks.add ( object.getString ( "reference" ) );
                      phoneNumbers.add ( object.getString ( "coustmerphone" ) );
                      addresses1.add ( object.getString ( "delivered" ) );
                      shop_address.add ( object.getString ( "shopkeeper_address" ) );
                      shop_no.add ( object.getString ( "shopkeeper_no" ) );
                      user_username.add ( object.getString ( "username" ) );

                  }
                  delivery_listview.setAdapter ( csapter );
              }
          }
      } );
    }
    class adapter extends BaseAdapter{

        @Override
        public int getCount() {
            return  fullnames.size ();
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

            view =  getLayoutInflater ().inflate ( R.layout.delivery_layout_custom, null );
            Full_Name = view.findViewById ( R.id.Full_Name_delivery );
            Landmark = view.findViewById ( R.id.Landmark_delivery );
            phone_Number = view.findViewById ( R.id.Phone_Number_delivery );
            Address1 = view.findViewById ( R.id.Address1_delivery );
            shop_address1 = view.findViewById ( R.id.shopkeeper_address );
            shop_num = view.findViewById ( R.id.shop_num );

            delivery = view.findViewById ( R.id.delivered );
            delivery.setTag ( position );


            Full_Name.setText ( "NAME : "+ fullnames.get ( position ) );
            Landmark.setText ("Landmark : " +landmarks.get ( position ) );
            phone_Number.setText ( "PhoneNumber :"+phoneNumbers.get ( position ) );
            Address1.setText ( "Address : "+addresses1.get ( position ) );
            shop_address1.setText ( "shop's Address :"+shop_address.get ( position ) );
            shop_num.setText ( "shop_no. :"+shop_no.get ( position ) );

            delivery.setOnClickListener ( new View.OnClickListener ( ) {
                @Override
                public void onClick(View v) {

                   ParseObject parseObject = new ParseObject ( "delivered_product" );
                   parseObject.put ( "user_username", user_username.get ( position ) );
                   parseObject.put ( "landmark",landmarks.get ( position ) );
                   parseObject.put ( "Address1", addresses1.get ( position ) );
                   parseObject.put ( "shopAddress", shop_address.get ( position ) );
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
// SharedPreferences sharedPreferences_username = PreferenceManager.getDefaultSharedPreferences ( delivery_address.this );
//                    sharedPreferences_username.edit ().putString ( "username", username ).commit ();