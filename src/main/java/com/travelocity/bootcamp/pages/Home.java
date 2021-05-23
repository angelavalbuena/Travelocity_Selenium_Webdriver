package com.travelocity.bootcamp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

/***
 * Page class that controls all the actions and webElements present on the Home travelocity section.
 */
public class Home extends Base{

    private WebDriver driver;

    public Home(WebDriver driver){
        super(driver);
        this.driver= driver;
    }

    By flightsLinkLocator = By.cssSelector("[aria-controls='wizard-flight-pwa']");
    By roundTripLocator = By.cssSelector("a[href='?flightType=roundtrip']");
    By departingCityLocator = By.cssSelector("button[data-stid='location-field-leg1-origin-menu-trigger']");
    By departingCityTextLocator = By.id("location-field-leg1-origin");
    By departingCityOptionLocator = By.cssSelector("[data-stid='location-field-leg1-origin-result-item-button']:first-child");
    By returningCityLocator = By.cssSelector("button[data-stid='location-field-leg1-destination-menu-trigger']");
    By returningCityTextLocator= By.id("location-field-leg1-destination");
    By returningCityOptionLocator = By.cssSelector("[data-stid='location-field-leg1-destination-result-item-button']:first-child");
    By departingDayLocator = By.cssSelector("[class = 'uitk-date-picker-month-name uitk-type-medium']");
    By returningDayLocator = By.cssSelector("[class = 'uitk-date-picker-month-name uitk-type-medium']");
    By nextMonthDatePickerLocator = By.cssSelector("button[data-stid='date-picker-paging']:nth-of-type(2)");
    By doneButtonLocator = By.cssSelector("[data-stid='apply-date-picker']");
    By departingButtonLocator = By.cssSelector("button[aria-label*='Departing']");
    By returningButtonLocator = By.cssSelector("button[aria-label*='Returning']");
    By searchButtonLocator = By.cssSelector("button[data-testid='submit-button']");


    public void navigateFlights(){
        clickElement(flightsLinkLocator);
        clickElement(roundTripLocator);
    }

    public void typeFromTo(String returningCity, String departingCity){

        clickElement(departingCityLocator);
        sendText(departingCityTextLocator,returningCity);
        clickElement(departingCityTextLocator);
        clickElement(departingCityOptionLocator);

        clickElement(returningCityLocator);
        sendText(returningCityTextLocator,departingCity);
        clickElement(returningCityTextLocator);
        clickElement(returningCityOptionLocator);
    }

    /***
     * Method to select dates and interact with the date picker
     * @param departingYear
     * @param departingMonth
     * @param departingDay
     * @param returningYear
     * @param returningMonth
     * @param returningDay
     */
    public void selectDates(String departingYear, String departingMonth, String departingDay, String returningYear, String returningMonth, String returningDay) {
        //It is selected the departing field
        clickElement(departingButtonLocator);
        boolean monthFound1 = false;
        boolean monthFound2 = false;

        // Cycle to find the departing month.
        String deMonth = getMonth(departingMonth);
        do {
            List<WebElement> calendarMonth = locateElements(departingDayLocator);
            String month1 = calendarMonth.get(0).getText();
            String month2 = calendarMonth.get(1).getText();

            if(month1.equals(deMonth + " " + departingYear) || month2.equals(deMonth + " " + departingYear)) {
                monthFound1 = true;
            } else {
                //Click on next button
                clickElement(nextMonthDatePickerLocator);
            }
        } while (monthFound1 == false);

        //It is selected the departure day of the trip.
        String monthSubstring1 = deMonth.substring(0,3);
        clickElement(By.cssSelector("[aria-label='"+monthSubstring1+ " "+departingDay+", "+departingYear+"']"));
        clickElement(doneButtonLocator);

        // Returning field is selected
        clickElement(returningButtonLocator);
        String reMonth = getMonth(returningMonth);
        do {
            List<WebElement> calendarMonth = locateElements(returningDayLocator);
            String month1 = calendarMonth.get(0).getText();
            String month2 = calendarMonth.get(1).getText();

            if(month1.equals(reMonth + " " + returningYear) || month2.equals(reMonth + " " + returningYear)) {
                monthFound2 = true;
            } else {
                //Click on the next button
               clickElement(nextMonthDatePickerLocator);
            }
        } while (monthFound2 == false);
        //It is selected the returning date of the trip.
        String monthSubstring2 = reMonth.substring(0,3);
        clickElement(By.cssSelector("[aria-label='"+monthSubstring2+ " "+returningDay+", "+returningYear+"']"));
        clickElement(doneButtonLocator);
    }

    public FlightsSearch searchOptions(){
        clickElement(searchButtonLocator);
        return new FlightsSearch(driver);
    }

    /***
     * Map the months in int format to string format
     * @param departingMonth
     * @return
     */
    private static String getMonth(String departingMonth) {
        switch (departingMonth) {
            case "1":
                return "January";
            case "2":
                return "February";

            case "3":
                return "March";

            case "4":
                return "April";

            case "5":
                return "May";

            case "6":
                return "June";

            case "7":
                return "July";

            case "8":
                return "August";

            case "9":
                return "September";

            case "10":
                return "October";

            case "11":
                return "November";

            case "12":
                return "December";
        }
        return null;
    }
}
