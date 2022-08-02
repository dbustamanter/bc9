package aut.testcreation.testcases;

import aut.engine.selenium.DriverFactory;
import aut.engine.selenium.SeleniumTestBase;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.ArrayList;

public class testPortalUdp extends SeleniumTestBase {
    WebDriver driver;
    @Test
    @Description
    public void testRecuperarPasswordEmpty() throws InterruptedException{
        driver = DriverFactory.getDriver();
        driver.navigate().to("https://portal.udp.cl/irj/portal");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        By btnReset = By.xpath("//table[@class='urLogonTable']/descendant::a[@class='urLnk']");
        By btnSend = By.xpath("//*[@id=\"OODK.Identification.Submit\"]");
        By stringError = By.xpath("//span[@id=\"OODK.Identification.MessageArea-txt\"]");

        WebElement webElementbtnReset = driver.findElement(btnReset);
        webElementbtnReset.click();
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        WebElement webElementbtnSend = driver.findElement(btnSend);
        webElementbtnSend.click();
        WebElement webElementstringError = driver.findElement(stringError);
        String text = webElementstringError.getText();
        Assertions.assertEquals("User information incorrect. Cannot send e-mail with new password.",text);
        Assertions.assertTrue(true);
    }
}
