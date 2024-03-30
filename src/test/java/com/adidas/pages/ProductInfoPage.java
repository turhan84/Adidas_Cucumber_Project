package com.adidas.pages;

import com.adidas.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductInfoPage {
    public ProductInfoPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(linkText = "Add to cart")
    public WebElement addToCartBtn;


    @FindBy(xpath = "//a[text()='Add to cart']")
    public WebElement addToCartBtn_2;
}
