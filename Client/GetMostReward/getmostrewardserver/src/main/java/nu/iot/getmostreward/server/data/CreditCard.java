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
    private Reward highestReward;
    private Category highestCategory;
    private Reward defaultReward;

    private CreditCard() {
        // Disable Default Constructor
    }

    public CreditCard(String ccName, String ccDescription, Reward defaultReward) {
        this.ccName = ccName;
        this.ccDescription = ccDescription;
        this.defaultReward = defaultReward;
        this.highestCategory = new Category(CategoryEnum.everything);
        this.highestReward = defaultReward;
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

    public Category getHighestCategory() {
        return this.highestCategory;
    }

    public Reward getHighestReward() {
        return this.highestReward;
    }

    public void addRewardCategory(Category category, Reward reward) {
        if (this.ccReward == null) {
            ccReward = new HashMap<Category, Reward>();
        }
        ccReward.put(category, reward);
        updateCCInfo();
    }

    public void addRewardCategory(CategoryEnum categoryEnum, Reward reward) {
        addRewardCategory(new Category(categoryEnum), reward);
    }


    public Reward getDefaultReward() {
        return defaultReward;
    }

    private void updateCCInfo() {
        for (Map.Entry<Category, Reward> entry : ccReward.entrySet()) {
            if (entry.getValue().getReward() > this.highestReward.getReward()) {
                this.highestCategory = entry.getKey();
                this.highestReward = entry.getValue();
            }
        }
    }
}
