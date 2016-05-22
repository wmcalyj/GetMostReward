package nu.iot.getmostreward.server.data;

/**
 * Created by mengchaowang on 5/9/16.
 */
public enum CategoryEnum {
    
    everything("everything"),
    none("not found"),
    movie_rental("movie_rental"),
    movie_theater("movie_theater"),
    moving_company("moving_company"),
    museum("museum"),
    night_club("night_club"),
    pet_store("pet_store"),
    pharmacy("pharmacy"),
    physiotherapist("physiotherapist"),
    plumber("plumber"),
    post_office("post_office"),
    real_estate_agency("real_estate_agency"),
    roofing_contractor("roofing_contractor"),
    shoe_store("shoe_store"),
    shopping_mall("shopping_mall"),
    spa("spa"),
    stadium("stadium"),
    storage("storage"),
    store("store"),
    travel_agency("travel_agenc"),
    zoo("zoo"),
    car_dealer("car_dealer"),
    car_rental("car_rental"),
    car_repair("car_repair"),
    car_wash("car_wash"),
    casino("casino"),
    clothing_store("clothing_stor"),
    convenience_store("convenience_store"),
    dentist("dentist"),
    department_store("department_store"),
    doctor("doctor"),
    electronics_store("electronics_store"),
    florist("florist"),
    furniture_store("furniture_store"),
    gas_station("gas_station"),
    grocery_or_supermarket("grocery_or_supermarket"),
    gym("gym"),
    hair_care("hair_car"),
    hardware_store("hardware_store"),
    home_goods_store("home_goods_store");

    private String name;


    CategoryEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
