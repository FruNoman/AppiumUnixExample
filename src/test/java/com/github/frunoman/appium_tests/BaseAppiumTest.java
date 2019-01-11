package com.github.frunoman.appium_tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

public class BaseAppiumTest {
    AndroidDriver driver;

    @BeforeClass
    @Parameters("udid")
    public void beforeClass(@Optional String udid) throws MalformedURLException {
        DesiredCapabilities capabilities = DesiredCapabilities.android();
        capabilities.setCapability(MobileCapabilityType.APP,getClass().getClassLoader().getResource("rozetka.apk").getPath());
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
        capabilities.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY,"ua.com.rozetka.shop.*");
        capabilities.setCapability(MobileCapabilityType.UDID,"WWJNKFORFYSK8PPN");
        capabilities.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT,new Random().nextInt(9999));
        capabilities.setCapability("platformName","android");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"device");
        capabilities.setCapability("fastReset",true);
        capabilities.setCapability(MobileCapabilityType.FULL_RESET,false);
        driver = new AndroidDriver(new URL("http://0.0.0.0:4727/wd/hub"),capabilities);
    }

    @AfterClass
    public void afterClass(){
        driver.closeApp();
    }
}
