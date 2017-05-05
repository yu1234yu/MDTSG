package com.example.a00687560.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.a00687560.mdtug001.R;
import com.example.a00687560.model.LibsCollection;

import java.util.List;

public class LibsCollectionAdapter extends BaseAdapter {

    private List<LibsCollection> libsCollections;
    private Context context;

    public LibsCollectionAdapter(Context context, List<LibsCollection> libsCollections) {
        this.libsCollections = libsCollections;
        this.context = context;
    }

    public int getCount() {
        return libsCollections.size();
    }

    public Object getItem(int i) {
        return libsCollections.get(i);
    }

    public long getItemId(int i) {
        return i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_view_collection_item, viewGroup, false);
        }
        TextView tvName = (TextView) view.findViewById(R.id.collection_name);
        tvName.setText(libsCollections.get(i).getLib_name());
        return view;
    }
}
