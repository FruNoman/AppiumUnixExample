package com.github.frunoman.tests;

import bsh.Remote;
import com.github.frunoman.pages.MainPage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.grid.common.GridRole;
import org.openqa.grid.common.RegistrationRequest;
import org.openqa.grid.internal.utils.SelfRegisteringRemote;
import org.openqa.grid.internal.utils.configuration.GridHubConfiguration;
import org.openqa.grid.internal.utils.configuration.GridNodeConfiguration;
import org.openqa.grid.web.Hub;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class SeleniumGridBaseTest {
    protected Hub gridServer;
    protected WebDriver driver;
    protected AppiumServiceBuilder builder;
    protected static AppiumDriverLocalService service;

    @BeforeSuite
    public void beforeSeleniumGrid() throws Exception {

        // START SELENIUMGRID

        GridHubConfiguration hubConfiguration = new GridHubConfiguration();
        hubConfiguration.role = "hub";
        hubConfiguration.host = "localhost";
        hubConfiguration.port = 4445;
        gridServer = new Hub(hubConfiguration);
        gridServer.start();

        // START APPIUM SERVER

        if (service == null) {
            builder = new AppiumServiceBuilder();
            builder.withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"));
            builder.withIPAddress("0.0.0.0");
            builder.usingPort(4729);
            builder.withStartUpTimeOut(90, TimeUnit.SECONDS);
            builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
            service = AppiumDriverLocalService.buildService(builder);
            service.start();
        }

        GridNodeConfiguration nodeConfiguration = new GridNodeConfiguration();
        nodeConfiguration.hub = "http://localhost:4445/grid/register";
        nodeConfiguration.host = InetAddress.getLocalHost().getHostAddress();
        nodeConfiguration.port = 4729;
        nodeConfiguration.hubHost = "172.22.89.63";
        nodeConfiguration.hubPort = 4445;
        nodeConfiguration.role = GridRole.NODE.name();

//
        MutableCapabilities capabilities = new MutableCapabilities();
        capabilities.setCapability("browserName", "Android");
//        capabilities.setCapability("platformVersion", "26");
        capabilities.setCapability("maxInstances", 1);
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("deviceName", "device");


        List<MutableCapabilities> caps = new ArrayList<>();
        caps.add(capabilities);
        nodeConfiguration.capabilities = caps;

        File file = new File("/home/dfrolov/IdeaProjects/AppiumUnixExample/src/main/resources/some.json");
        nodeConfiguration.nodeConfigFile = file.getPath();

        RegistrationRequest req = new RegistrationRequest(nodeConfiguration);
        req.getConfiguration();
        req.validate();
        RegistrationRequest.build(nodeConfiguration);

        SelfRegisteringRemote remote = new SelfRegisteringRemote(req);
        remote.startRegistrationProcess();


    }

    @Test
    public void some() throws InterruptedException, MalformedURLException {
        Thread.sleep(3000);

        DesiredCapabilities capabilities = DesiredCapabilities.android();
        capabilities.setCapability("browserName", "Android");
//        capabilities.setCapability("platformVersion", "26");
        capabilities.setCapability("maxInstances", 1);
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("deviceName", "device");
        capabilities.setCapability(MobileCapabilityType.APP, getClass().getClassLoader().getResource("rozetka.apk").getPath());
        capabilities.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY, "ua.com.rozetka.shop.*");
        capabilities.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT, new Random().nextInt(5672));
        capabilities.setCapability("newCommandTimeout", "120");
        capabilities.setCapability(MobileCapabilityType.FULL_RESET, false);

        WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4445/wd/hub/"), capabilities);

        Thread.sleep(1000);

        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnPersonalArea();
        Thread.sleep(10000);
    }

    @AfterSuite
    public void afterSuite() {
        gridServer.stop();
    }
}
