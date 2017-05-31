package com.example.todolist;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 * Created by chenxin on 5/31/17.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private List<Task> mTaskList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View taskView;
        CheckBox isChecked;
        TextView TaskTitle;
        TextView TaskSubTitle;

        public ViewHolder(View view) {
            super(view);
            taskView = view;
            isChecked = (CheckBox) view.findViewById(R.id.task_item_checkBox);
            TaskTitle = (TextView) view.findViewById(R.id.task_item_title);
            TaskSubTitle = (TextView) view.findViewById(R.id.task_item_subTitle);
        }
    }

    public TaskAdapter(List<Task> taskList) {
        mTaskList = taskList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.taskView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Intent editIntent = new Intent(view.getContext(), EditActivity.class);
                
                view.getContext().startActivity(editIntent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Task task = mTaskList.get(position);
        holder.isChecked.setChecked(task.isChecked());
        holder.TaskTitle.setText(task.getTitle());
        holder.TaskSubTitle.setText(task.getSubTitle());
    }

    @Override
    public int getItemCount() {
        return mTaskList.size();
    }

}
