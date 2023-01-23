package utilities;

import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.remote.MobileCapabilityType;

public class util {

    private static DesiredCapabilities capabilities=null;

    public static void manageCapabilitiesAndroid()
    {
        capabilities = DesiredCapabilities.android();
        capabilities.setCapability(MobileCapabilityType.PLATFORM, "Android");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android");
        capabilities.setCapability("deviceName", "420052446a99337b");
        capabilities.setCapability("udid", "emulator-5554");
        capabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir")+"//App//booking-com-32-9.apk");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
        capabilities.setCapability("newCommandTimeout", 6000);
        capabilities.setCapability("autoGrantPermissions", true);
    }

    public static DesiredCapabilities getCapabilities()
    {
        return capabilities;
    }








}
