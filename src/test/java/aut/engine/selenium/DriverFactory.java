package aut.engine.selenium;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.net.MalformedURLException;

public class DriverFactory {

    public WebDriver driver;
    public static ThreadLocal<WebDriver> hiloLocal = new ThreadLocal<>();

    /**
     * inicializa el WebDriver segun la seleccion del browser
     * @param browser: chrome | firefox
     * @return Webdriver
     */
    public WebDriver inicializarDriver(String browser){
        System.out.println("browser value is: "+browser);

        if(browser.equals("edge")){
            WebDriverManager.edgedriver().setup();
            hiloLocal.set(new EdgeDriver());
        }else if(browser.equals("firefox")){
            WebDriverManager.firefoxdriver().setup();
            hiloLocal.set(new FirefoxDriver());
        }else if (browser.equals("chrome")){
            WebDriverManager.chromedriver().setup();
            hiloLocal.set(new ChromeDriver());
        }
        else {
            System.out.println("Please pass the correct browser value: "+browser);
        }
        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().minimize();
        getDriver().manage().window().maximize();
        getDriver().manage().window().setSize(new Dimension(800,600));
        getDriver().manage().window().fullscreen();
        return getDriver();
    }



    /**
     * retorna el WebDriver desde el ThreadLocal
     * @return WebDriver
     */
    public static synchronized WebDriver getDriver(){
        return hiloLocal.get();
    }
}