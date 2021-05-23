package com.travelocity.bootcamp.listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Listener implements ITestListener {

    private final static Logger LOGGER = Logger.getLogger("com.automation.training.pages.BasePage");
    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LOGGER.log(Level.INFO, "The test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LOGGER.log(Level.WARNING, "The test failed");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        LOGGER.log(Level.WARNING, "The test was skipped");

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        LOGGER.log(Level.WARNING, "The test assertion failed but execution continues");
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        LOGGER.log(Level.INFO, "Test Execution started");
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        LOGGER.log(Level.INFO, "Test Execution finished");

    }

}

