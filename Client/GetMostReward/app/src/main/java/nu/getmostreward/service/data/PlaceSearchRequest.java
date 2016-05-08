package nu.getmostreward.service.data;

import android.location.Location;

/**
 * Created by mengchaowang on 5/2/16.
 */
public class PlaceSearchRequest {
    private static StringBuilder KEY = new StringBuilder
            ("key=AIzaSyDPrwN1zh4t0XLNCpth8D4H-KpB4aSzX1M");
    // This should not be changed or modified
    public StringBuilder request = new StringBuilder("https://maps.googleapis" +
            ".com/maps/api/place/nearbysearch/json?");


    private PlaceSearchRequest() {
        // Should not allow default constructor
    }

    public PlaceSearchRequest(Location location, int radius) {
        StringBuilder locationSb = getLocationParameter(location);
        StringBuilder radiusSb = getRadiusParameter(radius);


    }

    private StringBuilder getLocationParameter(Location location) {
        //current location
        double mLatitude = location.getLatitude();
        double mLongitude = location.getLongitude();

        StringBuilder sb = new StringBuilder("location=");
        sb.append("location=" + mLatitude + "," + mLongitude);
        return sb;
    }

    private StringBuilder getRadiusParameter(int radius){
        StringBuilder sb = new StringBuilder("radius=");
        sb.append(radius);
        return sb;
    }

    public enum OPTIONAL {
        KEYWORD("keyword"),
        LANGUAGE("language"),
        MINPRICE("minprice"),
        MAXPRICE("maxprice"),
        NAME("name"),
        OPEN("opennow"),
        RANKBY("rankby"),
        TYPE("type"),
        PAGETOKEN("pagetoken");
        private String name;

        OPTIONAL(String name) {
            this.name = name;
        }

    }

}
