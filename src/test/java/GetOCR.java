import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GetOCR {

    public static String readScreen() throws TesseractException, IOException {
        String result = null;
        File imageFile = new File("Screenshot.png");

        ITesseract instance = new Tesseract();

        File tessDataFolder = LoadLibs.extractTessResources("tessdata");

        instance.setDatapath(tessDataFolder.getAbsolutePath());

        result = instance.doOCR(imageFile);
        return result;
    }

    public static void Invert() {
        BufferedImage img = null;
        File f = null;

        // read image
        try {
            f = new File("Screenshot.png");
            img = ImageIO.read(f);
        }
        catch (IOException e) {
            System.out.println(e);
        }

        // Get image width and height
        int width = img.getWidth();
        int height = img.getHeight();

        // Convert to negative
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int p = img.getRGB(x, y);
                int a = (p >> 24) & 0xff;
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;

                // subtract RGB from 255
                r = 255 - r;
                g = 255 - g;
                b = 255 - b;

                // set new RGB value
                p = (a << 24) | (r << 16) | (g << 8) | b;
                img.setRGB(x, y, p);
            }
        }

        // write image
        try {
            f = new File("Screenshot.png");
            ImageIO.write(img, "png", f);
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void BlackAndWhite() {
        try {

            File input = new File("Screenshot.png");
            BufferedImage image = ImageIO.read(input);

            BufferedImage result = new BufferedImage(
                    image.getWidth(),
                    image.getHeight(),
                    BufferedImage.TYPE_BYTE_BINARY);

            Graphics2D graphic = result.createGraphics();
            graphic.drawImage(image, 0, 0, Color.WHITE, null);
            graphic.dispose();

            File output = new File("Screenshot.png");
            ImageIO.write(result, "png", output);

        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void Binary() throws IOException {
        BufferedImage img = null;
        String fileName = "Screenshot.png";

        try {
            //Read in new image file
            img = ImageIO.read(new File(fileName));
        }
        catch (IOException e){
            System.out.println("Error: "+e);
        }
        int h=img.getHeight();
        int w=img.getWidth();


        BufferedImage bufferedImage = new BufferedImage(w,h, BufferedImage.TYPE_INT_RGB);
        if (img == null) {
            System.out.println("No image loaded");
        }
        else {
            for(int i=0;i<w;i++)
            {
                for(int j=0;j<h;j++)
                {


                    //Get RGB Value
                    int val = img.getRGB(i, j);
                    //Convert to three separate channels
                    int r = (0x00ff0000 & val) >> 16;
                    int g = (0x0000ff00 & val) >> 8;
                    int b = (0x000000ff & val);
                    int m=(r+g+b);
                    //(255+255+255)/2 =283 middle of dark and light
                    if(m>=383)
                    {
                        // for light color it set white
                        bufferedImage.setRGB(i, j, Color.WHITE.getRGB());
                    }
                    else{
                        // for dark color it will set black
                        bufferedImage.setRGB(i, j, 0);
                    }
                }
            }
        }


        File file = new File("Screenshot.png");
        ImageIO.write(bufferedImage, "png", file);
    }

}
