package com.appdrvn.templateone.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.appdrvn.templateone.R;
import com.appdrvn.templateone.adapters.MenuAdapter;
import com.appdrvn.templateone.models.Menu;
import com.appdrvn.templateone.models.Restaurant;
import com.paginate.Paginate;
import com.paginate.recycler.LoadingListItemSpanLookup;

import org.parceler.Parcels;

import java.util.ArrayList;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;

/**
 * Created by kelvynlaw on 21/09/2017.
 */

public class RestaurantMenuFragment extends Fragment {

    public static final String ARGUMENT_RESTAURANT = "ARGUMENT_RESTAURANT";

    RecyclerView mRecyclerView;

    MenuAdapter mAdapter;
    public int mCurrentPage = 0;
    public boolean isLoadingMore = false;
    public boolean isLastPage = false;
    Restaurant mRestaurant = new Restaurant();
    ImageView mIvBackToTop;

    public static Fragment newInstance(Restaurant restaurant) {

        Bundle args = new Bundle();

        args.putParcelable(ARGUMENT_RESTAURANT, Parcels.wrap(restaurant));
        RestaurantMenuFragment fragment = new RestaurantMenuFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_menu, null);

        mRestaurant = Parcels.unwrap(getArguments().getParcelable(ARGUMENT_RESTAURANT));


        mRecyclerView = rootView.findViewById(R.id.recycler_view);
        mIvBackToTop = rootView.findViewById(R.id.back_to_top);

        mAdapter = new MenuAdapter(getActivity());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);


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


        return rootView;
    }

    RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);

            if (newState == SCROLL_STATE_IDLE) {
                int firstIndex = ((LinearLayoutManager) mRecyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                if (firstIndex <= 1) {
                    mIvBackToTop.setVisibility(View.GONE);
                } else {
                    mIvBackToTop.setVisibility(View.VISIBLE);
                }
            }
        }
    };

    View.OnClickListener backToTopClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mRecyclerView.smoothScrollToPosition(0);

        }
    };

    public void loadData() {
        isLoadingMore = false;
        ArrayList<Menu> menuArrayList = Menu.createDummies(20);
        if (mCurrentPage == 1) {
            mAdapter.setData(menuArrayList);
        } else {
            mAdapter.add(menuArrayList);
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

}
