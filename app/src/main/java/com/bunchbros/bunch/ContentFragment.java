package com.bunchbros.bunch;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * Created by shah on 8/16/15.
 */
public class ContentFragment extends Fragment {


    private static final String KEY_POSITION = "position"; // used as a key for the bundle to retrieve the content
    static ContentFragment newInstance(int position)
    {
        ContentFragment frag = new ContentFragment();
        Bundle args = new Bundle(); // used to save the state
        args.putInt(KEY_POSITION, position);
        frag.setArguments(args);
        Log.d("TEST", "indeisde contructor!");
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState)
    {

        View result =inflater.inflate(R.layout.content, container, false); // call inflator to inflate the view
        int position = getArguments().getInt(KEY_POSITION,-1); // get the index we are at, (incase activity is destroyed)
        People temp = Bunch.getCurrentPerson(position); // get the current person
        TextView text = (TextView)result.findViewById(R.id.text);
        //text.setText("tetsing");
        text.setText(temp.getName());
        return result;


    }
}
