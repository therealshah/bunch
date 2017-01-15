package com.bunchbros.bunch;

/**
 * Created by shah on 8/16/15.
 */

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;

import static android.app.PendingIntent.getActivity;

public class ContentAdapter extends Activity {

    /**
     * Contructor
     */

    private GestureDetectorCompat mDetector;
    public final static String DEBUG = "shah";
    public static String SWIPE_DIRECTION = ""; // used to set which direction did the swipe occuer
    public Activity activity = this;


    public enum Direction{
        up,
        down,
        left,
        right;

        /**
         * Returns a direction given an angle.
         * Directions are defined as follows:
         *
         * Up: [45, 135]
         * Right: [0,45] and [315, 360]
         * Down: [225, 315]
         * Left: [135, 225]
         *
         * @param angle an angle from 0 to 360 - e
         * @return the direction of an angle
         */
        public static Direction get(double angle){
            if(inRange(angle, 45, 135)){
                return Direction.up;
            }
            else if(inRange(angle, 0,45) || inRange(angle, 315, 360)){
                return Direction.right;
            }
            else if(inRange(angle, 225, 315)){
                return Direction.down;
            }
            else{
                return Direction.left;
            }

        }

        /**
         * @param angle an angle
         * @param init the initial bound
         * @param end the final bound
         * @return returns true if the given angle is in the interval [init, end).
         */
        private static boolean inRange(double angle, float init, float end){
            return (angle >= init) && (angle < end);
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // chain to base class
        setContentView(R.layout.swipecard);

        //setContentView(R.layout.content);
        mDetector = new GestureDetectorCompat(this, new OnSwipeListener());

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container,ContentFragment.newInstance(Bunch.getIndex()));
        ft.commit();


    }

    //    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.mDetector.onTouchEvent(event);

        return super.onTouchEvent(event);
    }




    public class OnSwipeListener extends GestureDetector.SimpleOnGestureListener{

        @Override
        public boolean onDown(MotionEvent event) {
            Log.d(DEBUG, "In the onDown method");
            return true;
        }


        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            // Grab two events located on the plane at e1=(x1, y1) and e2=(x2, y2)
            // Let e1 be the initial event
            // e2 can be located at 4 different positions, consider the following diagram
            // (Assume that lines are separated by 90 degrees.)
            //
            //
            //         \ A  /
            //          \  /
            //       D   e1   B
            //          /  \
            //         / C  \
            //
            // So if (x2,y2) falls in region:
            //  A => it's an UP swipe
            //  B => it's a RIGHT swipe
            //  C => it's a DOWN swipe
            //  D => it's a LEFT swipe
            //

            float x1 = e1.getX();
            float y1 = e1.getY();

            float x2 = e2.getX();
            float y2 = e2.getY();

            Direction direction = getDirection(x1,y1,x2,y2);
            if (direction == Direction.right)
            {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.enter_from_left,R.anim.exit_to_right);
                TextView text = (TextView)findViewById(R.id.text);
                ft.replace(R.id.fragment_container,ContentFragment.newInstance(Bunch.getIndex()));
                //ft.replace(android.R.id.content,ContentFragment.newInstance(Bunch.getIndex()));
                ft.commit();
                Toast.makeText(activity,"You have liked " + text.getText(),Toast.LENGTH_SHORT).show();
            }
            else if (direction == Direction.left)
            {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left);
                TextView text = (TextView)findViewById(R.id.text);
                //ft.replace(android.R.id.content,ContentFragment.newInstance(Bunch.getIndex()));
                ft.replace(R.id.fragment_container,ContentFragment.newInstance(Bunch.getIndex()));
                ft.commit();
                Toast.makeText(activity, "You have liked " + text.getText(), Toast.LENGTH_SHORT).show();
            }
            return onSwipe(direction);
        }

        public boolean onSwipe(Direction direction){
            return false;
        }

        /**
         * Given two points in the plane p1=(x1, x2) and p2=(y1, y1), this method
         * returns the direction that an arrow pointing from p1 to p2 would have.
         * @param x1 the x position of the first point
         * @param y1 the y position of the first point
         * @param x2 the x position of the second point
         * @param y2 the y position of the second point
         * @return the direction
         */
        public Direction getDirection(float x1, float y1, float x2, float y2){
            double angle = getAngle(x1, y1, x2, y2);
            return Direction.get(angle);
        }

        /**
         *
         * Finds the angle between two points in the plane (x1,y1) and (x2, y2)
         * The angle is measured with 0/360 being the X-axis to the right, angles
         * increase counter clockwise.
         *
         * @param x1 the x position of the first point
         * @param y1 the y position of the first point
         * @param x2 the x position of the second point
         * @param y2 the y position of the second point
         * @return the angle between two points
         */
        public double getAngle(float x1, float y1, float x2, float y2) {

            double rad = Math.atan2(y1-y2,x2-x1) + Math.PI;
            return (rad*180/Math.PI + 180)%360;
        }



    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {



        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;


        @Override
        public boolean onDown(MotionEvent event) {
            Log.d(DEBUG, "In the onDown method");
            return true;
        }

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {
            boolean result = false;
            try {
                float diffY = event2.getY() - event1.getY();
                float diffX = event2.getX() - event1.getX();
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {
                        Log.d(DEBUG, "Right Swipe");
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.setCustomAnimations(R.anim.enter_from_left,R.anim.exit_to_right);
                        TextView text = (TextView)findViewById(R.id.text);
                        //ft.replace(R.id.fragment_container,ContentFragment.newInstance(Bunch.getIndex()));
                        ft.replace(android.R.id.content,ContentFragment.newInstance(Bunch.getIndex()));
                        ft.commit();
                        Toast.makeText(activity,"You have liked " + text.getText(),Toast.LENGTH_SHORT).show();


                    } else {
                        SWIPE_DIRECTION = "Left";
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left);
                        TextView text = (TextView)findViewById(R.id.text);
                        ft.replace(android.R.id.content,ContentFragment.newInstance(Bunch.getIndex()));
                      //  ft.replace(R.id.fragment_container,ContentFragment.newInstance(Bunch.getIndex()));
                        ft.commit();
                        Toast.makeText(activity, "You have liked " + text.getText(), Toast.LENGTH_SHORT).show();
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            //Log.d(DEBUG, "onFling: " + event1.toString()+event2.toString());
            return true;
        }

    }
}