package com.example.taskdoro;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import com.example.taskdoro.databinding.ActivityMainBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.tabs.TabLayout;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import com.example.taskdoro.ui.main.SectionsPagerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ActivityMainBinding binding;
    private int tabState = 0;

    //instance variables
    private EditText taskName;
    private TextView titleToDo;
    private Button addTaskButton;
    private RecyclerView myRecycler;
    private CheckBox checkBox;

    private AddTaskAdapter adapter;
    private DatabaseReference myRef;
    int newEntry = 4;

    //calendar views
    EditText selectDate,selectTime;
    private int mYear, mMonth, mDay, mHour, mMinute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this,
                getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);


        //instantiate views and buttons and firebase
        myRef = FirebaseDatabase.getInstance().getReference("tasks");
        myRecycler = (RecyclerView) findViewById(R.id.tasks_data);
        titleToDo = (TextView) findViewById(R.id.app_heading);
        addTaskButton = (Button) findViewById(R.id.add_button);
        taskName = (EditText) findViewById(R.id.task);
        checkBox = (CheckBox)findViewById(R.id.taskCheckBox);

        //initialiize date and time views
        selectDate=(EditText)findViewById(R.id.date);
        selectTime=(EditText)findViewById(R.id.time);
        selectDate.setOnClickListener(this);
        selectTime.setOnClickListener(this);

        //visibility between tabs
        tabState = 0;
        myRecycler.setVisibility(View.INVISIBLE);
        titleToDo.setVisibility(View.INVISIBLE);
        addTaskButton.setVisibility(View.INVISIBLE);
        taskName.setVisibility(View.INVISIBLE);
        selectDate.setVisibility(View.INVISIBLE);
        selectTime.setVisibility(View.INVISIBLE);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            { }

            @Override
            public void onPageSelected(int position) {

                tabState = position;
                if (tabState == 0){
                    myRecycler.setVisibility(View.INVISIBLE);
                    titleToDo.setVisibility(View.INVISIBLE);
                    addTaskButton.setVisibility(View.INVISIBLE);
                    taskName.setVisibility(View.INVISIBLE);
                    selectDate.setVisibility(View.INVISIBLE);
                    selectTime.setVisibility(View.INVISIBLE);
                }

                else if (tabState == 1){
                    myRecycler.setVisibility(View.VISIBLE);
                    titleToDo.setVisibility(View.VISIBLE);
                    addTaskButton.setVisibility(View.VISIBLE);
                    taskName.setVisibility(View.VISIBLE);
                    selectDate.setVisibility(View.VISIBLE);
                    selectTime.setVisibility(View.VISIBLE);
                }

                else if (tabState == 2){
                    myRecycler.setVisibility(View.INVISIBLE);
                    titleToDo.setVisibility(View.INVISIBLE);
                    addTaskButton.setVisibility(View.INVISIBLE);
                    taskName.setVisibility(View.INVISIBLE);
                    selectDate.setVisibility(View.INVISIBLE);
                    selectTime.setVisibility(View.INVISIBLE);
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //FIREBASE SECTION
        //show recycler view linearly
        myRecycler.setLayoutManager(new LinearLayoutManager(this));

        //fetch data with Recycler view
        FirebaseRecyclerOptions<TasksList> options = new FirebaseRecyclerOptions
                .Builder<TasksList>()
                .setQuery(myRef, TasksList.class)
                .build();
        //connecting object of adapter class to adapter class itself
        adapter = new AddTaskAdapter(options);

        //connect adapter class with the Recycler view
        myRecycler.setAdapter(adapter);

    }
    @Override protected void onStart()
    {
        super.onStart();
        adapter.startListening();
    }

    @Override protected void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }



    //click button, add task (change from employee class)
    public void addTask(View view) {

        String tName = taskName.getText().toString().trim();
        String dName = selectDate.getText().toString().trim();
        String tmName = selectTime.getText().toString().trim();

        closeKeyboard();

        if (!tName.trim().isEmpty() && !dName.trim().isEmpty()){

            String i = String.valueOf(newEntry);
            TasksList newTask = new TasksList(tName, dName, tmName);
            myRef.child(i).setValue(newTask);
            newEntry++;
        }

        else {
            Toast.makeText(this, "Must input a task, date, and time!",
                    Toast.LENGTH_SHORT).show();
        }
    }



    //delete task
    public void deleteTask(View view){

        myRef = FirebaseDatabase.getInstance().getReference();
        myRef.child("tasks").setValue("");

//        String i = String.valueOf(myRef.getKey());
//        String tkName = taskName.getText().toString().trim();
//        String dtName = selectDate.getText().toString().trim();
//        String tmName = selectTime.getText().toString().trim();
//
//        myRef.child(i).removeValue();
//        Toast.makeText(this, "Delete method invoked!", Toast.LENGTH_SHORT).show();

    }

    //close keyboard after adding task button
    private void closeKeyboard() {
        View view = this.getCurrentFocus();

        if (view != null){
            InputMethodManager imm = (InputMethodManager) getSystemService(Context
                    .INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        taskName.setText("");
        selectDate.setText("");
        selectTime.setText("");
    }

    //calendar edit text and date method
    @Override
    public void onClick(View view) {

        if (view == selectDate) {
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            selectDate.setText((monthOfYear + 1) + "-" + dayOfMonth + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (view == selectTime) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            String newAMPM;
                            if (hourOfDay > 12)
                                newAMPM = "PM";
                            else
                                newAMPM = "AM";

                            int newHour = hourOfDay % 12;

                            if (newHour == 0) {
                                newHour = 12;
                            }

                            String outputHour = String.format("%d", newHour);
                            String outputMinute = String.format("%02d", minute);
                            selectTime.setText(outputHour + ":" + outputMinute + " " + newAMPM);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    }



}