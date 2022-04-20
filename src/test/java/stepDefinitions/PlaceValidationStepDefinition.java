package stepDefinitions;


import resource.constants.APIResource;
import resource.data.TestDataBuild;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.runner.RunWith;
import utils.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static resource.constants.KeyWords.POST;
import static resource.constants.KeyWords.GET;
import static resource.constants.KeyWords.DELETE;
import static resource.constants.KeyWords.PLACE_ID;
import static resource.constants.KeyWords.NAME;

@RunWith(Cucumber.class)
public class PlaceValidationStepDefinition extends Utils {
    RequestSpecification givenRS;
    Response response;
    TestDataBuild data = new TestDataBuild();
    static String placeId;

    /*
    AddPlaceAPI with a validation using GetPlaceAPI
     */

    @Given("Add Place Payload with {string} {string} {string}")
    public void add_place_payload_with(String name, String language, String address) throws IOException {
        //request
        givenRS = given().spec(requestSpecification())
                .body(data.addPlacePayLoad(name, language, address));
    }

    @When("user calls {string} with {string} HTTP request") // you can use '"([^"]*)"' or {String} or {int}
    public void user_calls_with_post_http_request(String API, String method) {

        APIResource resourceAPI = APIResource.valueOf(API);

        //request
        if (method.equalsIgnoreCase(POST)){
            response = givenRS.when().post(resourceAPI.getResource());
        } else if (method.equalsIgnoreCase(GET)) {
            response = givenRS.when().get(resourceAPI.getResource());
        } else if (method.equalsIgnoreCase(DELETE)) {
            response = givenRS.when().delete(resourceAPI.getResource());
        }
    }

    @Then("the API call is success with status code {int}")
    public void the_api_call_is_success_with_status_code(int status) {
        assertEquals(response.getStatusCode(), status);
    }

    @Then("{string} in response body is {string}")
    public void in_response_body_(String keyValue, String expectedValue) {
        assertEquals(getJsonPath(response, keyValue), expectedValue);
    }

    @Then("verify place_id created maps to {string} using {string}")
    public  void verify_place_id_created_maps_to_using(String name, String API) throws IOException {

        placeId = getJsonPath(response, PLACE_ID);
        givenRS = given().spec(requestSpecification())
                .queryParam(PLACE_ID, placeId);
        user_calls_with_post_http_request(API, GET);

        String actualName = getJsonPath(response, NAME);
        assertEquals(actualName, name);
    }

    /*
    DeletePlaceAPI
     */

    @Given("DeletePlace Payload")
    public void delete_place_payload() throws IOException {
        givenRS = given().spec(requestSpecification())
                .body(data.deletePlacePayload(placeId));
    }
    @Then("the API call got success with status code {int}")
    public void the_api_call_got_success_with_status_code(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
    }
}
