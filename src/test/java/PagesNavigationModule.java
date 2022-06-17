import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.IOException;

public class PagesNavigationModule {

    LogInModule M = new LogInModule();

    //#Home Page TO Collect Page
    public boolean homeToCollect() throws InterruptedException, IOException {

        boolean LogStatus = false;

        //check current activity
        String activity = ((AndroidDriver<?>) MainTest.driver).currentActivity();

        if (!activity.equals(".HomeActivity")) {
            //make sure user is signed in
            LogStatus = M.loginWithValidCredentials();
        } else {
            LogStatus = true;
        }


        try {
            //check current page
            MobileElement element = (MobileElement) MainTest.driver.findElement(By.id("com.neospectracollect:id/btn_collect"));

            if (element.isDisplayed()) {
                //current page is home page
                // go to collect page

                if (LogStatus) {
                    MainTest.driver.findElementByXPath("//android.widget.FrameLayout[@content-desc=\"Collect\"]/android.widget.ImageView").click();

                    WebDriverWait wait = new WebDriverWait(MainTest.driver, 10);
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.neospectracollect:id/btn_scan")));

                    element = (MobileElement) MainTest.driver.findElement(By.id("com.neospectracollect:id/btn_scan"));

                    if (element.isDisplayed()) {
                        Helpers.SaveResult("Home to Collect Case Result is : Pass. \r\n");
                        System.out.print("Home to Collect Case Result is : Pass. \r\n");
                    } else {
                        Helpers.SaveResult("Home to Collect Case Result is : Fail. \r\n");
                        System.out.print("Home to Collect Case Result is : Fail. \r\n");
                    }
                    return element.isDisplayed();
                } else {
                    return false;
                }


            } else {
                //current page is not home page
                // go to home page then (start over)

                MainTest.driver.findElementByXPath("//android.widget.FrameLayout[@content-desc=\"Home\"]/android.widget.ImageView").click();
                homeToCollect();
                return true;
            }
        } catch (Exception n) {
            //current page is not home page
            // go to home page then (start over)

            MainTest.driver.findElementByXPath("//android.widget.FrameLayout[@content-desc=\"Home\"]/android.widget.ImageView").click();
            homeToCollect();
            return true;
        }
    }


    //#Collect Page TO Home Page
    public boolean collectToHome() throws InterruptedException, IOException {

        boolean LogStatus = false;

        //check current activity
        String activity = ((AndroidDriver<?>) MainTest.driver).currentActivity();

        if (!activity.equals(".HomeActivity")) {
            //make sure user is signed in
            LogStatus = M.loginWithValidCredentials();
        } else {
            LogStatus = true;
        }

        //check current page
        try {
            MobileElement element = (MobileElement) MainTest.driver.findElement(By.id("com.neospectracollect:id/txt_hd_material"));

            if (element.isDisplayed()) {
                //current page is collect page
                // go to home page
                if (LogStatus) {
                    MainTest.driver.findElementByXPath("//android.widget.FrameLayout[@content-desc=\"Home\"]/android.widget.ImageView").click();

                    WebDriverWait wait = new WebDriverWait(MainTest.driver, 10);
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.neospectracollect:id/btn_collect")));

                    element = (MobileElement) MainTest.driver.findElement(By.id("com.neospectracollect:id/btn_collect"));

                    if (element.isDisplayed()) {
                        Helpers.SaveResult("Collect To Home Case Result is : Pass. \r\n");
                        System.out.print("Collect To Home Case Result is : Pass. \r\n");
                    } else {
                        Helpers.SaveResult("Collect To Home Case Result is : Fail. \r\n");
                        System.out.print("Collect To Home Case Result is : Fail. \r\n");
                    }

                    return element.isDisplayed();
                } else {
                    return false;
                }
            } else {
                //current page is not collect page
                // go to collect page then (start over)

                MainTest.driver.findElementByXPath("//android.widget.FrameLayout[@content-desc=\"Collect\"]/android.widget.ImageView").click();
                collectToHome();
                return true;

            }

        } catch (Exception n) {
            //current page is not collect page
            // go to collect page then (start over)

            MainTest.driver.findElementByXPath("//android.widget.FrameLayout[@content-desc=\"Collect\"]/android.widget.ImageView").click();
            collectToHome();
            return true;
        }
    }


    //#Home Page TO Spectra Page
    public boolean homeToSpectra() throws InterruptedException, IOException {
        boolean LogStatus = false;

        //check current activity
        String activity = ((AndroidDriver<?>) MainTest.driver).currentActivity();

        if (!activity.equals(".HomeActivity")) {
            //make sure user is signed in
            LogStatus = M.loginWithValidCredentials();
        } else {
            LogStatus = true;
        }

        try {
            //check current page
            MobileElement element = (MobileElement) MainTest.driver.findElement(By.id("com.neospectracollect:id/btn_collect"));

            if (element.isDisplayed()) {
                //current page is home page
                // go to spectra page

                if (LogStatus) {
                    MainTest.driver.findElementByXPath("//android.widget.FrameLayout[@content-desc=\"Spectra\"]/android.widget.ImageView").click();

                    WebDriverWait wait = new WebDriverWait(MainTest.driver, 10);
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.neospectracollect:id/txt_hd_material")));

                    element = (MobileElement) MainTest.driver.findElement(By.id("com.neospectracollect:id/txt_hd_material"));

                    if (element.isDisplayed()) {
                        Helpers.SaveResult("Home To Spectra Case Result is : Pass. \r\n");
                        System.out.print("Home To Spectra Case Result is : Pass. \r\n");
                    } else {
                        Helpers.SaveResult("Home To Spectra Case Result is : Fail. \r\n");
                        System.out.print("Home To Spectra Case Result is : Fail. \r\n");
                    }

                    return element.isDisplayed();
                } else {
                    return false;
                }


            } else {
                //current page is not home page
                //go to home page then (start over)

                MainTest.driver.findElementByXPath("//android.widget.FrameLayout[@content-desc=\"Home\"]/android.widget.ImageView").click();
                homeToSpectra();
                return true;
            }
        } catch (Exception e) {
            //current page is not home page
            //go to home page then (start over)

            MainTest.driver.findElementByXPath("//android.widget.FrameLayout[@content-desc=\"Home\"]/android.widget.ImageView").click();
            homeToSpectra();
            return true;
        }
    }


    //#Spectra Page TO Home Page
    public boolean spectraToHome() throws InterruptedException, IOException {

        boolean LogStatus = false;

        //check current activity
        String activity = ((AndroidDriver<?>) MainTest.driver).currentActivity();

        if (!activity.equals(".HomeActivity")) {
            //make sure user is signed in
            LogStatus = M.loginWithValidCredentials();
        } else {
            LogStatus = true;
        }

        //check current page
        try {
            MobileElement element = (MobileElement) MainTest.driver.findElement(By.id("com.neospectracollect:id/txt_hd_material"));

            if (element.isDisplayed()) {
                //current page is spectra page
                // go to home page
                if (LogStatus) {
                    MainTest.driver.findElementByXPath("//android.widget.FrameLayout[@content-desc=\"Home\"]/android.widget.ImageView").click();

                    WebDriverWait wait = new WebDriverWait(MainTest.driver, 10);
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.neospectracollect:id/btn_collect")));

                    element = (MobileElement) MainTest.driver.findElement(By.id("com.neospectracollect:id/btn_collect"));

                    if (element.isDisplayed()) {
                        Helpers.SaveResult("Spectra To Home Case Result is : Pass. \r\n");
                        System.out.print("Spectra To Home Case Result is : Pass. \r\n");
                    } else {
                        Helpers.SaveResult("Spectra To Home Case Result is : Fail. \r\n");
                        System.out.print("Spectra To Home Case Result is : Fail. \r\n");
                    }

                    return element.isDisplayed();
                } else {
                    return false;
                }
            } else {
                //current page is not spectra page
                //go to spectra page then (start over)

                MainTest.driver.findElementByXPath("//android.widget.FrameLayout[@content-desc=\"Spectra\"]/android.widget.ImageView").click();
                spectraToHome();
                return true;

            }

        } catch (Exception n) {
            //current page is not spectra page
            //go to spectra page then (start over)

            MainTest.driver.findElementByXPath("//android.widget.FrameLayout[@content-desc=\"Spectra\"]/android.widget.ImageView").click();
            spectraToHome();
            return true;
        }
    }


    //#Home Page TO Settings Page
    public boolean homeToSettings() throws InterruptedException, IOException {

        boolean LogStatus = false;

        //check current activity
        String activity = ((AndroidDriver<?>) MainTest.driver).currentActivity();

        if (!activity.equals(".HomeActivity")) {
            //make sure user is signed in
            LogStatus = M.loginWithValidCredentials();
        } else {
            LogStatus = true;
        }


        try {
            //check current page
            MobileElement element = (MobileElement) MainTest.driver.findElement(By.id("com.neospectracollect:id/btn_collect"));

            if (element.isDisplayed()) {
                //current page is home page
                //go to settings page

                if (LogStatus) {
                    MainTest.driver.findElementByXPath("//android.widget.FrameLayout[@content-desc=\"Settings\"]/android.widget.ImageView").click();

                    WebDriverWait wait = new WebDriverWait(MainTest.driver, 10);
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.neospectracollect:id/txt_hd_title")));

                    element = (MobileElement) MainTest.driver.findElement(By.id("com.neospectracollect:id/txt_hd_title"));

                    if (element.isDisplayed()) {
                        Helpers.SaveResult("Home To Settings Case Result is : Pass. \r\n");
                        System.out.print("Home To Settings Case Result is : Pass. \r\n");
                    } else {
                        Helpers.SaveResult("Home To Settings Case Result is : Fail. \r\n");
                        System.out.print("Home To Settings Case Result is : Fail. \r\n");
                    }

                    return element.isDisplayed();
                } else {
                    return false;
                }


            } else {
                //current page is not home page
                //go to home page then (start over)

                MainTest.driver.findElementByXPath("//android.widget.FrameLayout[@content-desc=\"Home\"]/android.widget.ImageView").click();
                homeToSettings();
                return true;
            }
        } catch (Exception n) {
            //current page is not home page
            //go to home page then (start over)

            MainTest.driver.findElementByXPath("//android.widget.FrameLayout[@content-desc=\"Home\"]/android.widget.ImageView").click();
            homeToSettings();
            return true;
        }
    }


    //#Settings Page TO Home Page
    public boolean settingsToHome() throws InterruptedException, IOException {

        boolean LogStatus = false;

        //check current activity
        String activity = ((AndroidDriver<?>) MainTest.driver).currentActivity();

        if (!activity.equals(".HomeActivity")) {
            //make sure user is signed in
            LogStatus = M.loginWithValidCredentials();
        } else {
            LogStatus = true;
        }

        //check current page
        try {
            MobileElement element = (MobileElement) MainTest.driver.findElement(By.id("com.neospectracollect:id/txt_hd_title"));

            if (element.isDisplayed()) {
                //current page is settings page
                //go to home page
                if (LogStatus) {
                    MainTest.driver.findElementByXPath("//android.widget.FrameLayout[@content-desc=\"Home\"]/android.widget.ImageView").click();

                    WebDriverWait wait = new WebDriverWait(MainTest.driver, 10);
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.neospectracollect:id/btn_collect")));

                    element = (MobileElement) MainTest.driver.findElement(By.id("com.neospectracollect:id/btn_collect"));

                    if (element.isDisplayed()) {
                        Helpers.SaveResult("Settings To Home Case Result is : Pass. \r\n");
                        System.out.print("Settings To Home Case Result is : Pass. \r\n");
                    } else {
                        Helpers.SaveResult("Settings To Home Case Result is : Fail. \r\n");
                        System.out.print("Settings To Home Case Result is : Fail. \r\n");
                    }

                    return element.isDisplayed();
                } else {
                    return false;
                }
            } else {
                //current page is not settings page
                //go to settings page then (start over)

                MainTest.driver.findElementByXPath("//android.widget.FrameLayout[@content-desc=\"Settings\"]/android.widget.ImageView").click();
                settingsToHome();
                return true;

            }

        } catch (Exception n) {
            //current page is not settings page
            //go to settings page then (start over)

            MainTest.driver.findElementByXPath("//android.widget.FrameLayout[@content-desc=\"Settings\"]/android.widget.ImageView").click();
            settingsToHome();
            return true;
        }
    }


    //#Spectra TO Groups & Result
    public boolean spectraToGroupsAndResult() throws IOException, InterruptedException {

        boolean LogStatus = false;

        //check current activity
        String activity = ((AndroidDriver<?>) MainTest.driver).currentActivity();

        if (!activity.equals(".HomeActivity")) {
            //make sure user is signed in
            LogStatus = M.loginWithValidCredentials();
        } else {
            LogStatus = true;
        }

        //check current page
        try {
            MobileElement element = (MobileElement) MainTest.driver.findElement(By.id("com.neospectracollect:id/txt_hd_material"));

            if (element.isDisplayed()) {
                //current page is spectra page
                //open first group

                if (LogStatus) {

                    //go to group
                    MainTest.driver.findElement(By.id("com.neospectracollect:id/img_arrow")).click();

                    WebDriverWait wait = new WebDriverWait(MainTest.driver, 10);
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.neospectracollect:id/img_arrow")));

                    element = (MobileElement) MainTest.driver.findElement(By.id("com.neospectracollect:id/img_arrow"));

                    if(element.isDisplayed())
                    {
                        //go to result
                        MainTest.driver.findElement(By.id("com.neospectracollect:id/img_arrow")).click();

                        wait = new WebDriverWait(MainTest.driver, 10);
                        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.TextView")));

                        element = (MobileElement) MainTest.driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.TextView"));


                        if(element.isDisplayed())
                        {
                            //go back
                            MainTest.driver.findElement(By.id("com.neospectracollect:id/img_nav_back")).click();

                            wait = new WebDriverWait(MainTest.driver, 10);
                            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.neospectracollect:id/img_nav_back")));

                            //go back
                            MainTest.driver.findElement(By.id("com.neospectracollect:id/img_nav_back")).click();


                            wait = new WebDriverWait(MainTest.driver, 10);
                            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.TextView")));

                            element = (MobileElement) MainTest.driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.TextView"));

                            if (element.isDisplayed())
                            {
                                Helpers.SaveResult("Spectra To Groups And Results Case Result is : Pass. \r\n");
                                System.out.print("Spectra To Groups And Results Case Result is : Pass. \r\n");
                                return true;
                            }
                            else
                            {
                                Helpers.SaveResult("Spectra To Groups And Results Case Result is : Fail. \r\n");
                                System.out.print("Spectra To Groups And Results Case Result is : Fail. \r\n");
                                return false;
                            }
                        }

                        else
                        {
                            Helpers.SaveResult("Spectra To Groups And Results Case Result is : Fail. \r\n");
                            System.out.print("Spectra To Groups And Results Case Result is : Fail. \r\n");
                            return false;
                        }
                    }
                    else
                    {
                        Helpers.SaveResult("Spectra To Groups And Results Case Result is : Fail. \r\n");
                        System.out.print("Spectra To Groups And Results Case Result is : Fail. \r\n");
                        return false;
                    }
                }
                else
                {
                    Helpers.SaveResult("Spectra To Groups And Results Case Result is : Fail. \r\n");
                    System.out.print("Spectra To Groups And Results Case Result is : Fail. \r\n");
                    return false;
                }

                }
            else
            {
                //current page is not spectra page
                //go to spectra page then (start over)

                MainTest.driver.findElementByXPath("//android.widget.FrameLayout[@content-desc=\"Spectra\"]/android.widget.ImageView").click();
                spectraToGroupsAndResult();
                return true;
            }

            }
        catch (Exception e)
        {
            //current page is not spectra page
            //go to spectra page then (start over)

            MainTest.driver.findElementByXPath("//android.widget.FrameLayout[@content-desc=\"Spectra\"]/android.widget.ImageView").click();
            spectraToGroupsAndResult();
            return true;
        }

        }
    }

