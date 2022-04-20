@ValidationPlaceAPI
Feature: Validating place API's

  @AddPLace @Regression
  Scenario Outline: Verify if place is begin Successfully added using AddPlaceAPI
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When user calls "AddPlaceAPI" with "POST" HTTP request
    Then the API call is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_id created maps to "<name>" using "GetPlaceAPI"

    Examples:
      | name    | language | address            |
      | AAhouse | English  | world cross center |
 #     | BBhouse | Spanish  | sea cross center   |

  @DeletePlace @Regression
  Scenario: Verify if Delete Place functionality is working

    Given DeletePlace Payload
    When user calls "DeletePlaceAPI" with "POST" HTTP request
    Then the API call got success with status code 200
