package com.gmail.webtest.activity;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.gmail.webtest.R;

public class CheckWork2019Activity extends AppCompatActivity {
    TextView countTextView;
    int count = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkwork_2019);

        setTitle("なんでもカウントアプリ");

        countTextView = findViewById(R.id.count_text);
    }

    //プラスボタン
    public void plus(View v){
        count += 1;
        textKaemasu();
    }

    //マイナスボタン
    public void minus(View v){
        count -= 1;
        textKaemasu();
    }

    //クリアボタン
    public void clear(View v){
        count = 0;
        textKaemasu();
    }

    //テキストを変更する
    private void textKaemasu(){
            countTextView.setText(String.valueOf(count));
            if(count >= 0){
                countTextView.setTextColor(Color.rgb(0, 0, 0));
            }else{
                countTextView.setTextColor(Color.rgb(0, 255, 0));
            }
    }
}
