package com.example.connect;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.firebase.FirebaseApp;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.prefs.PreferenceChangeListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
// Parse Dependencies


public class mainpage_activity extends AppCompatActivity implements View.OnClickListener {
    Button cateogories, quicksearch;
    ImageView Logo,background;
    EditText searchquery;
    TextView textView,textView1,textView2,logout_text;
     String url, matching,price,name,size,starts,obj_user,current_user,past_product_name,notification_shop,notification_shop1,getName;
     String[] user_notification,user_order_notification;
    ParseQuery<ParseUser> query2,query3;
     Document document,document1;
     ImageView imageView,imageView1;
     Elements element;
    Elements element1;
    Elements element2;
    SharedPreferences sharedPreferences1,sharedPreferences;
     int i,j,k;
    ProgressDialog progressDialog;
    ParseObject parseObject;
    MenuItem menuItem,menuItem1,menuItem2;
    View view,view1,view2;
    String[] stockArr;
ArrayList<String>user_notification_names_array,user_notification_price_array;
ViewFlipper viewFlipper;

    @Override
    public void onClick(View view) {
        if (view.getId ( ) == R.id.logo2 || view.getId ( ) == R.id.background) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService ( INPUT_METHOD_SERVICE );
            inputMethodManager.hideSoftInputFromWindow ( getCurrentFocus ( ).getWindowToken ( ), 0 );        // hide keyboard if touch anywhere
        }
        if (view.getId ( ) == R.id.menuicon) {
            final PopupMenu popupMenu1 = new PopupMenu ( mainpage_activity.this, imageView );
            popupMenu1.getMenuInflater ( ).inflate ( R.menu.menu_user, popupMenu1.getMenu ( ) );
            popupMenu1.show ( );
            popupMenu1.setOnMenuItemClickListener ( new PopupMenu.OnMenuItemClickListener ( ) {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    if(item.getItemId ()==R.id.profile) {
                        Intent intent = new Intent ( mainpage_activity.this, user_profil.class );
                        startActivity ( intent );
                    }
                        if(item.getItemId ()==R.id.edit_address){
                            Intent intent1 = new Intent ( mainpage_activity.this, profile_edit.class );
                            startActivity ( intent1 );
                        }
                    if(item.getItemId ()==R.id.Logout_user){
                        ParseUser.logOut ();
                        Intent intent = new Intent ( mainpage_activity.this, loginactivity.class );
                        startActivity ( intent );
                    }
                    return false;
                }
            } );

        }
    }

    private class content extends AsyncTask<Void, Void , Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute ( );
            progressDialog = new ProgressDialog ( mainpage_activity.this );
            progressDialog.show ();

        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                 document = Jsoup.connect ( matching ).get ();
                 Log.i ( "document", document.text () );
                 element = document.select ( "#container > div > div._2c7YLP.UtUXW0._6t1WkM._3HqJxg > div._1YokD2._2GoDe3 > div._1YokD2._3Mn1Gg.col-8-12 > div:nth-child(2) > div > div.dyC4hf > div.CEmiEU > div > div._30jeq3._16Jk6d" );//flipkart
                //#container > div > div._2c7YLP.UtUXW0._6t1WkM._3HqJxg > div._1YokD2._2GoDe3 > div._1YokD2._3Mn1Gg.col-8-12 > div:nth-child(2) > div > div.dyC4hf > div.CEmiEU > div > div._30jeq3._16Jk6d
                //#container > div > div._2c7YLP.UtUXW0._6t1WkM._3HqJxg > div._1YokD2._2GoDe3 > div._1YokD2._3Mn1Gg.col-8-12 > div:nth-child(2) > div > div.dyC4hf > div.CEmiEU > div > div._30jeq3._16Jk6d
                //#container > div > div._2c7YLP.UtUXW0._6t1WkM._3HqJxg > div._1YokD2._2GoDe3 > div._1YokD2._3Mn1Gg.col-8-12 > div:nth-child(2) > div > div.dyC4hf > div.CEmiEU > div > div._30jeq3._16Jk6d
                element = document.select ( "#app > div > div._2Bze > div > div._1D6j > div > div._2rYD > div._39b7 > div > div._19Zj._9qaO > div._3t7S > div._3bvo > div._2viE > div > div._2LVL > span._1V3w" );//paytm
                //#app > div > div._2Bze > div > div._1D6j > div > div._2rYD > div._39b7 > div > div._19Zj._9qaO > div._3t7S > div._3bvo > div._2viE > div > div._2LVL > span._1V3w
                element= document.select ( "#buyPriceBox > div.row.reset-margin > div.col-xs-14.reset-padding.padL8 > div.disp-table > div.pdp-e-i-PAY-r.disp-table-cell.lfloat > span.pdp-final-price" );//snapdeal
                //#buyPriceBox > div.row.reset-margin > div.col-xs-14.reset-padding.padL8 > div.disp-table > div.pdp-e-i-PAY-r.disp-table-cell.lfloat > span.pdp-final-price > span
                element= document.select ( "#main_data > div.shd_box > div.prd_mid_info > div.price.spr_dis > span.f_price" );//shopclus
                //#main_data > div.shd_box > div.prd_mid_info > div.price.spr_dis > span.f_price
                element = document.select ( "#BPDT > div._3QxM820UNvRen7hLNf8P1Z > div.w_9jEmcINBVgphWDDl3-k > div._3BuuEa4DZJe-0OCEQmi_K_ > h3" );//tata cliq
                //#BPDT > div._3QxM820UNvRen7hLNf8P1Z > div.w_9jEmcINBVgphWDDl3-k > div._3BuuEa4DZJe-0OCEQmi_K_ > h3
                element = document.select ( "#priceblock_ourprice" );//amazon
               // element = document.select ( "#a-autoid-7-announce > span.a-color-base > span" );//amazon
                //#priceblock_ourprice
                //#a-autoid-7-announce
                //#a-autoid-2-announce > span.a-color-base > span
                //#price > table > tbody > tr:nth-child(1)
                Log.i ( "elementsget", String.valueOf ( element ) );
                price= element.text ();
                Log.i ( "price",price );
                element2= document.select ( "span.B_NuCI" );
                //#container > div > div._2c7YLP.UtUXW0._6t1WkM._3HqJxg > div._1YokD2._2GoDe3 > div._1YokD2._3Mn1Gg.col-8-12 > div:nth-child(2) > div > div.dyC4hf > div.CEmiEU > div > div._30jeq3._16Jk6d
                //#container > div > div._2c7YLP.UtUXW0._6t1WkM._3HqJxg > div._1YokD2._2GoDe3 > div._1YokD2._3Mn1Gg.col-8-12 > div:nth-child(2) > div > div:nth-child(1) > h1 > span
                //#container > div > div._2c7YLP.UtUXW0._6t1WkM._3HqJxg > div._1YokD2._2GoDe3 > div._1YokD2._3Mn1Gg.col-8-12 > div:nth-child(2) > div > div.dyC4hf > div.CEmiEU > div > div._30jeq3._16Jk6d
                //#container > div > div._2c7YLP.UtUXW0._6t1WkM._3HqJxg > div._1YokD2._2GoDe3 > div._1YokD2._3Mn1Gg.col-8-12 > div:nth-child(2) > div > div:nth-child(1) > h1 > span
                element2 = document.select ( "#app > div > div._2Bze > div > div._1D6j > div > div._2rYD > div._39b7 > div > div._19Zj._9qaO > div._3t7S > div._3bvo > div._2viE > h1" );//paytm
                element2 = document.select ( "#productOverview > div.col-xs-14.right-card-zoom.reset-padding > div > div.pdp-elec-topcenter-inner.layout > div.row > div.col-xs-22 > h1" );//snapdeal
                element2 = document.select ( "#main_data > div.shd_box > div.prd_mid_info > h1" );//shopclus
                element2 = document.select ( "#BPDT > div._3QxM820UNvRen7hLNf8P1Z > div.fMO6mN8qLtqRumW90vzk4 > a > div > h1" );//tata cliq
                element2 = document.select ( "#productTitle" );//amazon
                name = element2.text ();
                Log.i ( "name",name );
            } catch (Exception e) {
                e.printStackTrace ( );
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute ( aVoid );
            progressDialog.dismiss ();

            parseObject = new ParseObject ( "Notification" );
         //   parseObject.put ( "name",name );
         //   parseObject.put ( "price",price );
            parseObject.put ( "parent",ParseUser.getCurrentUser () );
            parseObject.put ( "link", matching );
            parseObject.saveInBackground ();
            searchquery.setText ( "" );
            Toast.makeText ( mainpage_activity.this, price , Toast.LENGTH_SHORT ).show ( );
        }
    }
    private class newcontent extends AsyncTask<Void, Void , Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute ( );
            progressDialog = new ProgressDialog ( mainpage_activity.this );
            progressDialog.show ();

        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                document1 = Jsoup.connect ( matching ).get ();
                element1 = document1.select ( "#container > div > div._2c7YLP.UtUXW0._6t1WkM._3HqJxg > div._1YokD2._2GoDe3 > div._1YokD2._3Mn1Gg.col-8-12 > div:nth-child(2) > div > div.dyC4hf > div.CEmiEU > div > div._30jeq3._16Jk6d" );//flipkart
                element1 = document1.select ( "#app > div > div._2Bze > div > div._1D6j > div > div._2rYD > div._39b7 > div > div._19Zj._9qaO > div._3t7S > div._3bvo > div._2viE > div > div._2LVL > span._1V3w" );//paytm
                //#app > div > div._2Bze > div > div._1D6j > div > div._2rYD > div._39b7 > div > div._19Zj._9qaO > div._3t7S > div._3bvo > div._2viE > div > div._2LVL > span._1V3w
                element1= document1.select ( "#buyPriceBox > div.row.reset-margin > div.col-xs-14.reset-padding.padL8 > div.disp-table > div.pdp-e-i-PAY-r.disp-table-cell.lfloat > span.pdp-final-price" );//snapdeal
                //#buyPriceBox > div.row.reset-margin > div.col-xs-14.reset-padding.padL8 > div.disp-table > div.pdp-e-i-PAY-r.disp-table-cell.lfloat > span.pdp-final-price > span
                element1= document1.select ( "#main_data > div.shd_box > div.prd_mid_info > div.price.spr_dis > span.f_price" );//shopclus
                //#main_data > div.shd_box > div.prd_mid_info > div.price.spr_dis > span.f_price
                element1 = document1.select ( "#BPDT > div._3QxM820UNvRen7hLNf8P1Z > div.w_9jEmcINBVgphWDDl3-k > div._3BuuEa4DZJe-0OCEQmi_K_ > h3" );//tata cliq
                //#BPDT > div._3QxM820UNvRen7hLNf8P1Z > div.w_9jEmcINBVgphWDDl3-k > div._3BuuEa4DZJe-0OCEQmi_K_ > h3
                element1 = document1.select ( "#priceblock_ourprice" );//amazon
                //#priceblock_ourprice
                //#price > table > tbody > tr:nth-child(1)

                price= element1.text ();
                element1 = document1.select ( "#container > div > div._2c7YLP.UtUXW0._6t1WkM._3HqJxg > div._1YokD2._2GoDe3 > div._1YokD2._3Mn1Gg.col-8-12 > div:nth-child(2) > div > div:nth-child(1) > h1 > span" );//flipkart
                element1 = document1.select ( "#app > div > div._2Bze > div > div._1D6j > div > div._2rYD > div._39b7 > div > div._19Zj._9qaO > div._3t7S > div._3bvo > div._2viE > h1" );//paytm
                element1 = document1.select ( "#productOverview > div.col-xs-14.right-card-zoom.reset-padding > div > div.pdp-elec-topcenter-inner.layout > div.row > div.col-xs-22 > h1" );//snapdeal
                element1 = document1.select ( "#main_data > div.shd_box > div.prd_mid_info > h1" );//shopclus
                element1 = document1.select ( "#BPDT > div._3QxM820UNvRen7hLNf8P1Z > div.fMO6mN8qLtqRumW90vzk4 > a > div > h1" );//tata cliq
                element1 = document1.select ( "#productTitle" );//amazon
                //#productTitle
                name = element1.text ();
                Log.i ( "price",price );
                Log.i ( "name",name );
            } catch (Exception e) {
                e.printStackTrace ( );
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute ( aVoid );
            progressDialog.dismiss ();

            parseObject = new ParseObject ( "Ele_Notification" );
            parseObject.put ( "name",name );
            parseObject.put ( "price",price );
            parseObject.put ( "parent",ParseUser.getCurrentUser () );
            parseObject.put ( "link", matching );
            parseObject.saveInBackground ();
            searchquery.setText ( "" );
            AlertDialog alertDialog = new AlertDialog.Builder(mainpage_activity.this).create();
            alertDialog.setTitle("Query sent");
            alertDialog.setMessage("Query of the product sent successfully ");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_mainpage_activity );


        user_notification_names_array = new ArrayList<> (  );
        user_notification_price_array = new ArrayList<> (  );

        setTitle ( null );

        cateogories = findViewById(R.id.Cateogories);
        quicksearch= findViewById(R.id.quicksearch);
        searchquery = findViewById(R.id.search);
        Logo = findViewById ( R.id.logo2 );
        Logo.setOnClickListener ( this );
        background = findViewById ( R.id.background );
        imageView = findViewById ( R.id.menuicon );
        viewFlipper = findViewById ( R.id.view_flipper );
        imageView.setOnClickListener ( this );
        background.setOnClickListener ( this );

        int image[] = {R.drawable.slideone, R.drawable.slidestwo};

        for(int images : image){
            flipper ( images );
        }

        Log.i ( "tagme", String.valueOf ( ParseUser.getCurrentUser () ) );

       /* query2 = ParseUser.getQuery ();
        query2.whereExists ( "code" );
        query2.findInBackground ( new FindCallback<ParseUser> ( ) {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                if(e==null){
                    for (ParseUser object : objects){
                        Log.i ( "tags",object.toString () );
                        puts = object.toString ();
                    }
                }
            }
        } );*/



        quicksearch.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                   String query = searchquery.getText ().toString ();

                   if(query.matches ( "" )){
                       Toast.makeText ( mainpage_activity.this, "try some query", Toast.LENGTH_SHORT ).show ( );
                   }else{
                       Intent intent = new Intent ( mainpage_activity.this,quick_sarch.class );
                       intent.putExtra ( "productForSearch", query );
                       startActivity ( intent );
                       searchquery.setText ( "" );
                   }

                }

        });

        cateogories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final PopupMenu popupMenu = new PopupMenu ( mainpage_activity.this, cateogories );
                popupMenu.getMenuInflater ( ).inflate ( R.menu.main_menu, popupMenu.getMenu ( ) );
                popupMenu.show ( );
                popupMenu.setOnMenuItemClickListener ( new PopupMenu.OnMenuItemClickListener ( ) {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getItemId()==R.id.bookform) {
                           url = searchquery.getText ().toString ();
                        //   ParseUser.getCurrentUser ().put ( "queryfor","book" );
                           if(searchquery.getText ().toString ().matches ( "" )){
                               Toast.makeText ( mainpage_activity.this, "Try some query", Toast.LENGTH_SHORT ).show ( );
                           }else {


                               if (url.contains ( "http" )) {

                                   ParsePush.subscribeInBackground ( url );

                                   JSONObject data = new JSONObject();
     // Put data in the JSON object
                                   try {
                                       data.put("alert", "Query Arrived!!");
                                       data.put("title", "Check your app");
                                   } catch ( JSONException e) {
                                       // should not happen
                                       throw new IllegalArgumentException("unexpected parsing error", e);
                                   }
       // Configure the push
                                   ParsePush push = new ParsePush();
                                   push.setChannel("Books");
                                   push.setData(data);
                                   push.sendInBackground();


                                   Pattern pattern = Pattern.compile ( "http" );
                                   Matcher matcher = pattern.matcher ( url );
                                   while (matcher.find ( )) {
                                       Log.i ( "another", matcher.group ( ) );
                                       for (i = -1; (i = url.indexOf ( matcher.group ( ), i + 1 )) != -1; i++) {
                                           matching = url.substring ( i );
                                           Log.i ( "matching", matching );
                                       }
                                   }
                                   content run = new content ( );
                                   run.execute ( );
                               }else {
                                   if(!url.contains ( "Bosdk")||!url.contains ( "bosdk")||!url.contains ( "chutiya")||!url.contains ( "Chutiya")||!url.contains ( "randi")||!url.contains ( "Randi")||!url.contains ( "chod")||!url.contains ( "chut")||!url.contains ( "Chut")||!url.contains ( "Madharchod")||!url.contains ( "madharchod")||!url.contains ( "betichod")||!url.contains ( "Betichod")||!url.contains ( "behnchod")||!url.contains ( "Behenchod")||!url.contains ( "")||!url.contains ( "madharchod")||!url.contains ( "madharchod")||!url.contains ( "madharchod")||!url.contains ( "madharchod")) {
                                       ParseObject directquery = new ParseObject ( "Direct_Book_product" );
                                       directquery.put ( "product_name", url );
                                       directquery.put ( "parent", ParseUser.getCurrentUser () );
                                       directquery.saveInBackground ();
                                       searchquery.setText ( "" );
                                   }
                                   else{
                                       Toast.makeText ( mainpage_activity.this, "inappropriate words", Toast.LENGTH_SHORT ).show ( );
                                       searchquery.setText ( "" );
                                   }
                               }
                           }

                        }

                        if(item.getItemId()==R.id.electronicform) {
                            url = searchquery.getText ( ).toString ( );
                            if (searchquery.getText ( ).toString ( ).matches ( "" )) {
                                Toast.makeText ( mainpage_activity.this, "Try some query", Toast.LENGTH_SHORT ).show ( );
                            } else {
                                JSONObject data = new JSONObject();
// Put data in the JSON object
                                try {
                                    data.put("alert", "Query Arrived!!");
                                    data.put("title", "Check your app");
                                } catch ( JSONException e) {
                                    // should not happen
                                    throw new IllegalArgumentException("unexpected parsing error", e);
                                }
// Configure the push
                                ParsePush push = new ParsePush();
                                push.setChannel("Electronics");
                                push.setData(data);
                                push.sendInBackground();


                                if (url.contains ( "http" )) {

                                    ParseInstallation installation = new ParseInstallation (); // creating installation class
                                    installation.put ( "channels", url );
                                    installation.saveInBackground ();

                                    Pattern pattern = Pattern.compile ( "http" );
                                    Matcher matcher = pattern.matcher ( url );
                                    while (matcher.find ( )) {
                                        Log.i ( "another", matcher.group ( ) );
                                        for (i = -1; (i = url.indexOf ( matcher.group ( ), i + 1 )) != -1; i++) {
                                            matching = url.substring ( i );
                                        }
                                    }
                                    newcontent runs = new newcontent ( );
                                    runs.execute ( );
                                }else {
                                    if(!url.contains ( "Bosdk")||!url.contains ( "Bosdk")||!url.contains ( "Bosdk")||!url.contains ( "Bosdk")||!url.contains ( "Bosdk")||!url.contains ( "Bosdk")||!url.contains ( "Bosdk")||!url.contains ( "Bosdk")||!url.contains ( "Bosdk")||!url.contains ( "Bosdk")||!url.contains ( "Bosdk")) {
                                        ParseObject directquery = new ParseObject ( "Direct_ele_product" );
                                        directquery.put ( "product_name", url );
                                        directquery.saveInBackground ();
                                        searchquery.setText ( "" );
                                    }
                                    else{
                                        Toast.makeText ( mainpage_activity.this, "inappropriate words", Toast.LENGTH_SHORT ).show ( );
                                        searchquery.setText ( "" );
                                    }
                                }
                            }
                        }
                        return false;
                    }
                } );
            }
        });

}
    public void flipper (int Images){
        ImageView imageView = new ImageView ( this );
        imageView.setBackgroundResource ( Images );
        viewFlipper.addView ( imageView );
        viewFlipper.setFlipInterval ( 4000 );
        viewFlipper.setAutoStart ( true );
        viewFlipper.setInAnimation ( this, android.R.anim.slide_in_left );
        viewFlipper.setOutAnimation ( this, android.R.anim.slide_out_right );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater ().inflate ( R.menu.user_notification,menu );
        menuItem = menu.findItem ( R.id.notification );
        menuItem1 = menu.findItem ( R.id.user_kart );
        menuItem2 = menu.findItem ( R.id.user_kart_cancel );





            final SharedPreferences avail_object = PreferenceManager.getDefaultSharedPreferences ( this );
            obj_user = avail_object.getString ( "id_user", "" );
//notification_shop.equals ( ParseUser.getCurrentUser ( ).getUsername ( ) ) ||
        ParseQuery<ParseObject> parseQuery_usernotification = new ParseQuery<ParseObject> ( "User_Notifications" );
        parseQuery_usernotification.findInBackground ( new FindCallback<ParseObject> ( ) {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e==null&&objects.size ()>0){
                    for(final ParseObject object : objects){

                        if(object.getString ( "queryfrom" ).equals ( ParseUser.getCurrentUser ().getUsername () )){
                            menuItem.setActionView ( R.layout.user_notifications_layout );
                            view = menuItem.getActionView ( );
                            textView = view.findViewById ( R.id.user_badge );
                            textView.setText ( "+1" );
                            view.setOnClickListener ( new View.OnClickListener ( ) {
                                @Override
                                public void onClick(View v) {
                                    if (ParseUser.getCurrentUser ( ).has ( "address1" )) {


                                            Intent i = new Intent ( mainpage_activity.this, User_Notification.class );
                                            startActivity ( i );
                                            ParseQuery<ParseObject>parseQuery = new ParseQuery<ParseObject> ( "User_Notifications" );
                                            parseQuery.getInBackground ( object.getObjectId ( ), new GetCallback<ParseObject> ( ) {
                                                @Override
                                                public void done(ParseObject newobject, ParseException e) {
                                                    newobject.put ( "queryfrom","" );
                                                    newobject.saveInBackground ();
                                                }
                                            } );
                                        }
                                     else {
                                        Intent intent = new Intent ( mainpage_activity.this, User_address.class );
                                        startActivity ( intent );
                                        ParseQuery<ParseObject>parseQuery = new ParseQuery<ParseObject> ( "User_Notifications" );
                                        parseQuery.getInBackground ( object.getObjectId ( ), new GetCallback<ParseObject> ( ) {
                                            @Override
                                            public void done(ParseObject newobject, ParseException e) {
                                                newobject.put ( "queryfrom","" );
                                                newobject.saveInBackground ();
                                            }
                                        } );
                                        }
                                }
                            } );
                        } else {
                            menuItem.setActionView ( R.layout.user_notifcation_null );
                            view = menuItem.getActionView ( );
                            view.setOnClickListener ( new View.OnClickListener ( ) {
                                @Override
                                public void onClick(View v) {
                                    Intent i = new Intent ( mainpage_activity.this, notification_null.class );
                                    startActivity ( i );

                                }
                            } );
                        }if(object.has ( "queryiedfrom" )&&object.getList ( "queryiedfrom" ).contains ( ParseUser.getCurrentUser ().getUsername () )){

                            menuItem.setActionView ( R.layout.user_notifications_layout );
                            view = menuItem.getActionView ( );
                            textView = view.findViewById ( R.id.user_badge );
                            textView.setText ( "+1" );
                            view.setOnClickListener ( new View.OnClickListener ( ) {
                                @Override
                                public void onClick(View v) {
                                    Intent x = new Intent ( mainpage_activity.this, buy_avialable.class );
                                     startActivity ( x );
                                     object.getList ( "queryiedfrom" ).remove ( ParseUser.getCurrentUser ().getUsername () );
                                     List newlist = object.getList ( "queryiedfrom" );
                                     object.remove ( "queryiedfrom" );
                                     object.put ( "queryiedfrom",newlist );
                                     object.saveInBackground ();
                                }
                            } );

                            //
                        }
                    }
                }
            }
        } );




        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

      final SharedPreferences  sharedPreferences_username_mainpage = PreferenceManager.getDefaultSharedPreferences ( this );

      ParseQuery<ParseObject> accepted_parsequery = new ParseQuery<ParseObject> ( "Order_Accepted" );
      accepted_parsequery.findInBackground ( new FindCallback<ParseObject> ( ) {
          @Override
          public void done(List<ParseObject> objects, ParseException e) {
              if(e==null&&objects.size ()>0){
                  for(ParseObject object : objects){
                      if(object.getString ( "order_from" ).equals ( ParseUser.getCurrentUser ().getUsername () )){
                          menuItem1.setActionView ( R.layout.user_kart_layout );
                          view1= menuItem1.getActionView ();
                          textView1 = view1.findViewById ( R.id.users_kart );
                          textView1.setText ( "1" );
                          view1.setOnClickListener ( new View.OnClickListener ( ) {
                              @Override
                              public void onClick(View v) {
                                  ParsePush.unsubscribeInBackground ( ParseUser.getCurrentUser ().getUsername () );
                                  ParseQuery<ParseUser>usersquery = ParseUser.getQuery ();
                                  usersquery.getInBackground ( ParseUser.getCurrentUser ( ).getObjectId ( ), new GetCallback<ParseUser> ( ) {
                                      @Override
                                      public void done(ParseUser object, ParseException e) {
                                          if(e==null){
                                              object.saveInBackground ();
                                          }
                                      }
                                  } );
                                  ParseQuery<ParseUser>parseQuery8 = ParseUser.getQuery ();
                                  parseQuery8.whereExists ( "code" );
                                  parseQuery8.findInBackground ( new FindCallback<ParseUser> ( ) {
                                      @Override
                                      public void done(List<ParseUser> objects, ParseException e) {
                                          if(e==null){
                                              for(final ParseUser user: objects){
                                                  ParseQuery<ParseUser> parseQuery7 = ParseUser.getQuery ();
                                                  parseQuery7.getInBackground ( ParseUser.getCurrentUser ( ).getObjectId ( ), new GetCallback<ParseUser> ( ) {
                                                      @Override
                                                      public void done(ParseUser object, ParseException e) {
                                                          if(e==null){
                                                              ArrayList<String> userTastesGot = (ArrayList<String>) object.get("ShopIdList");
                                                              stockArr = new String[userTastesGot.size()];

                                                              stockArr = userTastesGot.toArray(stockArr);
                                                              for( String s : stockArr) {
                                                                  size = s;
                                                                  Log.i ( "sizes",size );
                                                                  if(size.equals ( user.getUsername () )){
                                                                      ParseUser.getCurrentUser ().getList ( "ShopIdList" ).remove ( user.getUsername () );
                                                                      List newlist = ParseUser.getCurrentUser ().getList ( "ShopIdList" );
                                                                      ParseUser.getCurrentUser ().remove ( "ShopIdList" );
                                                                      ParseUser.getCurrentUser ().put ( "ShopIdList", newlist );
                                                                      ParseUser.getCurrentUser ().saveInBackground ();
                                                                  }
                                                              }
                                                          }
                                                      }
                                                  } );
                                                  Log.i ( "users", user.getUsername () );

                                              }
                                          }
                                      }
                                  } );


                                  Intent intent1 = new Intent ( mainpage_activity.this,user_kart_new.class );
                                  startActivity ( intent1 );
                              }
                          } );
                      }
                  }
              }
          }
      } );


        String name = preferences.getString("newvalue", "");// do not remove this shardprefernce
        String delivered_username= sharedPreferences_username_mainpage.getString ( "user_username","" );
        if(!name.equals ( ParseUser.getCurrentUser ().getUsername () )){

         menuItem1.setActionView ( null );
        }else {
            menuItem1.setActionView ( R.layout.user_kart_layout );
            view1 = menuItem1.getActionView ( );
            TextView textView = view1.findViewById ( R.id.users_kart );
            textView.setText ( "1" );

            view1.setOnClickListener ( new View.OnClickListener ( ) {
                @Override
                public void onClick(View v) {

                     preferences.edit ( ).clear ( ).commit ( );
                     preferences.edit ( ).putString ( "newvalue", "" ).commit ( );
                    Intent intent = new Intent ( mainpage_activity.this,user_kart_null.class );
                    startActivity ( intent );
                }
            } );
        }

        ParseQuery<ParseObject>parse = new ParseQuery<ParseObject> ( "delivered_product" );
        parse.findInBackground ( new FindCallback<ParseObject> ( ) {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e==null&&objects.size ()>0){
                    for(ParseObject object : objects) {
                        if(object.getString ( "user_username" ).equals ( ParseUser.getCurrentUser ().getUsername () )) {
                            menuItem1.setActionView ( R.layout.user_kart_layout );
                            view1 = menuItem1.getActionView ( );
                            TextView textView = view1.findViewById ( R.id.users_kart );
                            textView.setText ( "1" );

                            view1.setOnClickListener ( new View.OnClickListener ( ) {
                                @Override
                                public void onClick(View v) {

                                    ParseQuery<ParseUser>usersquery = ParseUser.getQuery ();
                                    usersquery.getInBackground ( ParseUser.getCurrentUser ( ).getObjectId ( ), new GetCallback<ParseUser> ( ) {
                                        @Override
                                        public void done(ParseUser object, ParseException e) {
                                            if(e==null){
                                                object.saveInBackground ();
                                            }
                                        }
                                    } );
                                    ParseQuery<ParseUser>parseQuery8 = ParseUser.getQuery ();
                                    parseQuery8.whereExists ( "code" );
                                    parseQuery8.findInBackground ( new FindCallback<ParseUser> ( ) {
                                        @Override
                                        public void done(List<ParseUser> objects, ParseException e) {
                                            if(e==null){
                                                for(final ParseUser user: objects){
                                                    ParseQuery<ParseUser> parseQuery7 = ParseUser.getQuery ();
                                                    parseQuery7.getInBackground ( ParseUser.getCurrentUser ( ).getObjectId ( ), new GetCallback<ParseUser> ( ) {
                                                        @Override
                                                        public void done(ParseUser object, ParseException e) {
                                                            if(e==null){
                                                                ArrayList<String> userTastesGot = (ArrayList<String>) object.get("ShopIdList");
                                                                stockArr = new String[userTastesGot.size()];

                                                                stockArr = userTastesGot.toArray(stockArr);
                                                                for( String s : stockArr) {
                                                                    size = s;
                                                                    Log.i ( "sizes",size );
                                                                    if(size.equals ( user.getUsername () )){
                                                                        ParseUser.getCurrentUser ().getList ( "ShopIdList" ).remove ( user.getUsername () );
                                                                        List newlist = ParseUser.getCurrentUser ().getList ( "ShopIdList" );
                                                                        ParseUser.getCurrentUser ().remove ( "ShopIdList" );
                                                                        ParseUser.getCurrentUser ().put ( "ShopIdList", newlist );
                                                                        ParseUser.getCurrentUser ().saveInBackground ();
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } );
                                                    Log.i ( "users", user.getUsername () );

                                                }
                                            }
                                        }
                                    } );
                                    //    sharedPreferences_username_mainpage.edit ().clear ().commit ();
                                    SharedPreferences sharedPreferences_username = PreferenceManager.getDefaultSharedPreferences ( mainpage_activity.this );
                                    sharedPreferences_username.edit ( ).putString ( "username", "0" ).commit ( );

                                    Intent intent1 = new Intent ( mainpage_activity.this, user_kart_notification.class );

                                    startActivity ( intent1 );


                                }
                            } );
                        }
                    }
                }
            }
        } );

        ParseQuery<ParseObject> parseQuery_cancel = new ParseQuery<ParseObject> ( "Cancel_Product" );
        parseQuery_cancel.findInBackground ( new FindCallback<ParseObject> ( ) {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e==null&&objects.size ()>0){
                    for(ParseObject object:objects){
                        if(object.getString ( "cousumer_name" ).equals ( ParseUser.getCurrentUser ().getUsername () )){
                            menuItem2.setActionView ( R.layout.cancel_order );
                            view2 = menuItem2.getActionView ();
                            textView2 = view2.findViewById ( R.id.cancel_order_badge );
                            textView2.setText ( "1" );
                            view2.setOnClickListener ( new View.OnClickListener ( ) {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent ( mainpage_activity.this,order_cancel.class );
                                    startActivity ( intent );
                                    ParseQuery<ParseObject> parseQuery = new ParseQuery<ParseObject> ( "Cancel_Product" );
                                    parseQuery.findInBackground ( new FindCallback<ParseObject> ( ) {
                                        @Override
                                        public void done(List<ParseObject> objects, ParseException e) {
                                            if(e==null){
                                                for(ParseObject object1 : objects){
                                                   if( object1.getString ( "cousumer_name" ).equals ( ParseUser.getCurrentUser ().getUsername () )){
                                                       object1.put ( "cousumer_name","" );
                                                       object1.saveInBackground ();
                                                   }
                                                }
                                            }
                                        }
                                    } );
                                }
                            } );
                        }else {
                            menuItem2.setActionView ( null );
                        }
                    }
                }
            }
        } );



        return super.onCreateOptionsMenu ( menu );
        }

}
/*

 query3 = ParseUser.getQuery ();
        query3.whereDoesNotExist ( "code" );
        query3.findInBackground ( new FindCallback<ParseUser> ( ) {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                if(e==null){
                    for(ParseUser user : objects){
                        user_order_notification_array = (ArrayList<String>) user.get ( "ShopIdList" );
                        user_order_notification = new String[user_order_notification_array.size ( )];

                        user_order_notification = user_order_notification_array.toArray ( user_order_notification );
                        for(String s : user_order_notification){
                            size= s;
                        }
                    }
                    if(size.equals ( ParseUser.getCurrentUser ().getUsername () )){
                        for(k=0;k<=user_order_notification.length;k++){

                        }
                    }else {

                    }
                    if(j==0){
                        menuItem1.setActionView ( null );
                    }else {
                        menuItem1.setActionView ( R.layout.user_notifications_layout );
                        view1 = menuItem.getActionView ();
                        textView= view1.findViewById ( R.id.user_badge );



                        view1.setOnClickListener ( new View.OnClickListener ( ) {
                            @Override
                            public void onClick(View v) {

                                Intent i = new Intent ( mainpage_activity.this, User_Notification.class );
                                startActivity ( i );

                            }
                        } );
                    }
                }
            }
        } );



 SharedPreferences sharedPreferences1 = PreferenceManager.getDefaultSharedPreferences ( this );
   String start = sharedPreferences1.getString ( "StartAfter","" );




 */
// query2 = ParseUser.getQuery ( );
//       query2.whereExists ( "ids" );
//       query2.findInBackground ( new FindCallback<ParseUser> ( ) {
//           @Override
//           public void done(List<ParseUser> objects, ParseException e) {
//               if (e == null) {
//                   for (final ParseUser user : objects) {
//                       if (!user.getList ( "ids" ).isEmpty ( ) ) {
//                           user_notification_array = (ArrayList<String>) user.get ( "ids" );
//                           user_notification = new String[user_notification_array.size ( )];
//
//                           user_notification = user_notification_array.toArray ( user_notification );
//                           for (String s : user_notification) {
//                               size = s;
//                           }
//                           if (size.equals ( ParseUser.getCurrentUser ( ).getUsername ( ) )) {
//                               for (j = 0; j <= user_notification.length; j++) {
//
//                               }
//                               if (j== 0) {
//                                   menuItem.setActionView ( R.layout.user_notifcation_null );
//                                   view = menuItem.getActionView ( );
//                                   view.setOnClickListener ( new View.OnClickListener ( ) {
//                                       @Override
//                                       public void onClick(View v) {
//
//                                           Intent i = new Intent ( mainpage_activity.this, notification_null.class );
//                                           startActivity ( i );
//
//                                       }
//                                   } );
//                               } else {
//
//                                   menuItem.setActionView ( R.layout.user_notifications_layout );
//                                   view = menuItem.getActionView ( );
//                                   textView = view.findViewById ( R.id.user_badge );
//                                   textView.setText ( String.valueOf ( j ) );
//
//
//                                   view.setOnClickListener ( new View.OnClickListener ( ) {
//                                       @Override
//                                       public void onClick(View v) {
//                                           if (ParseUser.getCurrentUser ().has ( "address1" )) {
//
//
//                                               Intent i = new Intent ( mainpage_activity.this, User_Notification.class );
//                                               startActivity ( i );
//                                           } else{
//
//
//                                               Intent intent = new Intent ( mainpage_activity.this,  User_address.class );
//                                               startActivity ( intent );
//                                           }
//
//                                       }
//                                   } );
//
//                               }
//                           } else {
//                               //do nothing
//                           }
//
//                       }else{
//                           menuItem.setActionView ( R.layout.user_notifcation_null );
//                           view = menuItem.getActionView ( );
//                           view.setOnClickListener ( new View.OnClickListener ( ) {
//                               @Override
//                               public void onClick(View v) {
//
//                                   Intent i = new Intent ( mainpage_activity.this, notification_null.class );
//                                   startActivity ( i );
//
//                               }
//                           } );
//                       }
//                   }
//
//               }
//           }
//       } );

/*
 final
        if(obj_user.equals ( ParseUser.getCurrentUser ().getUsername () )){
            menuItem.setActionView ( R.layout.user_notifications_layout );
            view = menuItem.getActionView ();
            textView = view.findViewById ( R.id.user_badge );
            textView.setText ( "1" );
            view.setOnClickListener ( new View.OnClickListener ( ) {
                @Override
                public void onClick(View v) {
                    avail_object.edit ().clear ().commit ();
                    avail_object.edit ().putString ( "id_user","" );
                    Intent intent = new Intent ( mainpage_activity.this, buy_avialable.class );
                    startActivity ( intent );
                }
            } );
        }else{
            menuItem.setActionView ( null );
        }

 /*
  <item
        android:id="@+id/user_kart_cancel"
        android:icon="@drawable/ic_check_box_outline_blank_24"
        android:title="cart"
        app:showAsAction="if room"/>
 */


/*
 */