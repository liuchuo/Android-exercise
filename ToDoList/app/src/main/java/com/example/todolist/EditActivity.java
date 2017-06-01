package com.example.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        //读取Intent中的数据
        Intent intent = getIntent();
        String intentTitle = intent.getStringExtra("intentTitle");
        String intentSubTitle = intent.getStringExtra("intentSubTitle");
        EditText titleText = (EditText) findViewById(R.id.edit_title);
        titleText.setText(intentTitle);
        EditText subTitleText = (EditText) findViewById(R.id.edit_subTitle);
        subTitleText.setText(intentSubTitle);


        // 取消按钮
        Button cancelBtn = (Button) findViewById(R.id.edit_cancel_button);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 修改按钮
    }
}
