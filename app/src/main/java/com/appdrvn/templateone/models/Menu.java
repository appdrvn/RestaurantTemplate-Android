package com.appdrvn.templateone.models;

import org.parceler.Parcel;

import java.util.ArrayList;

/**
 * Created by kelvynlaw on 20/09/2017.
 */

@Parcel
public class Menu {
    public String thumbnailUrl = "";
    public String price = "";
    public String title = "";
    public String ingredient = "";

    public Menu() {
    }

    /**
     * Dummy Data
     */
    public static final String[] THUMBNAIL_URLS = new String[]{"https://sparkpeo.hs.llnwd.net/e1/resize/630m620/e2/guid/WW-Feta-Chicken-Burgers/b83e3206-f74a-459d-bfc6-c5718dc9bcd0.jpg",
            "http://www.simplyrecipes.com/wp-content/uploads/2009/09/caesar-salad-horiz-800.jpg",
            "https://assets.epicurious.com/photos/57c5b9ddcf9e9ad43de2d96a/2:1/w_1260%2Ch_630/fresh-wild-mushroom-soup.jpg",
            "http://www.theship.com.sg/images/food/W-757.jpg",
            "http://i3.bristolpost.co.uk/incoming/article852.ece/ALTERNATES/s1200/12796314-large.jpg",
            "https://www.fareway.com/media/products/MS_ribeye_steak_crop_79C0590A6DBC9.jpg?dimensions=500x250",
            "https://images.unsplash.com/photo-1426869981800-95ebf51ce900?dpr=1&auto=compress,format&fit=crop&w=640&h=&q=80&cs=tinysrgb&crop=",
            "https://scontent-kut2-1.xx.fbcdn.net/v/t31.0-8/21950890_1351211624976908_1380420854398388496_o.jpg?oh=0216a0576acef7f78510b9d94671a6a0&oe=5A46B2CF"};
    public static final String[] TITLES = new String[]{"Chicken Burger", "Ceasar Salad", "Mushroom Soup", "Chicken Maryland", "Fish N Chips", "Ribeye Steak"};
    public static final String[] PRICES = new String[]{"RM19.90", "RM12.50", "RM9.90", "RM15.50", "RM12.90", "RM21.60"};
    public static final String[] INGREDIENTS = new String[]{"Grilled chicken, cheese, tomato, basil leaf, onion", "Basil leaf, potatoes, cabbage", "Mushrooms, onions, cream, evaporated milk", "Deep fried chicken, breadcrumbs, bacon, potatoes", "Deep fried dory fillet, potatoes", "Ribeye beef steak, Cherry tomatoes"};


    public static ArrayList<Menu> createDummies(int count) {
        ArrayList<Menu> output = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Menu menu = new Menu();
            menu.thumbnailUrl = THUMBNAIL_URLS[i % THUMBNAIL_URLS.length];
            menu.title = TITLES[i % TITLES.length];
            menu.price = PRICES[i % PRICES.length];
            menu.ingredient = INGREDIENTS[i % INGREDIENTS.length];
            output.add(menu);
        }
        return output;
    }
}
