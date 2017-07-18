package com.magicwindow.deeplink.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.magicwindow.deeplink.R;
import com.magicwindow.deeplink.config.Constant;
import com.magicwindow.deeplink.domain.ListInfo;

import java.util.List;

import cn.magicwindow.AdManager;
import cn.magicwindow.FeedAdView;

/**
 * Author：Aaron
 * Time：07/07/2017:2:44 PM
 */
public class FeedAdListViewAdapter extends BaseAdapter {


    public static final int TYPE_AD = 0;
    public static final int TYPE_DATA = 1;
    private static final int TYPE_MAX_COUNT = TYPE_DATA + 1;
    private Activity mContext;



    private List<ListInfo> mListViewItems;

    public FeedAdListViewAdapter(Activity context) {
        mContext = context;

    }


    @Override
    public int getCount() {

        return mListViewItems != null ? mListViewItems.size() : 0;
    }

    @Override
    public int getItemViewType(int i) {
        if (i % 5 == 0 && i > 0) {
            return TYPE_AD;
        } else {
            return TYPE_DATA;
        }

    }

    @Override
    public int getViewTypeCount() {
        return TYPE_MAX_COUNT;
    }

    @Override
    public Object getItem(int i) {
        return mListViewItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void addItem(ListInfo info) {

        mListViewItems.add(info);
        notifyDataSetChanged();
    }


    public void setData(List<ListInfo> list) {
        mListViewItems = list;
        notifyDataSetInvalidated();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {


        final ViewHolder holder;
        int type = getItemViewType(position);

        if (type == TYPE_AD) {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.item_category_ad, viewGroup, false);
            } else {
                ((RelativeLayout) convertView).removeAllViews();
            }

            FeedAdView adView = AdManager.getFeedAdView(Constant.MW_AD, position);

            if (adView != null) {
//                adView.setTitleTextColor(Color.GREEN);
//                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Util.dip2px(mContext,200));
                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                ((RelativeLayout) convertView).addView(adView, params);
            }

        } else {
            if (convertView != null) {
                holder = (ViewHolder) convertView.getTag();
            } else {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.item_category_list, viewGroup, false);
                holder = new ViewHolder();
                holder.title = (TextView) convertView.findViewById(R.id.title);
                holder.desc = (TextView) convertView.findViewById(R.id.desc);
                convertView.setTag(holder);
            }

            final ListInfo info = (ListInfo) mListViewItems.get(position);

            holder.title.setText((String) info.title);
            holder.desc.setText((String) info.desc);


        }


        return convertView;
    }


    private class ViewHolder {
        TextView title;
        TextView desc;
    }
}
