package com.example.daniel.tablerow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class MyAdapter extends BaseAdapter {

    Context context;
    protected List<Data> listData;
    LayoutInflater inflater;

    public MyAdapter(Context context, List<Data> listData) {
        this.listData = listData;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }
    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listData.get(position).getDrawable();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {

            holder = new ViewHolder();
            convertView = this.inflater.inflate(R.layout.activity_adapert,
                    parent, false);

            holder.txtTitle = (TextView) convertView
                    .findViewById(R.id.title);
            holder.txtDescription= (TextView) convertView
                    .findViewById(R.id.label);
            holder.img = (ImageView) convertView.findViewById(R.id.icon);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Data data = listData.get(position);
        holder.txtTitle.setText((data.getTitle()));
        holder.txtDescription.setText(data.getDescription());


        holder.img.setImageResource(data.getDrawable());

        return convertView;
    }

    private class ViewHolder {
        TextView txtTitle;
        TextView txtDescription;
        ImageView img;
    }
}
