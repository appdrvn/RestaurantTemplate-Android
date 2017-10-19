## Introduction

Restaurant Directory App Template is based on a restaurant directory app. The app consists of list view, and also map view. List view display the restaurant list in grid format, while map view has the concept of showing the restaurants in an area based on the center of the map. Other than the list and map view, this template consists of details page as well, displaying the restaurant details, and also the menu that are available in this restaurant.

The main objective of this template is to assist startups to build their mobile application faster and easier. 

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

### File Structure
```
RestaurantTemplate
|---app
|      |---src
|      |      |---main
|      |      |      |---java
|      |      |      |      |---com/appdrvn/templateone
|      |      |      |      |      |---activities
|      |      |      |      |      |       |---AboutActivity.java
|      |      |      |      |      |       |---DetailsActivity.java
|      |      |      |      |      |       |---LandingActivity.java
|      |      |      |      |      |       |---MainActivity.java
|      |      |      |      |      |       |---MoreInfoActivity.java
|      |      |      |      |      |       |---ViewFullImageActivity.java
|      |      |      |      |      |---adapters
|      |      |      |      |      |       |---AboutReferenceAdapter.java
|      |      |      |      |      |       |---BannerPagerAdapter.java
|      |      |      |      |      |       |---MenuAdapter.java
|      |      |      |      |      |       |---RestaurantAdapter.java
|      |      |      |      |      |       |---ViewFullImageAdapter.java
|      |      |      |      |      |---delegates
|      |      |      |      |      |       |---Constants.java
|      |      |      |      |      |       |---DialogsDelegates.java
|      |      |      |      |      |       |---Util.java
|      |      |      |      |      |---dialogs
|      |      |      |      |      |       |---RestaurantDialog.java
|      |      |      |      |      |---fragments
|      |      |      |      |      |       |---MapFragment.java
|      |      |      |      |      |       |---RestaurantListFragment.java
|      |      |      |      |      |       |---RestaurantMenuFragment.java
|      |      |      |      |      |---models
|      |      |      |      |      |       |---AboutReference.java
|      |      |      |      |      |       |---Address.java
|      |      |      |      |      |       |---Menu.java
|      |      |      |      |      |       |---Restaurant.java
|      |      |      |      |      |---widgets
|      |      |      |      |      |       |---ViewPagerForPhotoView.java
|      |      |      |---res
|      |      |      |      |---color
|      |      |      |      |      |---main_page_toggle_text_color.xml
|      |      |      |      |---drawable
|      |      |      |      |      |---custom_seekbar.xml
|      |      |      |      |      |---custom_seekbar_thumb.xml
|      |      |      |      |      |---deep_rounded_corner_white_solid.xml
|      |      |      |      |      |---gradient_white.xml
|      |      |      |      |      |---gradient_white_dark.xml
|      |      |      |      |      |---main_page_toggle_bg.xml
|      |      |      |      |      |---oval_light_grey_50.xml
|      |      |      |      |      |---oval_white_70.xml
|      |      |      |      |      |---rating_yellow_selector.xml
|      |      |      |      |      |---rounded_corner_theme_solid.xml
|      |      |      |      |      |---rounded_corner_white_solid.xml
|      |      |      |      |      |---rounded_side_theme_solid.xml
|      |      |      |      |---drawable-v21
|      |      |      |      |      |---rounded_corner_white_solid.xml
|      |      |      |      |---drawable-xxxhdpi
|      |      |      |      |      |---ic_arrow_up_grey.png
|      |      |      |      |      |---ic_back_grey.png
|      |      |      |      |      |---ic_back_white.png
|      |      |      |      |      |---ic_email.png
|      |      |      |      |      |---ic_facebook.png
|      |      |      |      |      |---ic_info_grey.png
|      |      |      |      |      |---ic_location_black.png
|      |      |      |      |      |---ic_marker_green.png
|      |      |      |      |      |---ic_menu_grey.png
|      |      |      |      |      |---ic_more_white.png
|      |      |      |      |      |---ic_my_location_white.png
|      |      |      |      |      |---ic_rating_yellow.png
|      |      |      |      |      |---ic_rating_yellow_h.png
|      |      |      |      |      |---ic_rotated_div.png
|      |      |      |      |      |---ic_search_grey.png
|      |      |      |      |      |---ic_settings_white.png
|      |      |      |      |      |---ic_website.png
|      |      |      |      |      |---img_landing_page.jpg
|      |      |      |      |      |---img_logo_long.png
|      |      |      |      |      |---img_placeholder.png
|      |      |      |      |---layout
|      |      |      |      |      |---activity_about.xml
|      |      |      |      |      |---activity_details.xml
|      |      |      |      |      |---activity_landing.xml

|      |      |      |      |      |---activity_main.xml
|      |      |      |      |      |---activity_more_info.xml
|      |      |      |      |      |---activity_view_full.xml
|      |      |      |      |      |---dialog_restaurant.xml
|      |      |      |      |      |---fragment_list.xml
|      |      |      |      |      |---fragment_map.xml
|      |      |      |      |      |---fragment_menu.xml
|      |      |      |      |      |---item_about_references.xml
|      |      |      |      |      |---item_menu_left.xml
|      |      |      |      |      |---item_menu_right.xml
|      |      |      |      |      |---item_restaurant.xml
|      |      |      |      |      |---layout_search_bar.xml
|      |      |      |      |      |---layout_toolbar.xml
|      |      |      |      |      |---pager_banner.xml
|      |      |      |      |      |---pager_details.xml
|      |      |      |      |---mipmap-hdpi
|      |      |      |      |      |---ic_launcher.png
|      |      |      |      |      |---ic_launcher_round.png
|      |      |      |      |---mipmap-mdpi
|      |      |      |      |      |---ic_launcher.png
|      |      |      |      |      |---ic_launcher_round.png
|      |      |      |      |---mipmap-xhdpi
|      |      |      |      |      |---ic_launcher.png
|      |      |      |      |      |---ic_launcher_round.png
|      |      |      |      |---mipmap-xxhdpi
|      |      |      |      |      |---ic_launcher.png
|      |      |      |      |      |---ic_launcher_round.png
|      |      |      |      |---mipmap-xxxhdpi
|      |      |      |      |      |---ic_launcher.png
|      |      |      |      |      |---ic_launcher_round.png
|      |      |      |      |---values
|      |      |      |      |      |---arrays.xml
|      |      |      |      |      |---colors.xml
|      |      |      |      |      |---dimens.xml
|      |      |      |      |      |---strings.xml
|      |      |      |      |      |---styles.xml
|      |      |      |---AndroidManifest.xml
|      |      |---app.iml
|      |      |---build.gradle
|      |      |---proguard-rules.pro
|---build.gradle
|---gradle.properties
|---gradlew
|---gradlew.bat
|---local.properties
|---RestaurantTemplate.iml
|---settings.gradle
```
### Tools and Libraries used

Glide - https://github.com/bumptech/glide 
PhotoView - https://github.com/chrisbanes/PhotoView
Glide Transformations - https://github.com/wasabeef/glide-transformations 
Parceler - https://github.com/johncarl81/parceler 
Dexter - https://github.com/Karumi/Dexter 
Google-gson - https://github.com/google/gson
Paginate - https://github.com/MarkoMilos/Paginate
Icons8 - https://icons8.com/
Android Asset Studio - https://romannurik.github.io/AndroidAssetStudio/icons-generic.html#source.type=clipart&source.clipart=ac_unit&source.space.trim=1&source.space.pad=0&size=32&padding=8&color=rgba(0%2C%200%2C%200%2C%200.54)&name=ic_ac_unit


## Useful Links

Appdrvn official website - http://www.appdrvn.com/ 

Appdrvn official facebook page - https://www.facebook.com/Appdrvn/ 

Appdrvn email address - hello@appdrvn.com


