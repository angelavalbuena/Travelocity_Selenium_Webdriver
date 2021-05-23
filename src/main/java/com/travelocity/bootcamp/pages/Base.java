package com.travelocity.bootcamp.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;


/***
 * Base class that contains all the basic methods to be reused on the different children pages classes. It also
 * contains the definition of Waits and driver manager.
 */
public class Base {


    private WebDriver driver;
    private WebDriverWait wait;
    
    public Base(WebDriver driver) {
        this.driver = driver;
    }

    /***
     * Driver manager to set the driver for the specific browser
     * @param browser parameter used to indicate the browser to be used
     * @param url base url to the travelocity project
     * @return
     */
    @Parameters({"browser", "url"})
    public WebDriver driverConnection(String browser, String url){
        switch (browser){
            
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chromedriver.exe");
                driver = new ChromeDriver();
                wait = new WebDriverWait(driver, 5);
                break;
            default:
                break;
        }
        return driver;
    }


    public List<WebElement> locateElements(By locator){
        return driver.findElements(locator);
    }

    /***
     * General fluent Wait definition for WebElement Locators
     * @param elementLocator
     * @param timeout
     * @return
     */
    public WebElement waitUntilElementIsPresentAndVisible (final By elementLocator, int timeout){

        Wait wait = new FluentWait(driver)
                .withTimeout(timeout, TimeUnit.SECONDS)
                .pollingEvery(2, TimeUnit.SECONDS)
                .ignoring(org.openqa.selenium.NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);

        WebElement element = (WebElement) wait.until(new Function<WebDriver, WebElement>(){

            @Override
            public WebElement apply(WebDriver webDriver){
                return webDriver.findElement(elementLocator);

            }

        });
        return element;
    }

    public void sendText(By locator, String value) {
        waitUntilElementIsPresentAndVisible(locator, 20000).sendKeys(value);
    }

    public void clickElement(By locator) {
        waitUntilElementIsPresentAndVisible(locator,20000).click();
    }

    public WebDriverWait waitFor(){
        return  new WebDriverWait(driver, 20);
    }

    @Parameters({"url"})
    public void visit (String url){
        driver.get(url);
    }

}
