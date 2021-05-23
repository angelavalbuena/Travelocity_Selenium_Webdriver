package com.travelocity.bootcamp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

/***
 * Page class that controls all the actions and webElements present on the "Secure Booking" or Who's travelling travelocity section.
 */

public class Checkout extends Base{

    private WebDriver driver;

    By firstNameLocator = By.cssSelector("[id='firstname[0]']");
    By lastNameLocator = By.cssSelector("[id='lastname[0]']");
    By phoneNumberLocator = By.cssSelector("[id='phone-number[0]']");
    By maleGenderLocator = By.cssSelector("[type='radio'][value='MALE']");
    By femaleGenderLocator = By.cssSelector("[type='radio'][value='FEMALE']");
    By countryPaymentLocator = By.cssSelector("[name='country']");
    By checkoutHeaderLocator = By.cssSelector("[id='page-header']");


    public Checkout(WebDriver driver) {
        super(driver);
        this.driver=driver;
    }

    /***
     * Verifyy that Secure booking header is present.
     */
    public void waitUntilSecureBookingIsDisplayed(){
        waitFor().until(ExpectedConditions.visibilityOfElementLocated(checkoutHeaderLocator));
    }

    /***
     * Boolean Methods to be used to verify that web elements are present into the Who's travelling or checkout travelocity section
     * @return
     */
    public boolean isFirstnameDisplayed(){
        return driver.findElement(firstNameLocator).isDisplayed();
    }

    public boolean isLastNameDisplayed(){
        return driver.findElement(lastNameLocator).isDisplayed();
    }

    public boolean IsPhoneNumberDisplayed(){
        return driver.findElement(phoneNumberLocator).isDisplayed();
    }

    public boolean isMaleGenderDisplayed(){
        return driver.findElement(maleGenderLocator).isDisplayed();
    }

    public boolean isFemaleGenderDisplayed(){
        return driver.findElement(femaleGenderLocator).isDisplayed();
    }

    public boolean isCountryPaymentDisplayed(){
        return driver.findElement(countryPaymentLocator).isDisplayed();
    }

}
