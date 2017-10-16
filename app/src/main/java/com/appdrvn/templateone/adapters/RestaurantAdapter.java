package com.appdrvn.templateone.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.appdrvn.templateone.R;
import com.appdrvn.templateone.delegates.Util;
import com.appdrvn.templateone.models.Restaurant;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import jp.wasabeef.glide.transformations.CropTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by kelvynlaw on 10/09/2017.
 */

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ItemViewHolder> {

    ArrayList<Restaurant> mData = new ArrayList<>();
    Context context;
    MultiTransformation<Bitmap> multi;
    int thumbnailSize = 0;
    View.OnClickListener itemClickListener;
    int itemPaddingSize = 0;

    public RestaurantAdapter(Context context) {
        this.context = context;

        thumbnailSize = (Util.getScreenSize(context).x
                - (2 * context.getResources().getDimensionPixelSize(R.dimen.half_activity_margin))
                - (2 * context.getResources().getDimensionPixelSize(R.dimen.activity_margin))) / 2;
        itemPaddingSize = context.getResources().getDimensionPixelSize(R.dimen.half_activity_margin);
        multi = new MultiTransformation<>(
                new CropTransformation(thumbnailSize, thumbnailSize, CropTransformation.CropType.CENTER),
                new RoundedCornersTransformation(context.getResources().getDimensionPixelSize(R.dimen.half_activity_margin), 0, RoundedCornersTransformation.CornerType.ALL));
    }

    @Override
    public RestaurantAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RestaurantAdapter.ItemViewHolder vh;
        View v = inflater.inflate(R.layout.item_restaurant, parent, false);
        vh = new ItemViewHolder(v);
        vh.ivThumbnail.getLayoutParams().height = thumbnailSize;
        return vh;
    }

    @Override
    public void onBindViewHolder(RestaurantAdapter.ItemViewHolder holder, int position) {
        Restaurant item = mData.get(position);

        if (position % 2 == 0) {
            holder.rootView.setPadding(0, itemPaddingSize, itemPaddingSize, itemPaddingSize);
        } else {
            holder.rootView.setPadding(itemPaddingSize, itemPaddingSize, 0, itemPaddingSize);
        }

        Glide.with(context)
                .load(item.images[0])
                .apply(RequestOptions.bitmapTransform(multi).placeholder(R.drawable.img_placeholder))
                .into(holder.ivThumbnail);

        holder.tvName.setText(item.name);
        holder.tvAddress.setText(item.distance);
        holder.tvReviewCount.setText(context.getResources().getString(R.string.review_count_placeholder, item.reviewCount));
        holder.rbRating.setRating((float) item.rating);

        holder.rootView.setTag(item);
        holder.rootView.setOnClickListener(itemClickListener);
    }

    @Override
    public int getItemCount() {
        return mData.size();

    }

    public void setItemClickListener(View.OnClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setData(ArrayList<Restaurant> data) {
        this.mData = data;
        notifyDataSetChanged();
    }
    public void add(ArrayList<Restaurant> data) {
        int startIndex = this.mData.size();
        this.mData.addAll(data);
        notifyItemRangeInserted(startIndex, data.size());
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvAddress, tvReviewCount;
        View rootView;
        ImageView ivThumbnail;
        RatingBar rbRating;

        public ItemViewHolder(View convertView) {
            super(convertView);
            rootView = convertView;
            tvName =  convertView.findViewById(R.id.name);
            tvAddress =   convertView.findViewById(R.id.address);
            tvReviewCount =   convertView.findViewById(R.id.review_count);
            ivThumbnail =   convertView.findViewById(R.id.thumbnail);
            rbRating =   convertView.findViewById(R.id.rating);
        }
    }
}
