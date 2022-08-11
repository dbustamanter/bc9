package aut.testplan.sprint;

import framework.engine.selenium.DriverFactory;
import io.cucumber.java8.Scenario;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.qameta.allure.Allure;
import lombok.extern.apachecommons.CommonsLog;
import org.junit.runner.RunWith;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "io.qameta.allure.cucumber5jvm.AllureCucumber5Jvm"},
        glue = {"aut.testcreation.steps","aut.testplan.sprint"},
        tags = {"@bc9"},
        features = {"src/test/java/aut/testcreation/features"})
@CommonsLog
public class GoogleTestRunner {

    public static WebDriver driver;
    private static DriverFactory driverFactory;

    public static void setUp(){
        driverFactory = new DriverFactory();
        driver = driverFactory.createWebDriver();
        if (driver != null) {
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
            driver.manage().window().maximize();
        }
    }

    public static void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);;
                InputStream targetStream = new FileInputStream(screenshot);
                System.setProperty("allure.results.directory", "build/allure-results");
                Allure.addAttachment("Screenshot on fail", "image/png", targetStream, "png");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        driver.manage().deleteAllCookies();
        if (driver != null) {
            driver.close();
        }
    }
}
