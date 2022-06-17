import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import net.sourceforge.tess4j.TesseractException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.IOException;
import java.util.Objects;

public class LogInModule {


    //#check forget password button case
    public boolean forgetPassword () throws InterruptedException, IOException, TesseractException {

        //check if current activity is login activity
        String activity = ((AndroidDriver<?>) MainTest.driver).currentActivity();

        if(activity.equals(".LoginActivity"))
        {
            MainTest.driver.findElement(By.id("com.neospectracollect:id/forget_password")).click();

            Thread.sleep(10000);

            String url = Helpers.GetScreen();

            if(url.contains("portal.neospectra.cloud") && url.contains("Need help signing in?"))
            {
                Helpers.SaveResult("Forget Password Case Result is : Pass. \r\n");
                System.out.print("Forget Password Case Result is : Pass. \r\n");
                return true;
            }
            else
            {
                Helpers.SaveResult("Forget Password Case Result is : Fail. \r\n");
                System.out.print("Forget Password Case Result is : Fail. \r\n");
                return false;
            }
        }
        else
        {
            //log out first
            logoutConfirm();
            forgetPassword();
            return true;
        }
    }


    //#check show password button (hide) case
    public boolean passwordFieldHide() throws IOException, InterruptedException {

        //check if current activity is login activity
        String activity = ((AndroidDriver<?>) MainTest.driver).currentActivity();

        if(activity.equals(".LoginActivity"))
        {
            MainTest.driver.findElement(By.id("com.neospectracollect:id/txt_email")).clear();
            MainTest.driver.findElement(By.id("com.neospectracollect:id/txt_password")).clear();
            MainTest.driver.findElement(By.id("com.neospectracollect:id/txt_password")).sendKeys("12345");
            //result
            //assertNotEquals("12345", driver.findElement(By.id("com.neospectracollect:id/txt_password")).getText());

            if(!MainTest.driver.findElement(By.id("com.neospectracollect:id/txt_password")).getText().equals("12345"))
            {
                Helpers.SaveResult("Password Field Hide Case Result is : Pass. \r\n");
                System.out.print("Password Field Hide Case Result is : Pass. \r\n");
            }
            else
            {
                Helpers.SaveResult("Password Field Hide Case Result is : Fail. \r\n");
                System.out.print("Password Field Hide Case Result is : Fail. \r\n");
            }
            return !MainTest.driver.findElement(By.id("com.neospectracollect:id/txt_password")).getText().equals("12345");
        }
        else
        {
            //log out first
            logoutConfirm();
            passwordFieldHide();
            return true;
        }
    }


    //#check show password button (show) case
    public boolean passwordFieldShow() throws IOException, InterruptedException {

        //check if current activity is login activity
        String activity = ((AndroidDriver<?>) MainTest.driver).currentActivity();

        if(activity.equals(".LoginActivity"))
        {
            MainTest.driver.findElement(By.id("com.neospectracollect:id/txt_email")).clear();
            MainTest.driver.findElement(By.id("com.neospectracollect:id/txt_password")).clear();
            MainTest.driver.findElement(By.id("com.neospectracollect:id/txt_password")).sendKeys("12345");
            MainTest.driver.findElement(By.id("com.neospectracollect:id/img_password")).click();

            //result
            //assertEquals("12345", driver.findElement(By.id("com.neospectracollect:id/txt_password")).getText());

            if(MainTest.driver.findElement(By.id("com.neospectracollect:id/txt_password")).getText().equals("12345"))
            {
                Helpers.SaveResult("Password Field Show Case Result is : Pass. \r\n");
                System.out.print("Password Field Show Case Result is : Pass. \r\n");
            }
            else
            {
                Helpers.SaveResult("Password Field Show Case Result is : Fail. \r\n");
                System.out.print("Password Field Show Case Result is : Fail. \r\n");
            }
            return MainTest.driver.findElement(By.id("com.neospectracollect:id/txt_password")).getText().equals("12345");
        }
        else
        {
            //log out first
            logoutConfirm();
            passwordFieldShow();
            return true;
        }
    }


    //#check empty fields case  //emulator
    public boolean emptyFields() throws TesseractException, IOException, InterruptedException {

        //check if current activity is login activity
        String activity = ((AndroidDriver<?>) MainTest.driver).currentActivity();

        if(activity.equals(".LoginActivity"))
        {
            String Res = null;
            MainTest.driver.findElement(By.id("com.neospectracollect:id/btn_submit")).click();
            Thread.sleep(1000);
            Res = Helpers.GetToast();
            //result
            //assertEquals("Please Enter Email ID", Res);
            if(Res.equals("Please Enter Email ID"))
            {
                Helpers.SaveResult("Empty Fields Case Result is : Pass. \r\n");
                System.out.print("Empty Fields Case Result is : Pass. \r\n");
            }
            else
            {
                Helpers.SaveResult("Empty Fields Case Result is : Fail. \r\n");
                System.out.print("Empty Fields Case Result is : Fail. \r\n");
            }
            return Res.equals("Please Enter Email ID");
        }
        else
        {
            //log out first
            logoutConfirm();
            emptyFields();
            return true;
        }
    }


    //#check login with no password case  //emulator
    public boolean loginWithNoPassword() throws TesseractException, IOException, InterruptedException {

        //check if current activity is login activity
        String activity = ((AndroidDriver<?>) MainTest.driver).currentActivity();

        if(activity.equals(".LoginActivity"))
        {
            String Res = null;
            MainTest.driver.findElement(By.id("com.neospectracollect:id/txt_email")).clear();
            MainTest.driver.findElement(By.id("com.neospectracollect:id/txt_password")).clear();
            MainTest.driver.findElement(By.id("com.neospectracollect:id/txt_email")).sendKeys("mostafa.hassan+farm@si-ware.com");
            MainTest.driver.findElement(By.id("com.neospectracollect:id/btn_submit")).click();
            Thread.sleep(1000);
            Res = Helpers.GetToast();
            //result
            //assertEquals("Please Enter Password", Res);

            if(Res.equals("Please Enter Password"))
            {
                Helpers.SaveResult("Log in With no Password Case Result is : Pass. \r\n");
                System.out.print("Log in With no Password Case Result is : Pass. \r\n");
            }
            else
            {
                Helpers.SaveResult("Log in With no Password Case Result is : Fail. \r\n");
                System.out.print("Log in With no Password Case Result is : Fail. \r\n");
            }
            return Res.equals("Please Enter Password");
        }
        else
        {
            //log out first
            logoutConfirm();
            loginWithNoPassword();
            return true;
        }
    }


    //#check invalid user case  //emulator
    public boolean loginWithInvalidUser() throws TesseractException, IOException, InterruptedException {

        //check if current activity is login activity
        String activity = ((AndroidDriver<?>) MainTest.driver).currentActivity();

        if(activity.equals(".LoginActivity"))
        {
            String Res = null;
            MainTest.driver.findElement(By.id("com.neospectracollect:id/txt_email")).clear();
            MainTest.driver.findElement(By.id("com.neospectracollect:id/txt_password")).clear();
            MainTest.driver.findElement(By.id("com.neospectracollect:id/txt_email")).sendKeys("mohamed@si-ware.com");
            MainTest.driver.findElement(By.id("com.neospectracollect:id/txt_password")).sendKeys("Temp@1234");
            MainTest.driver.findElement(By.id("com.neospectracollect:id/btn_submit")).click();

            Res = Helpers.GetToast();
            while (!Objects.equals(Res, "Please Enter Valid Login Details"))
            {
                Res = Helpers.GetToast();
            }
            //result
            //assertEquals("Please Enter Valid Login Details", Res);

            if(Res.equals("Please Enter Valid Login Details"))
            {
                Helpers.SaveResult("Log in With Invalid User Case Result is : Pass. \r\n");
                System.out.print("Log in With Invalid User Case Result is : Pass. \r\n");
            }
            else
            {
                Helpers.SaveResult("Log in With Invalid User Case Result is : Fail. \r\n");
                System.out.print("Log in With Invalid User Case Result is : Fail. \r\n");
            }
            return Res.equals("Please Enter Valid Login Details");
        }
        else
        {
            //log out first
            logoutConfirm();
            loginWithInvalidUser();
            return true;
        }
    }


    //#check invalid password case  //emulator
    public boolean loginWithInvalidPassword() throws TesseractException, IOException, InterruptedException {

        //check if current activity is login activity
        String activity = ((AndroidDriver<?>) MainTest.driver).currentActivity();

        if(activity.equals(".LoginActivity"))
        {
            String Res = null;
            MainTest.driver.findElement(By.id("com.neospectracollect:id/txt_email")).clear();
            MainTest.driver.findElement(By.id("com.neospectracollect:id/txt_password")).clear();
            MainTest.driver.findElement(By.id("com.neospectracollect:id/txt_email")).sendKeys("mostafa.hassan+farm@si-ware.com");
            MainTest.driver.findElement(By.id("com.neospectracollect:id/txt_password")).sendKeys("1234");
            MainTest.driver.findElement(By.id("com.neospectracollect:id/btn_submit")).click();

            Res = Helpers.GetToast();
            while (!Objects.equals(Res, "Please Enter Valid Login Details"))
            {
                Res = Helpers.GetToast();
            }
            //result
            //assertEquals("Please Enter Valid Login Details", Res);

            if(Res.equals("Please Enter Valid Login Details"))
            {
                Helpers.SaveResult("Log in With Invalid Password Case Result is : Pass. \r\n");
                System.out.print("Log in With Invalid Password Case Result is : Pass. \r\n");
            }
            else
            {
                Helpers.SaveResult("Log in With Invalid Password Case Result is : Fail. \r\n");
                System.out.print("Log in With Invalid Password Case Result is : Fail. \r\n");
            }
            return Res.equals("Please Enter Valid Login Details");
        }
        else
        {
            //log out first
            logoutConfirm();
            loginWithInvalidPassword();
            return true;
        }
    }


    //#check invalid user & password case  //emulator
    public boolean loginWithInvalidCredentials() throws TesseractException, IOException, InterruptedException {

        //check if current activity is login activity
        String activity = ((AndroidDriver<?>) MainTest.driver).currentActivity();

        if(activity.equals(".LoginActivity"))
        {
            String Res = null;
            MainTest.driver.findElement(By.id("com.neospectracollect:id/txt_email")).clear();
            MainTest.driver.findElement(By.id("com.neospectracollect:id/txt_password")).clear();
            MainTest.driver.findElement(By.id("com.neospectracollect:id/txt_email")).sendKeys("mohamed@si-ware.com");
            MainTest.driver.findElement(By.id("com.neospectracollect:id/txt_password")).sendKeys("1234");
            MainTest.driver.findElement(By.id("com.neospectracollect:id/btn_submit")).click();
            Res = Helpers.GetToast();
            while (!Objects.equals(Res, "Please Enter Valid Login Details"))
            {
                Res = Helpers.GetToast();
            }
            //result
            //assertEquals("Please Enter Valid Login Details", Res);

            if(Res.equals("Please Enter Valid Login Details"))
            {
                Helpers.SaveResult("Log in With Invalid Credentials Case Result is : Pass. \r\n");
                System.out.print("Log in With Invalid Credentials Case Result is : Pass. \r\n");
            }
            else
            {
                Helpers.SaveResult("Log in With Invalid Credentials Case Result is : Fail. \r\n");
                System.out.print("Log in With Invalid Credentials Case Result is : Fail. \r\n");
            }
            return Res.equals("Please Enter Valid Login Details");
        }
        else
        {
            //log out first
            logoutConfirm();
            loginWithInvalidCredentials();
            return true;
        }
    }


    //#check valid user & password case
    public boolean loginWithValidCredentials() throws InterruptedException, IOException {

        //check if current activity is login activity
        String activity = ((AndroidDriver<?>) MainTest.driver).currentActivity();

        if(activity.equals(".LoginActivity"))
        {
            MainTest.driver.findElement(By.id("com.neospectracollect:id/txt_email")).clear();
            MainTest.driver.findElement(By.id("com.neospectracollect:id/txt_password")).clear();
            MainTest.driver.findElement(By.id("com.neospectracollect:id/txt_email")).sendKeys("mostafa.hassan+farm@si-ware.com");
            MainTest.driver.findElement(By.id("com.neospectracollect:id/txt_password")).sendKeys("Temp@1234");
            MainTest.driver.findElement(By.id("com.neospectracollect:id/btn_submit")).click();
            Thread.sleep(15000);

            //assertEquals(".HomeActivity", ((AndroidDriver<?>) driver).currentActivity());

            if(((AndroidDriver<?>) MainTest.driver).currentActivity().equals(".HomeActivity"))
            {
                Helpers.SaveResult("Log in With Valid Credentials Case Result is : Pass. \r\n");
                System.out.print("Log in With Valid Credentials Case Result is : Pass. \r\n");
            }
            else
            {
                Helpers.SaveResult("Log in With Valid Credentials Case Result is : Fail. \r\n");
                System.out.print("Log in With Valid Credentials Case Result is : Fail. \r\n");
            }
            return ((AndroidDriver<?>) MainTest.driver).currentActivity().equals(".HomeActivity");
        }
        else
        {
            //log out first
            logoutConfirm();
            loginWithValidCredentials();
            return true;
        }
    }


    //#check log out (cancel) case
    public boolean logoutCancel() throws IOException, InterruptedException {

        LogInModule M = new LogInModule();
        boolean LogStatus = false;

        //check if current activity is Home activity

        if (!((AndroidDriver<?>) MainTest.driver).currentActivity().equals(".HomeActivity")) {
            //make sure user is signed in
            LogStatus = M.loginWithValidCredentials();
        } else {
            LogStatus = true;
        }

        if(LogStatus)
        {
            WebDriverWait wait = new WebDriverWait(MainTest.driver, 2);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.neospectracollect:id/nav_drawar")));
            MainTest.driver.findElement((By.id("com.neospectracollect:id/nav_drawar"))).click();

            wait = new WebDriverWait(MainTest.driver, 1);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.neospectracollect:id/nav_logout")));

            MainTest.driver.findElement(By.id("com.neospectracollect:id/nav_logout")).click();

            wait = new WebDriverWait(MainTest.driver, 1);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("android:id/button2")));

            MainTest.driver.findElement(By.id("android:id/button2")).click();

            Thread.sleep(1000);

            if(!((AndroidDriver<?>) MainTest.driver).currentActivity().equals(".LoginActivity"))
            {
                Helpers.SaveResult("Log out Cancel Case Result is : Pass. \r\n");
                System.out.print("Log out Cancel Case Result is : Pass. \r\n");
                return true;
            }
            else
            {
                Helpers.SaveResult("Log out Cancel Case Result is : Fail. \r\n");
                System.out.print("Log out Cancel Case Result is : Fail. \r\n");
                return false;
            }
        }
        else
        {
            //log in first
            M.loginWithValidCredentials();
            logoutCancel();
            return true;
        }
    }


    //#check log out (confirm) case
    public boolean logoutConfirm () throws IOException, InterruptedException {

        LogInModule M = new LogInModule();
        boolean LogStatus = false;

        //check if current activity is Home activity

        if (!((AndroidDriver<?>) MainTest.driver).currentActivity().equals(".HomeActivity")) {
            //make sure user is signed in
            LogStatus = M.loginWithValidCredentials();
        } else {
            LogStatus = true;
        }


        if(LogStatus)
        {
            WebDriverWait wait = new WebDriverWait(MainTest.driver, 2);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.neospectracollect:id/nav_drawar")));

            MainTest.driver.findElement((By.id("com.neospectracollect:id/nav_drawar"))).click();

            wait = new WebDriverWait(MainTest.driver, 1);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.neospectracollect:id/nav_logout")));

            MainTest.driver.findElement(By.id("com.neospectracollect:id/nav_logout")).click();

            wait = new WebDriverWait(MainTest.driver, 1);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("android:id/button1")));

            MainTest.driver.findElement(By.id("android:id/button1")).click();


            Thread.sleep(5000);

            if(((AndroidDriver<?>) MainTest.driver).currentActivity().equals(".LoginActivity"))
            {
                Helpers.SaveResult("Log out Confirm Case Result is : Pass. \r\n");
                System.out.print("Log out Confirm Case Result is : Pass. \r\n");
                return true;
            }
            else
            {
                Helpers.SaveResult("Log out Confirm Case Result is : Fail. \r\n");
                System.out.print("Log out Confirm Case Result is : Fail. \r\n");
                return false;
            }

        }
        else
        {
            //log in first
            M.loginWithValidCredentials();
            logoutConfirm();
            return true;
        }
    }


    //#check switching to dev environment case  //emulator
    public boolean switchToDevEnvironment() throws InterruptedException, TesseractException, IOException {
        String Res = null;

        //check if current activity is login activity
        String activity = ((AndroidDriver<?>) MainTest.driver).currentActivity();

        if(activity.equals(".LoginActivity"))
        {
            for(int i = 0; i < 10; i++)
            {
                Thread.sleep(500);
                MainTest.driver.findElement(By.id("com.neospectracollect:id/logo")).click();
            }

                Thread.sleep(500);

                Res = Helpers.GetToast();

                //result
                //assertEquals("Please Enter Email ID", Res);

                if(Res.equals("Switched to dev mode"))
                {
                    Helpers.SaveResult("Switch to Dev Environment Case Result is : Pass. \r\n");
                    System.out.print("Switch to Dev Environment Case Result is : Pass. \r\n");
                    return true;
                }
                else
                {
                    Helpers.SaveResult("Switch to Dev Environment Case Result is : Fail. \r\n");
                    System.out.print("Switch to Dev Environment Case Result is : Fail. \r\n");
                    return false;
                }
        }
        else
        {
            //log out first
            logoutConfirm();
            switchToDevEnvironment();
            return true;
        }
    }



}
