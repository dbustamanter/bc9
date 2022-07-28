package aut.testcreation.testcases;

import aut.engine.selenium.DriverFactory;
import aut.engine.selenium.SeleniumTestBase;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static aut.engine.utils.Constants.BASE_URL_AUT;

public class LocalizadoresTest extends SeleniumTestBase {

    WebDriver driver;
    //Localizadores
    By btnEnviarA = By.cssSelector(".nav-menu-cp");
    By popElegiDondeRecibir = By.cssSelector(".andes-modal-dialog__content");
    By cerrarPopElegi = By.cssSelector(".andes-modal-dialog__button-close");

    By btnEntendido = By.xpath("/html/body/div[2]/div[1]/div[2]/button[1]");

    @Test
    @Description("Test de localizadores")
    public void localizandoElementos() throws InterruptedException{

        driver = DriverFactory.getDriver();
        driver.navigate().to(BASE_URL_AUT);
        // ya debería estar disponible el elemento web
        WebElement webElementbtnEnviarA = driver.findElement(btnEnviarA);
        if (webElementbtnEnviarA.isDisplayed()){
            String text = webElementbtnEnviarA.getText();
            System.out.println(text);
            webElementbtnEnviarA.click();
        }
        if (driver.findElement(popElegiDondeRecibir).isDisplayed()){
            WebElement webElementCerrarPop = driver. findElement(cerrarPopElegi);
            webElementCerrarPop.click();
        }


    }
}
