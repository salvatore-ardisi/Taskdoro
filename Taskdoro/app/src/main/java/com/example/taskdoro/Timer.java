package com.example.taskdoro;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


public class Timer extends Fragment {


    Button btnstart;
    TextView cntdwntxt;
    TextView motivationalText;
    CountDownTimer cntdwntmr;
    ProgressBar progressBar;
    private long timeleft = 1500000;
    private boolean timerRunning;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Timer() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static Timer newInstance(String param1, String param2) {
        Timer fragment = new Timer();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_timer, container, false);
        btnstart = view.findViewById(R.id.btnstart);
        cntdwntxt = view.findViewById(R.id.cntdwntxt);
        progressBar = view.findViewById(R.id.progress_bar);
        motivationalText = view.findViewById(R.id.text_to_change);


        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setProgress(100);
                btnstart.setVisibility(View.GONE);
                startTimer();
            }
        });


        return view;
    }


    public void startTimer(){
        cntdwntmr = new CountDownTimer(timeleft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                btnstart.setVisibility(View.GONE);
                timeleft = millisUntilFinished;
                int progress = (int) (millisUntilFinished/15000);

                if (progress >= 0 && progress < 33) {

                    motivationalText.setVisibility(View.VISIBLE);
                    motivationalText.setText(R.string.motivationaltext);
                }
                else if (progress >= 33 && progress< 66) {
                    motivationalText.setVisibility(View.VISIBLE);
                    motivationalText.setText(R.string.motivationaltext2);
                }
                else {
                    motivationalText.setVisibility(View.VISIBLE);
                    motivationalText.setText(R.string.motivationaltext3);
                }

                progressBar.setProgress(progress);
                updateTimer();


            }
    @Override
            public void onFinish() {
                String done = "Time's Up!";
                cntdwntxt.setTextSize(40);
                cntdwntxt.setText(done);
                cntdwntxt.setTextSize(80);
                timeleft = 1500000;
                progressBar.setProgress(0);
                btnstart.setVisibility(View.VISIBLE);

            }
        }.start();

        timerRunning = true;
    }


    public void updateTimer(){
        int minutes = (int) (timeleft / 1000) / 60;
        int seconds = (int) (timeleft / 1000) % 60;

        String timeleftText;

        timeleftText = "" + minutes;
        timeleftText += ":";
        if (seconds < 10) timeleftText += "0";
        timeleftText += seconds;

        cntdwntxt.setText(timeleftText);
    }
}