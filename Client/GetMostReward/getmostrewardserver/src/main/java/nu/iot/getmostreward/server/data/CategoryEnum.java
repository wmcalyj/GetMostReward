package nu.iot.getmostreward.server.data;

/**
 * Created by mengchaowang on 5/9/16.
 */
public enum CategoryEnum {

    EVERYTHING("everything");
    private String name;

    CategoryEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
