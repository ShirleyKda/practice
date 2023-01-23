package stepDefinitions;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.appium.java_client.android.AndroidDriver;
import screenMobile.booking_screen;
import utilities.*;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;

public class booking_mobile_Steps {

    private static WebDriver driver;

    @Before("@pruebita")
    public void setUpAppium() throws MalformedURLException
    {
        util.manageCapabilitiesAndroid();
        driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), util.getCapabilities());
        driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }

    @When("^el ingresa al app mobile$")
    public void elIngresaAlAppMobile() {
        driver.findElement(booking_screen.txtUser).clear();
        driver.findElement(booking_screen.txtPass).clear();
        System.out.println("hola");
    }


    @Given("^el busca un viaje a la ciudad de \"([^\"]*)\"$")
    public void elBuscaUnViajeALaCiudadDe(String ciudad)  {
        driver.findElement(booking_screen.enterDestino).click();
        driver.findElement(booking_screen.txtDestino).sendKeys(ciudad);
        driver.findElement(booking_screen.firtOpcionCiudad).click();
    }


    @And("^el selecciona las fechas de \"([^\"]*)\" al \"([^\"]*)\"$")
    public void elSeleccionaLasFechasDeAl(String fecha_inicio, String fecha_fin) throws InterruptedException {
        Thread.sleep(3000);

        List<WebElement> list_fecha_inicio = driver.findElements(booking_screen.objeto_fecha);
        WebElement mainElement_fecha_inicio = list_fecha_inicio.get(0);
        WebElement mainElement_fecha_fin = list_fecha_inicio.get(1);

        List<WebElement> childElements_inicio = mainElement_fecha_inicio.findElements(By.className("android.view.View"));
        for(WebElement value: childElements_inicio)
        {
            if (value.getTagName().equals(fecha_inicio))
            {
                System.out.println("\nElemento identificado: "+value.getTagName());
                value.click();
                break;
            }
        }


        List<WebElement> childElements_find = mainElement_fecha_fin.findElements(By.className("android.view.View"));
        for(WebElement value: childElements_find)
        {
            if (value.getTagName().equals(fecha_fin))
            {
                System.out.println("\nElemento identificado: "+value.getTagName());
                value.click();
                break;
            }
        }

        driver.findElement(booking_screen.btn_select_date).click();
    }

    @And("^el selecciona una habitacion para dos adultos y un niño de \"([^\"]*)\"$")
    public void elSeleccionaHabitacionAdultosY(String nino) {
        driver.findElement(booking_screen.objeto_persona).click();
        driver.findElement(booking_screen.objeto_sumar_nino).click();

        String _scrollContainer = "com.booking:id/age_picker_view";
        String _childElement = "android.widget.EditText";

        MobileElement element = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator(
                "new UiScrollable(new UiSelector().resourceId(\"" + _scrollContainer + "\")).getChildByText("
                        + "new UiSelector().className(\"" + _childElement + "\"), \"" + nino + "\")"));

        element.click();
        driver.findElement(booking_screen.label_seleccionar_nino).click();
        driver.findElement(booking_screen.btn_aplicar_nino).click();
    }

    @Then("^el le da en buscar la reserva$")
    public void elSeleccionaBuscarReserva() {
        driver.findElement(booking_screen.btn_buscar_reserva).click();
    }

    @And("^el elige la segunda opcion listada$")
    public void elEligeLaSegundaOpcionBuscada() {
        driver.findElement(booking_screen.label_elegir_ciudad).click();
    }


    @And("^el ahora elige el cuarto$")
    public void elAhoraEligeElCuarto() {
        driver.findElement(booking_screen.btn_select_room).click();
        driver.findElement(booking_screen.btn_select_this_option).click();
    }
}
