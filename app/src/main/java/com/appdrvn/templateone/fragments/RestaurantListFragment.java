package com.appdrvn.templateone.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.appdrvn.templateone.R;
import com.appdrvn.templateone.activities.AboutActivity;
import com.appdrvn.templateone.activities.DetailsActivity;
import com.appdrvn.templateone.adapters.RestaurantAdapter;
import com.appdrvn.templateone.models.Restaurant;
import com.paginate.Paginate;
import com.paginate.recycler.LoadingListItemSpanLookup;

import java.util.ArrayList;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;

/**
 * Created by kelvynlaw on 10/09/2017.
 */

public class RestaurantListFragment extends Fragment {
    RecyclerView mRecyclerView;

    RestaurantAdapter mAdapter;
    public int mCurrentPage = 0;
    public boolean isLoadingMore = false;
    public boolean isLastPage = false;
    ImageView mIvBackToTop;
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, null);


        mRecyclerView = rootView.findViewById(R.id.recycler_view);
        mIvBackToTop = rootView.findViewById(R.id.back_to_top);
        View vMenu = rootView.findViewById(R.id.action_bar_left_icon);
        mAdapter = new RestaurantAdapter(getActivity());

        mSwipeRefreshLayout = rootView.findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(refreshListener);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setItemClickListener(itemClickListener);


        Paginate.with(mRecyclerView, paginationCallback)
                .setLoadingTriggerThreshold(2)
                .addLoadingListItem(true)
                .setLoadingListItemCreator(null)
                .setLoadingListItemSpanSizeLookup(new LoadingListItemSpanLookup() {
                    @Override
                    public int getSpanSize() {
                        return 2;
                    }
                })
                .build();

        mRecyclerView.addOnScrollListener(onScrollListener);
        mIvBackToTop.setOnClickListener(backToTopClickListener);
        vMenu.setOnClickListener(menuClickListener);

        return rootView;
    }

    RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);

            if (newState == SCROLL_STATE_IDLE) {
                int firstIndex = ((GridLayoutManager) mRecyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                if (firstIndex <= 1) {
                    mIvBackToTop.setVisibility(View.GONE);
                } else {
                    mIvBackToTop.setVisibility(View.VISIBLE);
                }
            }
        }
    };

    View.OnClickListener menuClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(getActivity(), AboutActivity.class));
        }
    };
    View.OnClickListener backToTopClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mRecyclerView.smoothScrollToPosition(0);
        }
    };

    View.OnClickListener itemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Restaurant restaurant = (Restaurant) view.getTag();
            startActivity(DetailsActivity.newInstance(getActivity(), restaurant));
        }
    };

    public void loadData() {
        isLoadingMore = false;
        mSwipeRefreshLayout.setRefreshing(false);
        ArrayList<Restaurant> restaurantArrayList = Restaurant.createDummies(20);

        if (mCurrentPage == 1) {
            mAdapter.setData(restaurantArrayList);
        } else {
            mAdapter.add(restaurantArrayList);
        }
    }

    public Paginate.Callbacks paginationCallback = new Paginate.Callbacks() {
        @Override
        public void onLoadMore() {
            mCurrentPage++;
            isLoadingMore = true;
            loadData();
        }

        @Override
        public boolean isLoading() {
            // Indicate whether new page loading is in progress or not
            return isLoadingMore;
        }

        @Override
        public boolean hasLoadedAllItems() {
            // Indicate whether all data (pages) are loaded or not
            return isLastPage;
        }
    };

    public SwipeRefreshLayout.OnRefreshListener refreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            mCurrentPage = 1;
            isLastPage = false;
            loadData();
        }
    };
}
