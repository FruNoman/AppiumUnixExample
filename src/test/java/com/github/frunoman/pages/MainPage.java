package com.github.frunoman.pages;

import com.github.frunoman.utils.Categories;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class MainPage extends BasePage{

    @AndroidFindBy(xpath = "//*[@resource-id='ua.com.rozetka.shop:id/rl_background'][5]")
//    @FindBy(css = "a.header-topline__user-link")
    private RemoteWebElement personalAreaButton;


    public MainPage(WebDriver driver) {
        super(driver);
    }



    @Step("Щелкнуть по 'Личные данные'")
    public void clickOnPersonalArea(){
        personalAreaButton.click();
    }



}
