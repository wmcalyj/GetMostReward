package nu.iot.getmostreward.server.service;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.EmbeddedEntity;
import com.google.appengine.api.datastore.Entity;

import java.util.Map;

import nu.iot.getmostreward.server.Category;
import nu.iot.getmostreward.server.CreditCard;

/**
 * Created by mengchaowang on 5/8/16.
 */
public class DataService {
    private static DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

    public static void saveNewCreditCard(CreditCard cc) {
        // Use default ID
        Entity creditcard = new Entity("CreditCard");
        Map<Category, Float> allRewards = cc.getAllCategoryReward();
        EmbeddedEntity rewards = new EmbeddedEntity();
        if (allRewards != null) {
            for (Map.Entry<Category, Float> entry : allRewards.entrySet()) {
                rewards.setProperty("Category", entry.getKey());
                rewards.setProperty("Rewards", entry.getValue());
            }
        }
        creditcard.setProperty("CategoryRewards", rewards);

        EmbeddedEntity highestReward = new EmbeddedEntity();
        highestReward.setProperty("Category", cc.getHighestCategory());
        highestReward.setProperty("Reward", cc.getHighestReward().floatValue());
        creditcard.setProperty("HighestReward", highestReward);
        creditcard.setProperty("DefaultReward", cc.getDefaultReward());
        creditcard.setProperty("CreditCardName", cc.getName());
        creditcard.setProperty("CreditCardDescription", cc.getDescription());
        datastore.put(creditcard);
    }
}
