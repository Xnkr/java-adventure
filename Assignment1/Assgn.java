import edu.duke.*;
import java.io.*;
/**
 * Write a description of Assgn here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Assgn {
    
    public ImageResource makeGray(ImageResource inImage){
        ImageResource outImage = new ImageResource(inImage.getWidth(),inImage.getHeight());
        for(Pixel px : outImage.pixels()){
            Pixel inPx = inImage.getPixel(px.getX(),px.getY());
            int avg = (inPx.getRed()+inPx.getGreen()+inPx.getBlue())/3;
            px.setRed(avg);
            px.setBlue(avg);
            px.setGreen(avg);
        }
        return outImage;
    }
    
    public void process(){
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles()){
            ImageResource ir = new ImageResource(f);
            ImageResource gray = makeGray(ir);
            String newName = "y-" + ir.getFileName();
            String newNe = "ccc-" + newName;
            gray.setFileName(newName);
            gray.save();
            gray.setFileName(newNe);
            gray.save();
        }
    }
}
