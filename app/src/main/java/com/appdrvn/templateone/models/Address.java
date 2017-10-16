package com.appdrvn.templateone.models;

import org.parceler.Parcel;

/**
 * Created by kelvynlaw on 20/09/2017.
 */

@Parcel
public class Address {
    public String line1 = "";
    public String line2 = "";
    public String city = "";
    public String state = "";
    public String country = "";
    public String postCode = "";

    public Address() {
    }

    /**
     * Dummy Data
     */
    public static final String[] LINE1S = new String[]{"12, Jalan Ss2/2", "102, Block B", "8, Jalan Temperung 19", "102, Block A", "12-2, Block B"};
    public static final String[] LINE2S = new String[]{"", "560, Jalan Bukit Bintang", "", "Menara KLMN", "100, Jalan Persatuan"};
    public static final String[] CITIES = new String[]{"Petaling Jaya", "Cheras", "Pandan Indah", "Ampang", "Kepong"};
    public static final String[] STATES = new String[]{"Selangor", "Selangor", "Kedah", "Perak", "Johor Bahru"};
    public static final String[] COUNTRIES = new String[]{"Malaysia"};
    public static final String[] POSTCODES = new String[]{"56100", "43440", "43540", "51100", "70800"};

    public static Address createDummy(int index) {
        Address output = new Address();
        output.line1 = LINE1S[index % LINE1S.length];
        output.line2 = LINE2S[index % LINE2S.length];
        output.city = CITIES[index % CITIES.length];
        output.state = STATES[index % STATES.length];
        output.country = COUNTRIES[index % COUNTRIES.length];
        output.postCode = POSTCODES[index % POSTCODES.length];
        return output;
    }

    public String getFullAddress() {
        String output = this.line1;

        if (output.isEmpty() || this.line2.isEmpty()) {
            output += this.line2;
        } else {
            output += ", " + this.line2;
        }
        if (output.isEmpty() || this.city.isEmpty()) {
            output += this.city;
        } else {
            output += ", " + this.city;
        }
        if (output.isEmpty() || this.postCode.isEmpty()) {
            output += this.postCode;
        } else {
            output += ", " + this.postCode;
        }
        if (output.isEmpty() || this.state.isEmpty()) {
            output += this.state;
        } else {
            output += ", " + this.state;
        }
        if (output.isEmpty() || this.country.isEmpty()) {
            output += this.country;
        } else {
            output += ", " + this.country;
        }
        return output;
    }
}
