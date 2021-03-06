package com.cec.zbgl.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cec.zbgl.model.DeviceCourse;

import java.util.List;

public class ImageAdapter extends PagerAdapter {

    private List<View> datas;

    public ImageAdapter(List<View> mData) {
        datas = mData;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = datas.get(position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(datas.get(position));
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public void onDateChange(List<View> list) {
        datas.clear();
        datas.addAll(list);
        notifyDataSetChanged();
    }

}
