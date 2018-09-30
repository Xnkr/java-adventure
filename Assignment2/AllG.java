import java.io.*;
import edu.duke.*;
/**
 * Write a description of AllG here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AllG {
    public int findStopIndex(String dna, int tag){
        int stop = -1;
        int min = stop;
        if((stop = dna.indexOf("tag",tag+2)) != -1){
            if((stop - tag) % 3 == 0)
               if(stop < min || min == -1) 
                    min = stop;
        }
        if((stop = dna.indexOf("tga",tag+2)) != -1){
            if((stop - tag) % 3 == 0)
                if(stop < min || min == -1)
                    min = stop;
        }
        if((stop = dna.indexOf("taa",tag+2)) != -1){
            if((stop - tag) % 3 == 0)
                if(stop < min || min == -1)
                    min = stop;
        }
        if(min == -1){
            return dna.length();
        }
        return min;
    }
    
    public void printAll(String dna,String sub){
        int start = 0;
        while(true){
            int tag = dna.indexOf("atg",start);
            if(tag == -1){
                break;
            }
            int end = findStopIndex(dna,tag);
            
            if(end != dna.length()){
                System.out.println(sub.substring(tag,end+3));
                start=end+3;
            }
            else{
                start = start + 3;
            }
        }
    }
    
    public void testFinder(String sub){
        String lowr = sub.toLowerCase();
        System.out.println("DNA string is:" + sub);
        System.out.println("Genes found are:");
        printAll(lowr,sub);
        
    }
}
