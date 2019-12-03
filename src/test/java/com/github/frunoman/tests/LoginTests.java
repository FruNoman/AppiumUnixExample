package com.github.frunoman.tests;

import com.github.frunoman.pages.MainPage;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;


public class LoginTests extends BaseTest {


    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Базовый вход в личный кабинет")
    public void baseLoginTest() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnPersonalArea();
        Thread.sleep(10000);
    }

    @Test
    public void some(){

    }



}
