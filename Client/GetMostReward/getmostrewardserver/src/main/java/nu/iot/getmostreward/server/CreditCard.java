package nu.iot.getmostreward.server;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mengchaowang on 5/8/16.
 */
public class CreditCard {
    private String ccName;
    private String ccDescription;
    private Map<Category, Float> ccReward;
    private Float highestReward;
    private Category highestCategory;
    private double defaultReward;

    private CreditCard() {
        // Disable Default Constructor
    }

    public CreditCard(String ccName, String ccDescription, double defaultReward) {
        this.ccName = ccName;
        this.ccDescription = ccDescription;
        this.defaultReward = defaultReward;
    }

    public String getName() {
        return this.ccName;
    }

    public String getDescription() {
        return this.ccDescription;
    }

    public Map<Category, Float> getAllCategoryReward() {
        return this.ccReward;
    }

    public Category getHighestCategory() {
        return this.highestCategory;
    }

    public Float getHighestReward() {
        return this.highestReward;
    }

    public void addRewardCategory(Category category, Float reward) {
        if (this.ccReward == null) {
            ccReward = new HashMap<Category, Float>();
        }
        ccReward.put(category, reward);
    }


    public double getDefaultReward() {
        return defaultReward;
    }
}
