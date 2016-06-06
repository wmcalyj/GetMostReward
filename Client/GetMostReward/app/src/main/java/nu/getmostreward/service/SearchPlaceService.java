package nu.getmostreward.service;

import android.location.Location;
import android.util.Log;

import java.util.List;

/**
 * Created by mengchaowang on 5/21/16.
 */
public class SearchPlaceService {
    public static StringBuilder searchPlaces(Location currentLocation, List<String> types, Integer
            radius) {
        //current location
        double mLatitude = currentLocation.getLatitude();
        double mLongitude = currentLocation.getLongitude();

        // Sample:
//         /*https://maps.googleapis
//         .com/maps/api/place/nearbysearch/json?key=AIzaSyDPrwN1zh4t0XLNCpth8D4H-KpB4aSzX1M
//         &location=42.001302,-87.657324&radius=10*/
        StringBuilder sb = new StringBuilder("https://maps.googleapis" +
                ".com/maps/api/place/nearbysearch/json?");
        sb.append("location=" + mLatitude + "," + mLongitude);
        if (radius != null) {
            sb.append("&radius=").append(radius.intValue());
        } else {
            sb.append("&radius=5000");
        }
        sb.append("&sensor=true");
        sb.append("&key=AIzaSyDPrwN1zh4t0XLNCpth8D4H-KpB4aSzX1M");
        if (types != null && types.size() > 0) {
            for (String type : types) {
                sb.append("&types=").append(type.toLowerCase());
            }
        }
        Log.d("Map", "<><>api: " + sb.toString());

        return sb;
    }
}
