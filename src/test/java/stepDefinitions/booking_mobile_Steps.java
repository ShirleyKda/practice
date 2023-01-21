package stepDefinitions;

import cucumber.api.java.en.When;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import screenMobile.booking_screen;

public class booking_mobile_Steps {

    private static WebDriver driver;
    private static DesiredCapabilities capabilities=null;


    @Before("@appium")
    public void setUpAppium() throws MalformedURLException
    {
        manageCapabilitiesAndroid();
        driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
    }

    public static void manageCapabilitiesAndroid()
    {
        capabilities = DesiredCapabilities.android();
        capabilities.setCapability("--session-override", true);
        capabilities.setCapability(MobileCapabilityType.PLATFORM, "Android");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android");
        capabilities.setCapability("deviceName", "420052446a99337b");
        capabilities.setCapability("udid", "420052446a99337b");
        capabilities.setCapability("platformVersion", "5.1");
        capabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir")+"//App//booking-com-32-9.apk");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
        capabilities.setCapability("newCommandTimeout", 6000);
        capabilities.setCapability("autoGrantPermissions", true);
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
    }







}
