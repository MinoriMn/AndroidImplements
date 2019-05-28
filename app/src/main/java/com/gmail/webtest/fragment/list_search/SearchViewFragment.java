package com.gmail.webtest.fragment.list_search;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.gmail.webtest.R;

import java.util.ArrayList;
import java.util.List;

public class SearchViewFragment extends Fragment {
    List<CardItem> cards;
    CardItemAdapter adapter;
    ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_search_view, container, false);

        listView = view.findViewById(R.id.listview);

        cards = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            cards.add(new CardItem("title_" + i, "message_" + i, "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa_" + i));
        }
        adapter = new CardItemAdapter(getActivity(), R.layout.list_search_card_item, cards);
        listView.setAdapter(adapter);

        return view;
    }
}
