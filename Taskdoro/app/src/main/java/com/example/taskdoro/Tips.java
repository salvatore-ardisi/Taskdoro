package com.example.taskdoro;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.concurrent.TimeoutException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Tips#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tips extends Fragment {

    private TextView mapView;
    private TextView muteView;
    private TextView getSleepView;
    private TextView plannerView;
    private TextView timeView;
    private TextView breaksView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Tips() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tips.
     */
    // TODO: Rename and change types and number of parameters
    public static Tips newInstance(String param1, String param2) {
        Tips fragment = new Tips();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tips, container, false);

        mapView = (TextView) view.findViewById(R.id.map_textview_button);
        muteView = (TextView) view.findViewById(R.id.mute_textview);
        getSleepView = (TextView) view.findViewById(R.id.get_sleep_textview);
        plannerView = (TextView) view.findViewById(R.id.plan_textview);
        timeView = (TextView) view.findViewById(R.id.set_time_for_task_textview);
        breaksView = (TextView)view.findViewById(R.id.take_break_textview);

        //click textview, opens MapActivity
        mapView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), MapsActivity.class);
                startActivity(intent);
                //Toast.makeText(getActivity(), "HELLO", Toast.LENGTH_SHORT).show();
            }
        });
        //click textview, opens webpage
        muteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "https://www.lifehack.org/articles/productivity/10-ways-remove-the-distractions-that-keep-you-from-doing-the-best-work.html";
                //parse the URI and make intent
                Uri webpage = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(intent);
            }
        });

        getSleepView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "https://www.healthline.com/nutrition/17-tips-to-sleep-better";
                //parse the URI and make intent
                Uri webpage = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(intent);
            }
        });


        plannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.calendar.com/blog/25-benefits-of-creating-a-schedule-for-your-tasks/#:~:text=Having%20a%20plan%20paints%20a,role%20in%20the%20big%20picture.";
                //parse the URI and make intent
                Uri webpage = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(intent);
            }
        });
        timeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "https://todoist.com/productivity-methods/pomodoro-technique";
                //parse the URI and make intent
                Uri webpage = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(intent);
            }
        });

        breaksView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "https://dariusforoux.com/takebreaks-pomodoro/";
                //parse the URI and make intent
                Uri webpage = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(intent);
            }
        });


        return view;
    }


}