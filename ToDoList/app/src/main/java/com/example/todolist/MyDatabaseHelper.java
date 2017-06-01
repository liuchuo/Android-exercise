package com.example.todolist;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    public static final String CREATE_TASK_TABLE = "create table Task("
            + "id integer primary key autoincrement, "
            + "title text, "
            + "subtitle text)";

    private Context mContext;

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TASK_TABLE);
        Toast.makeText(mContext, "DB Create successed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Task");
        onCreate(db);
    }

    public List<Task> getTaskList() {
        SQLiteDatabase db = getReadableDatabase();

        // 查询数据
        Cursor cursor = db.query("Task", null, null, null, null, null, null);
        List<Task> taskList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                int taskId = cursor.getInt(cursor.getColumnIndex("id"));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String subTitle = cursor.getString(cursor.getColumnIndex("subtitle"));
                Task tempTask = new Task(false, taskId, title, subTitle);
                taskList.add(tempTask);
            } while(cursor.moveToNext());
        }
        cursor.close();
        Collections.reverse(taskList);
        return taskList;
    }
}
