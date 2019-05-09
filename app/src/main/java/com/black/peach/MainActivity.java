package com.black.peach;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener {

    private String[] mToolClassName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridView = (GridView) findViewById(R.id.grid_view);
        gridView.setOnItemClickListener(this);
        gridView.setAdapter(new ImageAdapter(this));

        mToolClassName = getResources().getStringArray(R.array.tool_class_name);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ComponentName componentName = new ComponentName(getPackageName(), mToolClassName[position]);
        Intent intent = new Intent();
        intent.setComponent(componentName);
        startActivity(intent);
    }

    private class ImageAdapter extends BaseAdapter {

        private Context mContext;
        private int[] mIconArray = null;
        private String[] mTitleArray = null;

        public ImageAdapter(Context context) {
            this.mContext = context;
            TypedArray typedArray = mContext.getResources().obtainTypedArray(R.array.tool_icon_array);
            mIconArray = new int[typedArray.length()];
            for (int i = 0; i < typedArray.length(); i++) {
                mIconArray[i] = typedArray.getResourceId(i, 0);
            }
            mTitleArray = mContext.getResources().getStringArray(R.array.tool_title_array);
        }


        @Override
        public int getCount() {
            return mIconArray.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;


            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_grid_item,
                        parent, false);
                viewHolder = new ViewHolder();
                viewHolder.mToolIcon = (ImageView) convertView.findViewById(R.id.tool_icon);
                viewHolder.mToolTitle = (TextView) convertView.findViewById(R.id.tool_title);
                viewHolder.mToolIcon.setImageDrawable(mContext.getDrawable(mIconArray[position]));
                viewHolder.mToolTitle.setText(mTitleArray[position]);
                convertView.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            return convertView;
        }

        class ViewHolder {
            ImageView mToolIcon;
            TextView mToolTitle;
        }
    }
}
