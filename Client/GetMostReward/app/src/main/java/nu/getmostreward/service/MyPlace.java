package nu.getmostreward.service;

/**
 * Created by mengchaowang on 4/29/16.
 */
public class MyPlace {
    public String icon;
    public String name;
    public String reference;
    public String formatted_address;
    public String formatted_phone_number;

    public MyPlace() {
        ;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        if (icon != null) {
            sb.append("icon: ").append(icon);
        }
        if (name != null) {
            sb.append(", name: ").append(name);
        }
        if (reference != null) {
            sb.append(", reference: ").append(reference);
        }
        if (formatted_address != null) {
            sb.append(", address: ").append(formatted_address);
        }
        if (formatted_phone_number != null) {
            sb.append(", phone: ").append(formatted_phone_number);
        }
        sb.append("]");
        return sb.toString();
    }

}
