package com.smart.ludoclassic;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressWarnings("ALL")
// list adapter to display menu items in list with icons.
public class TransListViewAdapter extends BaseAdapter {
    private Activity context;
    int currentExistingPosition=0;
    private String[] options ;
    private int[] icons ;

    SharedPreferences loginEnable;

    public TransListViewAdapter(Activity context)
    {
        this.context = context;

    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final MyViewHolder mViewHolder;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.trans_item, parent, false);

            mViewHolder = new MyViewHolder(convertView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }


        // setting data to items.

        return convertView;
    }

    // view holder to hold the view it increase the performance of list view.
    private class MyViewHolder {


        public MyViewHolder(View item) {


        }
    }
}
