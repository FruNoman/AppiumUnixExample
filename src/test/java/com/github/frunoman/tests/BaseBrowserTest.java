package com.github.frunoman.tests;


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
import java.util.concurrent.TimeUnit;


public class BaseBrowserTest {
    WebDriver driver;
    String Node = "http://172.22.89.63:4444/wd/hub";

    @Parameters("browser")
    @BeforeClass
    public void beforeSuite(@Optional String browser) {
        if(browser!=null) {
            if (browser.equals("firefox")) {
                System.setProperty("webdriver.gecko.driver", this.getClass().getClassLoader().getResource("geckodriver").getPath());
                driver = new FirefoxDriver();

            } else if (browser.equals("chrome")) {
                System.setProperty("webdriver.chrome.driver", this.getClass().getClassLoader().getResource("chromedriver").getPath());
                driver = new ChromeDriver();
            }
        }else {
            System.setProperty("webdriver.gecko.driver", this.getClass().getClassLoader().getResource("geckodriver").getPath());
            driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://rozetka.com.ua/");
    }

    @AfterClass
    public void afterSuite() {
        driver.close();
    }

}
