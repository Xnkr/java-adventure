import edu.duke.*;
import java.io.*;
/**
 * Write a description of Assgn here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Assgn2 {
    
    public ImageResource makeInvert(ImageResource inImage){
        ImageResource outImage = new ImageResource(inImage.getWidth(),inImage.getHeight());
        for(Pixel px : outImage.pixels()){
            Pixel inPx = inImage.getPixel(px.getX(),px.getY());
            px.setRed(255 - inPx.getRed());
            px.setBlue(255 - inPx.getBlue());
            px.setGreen(255 - inPx.getGreen());
        }
        return outImage;
    }
    
    public void process(){
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles()){
            ImageResource ir = new ImageResource(f);
            ImageResource invert = makeInvert(ir);
            String newName = "invert-" + ir.getFileName();
            System.out.println(newName);
            invert.setFileName(newName);
            invert.draw();
            invert.save();
        }
    }
}
