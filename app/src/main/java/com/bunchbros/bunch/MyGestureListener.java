package com.bunchbros.bunch;

import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

/**
 * Created by shah on 8/25/15.
 */
//public class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
//
//    public final static String DEBUG = "shah";
//
//    public static String SWIPE_DIRECTION = ""; // used to set which direction did the swipe occuer
//
//    private static final int SWIPE_THRESHOLD = 100;
//    private static final int SWIPE_VELOCITY_THRESHOLD = 100;
//
//
//    @Override
//    public boolean onDown(MotionEvent event)
//    {
//        Log.d(DEBUG,"In the onDown method");
//        return true;
//    }
//
//    @Override
//    public boolean onFling(MotionEvent event1, MotionEvent event2,
//                           float velocityX, float velocityY) {
//        boolean result = false;
//        try
//        {
//            float diffY = event2.getY() - event1.getY();
//            float diffX = event2.getX() - event1.getX();
//            if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD)
//            {
//                if (diffX > 0)
//                {
//                    Log.d(DEBUG,"Right Swipe");
//                    SWIPE_DIRECTION = "Right";
//
//                }
//                else
//                {
//                    SWIPE_DIRECTION = "Left";
//                    Log.d(DEBUG,"LEFT SWIPE");
//                }
//            }
//        }
//        catch (Exception ex)
//        {
//            ex.printStackTrace();
//        }
//        //Log.d(DEBUG, "onFling: " + event1.toString()+event2.toString());
//        return true;
//    }
//
//}
