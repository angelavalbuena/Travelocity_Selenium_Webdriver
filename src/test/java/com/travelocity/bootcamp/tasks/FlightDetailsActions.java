package com.travelocity.bootcamp.tasks;

import com.travelocity.bootcamp.pages.FlightDetails;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FlightDetailsActions extends FlightDetails {
    public FlightDetailsActions(WebDriver driver) {
        super(driver);
    }

    /***
     * Method used to assert presence of webElements on Flight details page
     */
    public void verifyCheckoutDetails() {
        waitUntilCheckoutReady();
        assertThat(isTripTotalPriceDisplayed()).isEqualTo(true);
        assertThat(isDepartureAndReturnLocatorDisplayed()).isEqualTo(true);
        assertThat(isPriceGuaranteeDisplayed()).isEqualTo(true);
    }
}
