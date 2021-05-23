package com.travelocity.bootcamp.tasks;

import com.travelocity.bootcamp.pages.Checkout;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CheckoutActions extends Checkout {

    public CheckoutActions(WebDriver driver) {
        super(driver);
    }

    /***
     * Method used to assert presence of webElements on Checkout page
     */
    public void verifyCheckoutDetails() {
        waitUntilSecureBookingIsDisplayed();
        assertThat(isFirstnameDisplayed()).isEqualTo(true);
        assertThat(isLastNameDisplayed()).isEqualTo(true);
        assertThat(isMaleGenderDisplayed()).isEqualTo(true);
        assertThat(isFemaleGenderDisplayed()).isEqualTo(true);
        assertThat(isCountryPaymentDisplayed()).isEqualTo(true);
        assertThat(IsPhoneNumberDisplayed()).isEqualTo(true);
    }
}
