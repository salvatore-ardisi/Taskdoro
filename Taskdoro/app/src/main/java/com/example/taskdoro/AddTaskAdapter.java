package com.example.taskdoro;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class AddTaskAdapter  extends FirebaseRecyclerAdapter<TasksList,
        AddTaskAdapter.tasksViewholder> {



    public AddTaskAdapter(@NonNull FirebaseRecyclerOptions<TasksList> options){
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull AddTaskAdapter.tasksViewholder holder, int position, @NonNull
            TasksList model) {


        holder.taskname.setText(model.getTaskName());

        holder.datename.setText(model.getDateName());

        holder.timename.setText(model.getTimeName());

    }

    public AddTaskAdapter.tasksViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tasks_list, parent,
                false);
        return new AddTaskAdapter.tasksViewholder(view);
    }

    class tasksViewholder extends RecyclerView.ViewHolder {
        TextView taskname, datename, timename;

        public tasksViewholder(@NonNull View itemView)
        {
            super(itemView);
            taskname = itemView.findViewById(R.id.task_name_text_view);
            datename = itemView.findViewById(R.id.date_text_view);
            timename = itemView.findViewById(R.id.time_text_view);
        }
    }




}
