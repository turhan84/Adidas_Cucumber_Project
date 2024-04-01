package com.adidas.pages;

import com.adidas.utilities.BrowserUtils;
import com.adidas.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage {
    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));

    public CartPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    // @FindBy(id = "tbodyid")
    //public List<WebElement> listOfAddedItems;

    @FindBy(className = "success")
    public List<WebElement> listOfAddedItems;

    // @FindBy(className = "table-responsive")
    // public List<WebElement> listOfAddedItems;

    @FindBy(xpath = "//td/a")
    public List<WebElement> listOfAddedItemsNumber;


    // @FindBy(xpath = "//tbody[@id='tbodyid']//a")
    // public List<WebElement> listOfAddedItems;

    @FindBy(id = "totalp")
    public WebElement totalPurchase;

    @FindBy(css = "button[class='btn btn-success']")
    public WebElement placeOrderBtn;


    @FindBy(id = "name")
    public WebElement placeOrderName;
    @FindBy(id = "country")
    public WebElement placeOrderCountry;
    @FindBy(id = "city")
    public WebElement placeOrderCity;
    @FindBy(id = "card")
    public WebElement placeOrderCard;
    @FindBy(id = "month")
    public WebElement placeOrderMonth;
    @FindBy(id = "year")
    public WebElement placeOrderYear;

    //button[text()='Purchase']

    @FindBy(xpath = "//button[text()='Purchase']")
    public WebElement purchaseBtn;

    @FindBy(css = "button[class='confirm btn btn-lg btn-primary']")
// css-> button[class="confirm btn btn-lg btn-primary"]
    public WebElement confirmBtn;

    @FindBy(xpath = "//p[@class='lead text-muted ']")
    public WebElement orderInfo;


    @FindBy(xpath = "//a[text()='Delete']")
    public WebElement deleteBtn;


    public void deleteItems(String item) {

        //Solution 1


        for (WebElement each : listOfAddedItemsNumber) {


            int index = 0;
            for (WebElement eachItem : listOfAddedItems) {
                //  System.out.println("listOfAddedItems.size() = " + listOfAddedItems.size());

                index++;
                if (listOfAddedItems.size() == 1) {
                    index = 2;
                }

                if (eachItem.getText().contains(item)) {

                    String xpath = "(//a[text()='Delete'])" + "[" + index + "]";
                    //String xpath = "((//td/a))" + "[" + index + "]";

                    Driver.getDriver().findElement(By.xpath(xpath)).click();

                    //TODO -> i can not found for wait method check for it

                    wait.until(ExpectedConditions.urlContains("cart.html#"));
                    //BrowserUtils.sleep(5);
                    break;
                }
            }
        }


        //Solution 2

      /*  List<WebElement> titleElements = Driver.getDriver().findElements(By.xpath("//tbody[@id='tbodyid']/tr/td[2]"));

        for (WebElement titleElement : titleElements) {
            if (titleElement.getText().equals(item)) {

                WebElement parentRow = (WebElement) ((JavascriptExecutor) Driver.getDriver()).executeScript(
                        "return arguments[0].parentNode;", titleElement);

                WebElement deleteLink = parentRow.findElement(By.xpath(".//a[contains(text(),'Delete')]"));

                deleteLink.click();

                break;
            }
        }
*/

//=================================================================
/*

        for (int index = 0; index < listOfAddedItems.size(); index++) {
            WebElement eachItem = listOfAddedItems.get(index);

            if (eachItem.getText().contains(item)) {

                String xpath = "(//a[text()='Delete'])[" + (index+1) + "]";

                Driver.getDriver().findElement(By.xpath(xpath)).click();

                wait.until(ExpectedConditions.urlContains("cart.html#"));

                break;
            }
        }
*/


    }


    public int calculateTotal() {

//BrowserUtils.sleep(2);
        int sum = 0;

        for (WebElement eachItem : listOfAddedItems) {
            String[] parts = eachItem.getText().split(" ");

            for (String each : parts) {
                try {
                    int number = Integer.parseInt(each);
                    sum += number;
                } catch (NumberFormatException e) {
                }
            }

        }

        return sum;

    }


    public void idAndAmount() {
        String[] lines = orderInfo.getText().split("\n");


        String idLine = null;
        String amountLine = null;
        for (String eachLine : lines) {
            if (eachLine.startsWith("Id:")) {
                idLine = eachLine;
            } else if (eachLine.startsWith("Amount:")) {
                amountLine = eachLine;
            }
        }

        String id = idLine.split(": ")[1];
        String amount = amountLine.split(": ")[1].split(" ")[0];

        System.out.println("Purchase ID: " + id);
        System.out.println("Purchase Amount: " + amount + " USD");

    }


    public static String amount() {
        String[] lines = new CartPage().orderInfo.getText().split("\n");

        String amountLine = null;
        for (String eachLine : lines) {
            if (eachLine.startsWith("Amount:")) {
                amountLine = eachLine;
            }
        }

        String amount = amountLine.split(": ")[1].split(" ")[0];

        return "Amount: " + amount + " USD";
    }

}






