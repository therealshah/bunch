package com.bunchbros.bunch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.SimpleOnPageChangeListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;


import java.util.ArrayList;


public class Bunch extends Activity {

    private ViewPager pager = null; // will hold the pager from the UI
    private static ArrayList<People> peopleList = new ArrayList<People>(); // used to hold the people
    private static int index = 0; // current index of the list
    private int savedState = 0; // used to keep track of which number was saved
    private static String DEBUG_TAG = "TESTING";

//    private GestureDetectorCompat mDetector;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bunch); // set the menuiew
        populateList();
        //mDetector = new GestureDetectorCompat(this,new MyGestureListener());


    }







    /**
     * used to get the index of the array we are in
     * @return
     */
    public static int getIndex()
    {
        incrementIndex();
        return index; // retrieve the index
    }

    public static void incrementIndex()
    {
        if (++index >= peopleList.size() )
            index = 0; // just as a test measurement, so we dont exceed the bounds of the array
    }

    /**
     * test method
     */
    private void populateList()
    {
        peopleList.add(new People("Adit"));
        peopleList.add(new People("shah"));
        peopleList.add(new People("amit"));
        peopleList.add(new People("saad"));
        peopleList.add(new People("Matt"));
        peopleList.add(new People("Deshawn"));
        peopleList.add(new People("Jimi"));
        peopleList.add(new People("Khem"));
        peopleList.add(new People("Kevin"));
        peopleList.add(new People("Depu"));

    }

    public static  People getCurrentPerson(int pos)
    {
        return peopleList.get(pos); // return the current person
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bunch, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showFragment(View view) {
        Log.d(DEBUG_TAG,"SHOWFRAG");
        startActivity(new Intent(this,ContentAdapter.class));
        finish(); // end the starting activity
       // Toast.makeText(this,"TESTING",Toast.LENGTH_SHORT).show();

    }
}
