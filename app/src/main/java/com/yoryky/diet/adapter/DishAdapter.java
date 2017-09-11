package com.yoryky.diet.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yoryky.diet.R;
import com.yoryky.diet.model.entity.Dish;
import com.yoryky.diet.util.HelpUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yoryky on 2017/8/31.
 */

public class DishAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Dish> mDishes = new ArrayList<>();
    private MyItemClickListener mItemClickListener;
    private boolean showAddButton = false;
    private boolean splitPage = false;//是否分页
    private boolean noMoreData = false;//是否有更多数据
    private boolean isRefresh = false;
    private int loadMoreStatus = 0;//0 提示上拉加载 1 正在上拉加载 2 无更多数据 3 隐藏上拉提示
    private static final int TYPE_ITEM = 0;//普通item view
    private static final int TYPE_FOOTER=1;//底部foot view
    public static final int PULL_UP_LOAD_MORE = 0;
    public static final int LOADING_MORE = 1;
    public static final int LOAD_END = 2;
    public static final int LOAD_GONE = 3;//不可见
    private int pageIndex = 1;
    private int pageSize = 10;
    private Context mContext;

    public DishAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_ITEM){
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_dish, parent, false);
            DishViewHolder dishViewHolder = new DishViewHolder(itemView, mItemClickListener);
            return dishViewHolder;
        }else if(viewType == TYPE_FOOTER){
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.footer_recycler_load_more,parent,false);
            FootViewHolder footViewHolder = new FootViewHolder(itemView);
            return footViewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof DishViewHolder){
            ((DishViewHolder)holder).tvName.setText(mDishes.get(position).getName());
            ((DishViewHolder)holder).tvContent.setText(mDishes.get(position).getContent());
            if(!showAddButton){
                ((DishViewHolder)holder).btnAdd.setVisibility(View.GONE);
            }else{
                ((DishViewHolder)holder).btnAdd.setVisibility(View.VISIBLE);
            }
            holder.itemView.setTag(position);//没添加这句时ServerDishOnScrollListener没触发
        }else if(holder instanceof FootViewHolder){
            FootViewHolder footViewHolder = (FootViewHolder)holder;
            switch (loadMoreStatus){
                case PULL_UP_LOAD_MORE:
                    footViewHolder.tvFootMind.setText("上拉加载更多...");
                    break;
                case LOADING_MORE:
                    footViewHolder.tvFootMind.setText("正在加载更多数据...");
                    break;
                case LOAD_END:
                    footViewHolder.tvFootMind.setText("已无更多数据");
                    break;
                case LOAD_GONE:
                    footViewHolder.tvFootMind.setVisibility(View.GONE);
                    break;
            }
        }
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return mDishes.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if(position + 1 == getItemCount()){
            return TYPE_FOOTER;
        }else{
            return TYPE_ITEM;
        }
    }

    public void getData(JSONObject jsonObject) {
        if(!splitPage){
            mDishes.clear();
            changeMoreStatus(DishAdapter.LOAD_GONE);
        }
        if(isRefresh){
            mDishes.clear();
            isRefresh = false;
            pageIndex = 1;
            noMoreData = false;
        }
        if(jsonObject.getIntValue("code") == 0){
            JSONArray dishArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < dishArray.size(); i++) {
                JSONObject json = dishArray.getJSONObject(i);
                addData(json);
            }
            if(splitPage){
                if(pageIndex == 1 && dishArray.size() < pageSize) {
                    changeMoreStatus(DishAdapter.LOAD_GONE);
                    noMoreData = true;
                }else if(dishArray.size() < pageSize){
                    changeMoreStatus(DishAdapter.LOAD_END);
                    noMoreData = true;
                }else{
                    ++pageIndex;
                    changeMoreStatus(DishAdapter.PULL_UP_LOAD_MORE);
                }
            }
        }else{
            HelpUtil.showToast(mContext,jsonObject.getString("message"));
        }
    }

    private void addData(JSONObject json) {
        Dish dish = new Dish();
        dish.setId(json.getString("id"));
        dish.setName(json.getString("name"));
        dish.setContent(json.getString("content"));
        mDishes.add(dish);
    }

    public void setOnItemClickListener(MyItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    public void setShowAddButton(boolean visible){
        showAddButton = visible;
    }

    public void setSplitPage(boolean split){splitPage = split;}

    public boolean getNoMoreData(){
        return noMoreData;
    }

    public int getPageIndex(){
        return pageIndex;
    }

    public int getPageSize(){
        return pageSize;
    }

    public void setRefresh(boolean refresh){
        isRefresh = refresh;
        pageIndex = 1;
    }

    public boolean isRefresh() {
        return isRefresh;
    }

    public void changeMoreStatus(int status){
        loadMoreStatus = status;
        notifyDataSetChanged();
    }

    public interface MyItemClickListener {
        public void onItemClick(View view, List<Dish> dishes,int position);
        public void onAddClick(View view,List<Dish> dishes,int position);
    }

    class DishViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvName;
        private TextView tvContent;
        private TextView btnAdd;
        private MyItemClickListener mItemListener;

        public DishViewHolder(View view, MyItemClickListener listener) {
            super(view);
            tvName = (TextView) view.findViewById(R.id.tv_dish_name);
            tvContent = (TextView) view.findViewById(R.id.tv_dish_content);
            btnAdd = (Button)view.findViewById(R.id.btn_add);
            this.mItemListener = listener;
            view.setOnClickListener(this);
            btnAdd.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    mItemListener.onAddClick(v,mDishes,getPosition());
                }
            });
        }

        @Override
        public void onClick(View v) {
            if (mItemListener != null) {
                mItemListener.onItemClick(v,mDishes, getPosition());
            }
        }
    }

    class FootViewHolder extends RecyclerView.ViewHolder{
        private TextView tvFootMind;
        public FootViewHolder(View view){
            super(view);
            tvFootMind = (TextView)view.findViewById(R.id.tv_footer_mind);
        }
    }
}
