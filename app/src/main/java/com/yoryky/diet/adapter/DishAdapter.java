package com.yoryky.diet.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yoryky.diet.R;
import com.yoryky.diet.model.entity.Dish;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yoryky on 2017/8/31.
 */

public class DishAdapter extends RecyclerView.Adapter<DishAdapter.DishViewHolder> {
    private List<Dish> mDishes = new ArrayList<>();
    private MyItemClickListener mItemClickListener;
    private Context mContext;

    public DishAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public DishViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_dish, parent, false);
        DishViewHolder dishViewHolder = new DishViewHolder(itemView,mItemClickListener);
        return dishViewHolder;
    }

    @Override
    public void onBindViewHolder(DishViewHolder holder, int position) {
        holder.tvName.setText(mDishes.get(position).getName());
        holder.tvContent.setText(mDishes.get(position).getContent());
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return mDishes.size();
    }

    public Dish getDishData(int position){
        return mDishes.get(position);
    }


    public void getData(JSONObject data) {
        try {
            mDishes.clear();
            JSONArray meatArray = data.getJSONArray("meat");
            JSONArray vegArray = data.getJSONArray("veg");
            JSONArray soupArray = data.getJSONArray("soup");
            for (int i = 0; i < meatArray.length(); i++) {
                JSONObject json = meatArray.getJSONObject(i);
                addData(json);
            }
            for (int i = 0; i < vegArray.length(); i++) {
                JSONObject json = vegArray.getJSONObject(i);
                addData(json);
            }
            for (int i = 0; i < soupArray.length(); i++) {
                JSONObject json = soupArray.getJSONObject(i);
                addData(json);
            }
            notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void addData(JSONObject json) {
        Dish dish = new Dish();
        try {
            dish.setName(json.getString("name"));
            dish.setContent(json.getString("content"));
            mDishes.add(dish);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setOnItemClickListener(MyItemClickListener listener){
        this.mItemClickListener = listener;
    }

    public interface MyItemClickListener{
        public void onItemClick(View view,int position);
    }

    class DishViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvName;
        private TextView tvContent;
        private MyItemClickListener mItemListener;
        public DishViewHolder(View view,MyItemClickListener listener) {
            super(view);
            tvName = (TextView) view.findViewById(R.id.tv_dish_name);
            tvContent = (TextView) view.findViewById(R.id.tv_dish_content);
            this.mItemListener = listener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mItemListener != null){
                mItemListener.onItemClick(v,getPosition());
            }
        }
    }
}
