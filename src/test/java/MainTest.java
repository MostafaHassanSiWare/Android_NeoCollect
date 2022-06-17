import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import net.sourceforge.tess4j.TesseractException;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;

public class MainTest {
    static AppiumDriver driver;

    @BeforeTest
    public void setUp() throws MalformedURLException {

        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("platformName","Android");
        caps.setCapability("automationName","UiAutomator2");
        caps.setCapability("platformVersion","11.0");
        caps.setCapability("deviceName","HA1FL99P");
        //caps.setCapability("deviceName","AndroidEmulator");
        //caps.setCapability("app",System.getProperty("user.dir")+"/apps/NeoCollect.apk");
        //caps.setCapability("app", "C:/Users/moham/IdeaProjects/FirstAppiumDemo/apps/NeoCollect.apk");
        caps.setCapability("appPackage","com.neospectracollect");
        caps.setCapability("appActivity", "com.neospectracollect.SplashActivity");
        caps.setCapability("autoGrantPermissions", true);
        caps.setCapability("noReset", true);


        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), caps);

    }


    @Test
    public void testRuns() throws InvocationTargetException, IllegalAccessException, TesseractException, IOException, InterruptedException {

        //loged in
        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.neospectracollect:id/btn_collect")));
        }
        //not loged in
        catch (Exception e)
        {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.neospectracollect:id/forget_password")));
        }


        TestRunner TR = new TestRunner();
        //TR.callAllLogInCases();---
        //TR.executeAllPagesNavigationCases();
        //TR.executeAllConnectionCases();
        //TR.executeAllCollectionCases();
        TR.executeAllSynchronizationCases();



    }




    //@AfterTest
    //public void tearDown(){
    //if (driver != null)
    //{
    // driver.quit();
    //}
    //}
}
