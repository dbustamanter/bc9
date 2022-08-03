package aut.testcreation.testcases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import java.time.Duration;
import java.util.List;

public class GoogleCalculator {

    WebDriver driver;

    By resultado = By.xpath("//div[@jsname='zLiRgc']");
    By calculatorPad = By.xpath("//table[@class='ElumCf']");

    @BeforeAll
    static void init(){
        WebDriverManager.edgedriver().setup();}

    @BeforeEach
    void setup(){
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
    }
    public int calculo(int x, int y, String operacion){
        int resultado=0;
        switch  (operacion){
            case "+":
                resultado=x+y;
                break;
            case "-":
                resultado= x-y;
                break;
            case "÷":
                resultado= x/y;
                break;
            case "x":
                resultado = x*y;
                break;
            default:
                break;
        }
        return resultado;
    }

    @Test
    void calculator(){
        driver.navigate().to("https://www.google.com/search?q=calculadora+google&hl=es&ei=baDqYqmQG7jx1sQP7ZmweA&ved=0ahUKEwjp-rfziav5AhW4uJUCHe0MDA8Q4dUDCA4&uact=5&oq=calculadora+google&gs_lcp=Cgdnd3Mtd2l6EAMyCwgAEIAEELEDEIMBMgUIABCABDIFCAAQgAQyBQgAEIAEMgUIABCABDIFCAAQgAQyBQgAEIAEMgUIABCABDIFCAAQgAQyBQgAEIAEOgcIABBHELADOgcIABCwAxBDOggIABCABBCxA0oECEEYAEoECEYYAFDiBVjCCWD2CmgBcAF4AIABxAGIAZQGkgEDMy40mAEAoAEByAEKwAEB&sclient=gws-wiz");
        /*int x=5;
        int y=6;
        String operacion="+";
        String enviar= x + operacion + y;

        WebElement webElementResultado = driver.findElement(resultado);
        List<WebElement> wecalculatorPad = driver.findElements(By.className("ElumCf"));

        List<WebElement> filas = wecalculatorPad.get(0).findElement(By.tagName("tbody")).findElements(By.tagName("tr"));

        webElementResultado.click();
        webElementResultado.sendKeys(enviar);*/




    }




    @AfterEach
    void close(){
        driver.close();
        driver.quit();
    }
}
