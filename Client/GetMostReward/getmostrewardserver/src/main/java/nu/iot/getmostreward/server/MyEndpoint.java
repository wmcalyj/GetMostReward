/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package nu.iot.getmostreward.server;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.inject.Named;

import nu.iot.getmostreward.server.data.CategoryEnum;
import nu.iot.getmostreward.server.data.CreditCard;
import nu.iot.getmostreward.server.data.Reward;
import nu.iot.getmostreward.server.service.DataService;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "server.getmostreward.iot.nu",
                ownerName = "server.getmostreward.iot.nu",
                packagePath = ""
        )
)
public class MyEndpoint {


    /**
     * A simple endpoint method that takes a name and says Hi back
     */
    @ApiMethod(name = "findCreditCard")
    public MyBean findCreditCard(@Named("locationTypes") String type) {
        MyBean response = new MyBean();
        String cc;
        String[] types = type.split(",");
        cc = DataService.getCreditCardNameWithRewardForType(new ArrayList<String>(Arrays.asList(types))).toString();
        response.setData(cc);
        return response;
    }

    @ApiMethod(name = "saveCC")
    public MyBean saveCC(@Named("name") String name) {
        CreditCard cc = new CreditCard("Discover It", "Discover It", new
                Reward(1));
        cc.addRewardCategory(CategoryEnum.movie_theater, new Reward(5));
        cc.addRewardCategory(CategoryEnum.gas_station, new Reward(5));
        DataService.saveNewCreditCard(cc);
        cc = new CreditCard("Discover It Miles", "Discover It Miles", new
                Reward(1.5));
        DataService.saveNewCreditCard(cc);
        cc = new CreditCard("American Express BlueCash Everyday", "American Express BlueCash Everyday", new
                Reward(1));
        cc.addRewardCategory(CategoryEnum.gas_station, new Reward(2));
        cc.addRewardCategory(CategoryEnum.grocery_or_supermarket, new Reward(3));
        DataService.saveNewCreditCard(cc);
        cc = new CreditCard("Chase Freedom Unlimited", "Chase Freedom Unlimited", new
                Reward(1.5));
        DataService.saveNewCreditCard(cc);
        cc = new CreditCard("Citi Double Cash", "Citi Double Cash", new Reward(2));
        MyBean response = new MyBean();
        response.setData("Saved Successfully");
        return response;
    }

}
