package nu.iot.getmostreward.server.service;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.EmbeddedEntity;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

import java.util.Map;

import nu.iot.getmostreward.server.data.Category;
import nu.iot.getmostreward.server.data.CategoryEnum;
import nu.iot.getmostreward.server.data.CreditCard;
import nu.iot.getmostreward.server.data.CreditCardReward;
import nu.iot.getmostreward.server.data.DatastoreEntity;
import nu.iot.getmostreward.server.data.Reward;

/**
 * Created by mengchaowang on 5/8/16.
 */
public class DataService {
    private static DatastoreService datastore = DatastoreServiceFactory
            .getDatastoreService();

    public static void saveNewCreditCard(CreditCard cc) {
        // Use default ID
        Entity creditcard = new Entity(DatastoreEntity.CREDITCARD);
        Map<Category, Reward> allRewards = cc.getAllCategoryReward();
        EmbeddedEntity rewards = new EmbeddedEntity();
        if (allRewards != null) {
            for (Map.Entry<Category, Reward> entry : allRewards.entrySet()) {
                rewards.setProperty("Category", entry.getKey().getCategory().getName());
                rewards.setProperty("Exclusion", entry.getKey().getCategoryExclusion());
                rewards.setProperty("Rewards", entry.getValue().getReward());
            }
        }
        creditcard.setProperty("CategoryRewards", rewards);

        EmbeddedEntity highestReward = new EmbeddedEntity();
        highestReward.setProperty("Category", cc.getHighestCategory().getCategory().getName());
        highestReward.setProperty("Exclusion", cc.getHighestCategory().getCategoryExclusion());
        highestReward.setProperty("Reward", cc.getHighestReward().getReward());
        creditcard.setProperty("HighestReward", highestReward);
        creditcard.setProperty("DefaultReward", cc.getDefaultReward().getReward());
        creditcard.setProperty("CreditCardName", cc.getName());
        creditcard.setProperty("CreditCardDescription", cc.getDescription());

        creditcard.setProperty("Approved", false);
        datastore.put(creditcard);
    }

    public static CreditCardReward getCreditCardNameWithRewardForType(CategoryEnum type) {

        // https://cloud.google.com/appengine/docs/java/datastore/retrieving-query-results
        Query q =
                new Query(DatastoreEntity.CREDITCARD)
                        .setFilter(new Query.FilterPredicate("CreditCardName", Query.FilterOperator
                                .EQUAL, "Chase Freedom"));

        PreparedQuery pq = datastore.prepare(q);
        Entity result = pq.asSingleEntity();
        return new CreditCardReward((String) (result.getProperty("CreditCardName")), (double) result
                .getProperty
                        ("DefaultReward"), "");

    }
}
