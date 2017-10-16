package com.appdrvn.templateone.models;

import java.util.ArrayList;

/**
 * Created by kelvynlaw on 30/09/2017.
 */

public class AboutReference {

    public String url = "";
    public String title = "";
    public String subtitle = "";

    static String[] URLs = new String[]{
            "https://github.com/bumptech/glide",
            "https://github.com/chrisbanes/PhotoView",
            "https://github.com/wasabeef/glide-transformations",
            "https://github.com/johncarl81/parceler",
            "https://github.com/Karumi/Dexter",
            "https://github.com/google/gson",
            "https://github.com/MarkoMilos/Paginate",
            "https://icons8.com/",
            "https://romannurik.github.io/AndroidAssetStudio/icons-generic.html#source.type=clipart&source.clipart=ac_unit&source.space.trim=1&source.space.pad=0&size=32&padding=8&color=rgba(0%2C%200%2C%200%2C%200.54)&name=ic_ac_unit"};
    static String[] TITLEs = new String[]{
            "Glide",
            "PhotoView",
            "Glide Transformations",
            "Parceler",
            "Dexter",
            "Google-gson",
            "Paginate",
            "Icons",
            "Icons"
    };
    static String[] SUBTITLEs = new String[]{
            "by Sam Judd",
            "by Chris Banes",
            "by Wasabeef",
            "by John Ericksen",
            "by Karumi",
            "by Google Inc.",
            "by Marko Milos",
            "by Icons8",
            "by Android Asset Studio"
    };

    public static ArrayList<AboutReference> createData() {
        ArrayList<AboutReference> output = new ArrayList<>();
        for (int i = 0; i < URLs.length; i++) {
            AboutReference aboutReference = new AboutReference();
            aboutReference.url = URLs[i];
            aboutReference.title = TITLEs[i];
            aboutReference.subtitle = SUBTITLEs[i];
            output.add(aboutReference);
        }
        return output;
    }
}
