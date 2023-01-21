package stepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.constantes;


import java.util.List;
import java.util.Map;
import org.junit.Assert;
import java.util.HashMap;

public class booking_Steps {

    private static Response response;
    private static String jsonString;

    private static String nombres;
    private static String apellidos;
    private static int monto_pagado;
    private static String fecha_inicio;
    private static String fecha_fin;
    private static String reserva_actualizar_or_eliminar;



    @When("^el lista todos los IDs de las reservas$")
    public void elListaTodosLosIDsDeLasReservas() {
        RestAssured.baseURI = constantes.BASE_URL;
        RequestSpecification request = RestAssured.given();

        response = request.get("/booking");
        jsonString = response.asString();

        jsonString = response.asString();
        List<Map<String, String>> listaReservaPorId = JsonPath.from(jsonString).get("");

        int totalIDs = listaReservaPorId.size();
        System.out.println("Numero de IDs reserva es: "+ totalIDs);

        //Validar devuelve true si el total es mayor a cero
        Assert.assertTrue(listaReservaPorId.size() > 0);
    }

    @Then("^el codigo de respuesta debe ser (\\d+)$")
    public void elCodigoEstadoDeLaReservaDebeSer(int expectedStatusCode) {
        Assert.assertEquals(expectedStatusCode, response.statusCode());
    }


    @When("^el busca una reserva especifica \"([^\"]*)\"$")
    public void elBuscaUnaReservaEspecifica(String id_reserva) {
        RestAssured.baseURI = constantes.BASE_URL;
        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");
        response = request.get("/booking/"+id_reserva);
    }

    @Then("^la busqueda debe ser exitosa$")
    public void laBusquedaDebeSerExitosa() {
        jsonString = response.asString();
        String firstname = JsonPath.from(jsonString).get("firstname");
        String lastname = JsonPath.from(jsonString).get("lastname");
        int totalprice = JsonPath.from(jsonString).get("totalprice");
        boolean depositpaid = JsonPath.from(jsonString).get("depositpaid");

        System.out.println("Primer nombre es: "+firstname);
        System.out.println("Apellido es: "+lastname);
        System.out.println("Precio es: "+totalprice);
        System.out.println("Deposito pagado?: "+depositpaid);
    }

    @Given("^el (?:reserva|actualiza) con \"([^\"]*)\" y \"([^\"]*)\"$")
    public void elReservaConY(String nombre, String apellido) {
        nombres = nombre;
        apellidos = apellido;
    }

    @And("^pagando la reserva con (\\d+) soles$")
    public void pagandoLaReservaConSoles(int monto) {
        monto_pagado = monto;
    }

    @And("^en la fecha de inicio \"([^\"]*)\" y fin \"([^\"]*)\"$")
    public void enLaFechaDeInicioYFin(String inicio, String fin)  {
        fecha_inicio = inicio;
        fecha_fin = fin;
    }

    @When("^el realiza la nueva reserva$")
    public void elRealizaLaNuevaReserva() {
        RestAssured.baseURI = constantes.BASE_URL;
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        String Breakfast = "desayuno";
        response = request.body("{ \"firstname\": \"" + nombres + "\", " +
                "\"lastname\": \"" + apellidos + "\", " +
                "\"totalprice\": \"" + monto_pagado + "\", " +
                "\"depositpaid\": \"" + true + "\", " +
                "\"bookingdates\": { \"checkin\": \"" + fecha_inicio + "\", \"checkout\": \"" + fecha_fin +"\"} , " +
                "\"additionalneeds\": \"" + Breakfast + "\" }")
                .post("/booking/");
    }

    @Then("^la reserva debe ser exitosa$")
    public void laReservaDebeSerExitosa() {
        String jsonString = response.asString();
        int id_reserva_creada = JsonPath.from(jsonString).get("bookingid");
        System.out.println("Id de la reserva creada: "+id_reserva_creada);

        HashMap<String, String> mapRespuesta = new HashMap<>();
        mapRespuesta = JsonPath.from(jsonString).get("booking");

        String nombres_respuesta = mapRespuesta.get("firstname");
        String apellidos_respuesta = mapRespuesta.get("lastname");

        Assert.assertEquals(nombres,nombres_respuesta);
        Assert.assertEquals(apellidos,apellidos_respuesta);
    }

    @Given("^el selecciona la reserva de ID \"([^\"]*)\" para (?:actualizar|eliminar)$")
    public void elSeleccionaLaReservaDeIDParaActualizar(String id_reserva)  {
        reserva_actualizar_or_eliminar = id_reserva;
    }

    @Then("^el ejecuta la actualizacion de la reserva$")
    public void laReservaDebeSerActualizadaExitosamente() {
        RestAssured.baseURI = constantes.BASE_URL;
        String Breakfast = "almuerzo";

        RequestSpecification request = RestAssured.given().auth().preemptive().basic("admin", "password123");

        request.header("Content-Type", "application/json");
        response = request.body("{ \"firstname\": \"" + nombres + "\", " +
                "\"lastname\": \"" + apellidos + "\", " +
                "\"totalprice\": \"" + monto_pagado + "\", " +
                "\"depositpaid\": \"" + true + "\", " +
                "\"bookingdates\": { \"checkin\": \"" + fecha_inicio + "\", \"checkout\": \"" + fecha_fin +"\"} , " +
                "\"additionalneeds\": \"" + Breakfast + "\" }")
                .put("/booking/"+reserva_actualizar_or_eliminar);
    }

    @Then("^la reserva se actualiza exitosamente$")
    public void laReservaSeActualizaExitosamente() {
        //Convertimos la respuesta a string
        String jsonString = response.asString();

        String nombres_respuesta = JsonPath.from(jsonString).get("firstname");
        String apellidos_respuesta = JsonPath.from(jsonString).get("lastname");
        System.out.println("El nuevo nombre es: "+nombres_respuesta);

        Assert.assertEquals(nombres,nombres_respuesta);
        Assert.assertEquals(apellidos,apellidos_respuesta);
    }


    @When("^el ejecuta la actualizacion parcial de (.*)$")
    public void elEjecutaLaActualizacionDeCiertosDatos(String valor) {
        RestAssured.baseURI = constantes.BASE_URL;
        String Breakfast = "cena";

        RequestSpecification request = RestAssured.given().auth().preemptive().basic("admin", "password123");
        request.header("Content-Type", "application/json");

        switch(valor) {
            case "nombres_apellidos":
                response = request.body("{ \"firstname\": \"" + nombres + "\", " +
                        "\"lastname\": \"" + apellidos + "\" }")
                        .patch("/booking/"+reserva_actualizar_or_eliminar);
                break;
            case "monto_pagado":
                response = request.body("{ \"totalprice\": \"" + monto_pagado + "\" }")
                        .patch("/booking/"+reserva_actualizar_or_eliminar);
                break;
            case "fecha_reserva":
                response = request.body("{ \"bookingdates\": { \"checkin\": \"" + fecha_inicio + "\", " +
                        "\"checkout\": \"" + fecha_fin +"\"}}")
                        .patch("/booking/"+reserva_actualizar_or_eliminar);
                break;
            default:
                response = request.body("{ \"additionalneeds\": \"" + Breakfast + "\"}")
                        .patch("/booking/"+reserva_actualizar_or_eliminar);
                break;
        }


    }

    @When("^el ejecuta la eliminación de reserva$")
    public void elEjecutaLaEliminaciónDeReserva() {
        RestAssured.baseURI = constantes.BASE_URL;

        RequestSpecification request = RestAssured.given().auth().preemptive().basic("admin", "password123");
        request.header("Content-Type", "application/json");

        response = request.delete("/booking/"+reserva_actualizar_or_eliminar);
    }


    @When("^el realiza la nueva reserva sin el campo monto pagado$")
    public void elRealizaLaNuevaReservaSinCampoDeMonto() {
        RestAssured.baseURI = constantes.BASE_URL;
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        String Breakfast = "desayuno";
        response = request.body("{ \"firstname\": \"" + nombres + "\", " +
                "\"lastname\": \"" + apellidos + "\", " +
                "\"depositpaid\": \"" + true + "\", " +
                "\"bookingdates\": { \"checkin\": \"" + fecha_inicio + "\", \"checkout\": \"" + fecha_fin +"\"} , " +
                "\"additionalneeds\": \"" + Breakfast + "\" }")
                .post("/booking/");
    }
}
