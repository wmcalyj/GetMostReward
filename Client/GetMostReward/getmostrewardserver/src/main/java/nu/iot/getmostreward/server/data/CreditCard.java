package nu.iot.getmostreward.server.data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mengchaowang on 5/8/16.
 */
public class CreditCard {
    private String ccName;
    private String ccDescription;
    private Map<Category, Reward> ccReward;
    private Reward defaultReward;

    private CreditCard() {
        // Disable Default Constructor
    }

    public CreditCard(String ccName, String ccDescription, Reward defaultReward) {
        this.ccName = ccName;
        this.ccDescription = ccDescription;
        this.defaultReward = defaultReward;
        this.ccReward = new HashMap<Category, Reward>();
        ccReward.put(new Category(CategoryEnum.everything), defaultReward);
    }

    public String getName() {
        return this.ccName;
    }

    public String getDescription() {
        return this.ccDescription;
    }

    public Map<Category, Reward> getAllCategoryReward() {
        return this.ccReward;
    }

    public void addRewardCategory(Category category, Reward reward) {
        if (this.ccReward == null) {
            ccReward = new HashMap<Category, Reward>();
        }
        ccReward.put(category, reward);
    }

    public void addRewardCategory(CategoryEnum categoryEnum, Reward reward) {
        addRewardCategory(new Category(categoryEnum), reward);
    }


    public Reward getDefaultReward() {
        return defaultReward;
    }

}
