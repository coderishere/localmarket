package com.example.connect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.prefs.PreferenceChangeListener;

public class Shopkeeper_mainpage extends AppCompatActivity implements View.OnClickListener{
    TextView itemcount,itemcount1,itemcount2;
    ParseQuery<ParseUser> parseQuery;
    ParseQuery<ParseObject>parseQuery1;
    ArrayList<String >userTastesGot,newuserTastesGot,arrayList,arrayList1,checkfornewobject,newdata;
    String size,objectid,objectid1;
    ImageView imageView;
    int sat;
    Set<String> set;
    SharedPreferences shop_order_size,sharedPreferencescheck;
    String storing;
    String[] stockArr,newstockArr,getStockArr1;
    int i,s,j,k;
    MenuItem menuItem,menuItem1,menuItem2,menuItem3;
    View view, view1,view2,view3;
    ViewFlipper viewFlipper;
    @Override
    public void onClick(View v) {
        if(v.getId ()==R.id.menu_icon){
            final PopupMenu popupMenu = new PopupMenu ( Shopkeeper_mainpage.this, imageView );
            popupMenu.getMenuInflater ( ).inflate ( R.menu.shop_menu, popupMenu.getMenu ( ) );
            popupMenu.show ( );
          //  menuItem3 = popupMenu.getMenu ().findItem ( R.id. )



            popupMenu.setOnMenuItemClickListener ( new PopupMenu.OnMenuItemClickListener ( ) {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    if(item.getItemId ()==R.id.returns){
                     Intent intent = new Intent ( Shopkeeper_mainpage.this, return_product.class );
                     startActivity ( intent );
                    }if(item.getItemId ()==R.id.replace){
                      Intent intent = new Intent ( Shopkeeper_mainpage.this, replace_product.class );
                      startActivity ( intent );
                    }if(item.getItemId ()==R.id.logout_shop){
                        ParseUser.logOut ();
                        Intent intent = new Intent ( Shopkeeper_mainpage.this, Shop_login.class );
                        startActivity ( intent );
                    }
                    return false;
                }
            } );
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_shopkeeper_mainpage );
        imageView = findViewById ( R.id.menu_icon );
        imageView.setOnClickListener ( this );
        arrayList = new ArrayList<> (  );
        arrayList1 = new ArrayList<> (  );
        checkfornewobject = new ArrayList<> (  );

        viewFlipper = findViewById ( R.id.view_flipper1 );
        int image[] = {R.drawable.slideone, R.drawable.slidestwo};

        for(int images : image){
            flipper ( images );
        }



      //set.add ( "" );



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
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.notification_menu, menu);

        menuItem = menu.findItem ( R.id.action_cart );
        menuItem1 = menu.findItem ( R.id.shoping_kart );
        menuItem2 = menu.findItem ( R.id.check_availability );
        if(ParseUser.getCurrentUser ().getString ( "cateogory" ).equals ( "Book" )) {
            if (ParseUser.getCurrentUser ( ).has ( "newlists" )) {
                Log.i ( "nwlistsis","ready" );
                ParseQuery<ParseUser> parseQuery = ParseUser.getQuery ( );
                parseQuery.getInBackground ( ParseUser.getCurrentUser ( ).getObjectId ( ), new GetCallback<ParseUser> ( ) {
                    @Override
                    public void done(ParseUser object, ParseException e) {
                        if (e == null) {
                            ParseQuery<ParseObject>parseQuery2 = new ParseQuery<ParseObject> ( "Notification" );
                            parseQuery2.findInBackground ( new FindCallback<ParseObject> ( ) {
                                @Override
                                public void done(List<ParseObject> objects, ParseException e) {
                                    if(e==null){
                                        for(ParseObject object1: objects){
                                            if(!ParseUser.getCurrentUser ().getList ( "newlists" ).contains ( object1.getObjectId () )) {
                                                ParseUser.getCurrentUser ( ).addUnique ( "list", object1.getObjectId ( ) );
                                                ParseUser.getCurrentUser ( ).saveInBackground ( );
                                            }
                                            ParseQuery<ParseObject>parseQuerynotification = new ParseQuery<ParseObject> ( "Notification" );
                                            parseQuerynotification.findInBackground ( new FindCallback<ParseObject> ( ) {
                                                @Override
                                                public void done(List<ParseObject> objects, ParseException e) {
                                                    if(e==null&& objects.size ()>0){
                                                        ParseQuery<ParseUser>   parseQuery = ParseUser.getQuery ( );
                                                        // parseQuery.selectKeys ( Arrays.asList ( "list" ) );
                                                        parseQuery.whereExists ( "code" );

                                                        parseQuery.getInBackground ( ParseUser.getCurrentUser ( ).getObjectId ( ), new GetCallback<ParseUser> ( ) {
                                                            @Override
                                                            public void done(final ParseUser object, ParseException e) {
                                                                if (e == null) {
                                                                    try {
                                                                        userTastesGot = (ArrayList<String>) object.get ( "list" );
                                                                        stockArr = new String[userTastesGot.size ( )];
                                                                        stockArr = userTastesGot.toArray ( stockArr );
                                                                        for (i = 0; i <= stockArr.length; i++) {

                                                                            s = i;

                                                                        }
                                                                    }catch (Exception e1){
                                                                        e1.printStackTrace ();
                                                                    }
                                                                    Log.i ( "sizes", String.valueOf ( userTastesGot.size ( ) ) );



                                                                    if (s == 0) {
                                                                        menuItem.setActionView ( R.layout.notification_null );
                                                                        view = menuItem.getActionView ( );
                                                                        view.setOnClickListener ( new View.OnClickListener ( ) {
                                                                            @Override
                                                                            public void onClick(View v) {
                                                                                Intent intent1 = new Intent ( Shopkeeper_mainpage.this, notification_null.class );
                                                                                startActivity ( intent1 );
                                                                            }
                                                                        } );

                                                                    } else {
                                                                        menuItem.setActionView ( R.layout.notification_layout );
                                                                        view = menuItem.getActionView ( );
                                                                        itemcount1 = view.findViewById ( R.id.cart_badge3 );
                                                                        itemcount1.setText ( String.valueOf ( s ) );

                                                                        view.setOnClickListener ( new View.OnClickListener ( ) {
                                                                            @Override
                                                                            public void onClick(View v) {
                                                                                Intent intent = new Intent ( Shopkeeper_mainpage.this, Notification_shop.class );
                                                                                startActivity ( intent );
                                                                                shop_order_size.edit ( ).clear ( ).commit ( );
                                                                                shop_order_size.edit ( ).putString ( "oder_size", "" ).commit ( );
                                                                                ParseQuery<ParseObject> parseQuery2 = new ParseQuery<ParseObject> ( "Direct_Book_product" );
                                                                                parseQuery2.findInBackground ( new FindCallback<ParseObject> ( ) {
                                                                                    @Override
                                                                                    public void done(List<ParseObject> objects, ParseException e) {
                                                                                        if(e==null){
                                                                                            for(ParseObject object1:objects){
                                                                                                ParseUser.getCurrentUser ().addUnique ( "newlists", object1.getObjectId () );
                                                                                                ParseUser.getCurrentUser ().saveInBackground ();
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                } );

                                                                                ParseQuery<ParseObject> parseQuery3 = new ParseQuery<ParseObject> ( "Notification" );
                                                                                parseQuery3.findInBackground ( new FindCallback<ParseObject> ( ) {
                                                                                    @Override
                                                                                    public void done(List<ParseObject> objects, ParseException e) {
                                                                                        if(e==null){
                                                                                            for(ParseObject object1: objects){
                                                                                                ParseUser.getCurrentUser ().addUnique ( "newlists", object1.getObjectId () );
                                                                                                ParseUser.getCurrentUser ().saveInBackground ();
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                } );

                                                                            }
                                                                        } );

                                                                    }

                                                                }
                                                            }
                                                        } );
                                                    }
                                                }
                                            } );
                                        }

                                    }
                                }
                            } );
                         /* ParseQuery<ParseObject> parseQuery3 = new ParseQuery<ParseObject> ( "Direct_Book_product" );
                          parseQuery3.findInBackground ( new FindCallback<ParseObject> ( ) {
                              @Override
                              public void done(List<ParseObject> objects, ParseException e) {
                                  if(e==null){
                                      for(ParseObject object1: objects){
                                          if(!ParseUser.getCurrentUser ().getList ( "newlists" ).contains ( object1.getObjectId () )) {
                                              ParseUser.getCurrentUser ( ).addUnique ( "list", object1.getObjectId ( ) );
                                              ParseUser.getCurrentUser ( ).saveInBackground ( );
                                          }
                                      }
                                  }
                              }
                          } );*/
                        }
                    }
                } );


            }else {

                Log.i ( "apple","seb" );
                ParseQuery<ParseObject>parseQuery3 = new ParseQuery<ParseObject> ( "Notification" );
                parseQuery3.findInBackground ( new FindCallback<ParseObject> ( ) {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if(e==null&&objects.size ()>0){
                            for(ParseObject object1: objects){
                                ParseUser.getCurrentUser ().addUnique ( "list",object1.getObjectId () );
                                ParseUser.getCurrentUser ().saveInBackground ();
                                ParseQuery<ParseObject>parseQuerynotification = new ParseQuery<ParseObject> ( "Notification" );
                                parseQuerynotification.findInBackground ( new FindCallback<ParseObject> ( ) {
                                    @Override
                                    public void done(List<ParseObject> objects, ParseException e) {
                                        if(e==null&& objects.size ()>0){
                                            ParseQuery<ParseUser>   parseQuery = ParseUser.getQuery ( );
                                            // parseQuery.selectKeys ( Arrays.asList ( "list" ) );
                                            parseQuery.whereExists ( "code" );

                                            parseQuery.getInBackground ( ParseUser.getCurrentUser ( ).getObjectId ( ), new GetCallback<ParseUser> ( ) {
                                                @Override
                                                public void done(final ParseUser object, ParseException e) {
                                                    if (e == null) {
                                                        try {
                                                            userTastesGot = (ArrayList<String>) object.get ( "list" );
                                                            stockArr = new String[userTastesGot.size ( )];
                                                            stockArr = userTastesGot.toArray ( stockArr );
                                                            for (i = 0; i <= stockArr.length; i++) {

                                                                s = i;

                                                            }
                                                        }catch (Exception e1){
                                                            e1.printStackTrace ();
                                                        }
                                                        Log.i ( "sizes", String.valueOf ( userTastesGot.size ( ) ) );



                                                        if (s == 0) {
                                                            menuItem.setActionView ( R.layout.notification_null );
                                                            view = menuItem.getActionView ( );
                                                            view.setOnClickListener ( new View.OnClickListener ( ) {
                                                                @Override
                                                                public void onClick(View v) {
                                                                    Intent intent1 = new Intent ( Shopkeeper_mainpage.this, notification_null.class );
                                                                    startActivity ( intent1 );
                                                                }
                                                            } );

                                                        } else {
                                                            menuItem.setActionView ( R.layout.notification_layout );
                                                            view = menuItem.getActionView ( );
                                                            itemcount1 = view.findViewById ( R.id.cart_badge3 );
                                                            itemcount1.setText ( String.valueOf ( s ) );

                                                            view.setOnClickListener ( new View.OnClickListener ( ) {
                                                                @Override
                                                                public void onClick(View v) {
                                                                    Intent intent = new Intent ( Shopkeeper_mainpage.this, Notification_shop.class );
                                                                    startActivity ( intent );
                                                                    shop_order_size.edit ( ).clear ( ).commit ( );
                                                                    shop_order_size.edit ( ).putString ( "oder_size", "" ).commit ( );
                                                                    ParseQuery<ParseObject> parseQuery2 = new ParseQuery<ParseObject> ( "Direct_Book_product" );
                                                                    parseQuery2.findInBackground ( new FindCallback<ParseObject> ( ) {
                                                                        @Override
                                                                        public void done(List<ParseObject> objects, ParseException e) {
                                                                            if(e==null){
                                                                                for(ParseObject object1:objects){
                                                                                    ParseUser.getCurrentUser ().addUnique ( "newlists", object1.getObjectId () );
                                                                                    ParseUser.getCurrentUser ().saveInBackground ();
                                                                                }
                                                                            }
                                                                        }
                                                                    } );

                                                                    ParseQuery<ParseObject> parseQuery3 = new ParseQuery<ParseObject> ( "Notification" );
                                                                    parseQuery3.findInBackground ( new FindCallback<ParseObject> ( ) {
                                                                        @Override
                                                                        public void done(List<ParseObject> objects, ParseException e) {
                                                                            if(e==null){
                                                                                for(ParseObject object1: objects){
                                                                                    ParseUser.getCurrentUser ().addUnique ( "newlists", object1.getObjectId () );
                                                                                    ParseUser.getCurrentUser ().saveInBackground ();
                                                                                }
                                                                            }
                                                                        }
                                                                    } );

                                                                }
                                                            } );

                                                        }

                                                    }
                                                }
                                            } );
                                        }
                                    }
                                } );
                            }

                        }
                    }
                } );
             /* ParseQuery<ParseObject> parseQuery4 = new ParseQuery<ParseObject> ( "Direct_Book_product" );
              parseQuery4.findInBackground ( new FindCallback<ParseObject> ( ) {
                  @Override
                  public void done(List<ParseObject> objects, ParseException e) {
                      if(e==null){
                          for(ParseObject object1: objects){
                                  ParseUser.getCurrentUser ( ).addUnique ( "list", object1.getObjectId ( ) );
                                  ParseUser.getCurrentUser ( ).saveInBackground ( );
                          }
                      }
                  }
              } );*/
            }

        }




        //start
       else if (ParseUser.getCurrentUser ( ).getString ( "cateogory" ).equals ( "Electronics" )) {
            if (ParseUser.getCurrentUser ( ).has ( "newlists" )) {
                Log.i ( "nwlistsis","ready" );
                ParseQuery<ParseUser> parseQuery = ParseUser.getQuery ( );
                parseQuery.getInBackground ( ParseUser.getCurrentUser ( ).getObjectId ( ), new GetCallback<ParseUser> ( ) {
                    @Override
                    public void done(ParseUser object, ParseException e) {
                        if (e == null) {
                            ParseQuery<ParseObject>parseQuery2 = new ParseQuery<ParseObject> ( "Ele_Notification" );
                            parseQuery2.findInBackground ( new FindCallback<ParseObject> ( ) {
                                @Override
                                public void done(List<ParseObject> objects, ParseException e) {
                                    if(e==null){
                                        for(ParseObject object1: objects){
                                            if(!ParseUser.getCurrentUser ().getList ( "newlists" ).contains ( object1.getObjectId () )) {
                                                ParseUser.getCurrentUser ( ).addUnique ( "list", object1.getObjectId ( ) );
                                                ParseUser.getCurrentUser ( ).saveInBackground ();
                                            }
                                            ParseQuery<ParseObject> parseQuerynotify = new ParseQuery<ParseObject> ( "Ele_Notification" );
                                            parseQuerynotify.findInBackground ( new FindCallback<ParseObject> ( ) {
                                                @Override
                                                public void done(List<ParseObject> objects, ParseException e) {
                                                    if (e == null && objects.size ( ) > 0) {
                                                        ParseQuery<ParseUser> parseQuery = ParseUser.getQuery ( );
                                                        // parseQuery.selectKeys ( Arrays.asList ( "list" ) );


                                                        parseQuery.getInBackground ( ParseUser.getCurrentUser ( ).getObjectId ( ), new GetCallback<ParseUser> ( ) {
                                                            @Override
                                                            public void done(final ParseUser object, ParseException e) {
                                                                if (e == null) {
                                                                    try {
                                                                        userTastesGot = (ArrayList<String>) object.get ( "list" );
                                                                        stockArr = new String[userTastesGot.size ( )];
                                                                        Log.i ( "sizes", String.valueOf ( userTastesGot.size ( ) ) );
                                                                        stockArr = userTastesGot.toArray ( stockArr );
                                                                        for (i = 0; i <= stockArr.length; i++) {
                                                                            s = i;
                                                                        }
                                                                    } catch (Exception e1) {
                                                                        e1.printStackTrace ( );
                                                                    }


                                                                    if (s == 0) {
                                                                        menuItem.setActionView ( R.layout.notification_null );
                                                                        view = menuItem.getActionView ( );
                                                                        view.setOnClickListener ( new View.OnClickListener ( ) {
                                                                            @Override
                                                                            public void onClick(View v) {
                                                                                Intent intent1 = new Intent ( Shopkeeper_mainpage.this, notification_null.class );
                                                                                startActivity ( intent1 );
                                                                            }
                                                                        } );

                                                                    } else {
                                                                        menuItem.setActionView ( R.layout.notification_layout );
                                                                        view = menuItem.getActionView ( );
                                                                        itemcount1 = view.findViewById ( R.id.cart_badge3 );
                                                                        itemcount1.setText ( String.valueOf ( s ) );

                                                                        view.setOnClickListener ( new View.OnClickListener ( ) {
                                                                            @Override
                                                                            public void onClick(View v) {

                                                                                Intent intent = new Intent ( Shopkeeper_mainpage.this, Notification_shop.class );
                                                                                startActivity ( intent );
                                                                                parseQuery1 = new ParseQuery<ParseObject> ( "Ele_Notification" );
                                                                                parseQuery1.findInBackground ( new FindCallback<ParseObject> ( ) {
                                                                                    @Override
                                                                                    public void done(List<ParseObject> objects, ParseException e) {
                                                                                        if (e == null) {

                                                                                            for (ParseObject object1 : objects) {
                                                                                                ParseUser.getCurrentUser ( ).addUnique ( "newlists", object1.getObjectId ( ) );
                                                                                                ParseUser.getCurrentUser ( ).saveInBackground ( );

                                                                                            }
                                                                                        }
                                                                                    }
                                                                                } );
                                                                                ParseQuery<ParseObject> parseQuery2 = new ParseQuery<ParseObject> ( "Direct_ele_product" );
                                                                                parseQuery2.findInBackground ( new FindCallback<ParseObject> ( ) {
                                                                                    @Override
                                                                                    public void done(List<ParseObject> objects, ParseException e) {
                                                                                        if (e == null) {
                                                                                            for (ParseObject object1 : objects) {
                                                                                                ParseUser.getCurrentUser ( ).addUnique ( "newlists", object1.getObjectId ( ) );
                                                                                                ParseUser.getCurrentUser ( ).saveInBackground ( );
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                } );

                                                                                shop_order_size.edit ( ).clear ( ).commit ( );
                                                                                shop_order_size.edit ( ).putString ( "oder_size", "" ).commit ( ); // don't remove this shared Preference

                                                                            }
                                                                        } );

                                                                    }

                                                                }
                                                            }
                                                        } );


                                                    }
                                                }
                                            } );
                                        }

                                    }
                                }
                            } );
                                             /*   ParseQuery<ParseObject> parseQuery3 = new ParseQuery<ParseObject> ( "Direct_ele_product" );
                                                parseQuery3.findInBackground ( new FindCallback<ParseObject> ( ) {
                                                    @Override
                                                    public void done(List<ParseObject> objects, ParseException e) {
                                                        if(e==null){
                                                            for(ParseObject object1: objects){
                                                                if(!ParseUser.getCurrentUser ().getList ( "newlists" ).contains ( object1.getObjectId () )) {
                                                                    ParseUser.getCurrentUser ( ).addUnique ( "list", object1.getObjectId ( ) );
                                                                    ParseUser.getCurrentUser ( ).saveInBackground ( );
                                                                }
                                                            }
                                                        }
                                                    }
                                                } );*/
                        }
                    }
                } );


            }else {
                Log.i ( "apple","seb" );
                ParseQuery<ParseObject>parseQuery3 = new ParseQuery<ParseObject> ( "Ele_Notification" );
                parseQuery3.findInBackground ( new FindCallback<ParseObject> ( ) {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if(e==null&&objects.size ()>0){
                            for(ParseObject object1: objects){
                                ParseUser.getCurrentUser ().addUnique ( "list",object1.getObjectId () );
                                ParseUser.getCurrentUser ().saveInBackground ();
                                ParseQuery<ParseObject> parseQuerynotify = new ParseQuery<ParseObject> ( "Ele_Notification" );
                                parseQuerynotify.findInBackground ( new FindCallback<ParseObject> ( ) {
                                    @Override
                                    public void done(List<ParseObject> objects, ParseException e) {
                                        if (e == null && objects.size ( ) > 0) {
                                            ParseQuery<ParseUser> parseQuery = ParseUser.getQuery ( );
                                            // parseQuery.selectKeys ( Arrays.asList ( "list" ) );


                                            parseQuery.getInBackground ( ParseUser.getCurrentUser ( ).getObjectId ( ), new GetCallback<ParseUser> ( ) {
                                                @Override
                                                public void done(final ParseUser object, ParseException e) {
                                                    if (e == null) {
                                                        try {
                                                            userTastesGot = (ArrayList<String>) object.get ( "list" );
                                                            stockArr = new String[userTastesGot.size ( )];
                                                            Log.i ( "sizes", String.valueOf ( userTastesGot.size ( ) ) );
                                                            stockArr = userTastesGot.toArray ( stockArr );
                                                            for (i = 0; i <= stockArr.length; i++) {
                                                                s = i;
                                                            }
                                                        } catch (Exception e1) {
                                                            e1.printStackTrace ( );
                                                        }


                                                        if (s == 0) {
                                                            menuItem.setActionView ( R.layout.notification_null );
                                                            view = menuItem.getActionView ( );
                                                            view.setOnClickListener ( new View.OnClickListener ( ) {
                                                                @Override
                                                                public void onClick(View v) {
                                                                    Intent intent1 = new Intent ( Shopkeeper_mainpage.this, notification_null.class );
                                                                    startActivity ( intent1 );
                                                                }
                                                            } );

                                                        } else {
                                                            menuItem.setActionView ( R.layout.notification_layout );
                                                            view = menuItem.getActionView ( );
                                                            itemcount1 = view.findViewById ( R.id.cart_badge3 );
                                                            itemcount1.setText ( String.valueOf ( s ) );

                                                            view.setOnClickListener ( new View.OnClickListener ( ) {
                                                                @Override
                                                                public void onClick(View v) {

                                                                    Intent intent = new Intent ( Shopkeeper_mainpage.this, Notification_shop.class );
                                                                    startActivity ( intent );
                                                                    parseQuery1 = new ParseQuery<ParseObject> ( "Ele_Notification" );
                                                                    parseQuery1.findInBackground ( new FindCallback<ParseObject> ( ) {
                                                                        @Override
                                                                        public void done(List<ParseObject> objects, ParseException e) {
                                                                            if (e == null) {

                                                                                for (ParseObject object1 : objects) {
                                                                                    ParseUser.getCurrentUser ( ).addUnique ( "newlists", object1.getObjectId ( ) );
                                                                                    ParseUser.getCurrentUser ( ).saveInBackground ( );

                                                                                }
                                                                            }
                                                                        }
                                                                    } );
                                                                    ParseQuery<ParseObject> parseQuery2 = new ParseQuery<ParseObject> ( "Direct_ele_product" );
                                                                    parseQuery2.findInBackground ( new FindCallback<ParseObject> ( ) {
                                                                        @Override
                                                                        public void done(List<ParseObject> objects, ParseException e) {
                                                                            if (e == null) {
                                                                                for (ParseObject object1 : objects) {
                                                                                    ParseUser.getCurrentUser ( ).addUnique ( "newlists", object1.getObjectId ( ) );
                                                                                    ParseUser.getCurrentUser ( ).saveInBackground ( );
                                                                                }
                                                                            }
                                                                        }
                                                                    } );

                                                                    shop_order_size.edit ( ).clear ( ).commit ( );
                                                                    shop_order_size.edit ( ).putString ( "oder_size", "" ).commit ( ); // don't remove this shared Preference

                                                                }
                                                            } );

                                                        }

                                                    }
                                                }
                                            } );


                                        }
                                    }
                                } );
                            }

                        }
                    }
                } );
                  /*  ParseQuery<ParseObject> parseQuery4 = new ParseQuery<ParseObject> ( "Direct_ele_product" );
                    parseQuery4.findInBackground ( new FindCallback<ParseObject> ( ) {
                        @Override
                        public void done(List<ParseObject> objects, ParseException e) {
                            if(e==null){
                                for(ParseObject object1: objects){
                                        ParseUser.getCurrentUser ( ).addUnique ( "list", object1.getObjectId ( ) );
                                        ParseUser.getCurrentUser ( ).saveInBackground ( );
                                }
                            }
                        }
                    } );*/
            }

        }
       else{
            Toast.makeText ( this, "no current usr", Toast.LENGTH_SHORT ).show ( );
        }




            shop_order_size = PreferenceManager.getDefaultSharedPreferences ( this );
            final String shop_order = shop_order_size.getString ( "oder_size", "" ); // don't remove this shared Preference
            parseQuery = ParseUser.getQuery ( );
            parseQuery.whereExists ( "ShopIdList" );
            parseQuery.whereDoesNotExist ( "code" );
            parseQuery.findInBackground ( new FindCallback<ParseUser> ( ) {
                @Override
                public void done(List<ParseUser> objects, ParseException e) {
                    if (e == null) {
                        for (ParseObject object : objects) {

                            if (!object.getList ( "ShopIdList" ).isEmpty ( )) {
                                newuserTastesGot = (ArrayList<String>) object.get ( "ShopIdList" );
                                newstockArr = new String[newuserTastesGot.size ( )];

                                newstockArr = newuserTastesGot.toArray ( newstockArr );
                                for (String s : newstockArr) {
                                    size = s;
                                }
                                if (size.equals ( ParseUser.getCurrentUser ( ).getUsername ( ) )) {
                                    for (j = 0; j <= newstockArr.length; j++) {
                                        k = j;
                                    }
                                    if (k == 0 || shop_order.equals ( "0" )) {
                                        menuItem1.setActionView ( null );
                                    } else {
                                        menuItem1 = menu.findItem ( R.id.shoping_kart );
                                        menuItem1.setActionView ( R.layout.kart_layout );
                                        view1 = menuItem1.getActionView ( );
                                        itemcount = view1.findViewById ( R.id.shopkeeper_kart );
                                        itemcount.setText ( String.valueOf ( k ) );
                                        view1.setOnClickListener ( new View.OnClickListener ( ) {
                                            @Override
                                            public void onClick(View v) {
                                                Log.i ( "worked", "worked" );
                                                Intent intent = new Intent ( Shopkeeper_mainpage.this, shop_order_activity.class );
                                                startActivity ( intent );

                                            }
                                        } );
                                    }
                                } else {
                                    menuItem.setActionView ( null );
                                }

                            } else {
                                menuItem1.setActionView ( null );
                            }
                        }

                    }
                }
            } );



            final ParseQuery<ParseObject> newparsequery = new ParseQuery<ParseObject> ( "Check_Available_product" );
            newparsequery.findInBackground ( new FindCallback<ParseObject> ( ) {
                @Override
                public void done(List<ParseObject> objects, ParseException e) {
                    if(e==null&&objects.size ()>0){
                        for(final ParseObject object:objects){
                            if(object.getString ( "Shopkeeper_username" ).equals ( ParseUser.getCurrentUser ().getUsername () )){
                                menuItem2.setActionView ( R.layout.availability_layout );
                                view2 = menuItem2.getActionView ();
                                TextView textView = view2.findViewById ( R.id.available_text );
                                textView.setText ( "1" );
                                view2.setOnClickListener ( new View.OnClickListener ( ) {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent ( Shopkeeper_mainpage.this, availability.class );
                                        startActivity ( intent );
                                        newparsequery.getInBackground ( object.getObjectId ( ), new GetCallback<ParseObject> ( ) {
                                            @Override
                                            public void done(ParseObject object, ParseException e) {
                                                if(e==null){
                                                    object.put ( "Shopkeeper_username","");
                                                    object.saveInBackground ();
                                                }
                                            }
                                        } );
                                        shop_order_size.edit ( ).clear ( ).commit ( );
                                        shop_order_size.edit ( ).putString ( "oder_size", "" ).commit ( );
                                    }
                                } );
                            }
                        }
                    }else {
                        menuItem2.setActionView ( null );
                    }

                }
            } );



        return super.onCreateOptionsMenu ( menu );
    }


}
/*
 if(item_counter.equals ( "1" )) {

        }else{
            menuItem1.setActionView ( null );
        }






SharedPreferences sharedPreferences3 = PreferenceManager.getDefaultSharedPreferences ( this );
        String count = sharedPreferences3.getString ( "nowstart","" );

 */