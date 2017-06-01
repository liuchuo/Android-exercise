package com.example.todolist;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {
    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        //读取Intent中的数据
        Intent intent = getIntent();
        final int intentId = intent.getIntExtra("intentId", -1);
        String intentTitle = intent.getStringExtra("intentTitle");
        String intentSubTitle = intent.getStringExtra("intentSubTitle");
        final EditText titleText = (EditText) findViewById(R.id.edit_title);
        titleText.setText(intentTitle);
        final EditText subTitleText = (EditText) findViewById(R.id.edit_subTitle);
        subTitleText.setText(intentSubTitle);

        dbHelper = new MyDatabaseHelper(this, "ToDoList.db", null, 2);

        // 取消按钮
        Button cancelBtn = (Button) findViewById(R.id.edit_cancel_button);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 修改按钮
        Button editBtn = (Button) findViewById(R.id.edit_enter_button);
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();

                values.put("title", titleText.getText().toString());
                values.put("subtitle", subTitleText.getText().toString());
                Log.d("123", intentId + " " + titleText.getText().toString() + " " + subTitleText.getText().toString());
                db.update("Task", values, "id = ?", new String[]{String.valueOf(intentId)});
                Intent serviceIntent = new Intent(EditActivity.this, NotifyService.class);
                startService(serviceIntent);
                finish();
            }
        });
    }
}
