package appSetting;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AppRuning {

    public MobileDriver RunApplication(String device) throws MalformedURLException {
        MobileDriver driver=null;
        DesiredCapabilities dc = new DesiredCapabilities();
        switch (device) {
            case "Android":
                dc.setCapability("deviceName", "TestDevice");
                dc.setCapability("udid", "emulator-5554");
                dc.setCapability("newCommandTimeout", "600");
                dc.setCapability("waitForAppScript", "$.delay(500); $.acceptAlert();");
                dc.setCapability("waitForQuiescence", "false");
                dc.setCapability("platformName", "Android");
                dc.setCapability("platformVersion", "10.0");
                dc.setCapability("autoGrantPermissions", "true");
                dc.setCapability("appPackage", "com.pordiva.nesine.android");
                dc.setCapability("app",System.getProperty("user.dir")+"\\src\\test\\resources\\app\\nesine.com.apk");
                dc.setCapability(MobileCapabilityType.TAKES_SCREENSHOT, true);
                dc.setCapability("appActivity", "com.nesine.ui.taboutside.splash.MainActivityDefault");
                driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), dc);
                break;
            case "iOS":
                //some code
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + device);
        }
        return driver;
    }
}
