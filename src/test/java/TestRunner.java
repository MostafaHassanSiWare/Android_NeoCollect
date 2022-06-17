import net.sourceforge.tess4j.TesseractException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class TestRunner {



    //define modules
    LogInModule LIM = new LogInModule();
    PagesNavigationModule PNM = new PagesNavigationModule();
    ConnectionModule CM = new ConnectionModule();
    CollectionModule LM = new CollectionModule();
    SynchronizationModule SM = new SynchronizationModule();


    //test steps/sequence goes here


    //log in cases
    public void executeAllLogInCases() throws InvocationTargetException, IllegalAccessException, TesseractException, IOException, InterruptedException {
        //LIM.forgetPassword();
        LIM.passwordFieldHide();
        LIM.passwordFieldShow();
        LIM.emptyFields();
        LIM.loginWithNoPassword();
        LIM.loginWithInvalidUser();
        LIM.loginWithInvalidPassword();
        LIM.loginWithInvalidCredentials();
        LIM.loginWithValidCredentials();
        LIM.logoutCancel();
        LIM.logoutConfirm();
        LIM.switchToDevEnvironment();
    }

    //navigation cases
    public void executeAllPagesNavigationCases() throws IOException, InterruptedException {
        PNM.homeToCollect();
        PNM.collectToHome();
        PNM.homeToSpectra();
        PNM.spectraToHome();
        PNM.homeToSettings();
        PNM.settingsToHome();
        PNM.spectraToGroupsAndResult();

    }

    //connection cases
    public void executeAllConnectionCases() throws IOException, InterruptedException {
        CM.connectToScanner();
        CM.disconnectScanner();
    }


    //collection cases
    public void executeAllCollectionCases() throws IOException, InterruptedException {
        LM.collectSample();
    }

    //synchronization cases
    public void executeAllSynchronizationCases() throws IOException, InterruptedException {
        SM.normalSync();
        SM.fullSync();
    }



}

  /*  public void callAllLogInCases() throws InvocationTargetException, IllegalAccessException {
        Method[] methods = LogInModule.class.getMethods();
        Object o = new Object();
        for (Method method : methods) {
            //Thread.sleep(10000);
            method.invoke(LIM, null);
        }
    }*/