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
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

public class ManejoWebElement {

    WebDriver driver;
    By dropdownLocator = By.id("dropdown");
    By check1 = By.xpath("//input[1][@type=\"checkbox\"]");
    By check2 = By.xpath("//input[2][@type=\"checkbox\"]");

    By btnEnabledLocator = By.xpath("//li[@id=\"ui-id-3\"]/a");
    By btnDownloadsLocator = By.xpath("//li[@id='ui-id-4']/a");
    By btnPdfLocator = By.xpath("//li[@id='ui-id-5']/a");

    By iframeTextLocator = By.xpath("//*[@id=\"tinymce\"]");



    @BeforeAll
    static void init(){
        WebDriverManager.edgedriver().setup();
    }
    @BeforeEach
    void setup(){
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
    }

    //dropdown
    @Test
    void dropdown(){
        System.out.println("Test Dropdown");
        driver.navigate().to("https://the-internet.herokuapp.com/dropdown");

        //WebElement -> select
        WebElement webElementdropdown = driver.findElement(dropdownLocator);
        //Select library
        Select dropDownSelect = new Select(webElementdropdown);

        //recorrer valores por tag value
        dropDownSelect.selectByValue("1");
        System.out.print(dropDownSelect.getFirstSelectedOption().getText());
        dropDownSelect.selectByValue("2");
        System.out.print(dropDownSelect.getFirstSelectedOption().getText());

        //recorrer valores por indice
        dropDownSelect.selectByIndex(1);
        dropDownSelect.selectByIndex(2);
    }

    @Test
    void checkbox(){
        driver.get("https://the-internet.herokuapp.com/checkboxes");

        //checkbox -> input
        WebElement checkbox1 = driver.findElement(check1);
        WebElement checkbox2 = driver.findElement(check2);

        if(!checkbox1.isSelected()){
            checkbox1.click();
        }
        if(checkbox2.isSelected()){
            checkbox2.click();
        }

    }

    @Test
    void menuDinamico(){
        driver.navigate().to("https://the-internet.herokuapp.com/jqueryui/menu");

        WebElement btnEnabled = driver.findElement(btnEnabledLocator);
        WebElement btnDownloads = driver.findElement(btnDownloadsLocator);
        WebElement btnPdf = driver.findElement(btnPdfLocator);

        if(btnPdf.isDisplayed()){
            btnPdf.click();
        }
        if(btnDownloads.isDisplayed()){
            btnDownloads.click();
        }
        if(btnEnabled.isDisplayed()){
            btnEnabled.click();
            btnDownloads.click();
            btnPdf.click();
        }


    }

    @Test
    void iframes(){
        driver.get("https://the-internet.herokuapp.com/iframe");

        //Traernos todos los iframes de la página
        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
        driver.switchTo().frame(iframes.get(0));
        WebElement iframeTextElement = driver.findElement(iframeTextLocator);
        iframeTextElement.clear();
        iframeTextElement.sendKeys("Hola mundo");

    }



    @AfterEach
    void close(){
        driver.close();
        driver.quit();
    }

}
