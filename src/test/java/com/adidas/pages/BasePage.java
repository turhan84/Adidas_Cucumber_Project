package com.adidas.pages;


import com.adidas.utilities.BrowserUtils;
import com.adidas.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public abstract class BasePage {

    @FindBy(css = "nav[class='navbar navbar-toggleable-md bg-inverse']")
    public List<WebElement> menuOptions;

    @FindBy(id = "nava")
    public WebElement homePageBtn;


    @FindBy(id = "cartur")
    public WebElement cartBtn;

    public void choseMenuOptions(String menuOpt){
        for (WebElement eachOption : menuOptions) {
            if(eachOption.getText().equalsIgnoreCase(menuOpt)){
                eachOption.click();
            }
        }
    }

    public BasePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

}


