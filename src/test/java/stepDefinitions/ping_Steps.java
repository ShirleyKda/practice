package stepDefinitions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import utilities.constantes;

public class ping_Steps {

    private static Response response;

    @When("^el realiza un ping al api$")
    public void elRealizaUnPingAlApi() {
        RestAssured.baseURI = constantes.BASE_URL;
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        response = request.get("/ping");
    }

    @Then("^el codigo de respuesta del api ping debe ser (\\d+)$")
    public void elCodigoEstadoDeLaReservaDebeSer(int expectedStatusCode) {
        Assert.assertEquals(expectedStatusCode, response.statusCode());
    }

    @When("^el realiza un ping al enpoint \"([^\"]*)\"$")
    public void elRealizaUnPingAlEnpoint(String endpoint)  {
        RestAssured.baseURI = constantes.BASE_URL;
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        response = request.get("/"+endpoint);
    }
}
