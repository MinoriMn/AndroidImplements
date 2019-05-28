package com.gmail.webtest.fragment.list_search;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.SearchView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.gmail.webtest.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SearchViewFragment extends Fragment implements SearchView.OnQueryTextListener {
    List<CardItem> cards;
    CardItemAdapter adapter;
    ListView listView;
    SearchView searchView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_search_view, container, false);

        listView = view.findViewById(R.id.listview);
        searchView = view.findViewById(R.id.searchView);

        cards = new ArrayList<>();
        Random random = new Random();
        String[] testData = {"apple", "banana", "orange", "grape", "pine"};
        for (int i = 0; i < 20; i++) {
            cards.add(new CardItem("title_" + i, "message_" + i, testData[random.nextInt(testData.length)] + "_" + + i));
        }
        adapter = new CardItemAdapter(getActivity(), R.layout.list_search_card_item, cards);
        listView.setAdapter(adapter);
        //text filter
        listView.setTextFilterEnabled(true);

        // SearchViewの初期表示状態を設定
        searchView.setIconifiedByDefault(false);
        // SearchViewにOnQueryTextListenerを設定
        searchView.setOnQueryTextListener(this);
        // SearchViewのSubmitボタンを使用不可にする
        searchView.setSubmitButtonEnabled(false);
        // SearchViewに何も入力していない時のテキストを設定
        searchView.setQueryHint("検索文字を入力して下さい。");

        return view;
    }

    // SearchViewにテキストを入力する度に呼ばれるイベント
    @Override
    public boolean onQueryTextChange(String s) {
        if(TextUtils.isEmpty(s)){
            listView.clearTextFilter();
        }else{
            //listViewのsetFilterを使うと検索時にポップアップで入力内容が表示される
//            listView.setFilterText(s.toString());
            //代わりに，直接Filterオブジェクトを操作する
            Filter filter = adapter.getFilter();
            filter.filter(s);
        }
        return true;
    }

    // SearchViewのSubmitButtonを押下した時に呼ばれるイベント
    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }
}
