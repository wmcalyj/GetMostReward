package nu.iot.getmostreward.server.data;

/**
 * Created by mengchaowang on 5/9/16.
 */
public class CreditCardReward {
    private String ccName;
    private double reward;
    private String categoryType;

    private CreditCardReward() {
    }

    public CreditCardReward(String ccName, double reward, String categoryType) {
        this.ccName = ccName;
        this.reward = reward;
        this.categoryType = categoryType;
    }

    public String getCcName() {
        return ccName;
    }

    public void setCcName(String ccName) {
        this.ccName = ccName;
    }

    public double getReward() {
        return reward;
    }

    public void setReward(double reward) {
        this.reward = reward;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Name: ");
        sb.append(this.ccName).append(", Category Type: ").append(this.categoryType).append(", " +
                "Reward: ").append(this.reward);
        return sb.toString();
    }
}
