import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

public class ConnectionModule {

    LogInModule M = new LogInModule();

    //#connect to scanner
    public boolean connectToScanner() throws IOException, InterruptedException {
        boolean LogStatus = false;

        //check current activity (loged in)
        String activity = ((AndroidDriver<?>) MainTest.driver).currentActivity();

        if (!activity.equals(".HomeActivity")) {
            //make sure user is signed in
            LogStatus = M.loginWithValidCredentials();
        } else {
            LogStatus = true;
        }

        try
        {
            //check current page (connect button)
            MobileElement element = (MobileElement) MainTest.driver.findElement(By.id("com.neospectracollect:id/btn_connect"));

            if (element.isDisplayed())
            {

                    WebDriverWait wait = new WebDriverWait(MainTest.driver, 10);
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.neospectracollect:id/btn_connect")));

                    MainTest.driver.findElement(By.id("com.neospectracollect:id/btn_connect")).click();

                    wait = new WebDriverWait(MainTest.driver, 10);
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.neospectracollect:id/parent_card")));

                    element = (MobileElement) MainTest.driver.findElement(By.id("com.neospectracollect:id/parent_card"));


                    if (element.isDisplayed())
                    {
                        //connect
                        MainTest.driver.findElement(By.id("com.neospectracollect:id/parent_card")).click();

                        Thread.sleep(5000);

                        try
                        {
                            element = (MobileElement) MainTest.driver.findElement(By.id("com.neospectracollect:id/clyr_device_found"));

                            if(element.isDisplayed())
                            {
                                Helpers.SaveResult("Connect to Scanner Case Result is : Pass. \r\n");
                                System.out.print("Connect to Scanner Case Result is : Pass. \r\n");
                            }
                            else
                            {
                                Helpers.SaveResult("Connect to Scanner Case Result is : Fail. \r\n");
                                System.out.print("Connect to Scanner Case Result is : Fail. \r\n");
                            }
                        }
                        catch (Exception e)
                        {
                            Helpers.SaveResult("Connect to Scanner Case Result is : Fail. \r\n");
                            System.out.print("Connect to Scanner Case Result is : Fail. \r\n");
                        }

                    }
                    else
                    {
                        Helpers.SaveResult("Connect to Scanner Case Result is : Fail. \r\n");
                        System.out.print("Connect to Scanner Case Result is : Fail. \r\n");
                    }


                    return element.isDisplayed();
            }
            else
            {
                //current page is not home page OR scanner already connected

                //go to home page
                MainTest.driver.findElementByXPath("//android.widget.FrameLayout[@content-desc=\"Home\"]/android.widget.ImageView").click();

                //if disconnect button exist disconnect first
                try
                {
                    //disconnect

                    //MainTest.driver.findElement(By.id("com.neospectracollect:id/btn_disconnect")).click();
                    disconnectScanner();

                }
                catch (Exception e)
                {
                    //button not displayed

                }


                connectToScanner();
                return true;
            }

        }
        catch (Exception e)
        {
            //current page is not home page
            //go to home page then (start over)
            MainTest.driver.findElementByXPath("//android.widget.FrameLayout[@content-desc=\"Home\"]/android.widget.ImageView").click();
            disconnectScanner();
            connectToScanner();
            return true;
        }
    }



    //#connect to scanner
    public boolean disconnectScanner() throws IOException, InterruptedException {

        boolean LogStatus = false;

        //check current activity (loged in)
        String activity = ((AndroidDriver<?>) MainTest.driver).currentActivity();

        if (!activity.equals(".HomeActivity")) {
            //make sure user is signed in
            LogStatus = M.loginWithValidCredentials();
        } else {
            LogStatus = true;
        }

        try
        {
            //check current page (disconnect button)
            MobileElement element = (MobileElement) MainTest.driver.findElement(By.id("com.neospectracollect:id/btn_disconnect"));

            if (element.isDisplayed())
            {
                    //click disconnect button
                    MainTest.driver.findElement(By.id("com.neospectracollect:id/btn_disconnect")).click();

                    WebDriverWait wait = new WebDriverWait(MainTest.driver, 10);
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("android:id/button1")));

                    MainTest.driver.findElement(By.id("android:id/button1")).click();


                    WebDriverWait wait2 = new WebDriverWait(MainTest.driver, 10);
                    wait2.until(ExpectedConditions.presenceOfElementLocated(By.id("com.neospectracollect:id/btn_connect")));

                    element = (MobileElement) MainTest.driver.findElement(By.id("com.neospectracollect:id/btn_connect"));


                    if (element.isDisplayed())
                    {
                        Helpers.SaveResult("Disconnect Scanner Case Result is : Pass. \r\n");
                        System.out.print("Disconnect Scanner Case Result is : Pass. \r\n");
                    }
                    else
                    {
                        Helpers.SaveResult("Disconnect Scanner Case Result is : Fail. \r\n");
                        System.out.print("Disconnect Scanner Case Result is : Fail. \r\n");
                    }

                    return element.isDisplayed();
            }
            else
            {
                Helpers.SaveResult("Disconnect Scanner Case Result is : Fail. \r\n");
                System.out.print("Disconnect Scanner Case Result is : Fail. \r\n");
                return false;
            }

        }
        catch (Exception e)
        {

            //current page is not home page
            //go to home page then (start over)

            MainTest.driver.findElementByXPath("//android.widget.FrameLayout[@content-desc=\"Home\"]/android.widget.ImageView").click();
            disconnectScanner();
            return true;
        }
    }




}
