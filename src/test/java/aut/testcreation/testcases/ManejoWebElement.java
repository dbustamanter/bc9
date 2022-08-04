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
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

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

        Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver)
                                        .withTimeout(Duration.ofMillis(5000))
                                        .pollingEvery(Duration.ofMillis(100))
                                        .ignoring(NoSuchElementException.class);

        fluentWait.until(ExpectedConditions.elementToBeSelected(check1));

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

        //Espera Explicita
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(3000));

        if(wait.until(ExpectedConditions.elementToBeSelected(btnPdf))){
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

    @Test
    void webTables(){

        driver.navigate().to("https://the-internet.herokuapp.com/tables");

        //Traer elementos de tabla
        List<WebElement> webTable = driver.findElements(By.id("table1"));

        //Cuantas Columnas tiene el webTable?
        List<WebElement> columnas = webTable.get(0).findElement(By.tagName("thead")).findElements(By.tagName("th"));
        for(WebElement th: columnas){
            String text = th.getText();
            if(text.contains("Due")){
                th.click();;
                th.click();
                break;
            }
        }

        //Cuantas filas
        List<WebElement> filas = webTable.get(0).findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
        List<WebElement> mayorDeudor = filas.get(0).findElements(By.tagName("td"));
        System.out.println("El cliente con mayor deuda es: ");
        /*for(WebElement td : mayorDeudor){
            System.out.println(td.getText());
        }*/
        for (int i=0;i< mayorDeudor.size();i++){
            String text = columnas.get(i).getText();
            if(text.contains("Last Name")){
                System.out.println("Last Name: " + mayorDeudor.get(i).getText());
            }
            else if(text.contains("First Name")){
                System.out.println("First Name: " + mayorDeudor.get(i).getText());
            }
            else if(text.contains("Due")){
                System.out.println("Due: "+mayorDeudor.get(i).getText());
            }
        }

        System.out.println("*****************************************Orden Alfabetico************************");
        for(WebElement th: columnas){
            String text = th.getText();
            if(text.contains("Last Name")){
                th.click();
                break;
            }
        }
        for(int tr=0;tr<filas.size();tr++){
            List<WebElement> fila = filas.get(tr).findElements(By.tagName("td"));
            for (int i=0;i< fila.size();i++){
                String text = columnas.get(i).getText();
                if(text.contains("Last Name")){
                    System.out.println("Last Name: " + fila.get(i).getText());
                }
                else if(text.contains("First Name")){
                    System.out.println("First Name: " + fila.get(i).getText());
                    System.out.println("_______________________________________");
                    break;
                }

            }
        }






    }



    @AfterEach
    void close(){
        driver.close();
        driver.quit();
    }

}
