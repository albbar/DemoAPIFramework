package stepDefinitions;

import io.cucumber.java.Before;
import stepDefinitions.PlaceValidationStepDefinition;

import java.io.IOException;

public class Hooks {

    @Before("DeletePlace")
    public void beforeScenario() throws IOException {

        PlaceValidationStepDefinition sD = new PlaceValidationStepDefinition();
        if (PlaceValidationStepDefinition.placeId == null){
            sD.add_place_payload_with("Alberto", "Spanish", "Latinoamerica");
            sD.user_calls_with_post_http_request("AddPlaceAPI", "POST");
            sD.verify_place_id_created_maps_to_using("Alberto","GetPlaceAPI");
        }
    }
}
