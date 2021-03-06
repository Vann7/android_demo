package com.cec.zbgl.holder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.ImageFormat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cec.zbgl.R;
import com.cec.zbgl.model.DeviceInfo;
import com.cec.zbgl.utils.FileUtils;
import com.squareup.picasso.Picasso;

import java.io.File;

import me.nereo.multi_image_selector.MultiImageSelectorFragment;

public class DeviceViewHolder extends RecyclerView.ViewHolder {

    private Context mContext;
    private LinearLayout item_ll;
    public ImageView imageView;
    private TextView name;
    private TextView type;
    private TextView desc;
    private final int mGridWidth = 80;

    public DeviceViewHolder(View itemView, Context mContext) {
        super(itemView);
        this.mContext = mContext;
        imageView = (ImageView) itemView.findViewById(R.id.item01_img);
        name = (TextView) itemView.findViewById(R.id.item01_tv_name);
        type = (TextView) itemView.findViewById(R.id.item01_tv_type);
        desc = (TextView) itemView.findViewById(R.id.item01_tv_desc);
        item_ll = (LinearLayout) itemView.findViewById(R.id.item01_ll);

    }

    public void bindHolder(DeviceInfo device) {
        imageView.setVisibility(View.VISIBLE);
        name.setText(device.getName());
        type.setText(device.getType());
        desc.setText(device.getDescription());
        if (imageView.getTag().equals(device.getId())) {
            if (device.getImage() != null) {
                File imageFile = FileUtils.byte2File(device.getImage(), mContext);
                // 显示图片
                Picasso.with(mContext)
                        .load(imageFile)
//                        .placeholder(me.nereo.multi_image_selector.R.drawable.mis_default_error)
                        .tag("deviceTag")
                        .resize(mGridWidth, mGridWidth)
                        .centerCrop()
                        .into(imageView);
            }
        }

    }


}
