package com.travelocity.bootcamp.tests;

import com.travelocity.bootcamp.pages.Home;
import com.travelocity.bootcamp.tasks.CheckoutActions;
import com.travelocity.bootcamp.tasks.FlightDetailsActions;
import com.travelocity.bootcamp.tasks.FlightSearchActions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class Tests {

    private WebDriver driver;
    Home home;
    FlightSearchActions flightActions;
    FlightDetailsActions flightDetails;
    CheckoutActions checkout;

    @BeforeMethod
    @Parameters({"browser","url"})
    public void setUp(String browser, String url){
        home = new Home(driver);
        driver = home.driverConnection(browser, url);
        flightActions =new FlightSearchActions(driver);
        flightDetails = new FlightDetailsActions(driver);
        checkout = new CheckoutActions(driver);
        home.visit(url);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @DataProvider(name = "bookingInfo")
    public Object [][] getData(){
        return new Object[][]{
                {"LAS", "LAX","2021","7", "20","2021","9","25"}
        };
    }



    @Test(dataProvider = "bookingInfo")
    public void bookFlight(String departureCity, String destinationCity, String departingYear, String departingMonth, String departingDay,String returningYear, String returningMonth, String returningDay){
        home.navigateFlights();
        home.typeFromTo(departureCity, destinationCity);
        home.selectDates(departingYear,departingMonth,departingDay,returningYear,returningMonth,returningDay);
        home.searchOptions();
        flightActions.verifyInformationIntoFlightDetails();
        flightActions.selectSortByOption();
        flightActions.verifyListOrderByDuration();
        flightActions.selectOptionFlight();
        flightActions.selectReturningOptionFlight();
        flightActions.skipSearchHotelsModal();
        flightActions.switchTab();
        flightDetails.verifyCheckoutDetails();
        flightDetails.clickCheckout();
        checkout.verifyCheckoutDetails();

    }
}
