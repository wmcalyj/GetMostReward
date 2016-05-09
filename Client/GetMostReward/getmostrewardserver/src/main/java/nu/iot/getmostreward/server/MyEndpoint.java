/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package nu.iot.getmostreward.server;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;

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
    @ApiMethod(name = "sayHi")
    public MyBean sayHi(@Named("name") String name) {
//        CreditCard cc = new CreditCard("Chase Freedom", "Chase Freedom", new Reward(1.5));
//        DataService.saveNewCreditCard(cc);
        MyBean response = new MyBean();
        String cc = DataService.getCreditCardNameWithRewardForType(null).toString();
        response.setData(cc);

        return response;
    }

}
