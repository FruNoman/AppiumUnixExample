package com.github.frunoman.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class BasePage {
    protected WebDriver driver;


    public BasePage(WebDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
        this.driver = driver;
    }





}
