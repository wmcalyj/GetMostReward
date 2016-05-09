package nu.iot.getmostreward.server.data;

import com.google.api.server.spi.config.DefaultValue;

/**
 * Created by mengchaowang on 5/9/16.
 */
public class POIBean {


    private CategoryEnum poiType;
    private String poiName;
    private String poiAddress;
    private String poiPhone;
    private String poiUrl;

    private POIBean() {
        //Disable Default Constructor
    }

    public POIBean(CategoryEnum type, String poiName) {
        this.poiType = type;
        this.poiName = poiName;
    }

    public POIBean(CategoryEnum type, String poiName, String poiAddress, String
            poiPhone, String poiUrl) {
        this.poiType = type;
        this.poiName = poiName;

        if (poiAddress != null && poiAddress.length() > 0) {
            this.poiAddress = poiAddress;
        }
        if (poiPhone != null && poiPhone.length() > 0) {
            this.poiPhone = poiPhone;
        }
        if (poiUrl != null && poiUrl.length() > 0) {
            this.poiUrl = poiUrl;
        }

    }

    public CategoryEnum getPoiType() {
        return poiType;
    }

    public void setPoiType(CategoryEnum poiType) {
        this.poiType = poiType;
    }

    public String getPoiName() {
        return poiName;
    }

    public void setPoiName(String poiName) {
        this.poiName = poiName;
    }

    public String getPoiAddress() {
        return poiAddress;
    }

    public void setPoiAddress(String poiAddress) {
        this.poiAddress = poiAddress;
    }

    public String getPoiPhone() {
        return poiPhone;
    }

    public void setPoiPhone(String poiPhone) {
        this.poiPhone = poiPhone;
    }

    public String getPoiUrl() {
        return poiUrl;
    }

    public void setPoiUrl(String poiUrl) {
        this.poiUrl = poiUrl;
    }


}
