package com.example.todolist;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        dbHelper = new MyDatabaseHelper(this, "ToDoList.db", null, 2);

        final EditText titleText = (EditText) findViewById(R.id.add_title);
        final EditText subTitleText = (EditText) findViewById(R.id.add_subTitle);

        // 取消按钮
        Button cancelBtn = (Button) findViewById(R.id.add_cancel_button);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 添加按钮
        Button addBtn = (Button) findViewById(R.id.add_enter_button);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                String titleString = titleText.getText().toString();
                String subtitleString = subTitleText.getText().toString();
                Log.d("123", titleString + " " + subtitleString);
                values.put("title", titleString);
                values.put("subtitle", subtitleString);
                db.insert("Task", null, values);
                finish();
            }
        });
    }


}
