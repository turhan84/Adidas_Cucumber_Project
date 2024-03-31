package com.adidas.step_definitions;

import com.adidas.pages.CartPage;
import com.adidas.pages.HomePage;
import com.adidas.pages.ProductInfoPage;
import com.adidas.utilities.BrowserUtils;
import com.adidas.utilities.Driver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.Month;
import java.time.Year;
import java.util.Map;

public class Purchase_StepDefs {

    HomePage homePage = new HomePage();
    ProductInfoPage infoPage = new ProductInfoPage();

    CartPage cartPage = new CartPage();

    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));

    @When("User navigates to the {string} category")
    public void user_navigates_to_the_category(String option) {


        homePage.homePageBtn.click();
        //  wait.until(ExpectedConditions.elementToBeClickable(homePage.laptopsBtn));
        homePage.laptopsBtn.click();

    }

    @When("User adds {string} to the cart and accepts the pop-up confirmation")
    public void user_adds_to_the_cart_and_accepts_the_pop_up_confirmation(String item) {
        //homePage.selectItem(item);
        //homePage.SonyVaioI5.click();


        homePage.choseItem(item);

        wait.until(ExpectedConditions.urlContains("prod.html"));

        infoPage.addToCartBtn.click();

        wait.until(ExpectedConditions.alertIsPresent());

        //JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        //js.executeScript("window.alert = function(msg){}; window.confirm = function(msg){ return true; };");
        Alert alert = Driver.getDriver().switchTo().alert();
        //String alertMessage= Driver.getDriver().switchTo().alert().getText();
        alert.accept();


    }

    @When("User navigates to the {string} page")
    public void user_navigates_to_the_page(String cartBtn) {
        homePage.cartBtn.click();

    }

    @When("User deletes {string} from the cart")
    public void user_deletes_from_the_cart(String item) {

        wait.until(ExpectedConditions.visibilityOf(cartPage.totalPurchase));
        cartPage.deleteItems(item);

        //  wait.until(ExpectedConditions.visibilityOfAllElements(cartPage.listOfAddedItems));
         Driver.getDriver().navigate().refresh();


    }

    @When("User clicks on {string}")
    public void user_clicks_on(String option) {

        if (option.equals("Place order")) {
          //  BrowserUtils.waitForClickablility(cartPage.deleteBtn, 10);
            cartPage.placeOrderBtn.click();
            wait.until(ExpectedConditions.elementToBeClickable(cartPage.purchaseBtn));
        } else if (option.equals("Purchase")) {

            cartPage.purchaseBtn.click();
        } else if (option.equals("Ok")) {
            cartPage.confirmBtn.click();
        }

    }

    @Then("User fills in all the web form fields")
    public void user_fills_in_all_the_web_form_fields(Map<String, String> info) {

        wait.until(ExpectedConditions.elementToBeClickable(cartPage.placeOrderBtn));
        cartPage.placeOrderName.sendKeys(info.get("Name"));

        cartPage.placeOrderCountry.sendKeys(info.get("Country"));

        cartPage.placeOrderCity.sendKeys(info.get("City"));

        cartPage.placeOrderCard.sendKeys(info.get("Credit Card"));

        cartPage.placeOrderMonth.sendKeys(info.get("Month"));

        cartPage.placeOrderYear.sendKeys(info.get("Year"));

    }

    @Then("User should capture and log the purchase ID and Amount")
    public void user_should_capture_and_log_the_purchase_id_and_amount() {

        System.out.println("Order Info;\n" + cartPage.orderInfo.getText());

    }

    @Then("User should assert that the purchase amount equals expected")
    public void user_should_assert_that_the_purchase_amount_equals_expected() {

        String expectedAmount = "Amount: 790 USD";

//        String amout = Character.toString(cartPage.calculateTotal());
//        amout = "Amount: " + amout + " USD";
//        System.out.println(amout);
        Assert.assertTrue(cartPage.orderInfo.getText().contains(expectedAmount));

    }


}
