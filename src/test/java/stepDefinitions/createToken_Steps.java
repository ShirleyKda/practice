package stepDefinitions;

import org.junit.Assert;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.constantes;

public class createToken_Steps {

    private static String token;
    private static Response response;

    @Given("^El envia su \"([^\"]*)\" y \"([^\"]*)\" para crear token$")
    public void elEnviaSuYParaCrearToken(String username, String password)  {

        RestAssured.baseURI = constantes.BASE_URL;
        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");
        response = request.body("{ \"username\":\"" + username + "\", \"password\":\"" + password + "\"}")
                .post("/auth");

        String jsonString = response.asString();
        token = JsonPath.from(jsonString).get("token");
        System.out.println("Token value: "+token);
    }

    @Then("^el codigo estado de la respuesta debe ser (\\d+)$")
    public void elCodigoEstadoDeLaRespuestaDebeSer(int expectedStatusCode) {
        Assert.assertEquals(expectedStatusCode, response.statusCode());
    }



}
