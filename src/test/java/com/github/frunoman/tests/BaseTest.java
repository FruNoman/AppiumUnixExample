package com.github.frunoman.tests;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class BaseTest {
    protected WebDriver driver;
    protected AppiumServiceBuilder builder;
    protected AppiumDriverLocalService service;
    protected DesiredCapabilities serviceCaps;

    @Parameters({"browser", "udid"})
    @BeforeClass(description = "Инициализация тестового окружения")
    public void beforeSuite(@Optional String browser, @Optional String udid) throws MalformedURLException {

            serviceCaps = new DesiredCapabilities();
            serviceCaps.setCapability("noReset", "false");

            builder = new AppiumServiceBuilder();
            builder.withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"));
            builder.withIPAddress("0.0.0.0");
            builder.usingAnyFreePort();
            builder.withCapabilities(serviceCaps);
            builder.withStartUpTimeOut(60, TimeUnit.SECONDS);
            builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);

            service = AppiumDriverLocalService.buildService(builder);

            service.start();

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
            driver = new AndroidDriver(service.getUrl(), capabilities);
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        } else {
            System.setProperty("webdriver.gecko.driver", this.getClass().getClassLoader().getResource("geckodriver").getPath());
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.get("http://rozetka.com.ua/");

        }


    }

    @AfterClass(description = "Закрытие тестового окружения")
    public void afterSuite() throws InterruptedException {
        driver.quit();
        service.stop();
        Thread.sleep(3000);
    }


    public boolean checkIfServerIsRunnning(int port) {

        boolean isServerRunning = false;
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.close();
        } catch (IOException e) {
            //If control comes here, then it means that the port is in use
            isServerRunning = true;
        } finally {
            serverSocket = null;
        }
        return isServerRunning;
    }

}
