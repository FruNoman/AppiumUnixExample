package com.github.frunoman.tests;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.io.File;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class AppiumBaseTest {
    protected RemoteWebDriver driver;
    protected AppiumServiceBuilder builder;
    protected static AppiumDriverLocalService service;

    @BeforeSuite(description = "Initialize appium server")
    public void beforeSuite() throws Exception {

        if (service == null) {
            builder = new AppiumServiceBuilder();
            builder.withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"));
            builder.withIPAddress("0.0.0.0");
            builder.usingPort(4727);
            builder.withStartUpTimeOut(90, TimeUnit.SECONDS);
            builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
            service = AppiumDriverLocalService.buildService(builder);
            service.start();
        }

    }

    @Parameters({"browser", "udid"})
    @BeforeClass(description = "Initialize webdriver")
    public void beforeClass(@Optional String browser, @Optional String udid) {
//        if (browser != null) {
//            if (browser.equals("firefox")) {
//                System.setProperty("webdriver.gecko.driver", this.getClass().getClassLoader().getResource("geckodriver").getPath());
//                driver = new FirefoxDriver();
//                driver.manage().window().maximize();
//                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//            } else if (browser.equals("chrome")) {
//                System.setProperty("webdriver.chrome.driver", this.getClass().getClassLoader().getResource("chromedriver").getPath());
//                driver = new ChromeDriver();
//                driver.manage().window().maximize();
//                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//            }
//        } else


        if (udid != null) {
            DesiredCapabilities capabilities = DesiredCapabilities.android();
            capabilities.setCapability(MobileCapabilityType.APP, getClass().getClassLoader().getResource("rozetka.apk").getPath());
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
            capabilities.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY, "ua.com.rozetka.shop.*");
            capabilities.setCapability(MobileCapabilityType.UDID, udid);
            capabilities.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT, new Random().nextInt(5672));
            capabilities.setCapability("platformName", "android");
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "device");
            capabilities.setCapability(MobileCapabilityType.FULL_RESET, false);
            driver = new AndroidDriver(service.getUrl(), capabilities);
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        }
//            else {
//            System.setProperty("webdriver.gecko.driver", this.getClass().getClassLoader().getResource("geckodriver").getPath());
//        }
    }

    @BeforeMethod
    public void beforeEveryTest() {


    }

    @AfterMethod
    public void afterEveryTest() {
        if (driver instanceof AndroidDriver) {
            ((AndroidDriver) driver).resetApp();
        }
    }

    @AfterClass(description = "Close webdriver")
    public void afterClass() {
        driver.quit();
    }

    @AfterSuite(description = "Stop appium server")
    public void afterSuite() {
        service.stop();
    }


}
