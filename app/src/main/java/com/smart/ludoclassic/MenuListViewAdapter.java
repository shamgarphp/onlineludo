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
public class MenuListViewAdapter extends BaseAdapter {
    private Activity context;
    int currentExistingPosition=0;
    private String[] options ;
    private int[] icons ;

    SharedPreferences loginEnable;

    public MenuListViewAdapter(Activity context, int currentPosition)
    {
        this.context = context;
        this.currentExistingPosition = currentPosition;
        loginEnable = context.getSharedPreferences("loginEnable", Context.MODE_PRIVATE);

            options = new String[]{"Home","Profile","Change Password ","Promotion","Withdraw","Transaction History","Contact Us","Privacy Policy","Terms & Conditions","Logout"};
            icons = new int[]{R.mipmap.appicon,R.mipmap.appicon,R.mipmap.appicon,R.mipmap.appicon,R.mipmap.appicon,R.mipmap.appicon,R.mipmap.appicon,R.mipmap.appicon,R.mipmap.appicon,R.mipmap.appicon};
         }

    @Override
    public int getCount() {
        return options.length;
    }

    @Override
    public Object getItem(int position) {
        return options[position];
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
            convertView = inflater.inflate(R.layout.sidemenu_list_item, parent, false);

            mViewHolder = new MyViewHolder(convertView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }


        // setting data to items.
        mViewHolder.listName.setText(options[position]);
        mViewHolder.listIcon.setImageDrawable(context.getDrawable(icons[position]));


        if (Utilites.getSharedInstance().menuSelectedPosition == position)
            convertView.setBackgroundResource(R.drawable.side_menu_selected_background);
        else
            convertView.setBackgroundColor(context.getResources().getColor(android.R.color.transparent));


        return convertView;
    }

    // view holder to hold the view it increase the performance of list view.
    private class MyViewHolder {

        ImageView listIcon;
        TextView listName;
        View mWhiteCircle;
        public MyViewHolder(View item) {

            listIcon     = (ImageView)item.findViewById(R.id.list_icon);
            listName     = (TextView) item.findViewById(R.id.list_item_name);
            mWhiteCircle = item.findViewById(R.id.existing_indicator);

        }
    }
}
