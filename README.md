## Introduction

Restaurant Directory App Template is based on a restaurant directory app. The app consists of list view, and also map view. List view display the restaurant list in grid format, while map view has the concept of showing the restaurants in an area based on the center of the map. Other than the list and map view, this template consists of details page as well, displaying the restaurant details, and also the menu that are available in this restaurant.

The main objective of this template is to assist startups to buy their mobile application faster and easier. 

### Tools and Libraries used

Name | Description | URL
---- | ------------- | --
Glide | Glide is a library which loads web images, and also provide caching. | https://github.com/bumptech/glide 
PhotoView | PhotoView is a library which display images, and allow user to zoom and pan | https://github.com/chrisbanes/PhotoView
Glide Transformations | Glide Transformations is a library which allow image transformation when loading the image with Glide. | https://github.com/wasabeef/glide-transformations 
Parceler | Parceler is a library which flatten the object and converts back to allow objects being passed via intent extra and bundle argument. | https://github.com/johncarl81/parceler 
Dexter | Dexter is a library checking use permission for Android OS v5.0 and above | https://github.com/Karumi/Dexter 
Google-gson | Google-gson is a library which can convert model objects into GSON format. | https://github.com/google/gson
Paginate |  Paginate is a library which handles pagination for recyclerview | https://github.com/MarkoMilos/Paginate
Icons8 | Icons8 is a website which provides images resources for icons. | https://icons8.com/
Android Asset Studio | Android Asset Studio is a website which provides image resources for icons, it also allow users to change the color of the icons, as well as controlling its size and padding. | https://romannurik.github.io/AndroidAssetStudio/icons-generic.html#source.type=clipart&source.clipart=ac_unit&source.space.trim=1&source.space.pad=0&size=32&padding=8&color=rgba(0%2C%200%2C%200%2C%200.54)&name=ic_ac_unit

## How to start
This template comes with dummy data in it, if the app need to connect to Web API, then modify loadData() methods to integrate Web API library.
All list page adapter is accepting ArrayList via setData() for setting data for the first page, add() method for appending data for the following pages.
Data binding from Web API result to Model object is not included, you have to implement your own data binding.

## What is inside
### Activities Classes

AboutActivity.java

This activity class is for about page.

DetailsActivity.java

This activity class is for restaurant details page, it is expecting Restaurant object, new instance can be created by calling its static method newInstance(). It displays the restaurant banner (refer to BannerPagerAdapter.java), and the Menu related to this restaurant (refer to RestaurantMenuFragment.java).

LandingActivity.java

This activity class is for the landing page of this app. It is the first activity the app will start. The landing activity oftenly use for showing tutorials or a brief description about the app.

MainActivity.java

This activity class is for the main page of the app. In this activity, it consists of 2 tabs, the list tab and the map tab. List tab will lead to Restaurant ListFragment.java, while map tab will lead to MapFragment.java.

MoreInfoActivity.java

This activity class is for restaurant extra information page, it is expecting Restaurant object, new instance can be created by calling its static method newInstance(). It displays restaurant’s name in full, descriptions in HTML format, and full address with button to open the restaurant’s coordinate in third party map app.

ViewFullImageActivity.java

This activity class displays images in full screen, it allows users zoom in and out and pan to view the image. It is expecting images URL or full path to the image file, both in String array format and the position of the image it should display first (optional). New instance can be created by calling its static method newInstance().

### Fragments Classes

MapFragment.java

This fragment class is for displaying the restaurant around the center of the map. It is currently being inflated and attached to MainActivity.java only. loadData() method should be modified to retrieve the restaurant data from Web API based on the given coordinate.

RestaurantListFragment.java

This fragment class is for displaying the restaurant list. It is currently being inflated and attached to MainActivity.java only. loadData() method should be modified to retrieve the restaurant data from Web API. The adapter used for the list is RestaurantAdapter.java. 

RestaurantMenuFragment.java

This fragment class is for displaying the menu list. New instance can be created by calling its static method newInstance(). It is currently being inflated and attached to DetailsActivity.java only. loadData() currently is loading the Restaurant object from arguments, which is passed as a parameter in the newInstance() method call. It can be modified to call Web API to retrieve data from web server if needed. It displays the menu list in the Restaurant object.

### Adapters Classes

BannerPagerAdapter.java

This adapter class is the adapter for banners in DetailsActivity.java. Its constructor is accepting images URL in String array format. The adapter display the image in center crop mode.

MenuAdapter.java

This adapter class is the adapter for menus in RestaurantMenuFragment.java. Use its setData() for setting data for the first page, add() method for appending data for the following pages.

RestaurantAdapter.java

This adapter class is the adapter for menus in RestaurantListFragment.java. Use its setData() for setting data for the first page, add() method for appending data for the following pages.

ViewFullImageAdapter

This adapter class is the adapter for banners in ViewFullImageActivity.java. Its constructor is accepting images URL in String array format.

### Models Classes

Address.java

This model class is for the address of the Restaurant object.

Menu.java

This model class is for the menu of the Restaurant object.

Restaurant.java

This model class is for the restaurant. It is the main object in this template.

## Useful Links

Appdrvn official website - http://www.appdrvn.com/ 

Appdrvn official facebook page - https://www.facebook.com/Appdrvn/ 

Appdrvn email address - hello@appdrvn.com


