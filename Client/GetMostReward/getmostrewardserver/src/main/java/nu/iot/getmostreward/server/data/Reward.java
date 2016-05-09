package nu.iot.getmostreward.server.data;

/**
 * Created by mengchaowang on 5/9/16.
 */
public class Reward {
    private double reward;

    private Reward() {
        //
    }

    public Reward(double reward) {
        this.reward = reward;
    }

    public double getReward() {
        return reward;
    }

    public void setReward(double reward) {
        this.reward = reward;
    }
}
