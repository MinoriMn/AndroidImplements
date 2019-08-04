package com.gmail.webtest.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.gmail.webtest.R;

public class RealmMemoActivity extends AppCompatActivity {
    public static final String EXTRA_KEY_IS_NEW = "EXTRA_KEY_IS_NEW";
    private boolean isNew = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm_memo);

        isNew = getIntent().getBooleanExtra(EXTRA_KEY_IS_NEW, false);

        if(isNew){
            ((Button)findViewById(R.id.memo_create_button)).setText("作成");
        }else{
            ((Button)findViewById(R.id.memo_create_button)).setText("更新");
        }
    }
}
