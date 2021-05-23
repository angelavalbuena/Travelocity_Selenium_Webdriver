package com.travelocity.bootcamp.tasks;

import com.google.common.collect.Ordering;
import com.travelocity.bootcamp.pages.FlightsSearch;
import org.openqa.selenium.WebDriver;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

public class FlightSearchActions extends FlightsSearch {

    private final static Logger LOGGER = Logger.getLogger("com.automation.training.pages.FlightSearchActions");

    public FlightSearchActions(WebDriver driver) {
        super(driver);
    }

    /***
     * Method used to iterate through the list of available results flights options and verify presence of Flight details
     */
    public void verifyInformationIntoFlightDetails(){
        waitUntilFlightListIsReady();
        locateElements(listFlightLocator).stream()
                .forEach(flight -> {
                    flight.click();
                    verifyFlightDetails();
                    closeFlightDetails();
                });
    }

    /***
     * Method to verify the presence of webElements on flight details page
     */
    private void verifyFlightDetails() {
        waitUntilFlightDetailsIsReady();
        assertThat(isDurationFlightDisplayed()).isEqualTo(true);
        assertThat(isSearchButtonDisplayed()).isEqualTo(true);
        assertThat(isFlightDetailDisplayed()).isEqualTo(true);
    }

    /***
     * Method to verify that a list of flight results is sorted by duration (Shortest)
     */
    public void verifyListOrderByDuration(){
        waitUntilFlightListIsReady();
        verifySortedList();
        assertThat(Ordering.natural().isOrdered(hours)).isEqualTo(true);
        LOGGER.log(Level.INFO, "The list is ordered");
    }
}