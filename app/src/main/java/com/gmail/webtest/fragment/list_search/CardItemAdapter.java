package com.gmail.webtest.fragment.list_search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gmail.webtest.R;

import java.util.List;

public class CardItemAdapter extends ArrayAdapter<CardItem> {
    List<CardItem> cardItems;

    public CardItemAdapter(Context context, int resource, List<CardItem> objects) {
        super(context, resource, objects);
        this.cardItems = objects;
    }

    @Override
    public int getCount() {
        return cardItems.size();
    }

    @Override
    public CardItem getItem(int position) {
        return cardItems.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_search_card_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        final CardItem item = getItem(position);

        if(item != null){
            viewHolder.content1TextView.setText(item.content1);
            viewHolder.content2TextView.setText(item.content2);
            viewHolder.content3TextView.setText(item.content3);
        }

        return convertView;
    }

    public static class ViewHolder{
        TextView content1TextView;
        TextView content2TextView;
        TextView content3TextView;

        public ViewHolder(View view){
            content1TextView = view.findViewById(R.id.content1);
            content2TextView = view.findViewById(R.id.content2);
            content3TextView = view.findViewById(R.id.content3);
        }
    }
}
