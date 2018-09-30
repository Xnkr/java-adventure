import java.io.*;
import edu.duke.*;
/**
 * Write a description of Assg2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Assg2 {
    public void findYoutube() {
        URLResource res = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for (String line : res.words()) {
           String lowr = line.toLowerCase();
           int start = lowr.indexOf("youtube.com");
           if(start != -1){
               int ini = line.lastIndexOf("\"",start);
               int end = line.indexOf("\"",start);
               String result = line.substring(ini,end+1);
               System.out.println(result);
           }
        }
    }
}
