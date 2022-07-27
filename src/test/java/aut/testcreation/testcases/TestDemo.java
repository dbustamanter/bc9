package aut.testcreation.testcases;

import aut.engine.selenium.DriverFactory;
import aut.engine.selenium.SeleniumTestBase;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static aut.engine.utils.Constants.BASE_URL_AUT;

public class TestDemo extends SeleniumTestBase {

    WebDriver driver;

    @Test
    @Description("Test creado para bootcamp 9 tsoft")
    public void testDemo() throws InterruptedException {
        //inicializacion
        driver = DriverFactory.getDriver(); //crea el browser de prueba


        //open web page
        driver.get(BASE_URL_AUT); //google.com
        String nombreUrlActual = driver.getCurrentUrl(); //tomar url actual
        String tituloWeb = driver.getTitle();//tomar titulo de la pagina
        System.out.println("Me encuentro en : "+nombreUrlActual+" y su titulo es : "+tituloWeb);
        Thread.sleep(3000);
        driver.navigate().to("https://selenium.dev");
        Thread.sleep(3000);
        nombreUrlActual = driver.getCurrentUrl(); //tomar url actual
        tituloWeb = driver.getTitle();//tomar titulo de la pagina
        System.out.println("Me encuentro en : "+nombreUrlActual+" y su titulo es : "+tituloWeb);

        //Browser :: Back - Forward - Refresh
        driver.navigate().back();
        nombreUrlActual = driver.getCurrentUrl(); //tomar url actual
        tituloWeb = driver.getTitle();//tomar titulo de la pagina
        System.out.println("Me encuentro en : "+nombreUrlActual+" y su titulo es : "+tituloWeb);
        Thread.sleep(3000);
        driver.navigate().forward();
        nombreUrlActual = driver.getCurrentUrl(); //tomar url actual
        tituloWeb = driver.getTitle();//tomar titulo de la pagina
        System.out.println("Me encuentro en : "+nombreUrlActual+" y su titulo es : "+tituloWeb);
        Thread.sleep(3000);
        driver.navigate().refresh();
        
        Assertions.assertTrue(true);
    }
}