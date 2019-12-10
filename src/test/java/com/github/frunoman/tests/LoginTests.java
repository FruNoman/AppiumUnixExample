package com.github.frunoman.tests;

import com.github.frunoman.pages.MainPage;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;


public class LoginTests extends AppiumBaseTest {

    @Severity(SeverityLevel.CRITICAL)
    @Test()
    public void baseLoginTest() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnPersonalArea();
        Thread.sleep(10000);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test()
    public void baseLoginTest1() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnPersonalArea();
        Thread.sleep(10000);
    }
}
