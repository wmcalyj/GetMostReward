package nu.getmostreward.service.data;

import com.google.android.gms.maps.model.LatLng;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by mengchaowang on 6/5/16.
 */
public class PlaceTypeData {
    private static Map<LatLng, Set<String>> data;

    private PlaceTypeData(){
        // Do nothing
    }

    public static void addPlaceType(LatLng pos, String type){
        if(data == null){
            data = new HashMap<LatLng, Set<String>>();
        }
        Set<String> types = data.get(pos);
        if(types == null){
            types = new HashSet<String>();
        }
        types.add(type);
        data.put(pos, types);
    }

    public static void addAllPlaceType(LatLng pos, Collection<String> type){
        if(data == null){
            data = new HashMap<LatLng, Set<String>>();
        }
        Set<String> types = data.get(pos);
        if(types == null){
            types = new HashSet<String>();
        }
        if(type.contains("movie_theater")){
            types.add("movie_theater");
        }
        if(type.contains("restaurant")){
            types.add("restaurant");
        }
        if(type.contains("car_rental")){
            types.add("car_rental");
        }
        if(type.contains("department_store")){
            types.add("department_store");
        }
        if(type.contains("gas_station")){
            types.add("gas_station");
        }
        if(type.contains("grocery_or_supermarket")){
            types.add("grocery_or_supermarket");
        }
//        types.addAll(type);
        data.put(pos, types);
    }

    public static Set<String> getTypeForPos(LatLng pos){
        if(pos == null){
            return null;
        }
        if(data == null){
            return null;
        }
        return data.get(pos);
    }

    public static Map<LatLng, Set<String>> getAllPlaceTypeMap(){
        if(data == null){
            data = new HashMap<LatLng, Set<String>>();
        }
        return data;
    }
}
