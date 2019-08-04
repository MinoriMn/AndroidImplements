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

import com.gmail.webtest.R;
import com.gmail.webtest.activity.RealmMemoActivity;

public class RealmMemoListFragment extends Fragment {
    private static final int REQUEST_NEW_MEMO = 100;
    private static final int REQUEST_MEMO_EDIT = 101;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_realm_memo_list, container, false);

        ((FloatingActionButton)view.findViewById(R.id.new_memo_fab)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(RealmMemoActivity.EXTRA_KEY_IS_NEW, true);
                startActivityForResult(intent, REQUEST_NEW_MEMO);
            }
        });

        return view;
    }
}
