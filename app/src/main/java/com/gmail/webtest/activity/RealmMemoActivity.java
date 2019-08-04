package com.gmail.webtest.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gmail.webtest.R;
import com.gmail.webtest.realm.RealmMemoData;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.realm.Realm;

public class RealmMemoActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String EXTRA_KEY_IS_NEW = "EXTRA_KEY_IS_NEW";
    public static final String EXTRA_KEY_DATE_STRING = "EXTRA_KEY_DATE_STRING";
    private boolean isNew = false;

    private EditText titleEditText, contentEditText;

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm_memo);

        isNew = getIntent().getBooleanExtra(EXTRA_KEY_IS_NEW, false);

        realm = Realm.getDefaultInstance();

        titleEditText = findViewById(R.id.memo_title);
        contentEditText = findViewById(R.id.memo_content);

        if(isNew){
            ((Button)findViewById(R.id.memo_create_button)).setText("作成");
        }else{
            ((Button)findViewById(R.id.memo_create_button)).setText("更新");
            final RealmMemoData realmMemoData = realm.where(RealmMemoData.class).equalTo("dateString", getIntent().getStringExtra(EXTRA_KEY_DATE_STRING)).findFirst();
            titleEditText.setText(realmMemoData.title);
            contentEditText.setText(realmMemoData.content);
        }
        findViewById(R.id.memo_create_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final String title = titleEditText.getText().toString();
        final String content = contentEditText.getText().toString();

        Date date = new Date();
        final String dateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.JAPANESE).format(date);

        if(isNew){
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    RealmMemoData realmMemoData = realm.createObject(RealmMemoData.class);
                    realmMemoData.title = title;
                    realmMemoData.content = content;
                    realmMemoData.dateString = dateString;
                }
            });
        }else{
            final RealmMemoData realmMemoData = realm.where(RealmMemoData.class).equalTo("dateString", getIntent().getStringExtra(EXTRA_KEY_DATE_STRING)).findFirst();

            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realmMemoData.title = title;
                    realmMemoData.content = content;
                    realmMemoData.dateString = dateString;
                }
            });
        }

        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
