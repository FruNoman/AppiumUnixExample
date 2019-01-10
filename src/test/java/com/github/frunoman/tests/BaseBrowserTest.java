package com.github.frunoman.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;


public class BaseBrowserTest {
    WebDriver driver;

    @Parameters("browser")
    @BeforeClass
    public void beforeSuite(String browser) {
        if (browser.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", this.getClass().getClassLoader().getResource("geckodriver").getPath());
            driver = new FirefoxDriver();
        } else if (browser.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", this.getClass().getClassLoader().getResource("chromedriver").getPath());
            driver = new ChromeDriver();
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://google.com");
    }

    @AfterClass
    public void afterSuite() {
        driver.close();
    }

}
