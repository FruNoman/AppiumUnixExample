package com.github.frunoman.pages;

import com.github.frunoman.utils.Categories;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MainPage extends BasePage{

    private WebDriver driver;
    @FindBy(css = "ul.menu-categories>li>a")
    private List<WebElement> mainCategories;

    public MainPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }


    @Step("Щелкнуть по категории")
    public void clickOnCategory(Categories category) {
//        waitForElements(mainCategories);
        for(WebElement element:mainCategories){
            if(element.getText().equals(category.toString())){
                element.click();
                break;
            }
        }
    }



}
