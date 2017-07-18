package com.magicwindow.deeplink.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.magicwindow.deeplink.R;
import com.magicwindow.deeplink.config.Constant;
import com.magicwindow.deeplink.domain.ListInfo;

import java.util.List;

import cn.magicwindow.AdManager;
import cn.magicwindow.FeedAdView;

import static com.magicwindow.deeplink.activity.FeedAdRecyclerViewActivity.INTERVAL;


/**
 * Author：Aaron
 * Time：07/07/2017:2:48 PM
 */
public class FeedAdRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private static final int TYPE_AD = 0;
    private static final int TYPE_DATA = 1;
    private Context context;
    private List<Object> mRecyclerViewItems;

    public FeedAdRecycleViewAdapter(Context context, List<Object> recyclerViewItems) {
        this.context = context;
        this.mRecyclerViewItems = recyclerViewItems;
    }



    @Override
    public int getItemViewType(int position) {
        if (position % INTERVAL == 0) {
            return TYPE_AD;
        } else {
            return TYPE_DATA;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_AD:
                View ad = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_ad, parent, false);

                return new FeedAdViewHolder(ad);

            case TYPE_DATA:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_list, parent, false);

                return new ItemViewHolder(view);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case TYPE_AD:

                RelativeLayout layout = (RelativeLayout) holder.itemView;
                layout.removeAllViews();

                FeedAdView adView = AdManager.getFeedAdView(Constant.MW_AD,position);
                if(adView!=null){
                    if (adView.getParent() != null) {
                        ((ViewGroup) adView.getParent()).removeView(adView);
                    }
                    ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    layout.addView(adView,params);
                }
                break;
            case TYPE_DATA:
                ItemViewHolder viewHolder = (ItemViewHolder) holder;
                final ListInfo info = (ListInfo) mRecyclerViewItems.get(position);
                viewHolder.title.setText(info.title);
                viewHolder.desc.setText(info.desc);
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mRecyclerViewItems.size();
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView desc;
        ItemViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            desc = (TextView) itemView.findViewById(R.id.desc);
        }
    }

    private class FeedAdViewHolder extends RecyclerView.ViewHolder {
        public FeedAdViewHolder(View itemView) {
            super(itemView);
        }
    }

}
