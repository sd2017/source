package swip.ch12decorating.httpstatuscode;

import com.google.common.collect.ImmutableMap;
import net.lightbody.bmp.proxy.ProxyServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URI;

import static org.junit.Assert.assertEquals;

public class HttpStatusCodeIT {
    private final ProxyServer server = new ProxyServer(9090);
    private final DesiredCapabilities desiredCapabilities = new DesiredCapabilities(
            ImmutableMap.of(CapabilityType.PROXY, server.seleniumProxy())
    );
    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        server.start();
        driver = HttpStatusCodeDecorator.httpStatusCodeDriver
                (new FirefoxDriver(desiredCapabilities), server, URI.create("http://localhost:8080"));
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        server.stop();
    }

    @Test
    public void notFound() throws Exception {
        driver.get("http://localhost:8080/not-found.html");

        assertEquals(404, ((HasHttpStatusCode) driver).getHttpStatusCode());
    }

    @Test
    public void resourceNotFound() throws Exception {
        driver.get("http://localhost:8080/resource-not-found.html");

        assertEquals(200, ((HasHttpStatusCode) driver).getHttpStatusCode());
    }

}
