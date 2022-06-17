import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.regex.Pattern;

public class SynchronizationModule {

    static LogInModule M = new LogInModule();
    static CollectionModule CM = new CollectionModule();

    //#normal sync scenario
    public boolean normalSync() throws IOException, InterruptedException {
        //first execute collect scenario
        CM.collectSample();

        //check sync icon
        WebDriverWait wait = new WebDriverWait(MainTest.driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.neospectracollect:id/img_isSynnc")));

        MobileElement element = (MobileElement) MainTest.driver.findElement(By.id("com.neospectracollect:id/img_isSynnc"));

        if(element.isDisplayed())
        {

            //check & validate icon
            org.openqa.selenium.Point point = element.getCenter();
            int centerX = point.getX();
            int centerY = point.getY()+5;

            File scrFile = ((TakesScreenshot) MainTest.driver).getScreenshotAs(OutputType.FILE);

            BufferedImage image = ImageIO.read(scrFile);
            // Getting pixel color by position x and y
            int clr=  image.getRGB(centerX,centerY);
            int  red   = (clr & 0x00ff0000) >> 16;
            int  green = (clr & 0x0000ff00) >> 8;
            int  blue  =  clr & 0x000000ff;
            //System.out.println("Red Color value = "+ red);
            //System.out.println("Green Color value = "+ green);
            //System.out.println("Blue Color value = "+ blue);

            if(green >= 180 && green <= 185)
            {
                Helpers.SaveResult("Sync Case Result is : Pass. \r\n");
                System.out.print("Sync Case Result is : Pass. \r\n");
            }
            else
            {
                Helpers.SaveResult("Sync Case Result is : Fail. \r\n");
                System.out.print("Sync Case Result is : Fail. \r\n");
            }
            return true;
        }
        else
        {
            Helpers.SaveResult("Sync Case Result is : Fail. \r\n");
            System.out.print("Sync Case Result is : Fail. \r\n");
            return false;
        }

    }


    //#full sync scenario
    public boolean fullSync() throws IOException, InterruptedException {
        boolean LogStatus = false;

        //check current activity
        String activity = ((AndroidDriver<?>) MainTest.driver).currentActivity();

        if (!activity.equals(".HomeActivity")) {
            //make sure user is signed in
            LogStatus = M.loginWithValidCredentials();
        } else {
            LogStatus = true;
        }

        try
        {

            //turn of sync
            turnOffSync();

            //collect/scan sample
            //first execute collect scenario
            CM.collectSample();

            //make sure samples not synced yet
            WebDriverWait wait = new WebDriverWait(MainTest.driver, 10);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.neospectracollect:id/img_isSynnc")));

            MobileElement element = (MobileElement) MainTest.driver.findElement(By.id("com.neospectracollect:id/img_isSynnc"));


            if(element.isDisplayed())
            {

                /*//check & validate icon
                org.openqa.selenium.Point point = element.getCenter();
                int centerX = point.getX();
                int centerY = point.getY()+5;

                File scrFile = ((TakesScreenshot) MainTest.driver).getScreenshotAs(OutputType.FILE);

                BufferedImage image = ImageIO.read(scrFile);
                // Getting pixel color by position x and y
                int clr=  image.getRGB(centerX,centerY);
                int  red   = (clr & 0x00ff0000) >> 16;
                int  green = (clr & 0x0000ff00) >> 8;
                int  blue  =  clr & 0x000000ff;
                //System.out.println("Red Color value = "+ red);
                //System.out.println("Green Color value = "+ green);
                //System.out.println("Blue Color value = "+ blue);*/

                if(isNotSynced())
                {
                    //do some modification on sample

                    //rename
                    ///////////////////////////////////////////////////////////////
                    MainTest.driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout/android.view.ViewGroup").click();


                    wait = new WebDriverWait(MainTest.driver, 10);
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[2]/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.FrameLayout/android.view.ViewGroup")));

                    MainTest.driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[2]/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.FrameLayout/android.view.ViewGroup").click();


                    wait = new WebDriverWait(MainTest.driver, 10);
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.neospectracollect:id/more_options")));

                    MainTest.driver.findElement(By.id("com.neospectracollect:id/more_options")).click();


                    wait = new WebDriverWait(MainTest.driver, 10);
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[1]/android.widget.LinearLayout")));

                    MainTest.driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[1]/android.widget.LinearLayout").click();



                    wait = new WebDriverWait(MainTest.driver, 10);
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.neospectracollect:id/txt_description")));

                    MainTest.driver.findElement(By.id("com.neospectracollect:id/txt_description")).sendKeys("NewDescription");


                    MainTest.driver.findElement(By.id("com.neospectracollect:id/btn_submit")).click();
                    ///////////////////////////////////////////////////////////////


                    //delete
                    ///////////////////////////////////////////////////////////////

                    wait = new WebDriverWait(MainTest.driver, 10);
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[2]/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[2]/android.widget.FrameLayout/android.view.ViewGroup")));

                    MainTest.driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[2]/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[2]/android.widget.FrameLayout/android.view.ViewGroup").click();


                    wait = new WebDriverWait(MainTest.driver, 10);
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.neospectracollect:id/more_options")));

                    MainTest.driver.findElement(By.id("com.neospectracollect:id/more_options")).click();


                    wait = new WebDriverWait(MainTest.driver, 10);
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[5]/android.widget.LinearLayout")));

                    MainTest.driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[5]/android.widget.LinearLayout").click();


                    wait = new WebDriverWait(MainTest.driver, 10);
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("android:id/button3")));

                    MainTest.driver.findElement(By.id("android:id/button3")).click();


                    ///////////////////////////////////////////////////////////////




                    //move
                    ///////////////////////////////////////////////////////////////




                    ///////////////////////////////////////////////////////////////




                    //back to group page
                    MainTest.driver.findElement(By.id("com.neospectracollect:id/img_nav_back")).click();

                    //turn on sync
                    turnOnSync();

                    //go to spectra page
                    MainTest.driver.findElementByXPath("//android.widget.FrameLayout[@content-desc=\"Spectra\"]/android.widget.ImageView").click();


                    //check if sync

                    if(isSynced())
                    {
                        Helpers.SaveResult("Full Sync Case Result is : Pass. \r\n");
                        System.out.print("Full Sync Case Result is : Pass. \r\n");

                        return true;
                    }
                    else
                    {
                        Helpers.SaveResult("Full Sync Case Result is : Fail. \r\n");
                        System.out.print("Full Sync Case Result is : Fail. \r\n");
                        return false;
                    }
                }
                else
                {
                    Helpers.SaveResult("Full Sync Case Result is : Fail. \r\n");
                    System.out.print("Full Sync Case Result is : Fail. \r\n");
                    return false;
                }
            }
            else
            {
                Helpers.SaveResult("Full Sync Case Result is : Fail. \r\n");
                System.out.print("Full Sync Case Result is : Fail. \r\n");
                return false;
            }
        }
        catch (Exception e)
        {
            //collect button not found
            Helpers.SaveResult("Full Sync Case Result is : Fail. \r\n");
            System.out.print("Full Sync Case Result is : Fail. \r\n");
            return false;
        }

    }


    //turn on sync
    public void turnOnSync() {

        MainTest.driver.findElementByXPath("//android.widget.FrameLayout[@content-desc=\"Home\"]/android.widget.ImageView").click();

        WebDriverWait wait = new WebDriverWait(MainTest.driver, 2);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.neospectracollect:id/nav_drawar")));

        MainTest.driver.findElement((By.id("com.neospectracollect:id/nav_drawar"))).click();


        wait = new WebDriverWait(MainTest.driver, 2);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.neospectracollect:id/sync_data")));

        //check switch status
       if(MainTest.driver.findElement(By.id("com.neospectracollect:id/sync_data")).getAttribute("checked").equals("false"))
        {
            MainTest.driver.findElement((By.id("com.neospectracollect:id/sync_data"))).click();
        }
        //close nav
        closeSideMenu((MobileElement) MainTest.driver.findElement(By.id("com.neospectracollect:id/nav_view")));
    }

    //turn off sync
    public void turnOffSync() {

        MainTest.driver.findElementByXPath("//android.widget.FrameLayout[@content-desc=\"Home\"]/android.widget.ImageView").click();

        WebDriverWait wait = new WebDriverWait(MainTest.driver, 2);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.neospectracollect:id/nav_drawar")));

        MainTest.driver.findElement((By.id("com.neospectracollect:id/nav_drawar"))).click();


        wait = new WebDriverWait(MainTest.driver, 2);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.neospectracollect:id/sync_data")));

        //check switch status
        if(MainTest.driver.findElement(By.id("com.neospectracollect:id/sync_data")).getAttribute("checked").equals("true"))
        {
            MainTest.driver.findElement((By.id("com.neospectracollect:id/sync_data"))).click();
        }

        //close nav
        closeSideMenu((MobileElement) MainTest.driver.findElement(By.id("com.neospectracollect:id/nav_view")));
    }


    //swipe nav (close)
    public void closeSideMenu(MobileElement el) {

        final int ANIMATION_TIME = 200; // ms

        final int PRESS_TIME = 200; // ms

        int edgeBorder;
        PointOption pointOptionStart, pointOptionEnd;

        // init screen variables
        Rectangle rect = el.getRect();

        edgeBorder = 0;

        pointOptionStart = PointOption.point(rect.x + rect.width - edgeBorder,
                rect.y + rect.height / 2);
        pointOptionEnd = PointOption.point(rect.x + edgeBorder,
                rect.y + rect.height / 2);

        // execute swipe using TouchAction
        try {
            new TouchAction(MainTest.driver)
                    .press(pointOptionStart)
                    // a bit more reliable when we add small wait
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                    .moveTo(pointOptionEnd)
                    .release().perform();
        } catch (Exception e) {
            System.err.println("swipeElementAndroid(): TouchAction FAILED\n" + e.getMessage());
            return;
        }

        // always allow swipe action to complete
        try {
            Thread.sleep(ANIMATION_TIME);
        } catch (InterruptedException e) {
            // ignore
        }
    }


    //check if synced
    public boolean isSynced(){

        // synced 1
        // in progress 2
        // paused 3

        WebDriverWait wait = new WebDriverWait(MainTest.driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.neospectracollect:id/sync_status_text")));

        //wait for sync status to be changed (max wait for 10m)
        wait = new WebDriverWait(MainTest.driver, 600);
        wait.until(ExpectedConditions.textMatches(By.id("com.neospectracollect:id/sync_status_text"), Pattern.compile("1")));

        MobileElement element = (MobileElement) MainTest.driver.findElement(By.id("com.neospectracollect:id/sync_status_text"));

        if(element.isDisplayed())
        {
            String RES  = MainTest.driver.findElement(By.id("com.neospectracollect:id/sync_status_text")).getText();

            return RES.equals("1");

        }
        else
        {
            return false;
        }
    }

    //check if not synced
    public boolean isNotSynced(){

        // synced 1
        // in progress 2
        // paused 3

        WebDriverWait wait = new WebDriverWait(MainTest.driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.neospectracollect:id/sync_status_text")));

        //wait for sync status to be changed (max wait for 10m)
        //wait = new WebDriverWait(MainTest.driver, 600);
        //wait.until(ExpectedConditions.textMatches(By.id("com.neospectracollect:id/sync_status_text"), Pattern.compile("1")));

        MobileElement element = (MobileElement) MainTest.driver.findElement(By.id("com.neospectracollect:id/sync_status_text"));

        if(element.isDisplayed())
        {
            String RES  = MainTest.driver.findElement(By.id("com.neospectracollect:id/sync_status_text")).getText();

            return !RES.equals("1");

        }
        else
        {
            return false;
        }
    }

}
