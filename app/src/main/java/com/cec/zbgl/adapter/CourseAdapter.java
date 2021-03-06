package com.cec.zbgl.adapter;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.cec.zbgl.R;
import com.cec.zbgl.holder.TypeDocmentHolder;
import com.cec.zbgl.holder.TypeImageHolder;
import com.cec.zbgl.holder.TypeItemHolder;
import com.cec.zbgl.holder.TypeVideoHolder;
import com.cec.zbgl.listener.ItemClickListener;
import com.cec.zbgl.model.DeviceCourse;
import com.cec.zbgl.model.DeviceInfo;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter <RecyclerView.ViewHolder>{

    private LayoutInflater mLayoutInflater;
    private List<DeviceCourse> mData;
    private Context mContext;

    private static final int VIEW_TYPE_TITLE= 101;
    private static final int VIEW_TYPE_ITEM = 100;
    int IS_TITLE_OR_NOT =1;
    int MESSAGE = 2;
    int ColumnNum = 6;
    private ItemClickListener mListener;
    private int mGridWidth;
    private int mGridWidth2;


    public CourseAdapter(Context context, List<DeviceCourse> mData) {
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
        this.mData = mData;

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            Point size = new Point();
            wm.getDefaultDisplay().getSize(size);
            width = size.x;
        }else{
            width = wm.getDefaultDisplay().getWidth();
        }
        mGridWidth = width / 4;
        mGridWidth2 = width / 2;

    }

    public CourseAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void addList(List<DeviceCourse> list) {
        mData.addAll(list);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //判断viewtype类型返回不同Viewholder
        switch (viewType) {
            case  DeviceCourse.TYPE_ONE :
                TypeImageHolder oneHolder = new TypeImageHolder(mLayoutInflater.inflate
                        (R.layout.course_image,parent,false), mContext,mGridWidth);

                oneHolder.itemView.setOnClickListener( v -> {
                    mListener.onItemClick(v, oneHolder.getLayoutPosition());
                });
                oneHolder.itemView.setOnLongClickListener(v -> {
                    mListener.onItemLongClick(v, oneHolder.getLayoutPosition());
                    return true;
                });
                return oneHolder;

            case   DeviceCourse.TYPE_TWO:

                TypeVideoHolder twoHolder = new TypeVideoHolder(mLayoutInflater.inflate
                        (R.layout.course_video,parent,false), mContext,mGridWidth2);

                twoHolder.itemView.setOnClickListener( v -> {
                    mListener.onItemClick(v, twoHolder.getLayoutPosition());
                });
                twoHolder.itemView.setOnLongClickListener(v -> {
                    mListener.onItemLongClick(v, twoHolder.getLayoutPosition());
                    return true;
                });
                return twoHolder;

            case   DeviceCourse.TYPE_THREE:
                TypeDocmentHolder threeHolder =  new TypeDocmentHolder(mLayoutInflater.inflate
                        (R.layout.course_document,parent,false), mContext);

                threeHolder.itemView.setOnClickListener( v -> {
                    mListener.onItemClick(v, threeHolder.getLayoutPosition());
                });
                threeHolder.itemView.setOnLongClickListener(v -> {
                    mListener.onItemLongClick(v, threeHolder.getLayoutPosition());
                    return true;
                });
                return threeHolder;
        }
        return new TypeItemHolder(mLayoutInflater.inflate(R.layout.course_item,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DeviceCourse course = mData.get(position);
        if(course.isTitle() == true){
            ((TypeItemHolder)holder).bindHolder(course.getMessage());
        }else {
            int viewType = getItemViewType(position);
            switch (viewType) {
                case   DeviceCourse.TYPE_ONE :
                    ((TypeImageHolder)holder).bindHolder(course);
                    break;
                case DeviceCourse.TYPE_TWO:
                    ((TypeVideoHolder)holder).bindHolder(course);
                    break;
                case DeviceCourse.TYPE_THREE:
                    ((TypeDocmentHolder)holder).bindHolder(course);
                    break;
                case DeviceCourse.TYPE_ITEM:
                    ((TypeItemHolder)holder).bindHolder(course.getMessage());
                    break;
            }
        }
    }


    //  删除数据
    public void removeData(int p) {
        mData.remove(p);
        //删除动画
        notifyItemRemoved(p);
        notifyDataSetChanged();
    }

    public void onDateChange(List<DeviceCourse> courses) {
        this.mData = courses;
        this.notifyDataSetChanged();
    }

    //判断RecyclerView的子项样式，返回一个int值表示
    @Override
    public int getItemViewType(int position) {
        DeviceCourse course = mData.get(position);
        if ("true".equals(course.isTitle())) {
            return VIEW_TYPE_TITLE;
        }else {

            return course.getCourseType();
        }
    }

    //判断是否是title，如果是，title占满一行的所有子项，则是ColumnNum个，如果是item，占满一个子项
  /*  @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        //如果是title就占据2个单元格(重点)
        GridLayoutManager manager = (GridLayoutManager) recyclerView.getLayoutManager();
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if("false".equals(mData.get(position).getIsTitle())){
                    return 1;
                }else {
                    return ColumnNum;
                }
            }
        });
    }*/


    @Override
    public int getItemCount() {
        return mData.size();
    }



    public void setOnListClickListener(ItemClickListener mListener) {
        this.mListener = mListener;
    }


}
