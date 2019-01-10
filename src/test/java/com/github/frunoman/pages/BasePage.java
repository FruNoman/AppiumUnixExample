package com.github.frunoman.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasePage {
    private WebDriver driver;

    public BasePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;

    }

    public void waitForElement(WebElement element) {
        new WebDriverWait(driver, 10000).until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementStaleness(WebElement element) {
        new WebDriverWait(driver, 10000).until(ExpectedConditions.stalenessOf(element));
    }

    public void waitForElementIsClickable(WebElement element){
        new WebDriverWait(driver,10000).until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForElements(List<WebElement> elements){
        new WebDriverWait(driver,10000).until(ExpectedConditions.visibilityOfAllElements(elements));
    }
}
