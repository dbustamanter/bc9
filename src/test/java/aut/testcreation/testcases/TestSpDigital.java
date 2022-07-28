package aut.testcreation.testcases;

import aut.engine.selenium.DriverFactory;
import aut.engine.selenium.SeleniumTestBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TestSpDigital extends SeleniumTestBase {
    WebDriver driver;


    @Test
    public void testSpDigital() throws InterruptedException {
        driver = DriverFactory.getDriver();
        driver.navigate().to("https://www.spdigital.cl/");

        By buscador = By.xpath("/html/body/div[1]/div[1]/div/div[2]/div/span[1]/form/div/input");
        By btnBuscar = By.xpath("/html/body/div[1]/div[1]/div/div[2]/div/span[1]/form/div/button");
        By noProduct = By.xpath("/html/body/div[1]/div[1]/div[4]/section[1]/div/div[2]/div[2]/span[1]");

        WebElement webElementBuscador = driver.findElement(buscador);
        WebElement webElementBtnBuscar =  driver.findElement(btnBuscar);
        webElementBuscador.sendKeys("asdfgh");
        webElementBtnBuscar.click();
        Thread.sleep(3000);
        WebElement webElementnoProduct = driver.findElement(noProduct);
        String text = webElementnoProduct.getText();
        Assertions.assertEquals("0 - 0 de 0 productos para tu búsqueda: \"asdfgh\"",text);


        Assertions.assertTrue(true);
    }
}
