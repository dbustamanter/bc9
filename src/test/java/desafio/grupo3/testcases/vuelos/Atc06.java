package desafio.grupo3.testcases.vuelos;

import desafio.grupo3.pages.HomePageRumbo;
import desafio.grupo3.pages.vuelos.BusquedaPageVuelos;
import desafio.grupo3.pages.vuelos.HomePageVuelos;
import framework.engine.selenium.DriverFactory;
import framework.engine.selenium.SeleniumTestBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Atc06 extends SeleniumTestBase {
    HomePageRumbo homePageRumbo;
    HomePageVuelos homePageVuelos;
    BusquedaPageVuelos busquedaPageVuelos;

    @Test
    void menores200() throws InterruptedException {
        homePageRumbo = new HomePageRumbo(DriverFactory.getDriver());
        homePageVuelos = new HomePageVuelos(DriverFactory.getDriver());
        busquedaPageVuelos = new BusquedaPageVuelos(DriverFactory.getDriver());
        homePageRumbo.navegarAlHome();
        homePageRumbo.aceptarCookies();
        homePageRumbo.navegarAPaginaVuelos();
        homePageVuelos.busquedaCompletaIdaYVuelta("Madrid", "Mexico", 9,1,9,2,1,0,0,"Turista");
        homePageVuelos.cambiarTab();
        busquedaPageVuelos.filtrarPecio(-159,0); //El problema es que tenemos que calcular bien los pixeles
        Thread.sleep(5000);
        Assertions.assertTrue(busquedaPageVuelos.compararPrecios());//Si es que son mayores a 2000
    }

}
