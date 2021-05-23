package com.travelocity.bootcamp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Thread.sleep;

/***
 * Page class that controls all the actions and webElements present on the Flight Search travelocity section.
 */

public class FlightsSearch extends Base{

    private WebDriver driver;
    private final static Logger LOGGER = Logger.getLogger("com.automation.training.pages.FlightsSearch");

    Utils utils = new Utils();

    protected By listFlightLocator = By.cssSelector("ul[data-test-id='listings'] li > div");
    By contentFlightDetailLocator = By.xpath("//div[@data-test-id='listing-details-and-fares']/section");
    By durationFlightLocator = By.cssSelector(".uitk-type-300.uitk-flex-item.uitk-text-emphasis-theme");
    By flightDetailLocator = By.cssSelector("[class='uitk-flex uitk-flex-column uitk-flex-justify-content-space-between link-container']");
    By searchButtonLocator = By.cssSelector("div button[data-test-id='select-button']");
    By closeButtonLocator = By.cssSelector("[data-icon='tool-close']");
    By loaderFlightListLocator = By.cssSelector(".loader-spacing .uitk-loading-bar");
    By flightDetailsOpenedLocator = By.id("uitk-live-announce");
    By sortByListOptionLocator = By.cssSelector("[data-test-id='sortDropdown-option']");
    By sortByButtonLocator = By.cssSelector("[data-test-id='sortBar']");
    By selectFirstOptionFlightLocator =By.cssSelector("[data-test-id='offer-listing']:first-child");
    By selectReturningFlightLocator = By.cssSelector("[data-test-id='offer-listing']:nth-of-type(3)");
    By searchHotelsLocator =By.cssSelector("[data-test-id='xsellAddHotelNow']");
    By sortByListLocator = By.xpath("//span[@data-test-id='departure-time']");

    public List<Integer> hours = new LinkedList();

    public FlightsSearch(WebDriver driver) {
        super(driver);
        this.driver=driver;
    }

    public void waitUntilFlightListIsReady() {
        waitFor().until(ExpectedConditions.invisibilityOfElementLocated(loaderFlightListLocator));
    }

    public void waitUntilFlightDetailsIsReady(){
        waitFor().until(ExpectedConditions.textToBePresentInElementLocated(flightDetailsOpenedLocator,"Flight details has opened"));
    }

    /***
     * Boolean Methods to be used to verify that web elements are present into the Flight Search travelocity section
     * @return
     */

    public boolean isDurationFlightDisplayed(){
       return driver.findElement(durationFlightLocator).isDisplayed();
   }

    public boolean isFlightDetailDisplayed(){
        return driver.findElement(flightDetailLocator).isDisplayed();
    }

    public boolean isSearchButtonDisplayed(){
        return driver.findElement(searchButtonLocator).isDisplayed();
    }

    public void waitUntilSearchButtonIsReady(){
        waitFor().until(ExpectedConditions.elementToBeClickable(searchButtonLocator));
    }

    public void closeFlightDetails() {
        waitUntilSearchButtonIsReady();
        clickElement(closeButtonLocator);
    }

    public void selectSortByOption(){
        waitUntilFlightListIsReady();
        clickElement(sortByButtonLocator);
        List<WebElement> resultList = driver.findElements(sortByListOptionLocator);
        resultList.get(2).click();
        clickElement(sortByButtonLocator);
        waitUntilFlightListIsReady();
    }

    /***
     * Method to extract and order by time from the list of flight results using the methods described on Utils class
     * @return
     */

    public List<Integer> verifySortedList(){

        List<WebElement> elements = driver.findElements(sortByListLocator);
        for(WebElement index:elements){
            String[] hour = utils.extractHours(index.getText());
            String hourA = utils.convertTypeTime(hour[0].trim());
            String hourB = utils.convertTypeTime(hour[1].trim());
            hours.add(utils.getMinutes(hourB) - utils.getMinutes(hourA));
        }
        return hours;
    }

    public void waitUntilFlightInformationIsReady() {
        waitFor().until(ExpectedConditions.invisibilityOfElementLocated(selectFirstOptionFlightLocator));
    }


    public void selectOptionFlight(){
        clickElement(selectFirstOptionFlightLocator);
        waitUntilSearchButtonIsReady();
        clickElement(searchButtonLocator);
    }
    public void selectReturningOptionFlight(){
        waitUntilFlightInformationIsReady();
        clickElement(selectReturningFlightLocator);
        waitUntilSearchButtonIsReady();
        clickElement(searchButtonLocator);
    }

    /***
     * method to evaluate if modal or pop up is shown, if so "No" option is selected
     */
    public void skipSearchHotelsModal(){
        try {
            if(driver.findElement(searchHotelsLocator).isDisplayed()){
            clickElement(searchHotelsLocator);
            }
        } catch (Exception e) {
            LOGGER.log(Level.INFO, "Search Hotel Modal not displayed");
        }
    }

    /***
     * Method to chain or connect the Flight Search page with the Flight Details page
     * @return
     */
    public FlightDetails switchTab(){
        ArrayList<String> tabs = new ArrayList<> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        return new FlightDetails(driver);
    }
}
