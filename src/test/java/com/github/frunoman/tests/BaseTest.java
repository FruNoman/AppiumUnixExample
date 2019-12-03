package com.github.frunoman.tests;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class BaseTest {
    WebDriver driver;
    String Node = "http://172.22.89.63:4444/wd/hub";

    @Parameters({"browser", "udid"})
    @BeforeClass(description = "Инициализация тестового окружения")
    public void beforeSuite(@Optional String browser, @Optional String udid) throws MalformedURLException {
        if (browser != null) {
            if (browser.equals("firefox")) {
                System.setProperty("webdriver.gecko.driver", this.getClass().getClassLoader().getResource("geckodriver").getPath());
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                driver.get("http://rozetka.com.ua/");

            } else if (browser.equals("chrome")) {
                System.setProperty("webdriver.chrome.driver", this.getClass().getClassLoader().getResource("chromedriver").getPath());
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                driver.get("http://rozetka.com.ua/");
            }
        } else if (udid != null) {
            DesiredCapabilities capabilities = DesiredCapabilities.android();
            capabilities.setCapability(MobileCapabilityType.APP, getClass().getClassLoader().getResource("rozetka.apk").getPath());
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
            capabilities.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY, "ua.com.rozetka.shop.*");
            capabilities.setCapability(MobileCapabilityType.UDID, udid);
            capabilities.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT, new Random().nextInt(5672));
            capabilities.setCapability("platformName", "android");
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "device");
            capabilities.setCapability(MobileCapabilityType.FULL_RESET, false);
            driver = new AndroidDriver(new URL("http://0.0.0.0:4727/wd/hub"), capabilities);
        } else {
            System.setProperty("webdriver.gecko.driver", this.getClass().getClassLoader().getResource("geckodriver").getPath());
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.get("http://rozetka.com.ua/");

        }



    }

    @AfterClass(description = "Закрытие тестового окружения")
    public void afterSuite() {
        driver.quit();
    }

}
