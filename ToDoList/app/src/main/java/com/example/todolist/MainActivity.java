package com.example.todolist;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Paint;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Task> taskList = new ArrayList<>();
    private MyDatabaseHelper dbHelper;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 创建数据库
        dbHelper = new MyDatabaseHelper(this, "ToDoList.db", null, 2);

        recyclerView = (RecyclerView) findViewById(R.id.task_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        refreshRecyclerView();

        // 浮动添加按钮的跳转功能
        FloatingActionButton addButton = (FloatingActionButton) findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addIntent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(addIntent);
            }
        });
    }

    private void refreshRecyclerView() {
        taskList = dbHelper.getTaskList();
        recyclerView.setAdapter(new TaskAdapter(taskList));
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshRecyclerView();
    }

    @Override
    protected void onStop() {
        TaskAdapter taskAdapter = (TaskAdapter) recyclerView.getAdapter();
        taskList =  taskAdapter.getmTaskList();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        for (Task task : taskList) {
            if (task.isChecked()) {
                db.delete("Task", "id = ?", new String[]{String.valueOf(task.getTaskId())});
            }
        }
        super.onStop();
    }
}
