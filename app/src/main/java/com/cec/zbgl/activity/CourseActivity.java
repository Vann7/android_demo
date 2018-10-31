package com.cec.zbgl.activity;

import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cec.zbgl.R;
import com.cec.zbgl.adapter.CourseAdapter;
import com.cec.zbgl.listener.ItemClickListener;
import com.cec.zbgl.model.DeviceCourse;
import com.cec.zbgl.utils.FileUtils;
import com.cec.zbgl.utils.LogUtil;
import com.cec.zbgl.utils.OpenFileUtil;
import com.cec.zbgl.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView back_iv;
    private TextView head_tv;
    private RecyclerView mRecyclerView ;
    private ImageView add_iv;
    private CourseAdapter mAdapter;
    private GridLayoutManager mGridLayoutManager;

    private int IS_TITLE_OR_NOT =1;
    private int MESSAGE = 2;

    private List<DeviceCourse> mData =new ArrayList<>();
//    private List<Map<Integer, DeviceCourse>> mData =new ArrayList<>();
    private Map<Integer, DeviceCourse> map = new HashMap<Integer, DeviceCourse>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        head_tv = (TextView) findViewById(R.id.bar_back_tv);
        initData();
        initView();
        initEvent();
    }

    private void initView() {
        back_iv = (ImageView) findViewById(R.id.bar_back_iv);
        add_iv = (ImageView) findViewById(R.id.course_add_img);
        add_iv.bringToFront();
        mAdapter = new CourseAdapter(this,mData);
        mGridLayoutManager = new GridLayoutManager(this, 8);
        mGridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int type = mRecyclerView.getAdapter().getItemViewType(position);
                if (type == DeviceCourse.TYPE_ONE) {
                    return 2;
                }else if (type == DeviceCourse.TYPE_TWO){
                    return 4;
                } else {
                    return 8;
                }
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.course_rv);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(12));//item之间的间距

    }

    private void initData() {

        head_tv.setText(getIntent().getStringExtra("name"));

        for (int i=0; i< 15; i++) {
            String name,typyName;
            int type;
            if (i < 4) {
                type = 1;
                name = "图片教程";
                typyName = "图片";
            }else if (i < 9){
                type = 2;
                name = "视频教程";
                typyName = "视频";
            } else {
                type = 3;
                name = "文档教程";
                typyName = "文档";
            }
            map = new HashMap<Integer, DeviceCourse>();
            DeviceCourse c0 = new DeviceCourse(String.valueOf(i),name,type,
                    "教程类别为:"+typyName,"false","item "+(i+1));

            mData.add(c0);
        }
        //对分类标题进行初始化
        DeviceCourse c1 = new DeviceCourse("true","图片教程",101);
        mData.add(0,c1);

        DeviceCourse c2 = new DeviceCourse("true","视频教程",101);
        mData.add( 5,c2);

        DeviceCourse c3 = new DeviceCourse("true","文档教程",101);
        mData.add(11,c3);

    }

    private void initEvent() {

        add_iv.setOnClickListener(this);

        back_iv.setOnClickListener(this);

        mAdapter.setOnListClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                DeviceCourse c = mData.get(position);
                String fileName;
                Intent intent;
                switch (mData.get(position).getCourseType()) {
                    case 1 :
                        fileName = "/storage/emulated/0/DCIM/Camera/IMG_20181030_113211.jpg";
                        intent = OpenFileUtil.openFile(fileName);
                        startActivity(intent);
                        ToastUtils.showShort("展示图片");
                        break;
                    case 2 :

                        ToastUtils.showShort("播放视频");
                        fileName = "/storage/emulated/0/DCIM/Camera/VID_20181030_113134.mp4";
                        intent = OpenFileUtil.openFile(fileName);
                        startActivity(intent);
                        break;
                    case 3 :
                        fileName = "storage/emulated/0/Download/11111.txt";
                        intent = OpenFileUtil.openFile(fileName);
                        startActivity(intent);
                        ToastUtils.showShort("查看文档");
                        break;

                }

            }

            @Override
            public void onItemLongClick(View v, int position) {
                ToastUtils.showShort("onItemLongClick: "+mData.get(position).toString() + "-"+position);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bar_back_iv :
                finish();
                break;
            case R.id.course_add_img :
                ToastUtils.showShort("新增视频");
                break;
        }
    }


    //设置recyclerView中item的上下左右间距
    public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        public SpaceItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            //分别设置item的间距
            if (parent.getChildViewHolder(view).getItemViewType() == 0) {
                outRect.bottom = 0;
                outRect.top = space / 2;
            } else {
                outRect.bottom = space;
                outRect.top = space;
            }
            outRect.right = space;
            outRect.left = space;

        }
    }

}
