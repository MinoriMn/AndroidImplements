package com.gmail.webtest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gmail.webtest.R;
import com.gmail.webtest.realm.RealmMemoData;

import java.util.List;

public class RealmMemoAdapter extends ArrayAdapter<RealmMemoData> {
    private LayoutInflater layoutInflater;

    public RealmMemoAdapter(Context context, int resource, List<RealmMemoData> objects) {
        super(context, resource, objects);
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RealmMemoData realmMemoData = getItem(position);

        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.layout_realm_memo, null);
        }

        ((TextView)convertView.findViewById(R.id.title_textview)).setText(realmMemoData.title);
        ((TextView)convertView.findViewById(R.id.content_textview)).setText(realmMemoData.content);
        ((TextView)convertView.findViewById(R.id.date_textview)).setText(realmMemoData.dateString);

        return convertView;
    }
}
