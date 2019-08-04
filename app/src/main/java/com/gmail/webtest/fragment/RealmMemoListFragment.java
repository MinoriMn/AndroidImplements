package com.gmail.webtest.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.gmail.webtest.R;
import com.gmail.webtest.activity.RealmMemoActivity;
import com.gmail.webtest.adapter.RealmMemoAdapter;
import com.gmail.webtest.realm.RealmMemoData;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmMemoListFragment extends Fragment {
    private static final int REQUEST_NEW_MEMO = 100;
    private static final int REQUEST_MEMO_EDIT = 101;

    private Realm realm;

    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_realm_memo_list, container, false);

        realm = Realm.getDefaultInstance();

        ((FloatingActionButton)view.findViewById(R.id.new_memo_fab)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), RealmMemoActivity.class);
                intent.putExtra(RealmMemoActivity.EXTRA_KEY_IS_NEW, true);
                startActivity(intent);
            }
        });

        listView = (ListView)view.findViewById(R.id.realm_memo_list);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RealmMemoData realmMemoData = (RealmMemoData)parent.getItemAtPosition(position);
                Intent intent = new Intent(getContext(), RealmMemoActivity.class);
                intent.putExtra(RealmMemoActivity.EXTRA_KEY_IS_NEW, false);
                intent.putExtra(RealmMemoActivity.EXTRA_KEY_DATE_STRING, realmMemoData.dateString);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        RealmResults<RealmMemoData> results = realm.where(RealmMemoData.class).findAll();
        List<RealmMemoData> items = realm.copyFromRealm(results);

        RealmMemoAdapter adapter = new RealmMemoAdapter(getContext(), R.layout.layout_realm_memo, items);
        listView.setAdapter(adapter);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
