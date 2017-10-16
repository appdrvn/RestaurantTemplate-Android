package com.appdrvn.templateone.models;

import com.google.android.gms.maps.model.LatLng;

import org.parceler.Parcel;

import java.util.ArrayList;

/**
 * Created by kelvynlaw on 10/09/2017.
 */

@Parcel
public class Restaurant {

    public String name = "";
    public String distance = "";
    public String htmlContent = "";
    public int rating = 0;
    public int reviewCount = 0;
    public String[] images = new String[0];
    public Address address = new Address();
    public ArrayList<Menu> menus = new ArrayList<>();
    public LatLng latLng = new LatLng(0, 0);

    public Restaurant() {
    }

    /**
     * Dummy Data
     */
    public static final String[] NAMES = new String[]{"Davie's", "Central Cafe", "Grand Dorset", "Waterlily Cafe", "Market Cafe"};
    public static final String[] DISTANCES = new String[]{"400m", "2km", "2.5km", "3km", "5km"};
    public static final int[] RATINGS = new int[]{3, 4, 5, 1, 2};
    public static final int[] REVIEW_COUNTS = new int[]{344, 489, 565, 100, 238};
    public static final String[][] IMAGES = new String[][]{{"http://img05.deviantart.net/b9e4/i/2012/092/d/b/french_cafe_by_adsarta-d4ur6f6.jpg", "https://i1.wp.com/globalgraphica.com/wp-content/uploads/2015/01/IMG_7997.jpg", "https://i.pinimg.com/736x/5d/b7/db/5db7dbeee56a5ced710314be83edc832--cafe-interior-designcommercial-interior-design.jpg"},
            {"https://i1.wp.com/globalgraphica.com/wp-content/uploads/2015/01/IMG_7997.jpg", "https://i.pinimg.com/736x/5d/b7/db/5db7dbeee56a5ced710314be83edc832--cafe-interior-designcommercial-interior-design.jpg", "http://www.ozarlington.com/wp-content/uploads/2017/04/bar-buffet.jpg", "http://noras.com/test/wp-content/themes/nora/images/home/rn-events-full.jpg"},
            {"https://i.pinimg.com/736x/5d/b7/db/5db7dbeee56a5ced710314be83edc832--cafe-interior-designcommercial-interior-design.jpg", "http://www.ozarlington.com/wp-content/uploads/2017/04/bar-buffet.jpg", "http://noras.com/test/wp-content/themes/nora/images/home/rn-events-full.jpg", "http://mangostudios.com/wp-content/uploads/2014/03/george-toronto-wedding-8.jpg"},
            {"http://www.ozarlington.com/wp-content/uploads/2017/04/bar-buffet.jpg", "http://noras.com/test/wp-content/themes/nora/images/home/rn-events-full.jpg"}};
    public static final double[] LATS = new double[]{3.044770d, 3.046742d, 3.042725d, 3.052519d, 3.040311d};
    public static final double[] LONGS = new double[]{101.622716d, 101.622458d, 101.617800d, 101.621185d, 101.619044d};


    public static ArrayList<Restaurant> createDummies(int count) {
        ArrayList<Restaurant> output = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Restaurant restaurant = new Restaurant();
            restaurant.name = NAMES[i % NAMES.length];
            restaurant.distance = DISTANCES[i % DISTANCES.length];
            restaurant.rating = RATINGS[i % RATINGS.length];
            restaurant.reviewCount = REVIEW_COUNTS[i % REVIEW_COUNTS.length];
            restaurant.images = IMAGES[i % IMAGES.length];
            restaurant.address = Address.createDummy(i);
            restaurant.menus = Menu.createDummies(25);
            restaurant.latLng = new LatLng(LATS[i % LATS.length], LONGS[i % LONGS.length]);
            restaurant.htmlContent = "<h2>Lorem ipsum dolor sit amet</h2>" +
                    "<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse congue pellentesque lectus vitae cursus. Etiam sodales sapien justo, vel congue eros congue aliquet. Aenean cursus euismod auctor. Cras quis auctor risus. Fusce et turpis nec nibh rhoncus tempor. Aliquam vel est vitae nisi fermentum rhoncus. Integer quis urna vel nibh tristique vehicula sed vel diam. Maecenas at justo eget libero ultrices pharetra nec ultricies risus. Fusce scelerisque, felis porttitor dapibus aliquet, arcu sapien bibendum magna, in malesuada nisl metus quis lorem. Aliquam auctor blandit ipsum, vitae dignissim odio iaculis vel. Mauris varius sit amet neque maximus aliquet. Donec aliquet, lectus id semper convallis, dui quam convallis ex, sed sollicitudin augue dui ut massa. Donec nec nisl dui. Donec eget porttitor erat.</p>" +
                    "<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse congue pellentesque lectus vitae cursus. Etiam sodales sapien justo, vel congue eros congue aliquet. Aenean cursus euismod auctor. Cras quis auctor risus. Fusce et turpis nec nibh rhoncus tempor. Aliquam vel est vitae nisi fermentum rhoncus. Integer quis urna vel nibh tristique vehicula sed vel diam. Maecenas at justo eget libero ultrices pharetra nec ultricies risus. Fusce scelerisque, felis porttitor dapibus aliquet, arcu sapien bibendum magna, in malesuada nisl metus quis lorem. Aliquam auctor blandit ipsum, vitae dignissim odio iaculis vel. Mauris varius sit amet neque maximus aliquet. Donec aliquet, lectus id semper convallis, dui quam convallis ex, sed sollicitudin augue dui ut massa. Donec nec nisl dui. Donec eget porttitor erat.</p>" +
                    "<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse congue pellentesque lectus vitae cursus. Etiam sodales sapien justo, vel congue eros congue aliquet. Aenean cursus euismod auctor. Cras quis auctor risus. Fusce et turpis nec nibh rhoncus tempor. Aliquam vel est vitae nisi fermentum rhoncus. Integer quis urna vel nibh tristique vehicula sed vel diam. Maecenas at justo eget libero ultrices pharetra nec ultricies risus. Fusce scelerisque, felis porttitor dapibus aliquet, arcu sapien bibendum magna, in malesuada nisl metus quis lorem. Aliquam auctor blandit ipsum, vitae dignissim odio iaculis vel. Mauris varius sit amet neque maximus aliquet. Donec aliquet, lectus id semper convallis, dui quam convallis ex, sed sollicitudin augue dui ut massa. Donec nec nisl dui. Donec eget porttitor erat.</p>" +
                    "<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse congue pellentesque lectus vitae cursus. Etiam sodales sapien justo, vel congue eros congue aliquet. Aenean cursus euismod auctor. Cras quis auctor risus. Fusce et turpis nec nibh rhoncus tempor. Aliquam vel est vitae nisi fermentum rhoncus. Integer quis urna vel nibh tristique vehicula sed vel diam. Maecenas at justo eget libero ultrices pharetra nec ultricies risus. Fusce scelerisque, felis porttitor dapibus aliquet, arcu sapien bibendum magna, in malesuada nisl metus quis lorem. Aliquam auctor blandit ipsum, vitae dignissim odio iaculis vel. Mauris varius sit amet neque maximus aliquet. Donec aliquet, lectus id semper convallis, dui quam convallis ex, sed sollicitudin augue dui ut massa. Donec nec nisl dui. Donec eget porttitor erat.</p>" +
                    "<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse congue pellentesque lectus vitae cursus. Etiam sodales sapien justo, vel congue eros congue aliquet. Aenean cursus euismod auctor. Cras quis auctor risus. Fusce et turpis nec nibh rhoncus tempor. Aliquam vel est vitae nisi fermentum rhoncus. Integer quis urna vel nibh tristique vehicula sed vel diam. Maecenas at justo eget libero ultrices pharetra nec ultricies risus. Fusce scelerisque, felis porttitor dapibus aliquet, arcu sapien bibendum magna, in malesuada nisl metus quis lorem. Aliquam auctor blandit ipsum, vitae dignissim odio iaculis vel. Mauris varius sit amet neque maximus aliquet. Donec aliquet, lectus id semper convallis, dui quam convallis ex, sed sollicitudin augue dui ut massa. Donec nec nisl dui. Donec eget porttitor erat.</p>" +
                    "<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse congue pellentesque lectus vitae cursus. Etiam sodales sapien justo, vel congue eros congue aliquet. Aenean cursus euismod auctor. Cras quis auctor risus. Fusce et turpis nec nibh rhoncus tempor. Aliquam vel est vitae nisi fermentum rhoncus. Integer quis urna vel nibh tristique vehicula sed vel diam. Maecenas at justo eget libero ultrices pharetra nec ultricies risus. Fusce scelerisque, felis porttitor dapibus aliquet, arcu sapien bibendum magna, in malesuada nisl metus quis lorem. Aliquam auctor blandit ipsum, vitae dignissim odio iaculis vel. Mauris varius sit amet neque maximus aliquet. Donec aliquet, lectus id semper convallis, dui quam convallis ex, sed sollicitudin augue dui ut massa. Donec nec nisl dui. Donec eget porttitor erat.</p>" +
                    "<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse congue pellentesque lectus vitae cursus. Etiam sodales sapien justo, vel congue eros congue aliquet. Aenean cursus euismod auctor. Cras quis auctor risus. Fusce et turpis nec nibh rhoncus tempor. Aliquam vel est vitae nisi fermentum rhoncus. Integer quis urna vel nibh tristique vehicula sed vel diam. Maecenas at justo eget libero ultrices pharetra nec ultricies risus. Fusce scelerisque, felis porttitor dapibus aliquet, arcu sapien bibendum magna, in malesuada nisl metus quis lorem. Aliquam auctor blandit ipsum, vitae dignissim odio iaculis vel. Mauris varius sit amet neque maximus aliquet. Donec aliquet, lectus id semper convallis, dui quam convallis ex, sed sollicitudin augue dui ut massa. Donec nec nisl dui. Donec eget porttitor erat.</p>" +
                    "<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse congue pellentesque lectus vitae cursus. Etiam sodales sapien justo, vel congue eros congue aliquet. Aenean cursus euismod auctor. Cras quis auctor risus. Fusce et turpis nec nibh rhoncus tempor. Aliquam vel est vitae nisi fermentum rhoncus. Integer quis urna vel nibh tristique vehicula sed vel diam. Maecenas at justo eget libero ultrices pharetra nec ultricies risus. Fusce scelerisque, felis porttitor dapibus aliquet, arcu sapien bibendum magna, in malesuada nisl metus quis lorem. Aliquam auctor blandit ipsum, vitae dignissim odio iaculis vel. Mauris varius sit amet neque maximus aliquet. Donec aliquet, lectus id semper convallis, dui quam convallis ex, sed sollicitudin augue dui ut massa. Donec nec nisl dui. Donec eget porttitor erat.</p>" +
                    "<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse congue pellentesque lectus vitae cursus. Etiam sodales sapien justo, vel congue eros congue aliquet. Aenean cursus euismod auctor. Cras quis auctor risus. Fusce et turpis nec nibh rhoncus tempor. Aliquam vel est vitae nisi fermentum rhoncus. Integer quis urna vel nibh tristique vehicula sed vel diam. Maecenas at justo eget libero ultrices pharetra nec ultricies risus. Fusce scelerisque, felis porttitor dapibus aliquet, arcu sapien bibendum magna, in malesuada nisl metus quis lorem. Aliquam auctor blandit ipsum, vitae dignissim odio iaculis vel. Mauris varius sit amet neque maximus aliquet. Donec aliquet, lectus id semper convallis, dui quam convallis ex, sed sollicitudin augue dui ut massa. Donec nec nisl dui. Donec eget porttitor erat.</p>" +
                    "<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse congue pellentesque lectus vitae cursus. Etiam sodales sapien justo, vel congue eros congue aliquet. Aenean cursus euismod auctor. Cras quis auctor risus. Fusce et turpis nec nibh rhoncus tempor. Aliquam vel est vitae nisi fermentum rhoncus. Integer quis urna vel nibh tristique vehicula sed vel diam. Maecenas at justo eget libero ultrices pharetra nec ultricies risus. Fusce scelerisque, felis porttitor dapibus aliquet, arcu sapien bibendum magna, in malesuada nisl metus quis lorem. Aliquam auctor blandit ipsum, vitae dignissim odio iaculis vel. Mauris varius sit amet neque maximus aliquet. Donec aliquet, lectus id semper convallis, dui quam convallis ex, sed sollicitudin augue dui ut massa. Donec nec nisl dui. Donec eget porttitor erat.</p>" +
                    "<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse congue pellentesque lectus vitae cursus. Etiam sodales sapien justo, vel congue eros congue aliquet. Aenean cursus euismod auctor. Cras quis auctor risus. Fusce et turpis nec nibh rhoncus tempor. Aliquam vel est vitae nisi fermentum rhoncus. Integer quis urna vel nibh tristique vehicula sed vel diam. Maecenas at justo eget libero ultrices pharetra nec ultricies risus. Fusce scelerisque, felis porttitor dapibus aliquet, arcu sapien bibendum magna, in malesuada nisl metus quis lorem. Aliquam auctor blandit ipsum, vitae dignissim odio iaculis vel. Mauris varius sit amet neque maximus aliquet. Donec aliquet, lectus id semper convallis, dui quam convallis ex, sed sollicitudin augue dui ut massa. Donec nec nisl dui. Donec eget porttitor erat.</p>" +
                    "<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse congue pellentesque lectus vitae cursus. Etiam sodales sapien justo, vel congue eros congue aliquet. Aenean cursus euismod auctor. Cras quis auctor risus. Fusce et turpis nec nibh rhoncus tempor. Aliquam vel est vitae nisi fermentum rhoncus. Integer quis urna vel nibh tristique vehicula sed vel diam. Maecenas at justo eget libero ultrices pharetra nec ultricies risus. Fusce scelerisque, felis porttitor dapibus aliquet, arcu sapien bibendum magna, in malesuada nisl metus quis lorem. Aliquam auctor blandit ipsum, vitae dignissim odio iaculis vel. Mauris varius sit amet neque maximus aliquet. Donec aliquet, lectus id semper convallis, dui quam convallis ex, sed sollicitudin augue dui ut massa. Donec nec nisl dui. Donec eget porttitor erat.</p>";
            output.add(restaurant);
        }
        return output;
    }


}
