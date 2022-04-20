package resource.data;

import pojo.googleMapApiPojo.googleMapApiAddPlace;
import pojo.googleMapApiPojo.googleMapApiLocation;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {

    public googleMapApiAddPlace addPlacePayLoad(String name, String language, String address){

        /*
        Set the data on the googleMapApiAssPlace object
        */

        //Create a type list
        List<String> typesList = new ArrayList<String>();
        typesList.add("shoe park");
        typesList.add("shop");

        //create location
        googleMapApiLocation location = new googleMapApiLocation();
        location.setLat(-38.383494);
        location.setLng(33.427362);

        //Add place
        googleMapApiAddPlace addPlace = new googleMapApiAddPlace();
        addPlace.setLocation(location);
        addPlace.setAccuracy(50);
        addPlace.setName(name);
        addPlace.setPhone_number("(+91) 983 893 3937");
        addPlace.setAddress(address);
        addPlace.setLanguage(language);
        addPlace.setTypes(typesList);

        return addPlace;
    }

    public String deletePlacePayload(String placeId){
        return "{\r\n    \"place_id\":\""+placeId+"\"\r\\n}\r\n";
    }
}