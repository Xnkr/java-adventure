import edu.duke.*;
import java.util.*;
/**
 * Write a description of codon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class codon {
    private HashMap<String,Integer> myMap;
    
    public codon(){
        myMap = new HashMap<String,Integer>();
    }
    
    private void buildCodonMap(int start, String dna){
        for(int k=start; k<dna.length(); k+=3){
            if(k+3<dna.length()){
                String sub = dna.substring(k, k+3);
                if(myMap.keySet().contains(sub)){
                    myMap.put(sub,myMap.get(sub)+1);
                }
                else{
                    myMap.put(sub,1);
                }
            }
        }
        System.out.println("Reading frame starting with "+ start +" results in "+myMap.size()+" unique codons");
    }
    
    private String getMostCommonCodon(){
        int count = 0;     
        String mostCommon = "";
        for(String s: myMap.keySet()){
            if(count < myMap.get(s)){
                count = myMap.get(s);
                mostCommon = s;
            }           
        }
        return mostCommon;
    }
    
    private void printCodonCounts(int start, int end){
        for(String s: myMap.keySet()){
            if(myMap.get(s)<=end && myMap.get(s)>=start){
                System.out.println(s + " " + myMap.get(s));
            }
        }
    }
    
    public void tester(){
        FileResource f = new FileResource();
        String str = f.asString().toUpperCase();
        int _start = 7, _end = 7;
        for(int start = 0; start < 3; start ++){
            myMap.clear();
            buildCodonMap(start,str);
            System.out.println("and most common codon is "+getMostCommonCodon()+" with count "+myMap.get(getMostCommonCodon()));
            System.out.println("Counts of codons between "+_start+" and "+_end+" inclusive are:");
            printCodonCounts(_start, _end);
        }        
    }
}
