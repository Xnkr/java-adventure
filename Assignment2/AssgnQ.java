import java.io.*;
import edu.duke.*;
/**
 * Write a description of AllG here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AssgnQ {
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
    
    public StorageResource storeAll(String dna,String sub){
        int start = 0;
        StorageResource store = new StorageResource();
        while(true){
            int tag = dna.indexOf("atg",start);
            if(tag == -1){
                break;
            }
            int end = findStopIndex(dna,tag);
            
            if(end != dna.length()){
                store.add(sub.substring(tag,end+3));
                start=end+3;
            }
            else{
                start = start + 3;
            }
        }
        return store;
    }
    
     public int ctgFinder (String gene){
        int start = 0,ctg=0;
        while(true){
            int tag = gene.indexOf("ctg",start);
            if(tag == -1){
                break;
            }
            else{
                ctg++;
                start = tag + 2;
            }
        }
        return ctg;
    }
    
    public float cgRatio(String gene){
        gene = gene.toLowerCase();
        int CG = 0,start = 0;
        while(true){
            int tag = gene.indexOf("c",start);
            if(tag == -1 || tag == gene.length())
                break;
            else{
                CG++;
                start = tag+1;
            }
        }
        start = 0;
        while(true){
            int tag = gene.indexOf("g",start);
            if(tag == -1 || tag == gene.length())
                break;
            else{
                CG++;
                start = tag+1;
            }
        }
        float cgR = (float) CG/gene.length();
        return cgR;
    }
    
    public void printGenes(StorageResource sr){
        System.out.println("Genes length > 60:");
        int count=0,max = 0;
        for(String gene: sr.data()){
            if(max < gene.length())
                max = gene.length();
            if(gene.length() > 60){
                System.out.println(gene);
                count++;
            }
        }
        
        System.out.println("No of Genes length > 60: " + count + " max leng: " + max);
        count = 0;
        System.out.println("CGR > 0.35:");
        for(String gene: sr.data()){
            float cgR = cgRatio(gene);
            if(cgR > 0.35){                
                System.out.println(gene);
                count++;
            }
        }
        System.out.println("No of CGR > 0.35: " + count);
      
    }
    
    public void testFinder(){
        FileResource f = new FileResource("GRch38dnapart.fa");
        String sub = f.asString();
        String lowr = sub.toLowerCase();
        StorageResource dna = storeAll(lowr,sub);
        printGenes(dna);
        //System.out.println(dna.size());
        System.out.println(ctgFinder(lowr));
    }
}
