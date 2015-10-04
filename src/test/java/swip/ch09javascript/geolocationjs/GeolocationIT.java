package swip.ch09javascript.geolocationjs;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import swip.ch07managingwebdriver.Config;
import swip.ch07managingwebdriver.WebDriverRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(WebDriverRunner.class)
@Config(exclude = "browserName=htmlunit")
public class GeolocationIT {
    @Inject
    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        driver.get("/geolocation.html");
    }

    @Test
    public void geolocationInjection() throws Exception {

        ((JavascriptExecutor) driver).executeScript(
                String.format(
                        "navigator.geolocation = navigator.geolocation || {};" +
                                "navigator.geolocation.getCurrentPosition = function(ok,err){" +
                                "ok({'coords': {'latitude': %s, 'longitude': %s}});" +
                                "}",
                        51.5106766,
                        -0.1231314
                ));

        driver.findElement(By.id("locate")).click();

        WebElement location = driver.findElement(By.id("location"));

        new WebDriverWait(driver, 10).until(
                (WebDriver d) -> !location.getText().equals("Loading..."));

        assertEquals("You state you are at + 51.5106766, -0.1231314", location.getText());
    }

    @Test
    public void positionError() throws Exception {

        ((JavascriptExecutor) driver).executeScript(
                String.format(
                        "navigator.geolocation = navigator.geolocation || {};" +
                                "navigator.geolocation.getCurrentPosition = function(ok,err){" +
                                "err({'error': {" +
                                "'PERMISSION_DENIED': 1, " +
                                "'POSITION_UNAVAILABLE': 2, " +
                                "'TIMEOUT': 3" +
                                "}, 'code': %d, 'message': '%s'});}",
                        1,
                        "User denied Geolocation"
                ));

        driver.findElement(By.id("locate")).click();

        WebElement location = driver.findElement(By.id("location"));

        new WebDriverWait(driver, 10).until(
                (WebDriver d) -> !location.getText().equals("Loading..."));

        assertEquals("Error: 1 User denied Geolocation", location.getText());
    }
}
