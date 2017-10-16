package com.appdrvn.templateone.dialogs;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.appdrvn.templateone.R;
import com.appdrvn.templateone.activities.DetailsActivity;
import com.appdrvn.templateone.models.Restaurant;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.request.RequestOptions;

import org.parceler.Parcels;

import java.util.Locale;

import jp.wasabeef.glide.transformations.CropTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by kelvynlaw on 22/09/2017.
 */

public class RestaurantDialog extends DialogFragment {

    static final String ARGUMENT_RESTAURANT = "ARGUMENT_RESTAURANT";

    ImageView mIvBanner;
    TextView mTvName;
    TextView mTvAddress;
    TextView mTvReviewCount;
    TextView mTvDistance;
    RatingBar mRbRating;
    View mVViewMore;
    boolean mFirstRun = true;

    Restaurant mRestaurant = new Restaurant();


    public static RestaurantDialog newInstance(Restaurant restaurant) {
        RestaurantDialog f = new RestaurantDialog();

        Bundle args = new Bundle();
        args.putParcelable(ARGUMENT_RESTAURANT, Parcels.wrap(restaurant));
        f.setArguments(args);

        return f;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, getTheme());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_restaurant, container, false);

        mRestaurant = Parcels.unwrap(getArguments().getParcelable(ARGUMENT_RESTAURANT));

        mIvBanner = rootView.findViewById(R.id.banner);
        mTvName = rootView.findViewById(R.id.name);
        mTvAddress = rootView.findViewById(R.id.address);
        mTvReviewCount = rootView.findViewById(R.id.review_count);
        mTvDistance = rootView.findViewById(R.id.distance);
        mRbRating = rootView.findViewById(R.id.rating);
        rootView.findViewById(R.id.view_more).setOnClickListener(viewMoreClickListener);

        rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (mFirstRun) {
                    mIvBanner.getLayoutParams().height = (int) ((double) mIvBanner.getWidth() / 16d * 7d);

                    MultiTransformation<Bitmap> multi = new MultiTransformation<>(
                            new CropTransformation(mIvBanner.getWidth(), mIvBanner.getLayoutParams().height, CropTransformation.CropType.CENTER),
                            new RoundedCornersTransformation(getResources().getDimensionPixelSize(R.dimen.deep_corner_radius), 0, RoundedCornersTransformation.CornerType.TOP));

                    Glide.with(getActivity())
                            .load(mRestaurant.images[0])
                            .apply(RequestOptions.bitmapTransform(multi).placeholder(R.drawable.img_placeholder))
                            .into(mIvBanner);
                    mFirstRun = false;
                }

            }
        });

        setCancelable(true);
        getDialog().setCanceledOnTouchOutside(true);
        mTvAddress.setOnClickListener(addressClickListener);
        fillInData();

        return rootView;
    }
    View.OnClickListener addressClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String output = String.format(Locale.ENGLISH, "geo:0,0?q=") + Uri.encode(String.format("%s@%s,%s", "", mRestaurant.latLng.latitude + "", mRestaurant.latLng.longitude + ""), "UTF-8");
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(output));
            startActivity(intent);
        }
    };

    private View.OnClickListener viewMoreClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(DetailsActivity.newInstance(getActivity(), mRestaurant));
        }
    };

    private void fillInData() {
        mTvDistance.setText(mRestaurant.distance);
        mTvName.setText(mRestaurant.name);
        SpannableString content = new SpannableString(mRestaurant.address.getFullAddress());
        content.setSpan(new UnderlineSpan(), 0, mRestaurant.address.getFullAddress().length(), 0);
        mTvAddress.setText(content);
        mTvReviewCount.setText(getResources().getString(R.string.review_count_placeholder, mRestaurant.reviewCount));
        mRbRating.setRating((float) mRestaurant.rating);
    }

    @Override
    public void onResume() {
        getDialog().getWindow().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        super.onResume();

    }
}
