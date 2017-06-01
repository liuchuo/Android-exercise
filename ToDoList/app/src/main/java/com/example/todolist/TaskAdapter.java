package com.example.todolist;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by chenxin on 5/31/17.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private List<Task> mTaskList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View taskView;
        CheckBox isCheckedBox;
        TextView TaskTitle;
        TextView TaskSubTitle;

        public ViewHolder(View view) {
            super(view);
            taskView = view;
            isCheckedBox = (CheckBox) view.findViewById(R.id.task_item_checkBox);
            TaskTitle = (TextView) view.findViewById(R.id.task_item_title);
            TaskSubTitle = (TextView) view.findViewById(R.id.task_item_subTitle);
        }
    }

    public TaskAdapter(List<Task> taskList) {
        mTaskList = taskList;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.taskView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Task task = mTaskList.get(position);
                Intent editIntent = new Intent(view.getContext(), EditActivity.class);
                int idData = task.getTaskId();
                String titleData = task.getTitle();
                String subTitleData = task.getSubTitle();
                editIntent.putExtra("intentId", idData);
                editIntent.putExtra("intentTitle", titleData);
                editIntent.putExtra("intentSubTitle", subTitleData);
                view.getContext().startActivity(editIntent);
            }
        });

        holder.isCheckedBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    holder.TaskTitle.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                    holder.TaskSubTitle.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                } else {
                    holder.TaskTitle.getPaint().setFlags(0);
                    holder.TaskSubTitle.getPaint().setFlags(0);
                }
                mTaskList.get(holder.getAdapterPosition()).setChecked(isChecked);
                notifyItemChanged(holder.getAdapterPosition());
            }

        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Task task = mTaskList.get(position);
        holder.TaskTitle.setText(task.getTitle());
        holder.TaskSubTitle.setText(task.getSubTitle());
    }

    @Override
    public int getItemCount() {
        return mTaskList.size();
    }

    public List<Task> getmTaskList() {
        return mTaskList;
    }
}
