package aut.testcreation.testcases;

import aut.engine.selenium.DriverFactory;
import aut.engine.selenium.SeleniumTestBase;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static aut.engine.utils.Constants.BASE_URL_AUT;


public class TestGoogle extends SeleniumTestBase{

    WebDriver driver;

    @Test
    @Description("Test para practicar selenium en google")
    public void testGoogle() throws InterruptedException {
        driver = DriverFactory.getDriver();
        driver.navigate().to("https://www.google.com/");
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();

        //respuestas
        String url= driver.getCurrentUrl();
        String title = driver.getTitle();
        String pageSource = driver.getPageSource();
        String handle = driver.getWindowHandle();

        //find by
        //driver.findElement(By.className("gLFyf").sendKeys("zapatos"));

        driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/input")).sendKeys("zapatos");
        driver.findElement(By.name("btnK")).click();
        Thread.sleep(5000);
        // driver.close(); //cerrar todas las pestañas del navegador
        driver.quit(); //cerrar ventanas de forma segura

        Assertions.assertTrue(true);
    }
}
