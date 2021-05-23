package com.travelocity.bootcamp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;


/***
 * Page class that controls all the actions and webElements present on the Flight Details travelocity section.
 */
public class FlightDetails extends  Base{

    private WebDriver driver;

    By checkoutBtnLocator = By.cssSelector("[data-test-id='goto-checkout-button']");
    By loaderCheckoutLocator = By.cssSelector("[data-test-id='summary-skeleton']");
    By tripTotalPriceLocator = By.cssSelector("[data-test-id='trip-total']");
    By departureReturnLocator = By.cssSelector("[class='uitk-flex uitk-flex-column uitk-flex-gap-twelve']");
    By priceGuaranteeLocator = By.xpath("//h2[@class='uitk-messaging-card-section-header'][contains(text(),'Stay flexible with no change fees')]");


    public FlightDetails(WebDriver driver) {
        super(driver);
        this.driver=driver;
    }

    /***
     * Boolean Methods to be used to verify that web elements are present into the Flight Details travelocity section
     * @return
     */
    public boolean isTripTotalPriceDisplayed(){
        return driver.findElement(tripTotalPriceLocator).isDisplayed();
    }

    public boolean isDepartureAndReturnLocatorDisplayed(){
        return driver.findElement(departureReturnLocator).isDisplayed();
    }

    public boolean isPriceGuaranteeDisplayed(){
        return driver.findElement(priceGuaranteeLocator).isDisplayed();
    }

    public void waitUntilCheckoutReady() {
        waitFor().until(ExpectedConditions.invisibilityOfElementLocated(loaderCheckoutLocator));
    }

    /***
     * Method to chain or connect the Flight details page with the Checkout page
     * @return
     */
    public Checkout clickCheckout(){
        waitUntilCheckoutReady();
        clickElement(checkoutBtnLocator);
        return new Checkout(driver);
    }
}
