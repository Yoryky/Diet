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

public class DishAdapter extends RecyclerView.Adapter<DishAdapter.DishViewHolder> {
    private List<Dish> mDishes = new ArrayList<>();
    private MyItemClickListener mItemClickListener;
    private boolean showAddButton = false;
    private Context mContext;

    public DishAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public DishViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_dish, parent, false);
        DishViewHolder dishViewHolder = new DishViewHolder(itemView, mItemClickListener);
        return dishViewHolder;
    }

    @Override
    public void onBindViewHolder(DishViewHolder holder, int position) {
        holder.tvName.setText(mDishes.get(position).getName());
        holder.tvContent.setText(mDishes.get(position).getContent());
        if(!showAddButton){
            holder.btnAdd.setVisibility(View.GONE);
        }else{
            holder.btnAdd.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return mDishes.size();
    }

    public void getData(JSONObject jsonObject) {
        mDishes.clear();
        if(jsonObject.getIntValue("code") == 0){
            JSONArray dishArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < dishArray.size(); i++) {
                JSONObject json = dishArray.getJSONObject(i);
                addData(json);
            }
            notifyDataSetChanged();
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
}
