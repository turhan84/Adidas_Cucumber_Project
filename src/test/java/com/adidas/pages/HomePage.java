package com.adidas.pages;

import com.adidas.utilities.BrowserUtils;
import com.adidas.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.logging.XMLFormatter;

public class HomePage extends BasePage{
    public HomePage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(css = "div[class='list-group']")
    public List<WebElement> categoriesOpt;

    @FindBy(linkText = "Laptops")
    public WebElement laptopsBtn;

    @FindBy(xpath = "//a[text()='Laptops']")
    public WebElement laptopsBtn_2;



    @FindBy(xpath = "//div[@id='tbodyid']//a")
   public List<WebElement> items;

    @FindBy(linkText = "Sony vaio i5")
    public WebElement SonyVaioI5;






    public void categories(String option){

        for (WebElement eachElement : categoriesOpt) {
            if(eachElement.getText().equals(option)){
                eachElement.click();
            }
        }


    }
    
    public void selectItem(String item){
        for (WebElement eachItem : items) {
            if(eachItem.getText().contains(item)) {
                eachItem.click();
                break;
            }
        }
        
    }

    public void choseItem(String item){
        String ItemXpath = "//a[text()='" + item + "']";
        Driver.getDriver().findElement(By.xpath(ItemXpath)).click();
    }



}
