package nu.iot.getmostreward.server.service;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.EmbeddedEntity;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

import java.io.Console;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
        Map<Category, Reward> allRewards = cc.getAllCategoryReward();
        if (allRewards != null) {
            for (Map.Entry<Category, Reward> entry : allRewards.entrySet()) {
                Entity creditcard = new Entity(DatastoreEntity.CREDITCARD);
                creditcard.setProperty("Category", entry.getKey().getCategory().getName());
                creditcard.setProperty("Exclusion", entry.getKey().getCategoryExclusion());
                creditcard.setProperty("Rewards", entry.getValue().getReward());
                creditcard.setProperty("DefaultReward", cc.getDefaultReward().getReward());
                creditcard.setProperty("CreditCardName", cc.getName());
                creditcard.setProperty("CreditCardDescription", cc.getDescription());
                creditcard.setProperty("Approved", false);
                datastore.put(creditcard);
            }
        }else {
            Entity creditcard = new Entity(DatastoreEntity.CREDITCARD);
            creditcard.setProperty("DefaultReward", cc.getDefaultReward().getReward());
            creditcard.setProperty("CreditCardName", cc.getName());
            creditcard.setProperty("CreditCardDescription", cc.getDescription());
            creditcard.setProperty("Approved", false);
            datastore.put(creditcard);
        }
    }

    public static CreditCardReward getCreditCardNameWithRewardForType(Set<String> types) {
        // https://cloud.google.com/appengine/docs/java/datastore/retrieving-query-results

        if(types == null || types.size() == 0 || types.isEmpty()){
            return getBestDefaultCreditCardReward();
        }
        Query.Filter typeFilter = new Query.FilterPredicate("Category", Query.FilterOperator.IN, types);
        Query.Filter approvedFilter = new Query.FilterPredicate("Approved", Query.FilterOperator.EQUAL, true);
        Query.Filter filter = Query.CompositeFilterOperator.and(typeFilter, approvedFilter);

        Query q = new Query(DatastoreEntity.CREDITCARD).setFilter(filter);
        q.addSort("Rewards", Query.SortDirection.DESCENDING);
        PreparedQuery pq = datastore.prepare(q);
        List<Entity> results = pq.asList(FetchOptions.Builder.withLimit(1));
        if(results == null || results.isEmpty() || results.size() < 1){
            return getBestDefaultCreditCardReward();
        }else{
            Entity result = results.get(0);
            return new CreditCardReward((String) (result.getProperty("CreditCardName")), (double) result.getProperty("Rewards"), (String) result.getProperty("Category"));
        }
    }

    public static CreditCardReward getBestDefaultCreditCardReward(){
        Query.Filter approvedFilter = new Query.FilterPredicate("Approved", Query.FilterOperator.EQUAL, true);
        Query q = new Query(DatastoreEntity.CREDITCARD).setFilter(approvedFilter);
        q.addSort("DefaultReward", Query.SortDirection.DESCENDING);
        PreparedQuery pq = datastore.prepare(q);
        List<Entity> results = pq.asList(FetchOptions.Builder.withLimit(1));
        if(results == null || results.isEmpty() || results.size() < 1){
            return null;
        }else{
            Entity result = results.get(0);
            return new CreditCardReward((String) (result.getProperty("CreditCardName")), (double) result.getProperty("DefaultReward"), "Everything");
        }


    }
}
