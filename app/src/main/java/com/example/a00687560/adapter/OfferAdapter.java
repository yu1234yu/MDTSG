package com.example.a00687560.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.a00687560.mdtug001.R;
import com.example.a00687560.model.LibsCollection;
import com.example.a00687560.model.LibsInfo;
import com.example.a00687560.model.LibsOffer;

import java.util.List;

public class OfferAdapter extends BaseAdapter {
    private List<LibsOffer> libsOfferList;
    private List<LibsInfo> libsInfoList;
   // private List<LibsCollection> libsCollectionList;
    private Context context;

    public OfferAdapter(Context context,List<LibsInfo> libsInfoList) {
        super();
        //this.libsOfferList = libsOfferList;
        this.libsInfoList=libsInfoList;
        //this.libsCollectionList=libsCollectionList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return libsInfoList.size();
    }

    @Override
    public Object getItem(int i) {
        return libsInfoList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_view_offer_me_item, viewGroup, false);
        }
        TextView book_name = (TextView) view.findViewById(R.id.lib_name);
        TextView book_author = (TextView) view.findViewById(R.id.lib_author);
        TextView book_ISBN = (TextView) view.findViewById(R.id.lib_ISBN);

        book_name.setText(libsInfoList.get(i).getLib_name());
        book_author.setText(libsInfoList.get(i).getType_id());
        book_ISBN.setText("678164189");


        return view;
    }
}