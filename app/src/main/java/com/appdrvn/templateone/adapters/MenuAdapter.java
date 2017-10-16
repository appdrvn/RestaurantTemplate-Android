package com.appdrvn.templateone.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.appdrvn.templateone.R;
import com.appdrvn.templateone.delegates.Util;
import com.appdrvn.templateone.models.Menu;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import jp.wasabeef.glide.transformations.CropTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by kelvynlaw on 10/09/2017.
 */

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ItemViewHolder> {

    int VIEW_TYPE_LEFT =1;
    int VIEW_TYPE_RIGHT =2;

    ArrayList<Menu> mData = new ArrayList<>();
    Context context;
    MultiTransformation<Bitmap> multi;
    int thumbnailSize = 0;
    View.OnClickListener itemClickListener;
    int itemPaddingSize = 0;

    public MenuAdapter(Context context) {
        this.context = context;

//        thumbnailSize = (Util.getScreenSize(mContext).x
//                - (2 * mContext.getResources().getDimensionPixelSize(R.dimen.half_activity_margin))
//                - (2 * mContext.getResources().getDimensionPixelSize(R.dimen.activity_margin))) / 2;
//        itemPaddingSize = mContext.getResources().getDimensionPixelSize(R.dimen.half_activity_margin);

        thumbnailSize = context.getResources().getDimensionPixelSize(R.dimen.menu_image_size);

        multi = new MultiTransformation<>(
                new CropTransformation(thumbnailSize, thumbnailSize, CropTransformation.CropType.CENTER),
                new RoundedCornersTransformation(context.getResources().getDimensionPixelSize(R.dimen.half_activity_margin), 0, RoundedCornersTransformation.CornerType.ALL));
    }

    @Override
    public MenuAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        MenuAdapter.ItemViewHolder vh;
        View v;
        if (viewType == VIEW_TYPE_LEFT) {
            v = inflater.inflate(R.layout.item_menu_left, parent, false);
        } else {
            v = inflater.inflate(R.layout.item_menu_right, parent, false);
        }
        vh = new ItemViewHolder(v);
        vh.ivThumbnail.getLayoutParams().height = thumbnailSize;
        return vh;
    }

    @Override
    public void onBindViewHolder(MenuAdapter.ItemViewHolder holder, int position) {
        Menu item = mData.get(position);

//        if (position % 2 == 0) {
//            holder.rootView.setPadding(0, itemPaddingSize, itemPaddingSize, itemPaddingSize);
//        } else {
//            holder.rootView.setPadding(itemPaddingSize, itemPaddingSize, 0, itemPaddingSize);
//        }

        Glide.with(context)
                .load(item.thumbnailUrl)
                .apply(RequestOptions.bitmapTransform(multi).placeholder(R.drawable.img_placeholder))
                .into(holder.ivThumbnail);

        holder.tvName.setText(item.title);
        holder.tvIngredient.setText(item.ingredient);
        holder.tvPrice.setText(item.price);
    }

    @Override
    public int getItemCount() {
        return mData.size();

    }

    @Override
    public int getItemViewType(int position) {
        if (position%2 == 0) {
            return VIEW_TYPE_LEFT;
        } else {
            return VIEW_TYPE_RIGHT;
        }
    }

    public void setItemClickListener(View.OnClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setData(ArrayList<Menu> data) {
        this.mData = data;
        notifyDataSetChanged();
    }
    public void add(ArrayList<Menu> data) {
        int startIndex = this.mData.size();
        this.mData.addAll(data);
        notifyItemRangeInserted(startIndex, data.size());
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvIngredient, tvPrice;
        View rootView;
        ImageView ivThumbnail;

        public ItemViewHolder(View convertView) {
            super(convertView);
            rootView = convertView;
            tvName = convertView.findViewById(R.id.name);
            tvIngredient = convertView.findViewById(R.id.ingredient);
            tvPrice = convertView.findViewById(R.id.price);
            ivThumbnail = convertView.findViewById(R.id.thumbnail);
        }
    }
}
