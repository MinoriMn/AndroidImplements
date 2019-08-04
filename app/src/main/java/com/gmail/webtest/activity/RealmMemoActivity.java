package com.gmail.webtest.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gmail.webtest.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class RealmMemoActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String EXTRA_KEY_IS_NEW = "EXTRA_KEY_IS_NEW";
    private boolean isNew = false;

    private EditText titleEditText, contentEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm_memo);

        isNew = getIntent().getBooleanExtra(EXTRA_KEY_IS_NEW, false);

        titleEditText = findViewById(R.id.memo_title);
        contentEditText = findViewById(R.id.memo_content);

        if(isNew){
            ((Button)findViewById(R.id.memo_create_button)).setText("作成");
        }else{
            ((Button)findViewById(R.id.memo_create_button)).setText("更新");
        }
        findViewById(R.id.memo_create_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String title = titleEditText.getText().toString();
        String content = contentEditText.getText().toString();

        Date date = new Date();
        String dateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.JAPANESE).format(date);
    }
}
