package aut.testcreation.testcases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import java.time.Duration;
import java.util.List;

public class GoogleTranslate {

    WebDriver driver;

    By btnFrom = By.xpath("//div[@class='ccvoYb EjH7wc']/descendant::div[@class='akczyd']/button[@jsname=\"RCbdJd\"]");

    By btnTo = By.xpath("//div[@class='ccvoYb EjH7wc']/descendant::div[@class='akczyd']/button[@jsname=\"zumM6d\"]");
    By inputLanguage = By.xpath("//div[@class='pEyuac X4hZJc']/descendant::input[@jsaction='input:G0jgYd;']");
    By languageSelect = By.xpath(" //div[@jsname='mm30Mc']/descendant::span[1]");
    By textArea = By.xpath("//*[@id=\"yDmH0d\"]/c-wiz/div/div[2]/c-wiz/div[2]/c-wiz/div[1]/div[2]/div[3]/c-wiz[1]/span/span/div/textarea");
    By translate = By.xpath("//*[@id=\"yDmH0d\"]/c-wiz/div/div[2]/c-wiz/div[2]/c-wiz/div[1]/div[2]/div[3]/c-wiz[2]/div[8]/div/div[1]/span[1]/span/span");
    By closeiFrame = By.xpath("//button[@aria-label='NO, GRACIAS']");



    @BeforeAll
    static void init(){WebDriverManager.edgedriver().setup();}

    @BeforeEach
    void setup(){
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
    }

    public void setLanguages(String from, String to){
        driver.navigate().to("https://translate.google.com");
        //Configurar Lenguaje "Desde"
        WebElement webtnFrom = driver.findElement(btnFrom);
        webtnFrom.click();
        WebElement weinputLanguage = driver.findElement(inputLanguage);
        weinputLanguage.sendKeys(from);
        WebElement welanguageSelect = driver.findElement(languageSelect);
        welanguageSelect.click();
        //cerrar iframe
        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
        driver.switchTo().frame(iframes.get(0));
        WebElement wecloseiFrame = driver.findElement(closeiFrame);
        wecloseiFrame.click();
        driver.switchTo().defaultContent();

        //Configurar Lenguaje destino
        WebElement webbtnTo = driver.findElement(btnTo);
        webbtnTo.click();
        WebElement weinputLanguageTo = driver.findElement(inputLanguage);
        weinputLanguageTo.sendKeys(to);
        WebElement welanguageSelectTo = driver.findElement(languageSelect);
        welanguageSelectTo.click();
    }


    @Test
    void espanolIngles(){
        setLanguages("español","inglés");
        WebElement webElementTextArea = driver.findElement(textArea);
        String textToTranslate = "La teoría del caos es la rama de la matemática, la física y otras ciencias (biología, meteorología, economía, entre otras) que trata ciertos tipos de sistemas complejos y sistemas dinámicos no lineales muy sensibles a las variaciones en las condiciones iniciales.";
        webElementTextArea.sendKeys(textToTranslate);
        WebElement webElementResult = driver.findElement(translate);
        String resultado = webElementResult.getText();
        String esperado = "Chaos theory is the branch of mathematics, physics and other sciences (biology, meteorology, economy, among others) that deals with certain types of complex systems and non -linear dynamic systems very sensitive to variations in the initial conditions.";
        Assertions.assertEquals(esperado,resultado);;
    }

    @Test
    void espanolFrances(){
        setLanguages("español","francés");
        WebElement webElementTextArea = driver.findElement(textArea);
        String textToTranslate = "La teoría del caos es la rama de la matemática, la física y otras ciencias (biología, meteorología, economía, entre otras) que trata ciertos tipos de sistemas complejos y sistemas dinámicos no lineales muy sensibles a las variaciones en las condiciones iniciales.";
        webElementTextArea.sendKeys(textToTranslate);
        WebElement webElementResult = driver.findElement(translate);
        String resultado = webElementResult.getText();
        String esperado = "La théorie du chaos est la branche des mathématiques, de la physique et d'autres sciences (biologie, météorologie, économie, entre autres) qui traite de certains types de systèmes complexes et de systèmes dynamiques non linéaires très sensibles aux variations dans les conditions initiales.";
        Assertions.assertEquals(esperado,resultado);
    }

    @Test
    void espanolPortugues(){
        setLanguages("español","portugués");
        WebElement webElementTextArea = driver.findElement(textArea);
        String textToTranslate = "La teoría del caos es la rama de la matemática, la física y otras ciencias (biología, meteorología, economía, entre otras) que trata ciertos tipos de sistemas complejos y sistemas dinámicos no lineales muy sensibles a las variaciones en las condiciones iniciales.";
        webElementTextArea.sendKeys(textToTranslate);
        WebElement webElementResult = driver.findElement(translate);
        String resultado = webElementResult.getText();
        String esperado = "A teoria do caos é o ramo da matemática, física e outras ciências (biologia, meteorologia, economia, entre outros) que lida com certos tipos de sistemas complexos e sistemas dinâmicos não lineares muito sensíveis a variações nas condições iniciais.";
        Assertions.assertEquals(esperado,resultado);

    }


    @AfterEach
    void close(){
        driver.close();
        driver.quit();
    }

}
