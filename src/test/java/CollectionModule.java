import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.reporters.jq.Main;

import java.io.IOException;
import java.util.concurrent.TransferQueue;

public class CollectionModule {

    static LogInModule M = new LogInModule();
    static ConnectionModule N = new ConnectionModule();


    //#collect sample scenario
    public boolean collectSample () throws IOException, InterruptedException {
        boolean LogStatus = false;

        //check current activity
        String activity = ((AndroidDriver<?>) MainTest.driver).currentActivity();

        if (!activity.equals(".HomeActivity")) {
            //make sure user is signed in
            LogStatus = M.loginWithValidCredentials();
        } else {
            LogStatus = true;
        }

        N.connectToScanner();


        try
        {
            MobileElement element = (MobileElement) MainTest.driver.findElement(By.id("com.neospectracollect:id/btn_collect"));

            if (element.isDisplayed())
            {
                if (LogStatus)
                {
                    MainTest.driver.findElement(By.id("com.neospectracollect:id/btn_collect")).click();

                    WebDriverWait wait = new WebDriverWait(MainTest.driver, 10);
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.neospectracollect:id/btn_scan")));

                    element = (MobileElement) MainTest.driver.findElement(By.id("com.neospectracollect:id/btn_scan"));

                    if (element.isDisplayed())
                    {
                        //set collect parameters

                        //set scan time
                        MainTest.driver.findElement(By.id("com.neospectracollect:id/scan_time_edit_text")).sendKeys(String.valueOf(Helpers.GetRandomValue(1,14)));

                        //set interval
                        MainTest.driver.findElement(By.id("com.neospectracollect:id/interval_edit_text")).sendKeys(String.valueOf(Helpers.GetRandomValue(0,10)));

                        //set number of measurements
                        MainTest.driver.findElement(By.id("com.neospectracollect:id/no_of_measurements_edit_text")).sendKeys(String.valueOf(Helpers.GetRandomValue(1,10)));

                        //set material name
                        MainTest.driver.findElement(By.id("com.neospectracollect:id/material_name_edit_text")).sendKeys("TestMaterial");

                        //set sample name/ID
                        MainTest.driver.findElement(By.id("com.neospectracollect:id/sample_name_edit_text")).sendKeys("SampleMaterial");


                        //geo location
                        boolean GeoEnabled = Helpers.GetRandomBoolean();
                        if(GeoEnabled)
                        {
                            MainTest.driver.findElement(By.id("com.neospectracollect:id/img_location")).click();
                        }


                        //note
                        boolean NoteEnabled = Helpers.GetRandomBoolean();
                        if(NoteEnabled)
                        {
                            MainTest.driver.findElement(By.id("com.neospectracollect:id/img_document")).click();

                            wait = new WebDriverWait(MainTest.driver, 10);
                            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.neospectracollect:id/txt_description")));

                            MainTest.driver.findElement(By.id("com.neospectracollect:id/txt_description")).sendKeys("Material Notes");

                            MainTest.driver.findElement(By.id("com.neospectracollect:id/btn_submit")).click();
                        }


                        //add image
                        boolean ImageEnabled = Helpers.GetRandomBoolean();
                        /*if(ImageEnabled)
                        {
                            MainTest.driver.findElement(By.id("com.neospectracollect:id/img_camera")).click();

                            wait = new WebDriverWait(MainTest.driver, 5);
                            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.appcompat.widget.LinearLayoutCompat/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[2]")));

                            MainTest.driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.appcompat.widget.LinearLayoutCompat/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[2]").click();


                            wait = new WebDriverWait(MainTest.driver, 5);
                            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.google.android.apps.photosgo:id/image1")));

                            MainTest.driver.findElement(By.id("com.google.android.apps.photosgo:id/image1")).click();


                            wait = new WebDriverWait(MainTest.driver, 5);
                            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.google.android.apps.photosgo:id/single_photo")));

                            MainTest.driver.findElement(By.id("com.google.android.apps.photosgo:id/single_photo")).click();


                            wait = new WebDriverWait(MainTest.driver, 5);
                            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.neospectracollect:id/btn_calibrate")));
                        }
*/

                        //calibrate
                        MainTest.driver.findElement(By.id("com.neospectracollect:id/btn_calibrate")).click();


                        //wait until scan button is enabled
                        wait = new WebDriverWait(MainTest.driver, 100);
                        wait.until(ExpectedConditions.elementToBeClickable(By.id("com.neospectracollect:id/btn_scan")));


                        if(MainTest.driver.findElement(By.id("com.neospectracollect:id/btn_scan")).isEnabled())
                        {
                            MainTest.driver.findElement(By.id("com.neospectracollect:id/btn_scan")).click();
                        }


                        //check if scan begin
                        wait = new WebDriverWait(MainTest.driver, 100);
                        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.neospectracollect:id/pb_loader")));

                        Thread.sleep(1000);

                        wait = new WebDriverWait(MainTest.driver, 1400);
                        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("com.neospectracollect:id/pb_loader")));

                        Thread.sleep(1000);

                        //go to spectra page and check is scan exist
                        MainTest.driver.findElementByXPath("//android.widget.FrameLayout[@content-desc=\"Spectra\"]/android.widget.ImageView").click();

                        wait = new WebDriverWait(MainTest.driver, 10);
                        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.neospectracollect:id/txt_hd_material")));


                        //check scans
                        wait = new WebDriverWait(MainTest.driver,10);
                        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.neospectracollect:id/txt_title")));

                        if(MainTest.driver.findElement(By.id("com.neospectracollect:id/txt_title")).isDisplayed())
                        {
                            Helpers.SaveResult("Collect Case Result is : Pass. \r\n");
                            System.out.print("Collect Case Result is : Pass. \r\n");
                        }


                        //scan failed
                        else
                        {
                            Helpers.SaveResult("Collect Case Result is : Fail. \r\n");
                            System.out.print("Collect Case Result is : Fail. \r\n");
                        }


                        return true;
                    }
                    else
                    {
                        Helpers.SaveResult("Collect Case Result is : Fail. \r\n");
                        System.out.print("Collect Case Result is : Fail. \r\n");
                        return false;
                    }

                }
                else
                {
                    return false;
                }

            }
            else
            {
                //collect button not found
                return false;
            }
        }
        catch (Exception e)
        {
            //collect button not found
            Helpers.SaveResult("Collect Case Result is : Fail. \r\n");
            System.out.print("Collect Case Result is : Fail. \r\n");
            return false;
        }
    }
}
