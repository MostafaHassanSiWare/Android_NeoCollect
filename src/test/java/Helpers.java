import net.sourceforge.tess4j.TesseractException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Helpers {

    //get toast result
    public static String GetToast() throws IOException, TesseractException {
        File file  = ((TakesScreenshot)MainTest.driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("Screenshot.png"));

        String ScreenResult = GetOCR.readScreen();

        String[] Lines = ScreenResult.split("\\r?\\n");

        //System.out.println(ScreenResult);

        return Lines[Lines.length-1];
    }


    //get screen result
    public static String GetScreen() throws IOException, TesseractException {
        File file  = ((TakesScreenshot)MainTest.driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("Screenshot.png"));

        return GetOCR.readScreen();
    }


    //create file and write result to it
    public static void SaveResult (String result) throws IOException {
        File testResult = new File("TestResult.txt");
        testResult.createNewFile(); // if file already exists will do nothing
        //FileOutputStream oFile = new FileOutputStream(yourFile, false);

        FileWriter fr = new FileWriter(testResult, true);

        fr.write(result);
        fr.close();
    }

    //get random number between 2 values
    public static int GetRandomValue (int minBoundary, int maxBoundary)
    {
        Random rand = new Random();
        return rand.nextInt(maxBoundary-minBoundary) + minBoundary;
    }


    //get random boolean value
    public static boolean GetRandomBoolean ()
    {
        return Math.random() < 0.5;
    }
}
