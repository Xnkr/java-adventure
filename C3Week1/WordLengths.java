import edu.duke.*;

/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordLengths {
    public void countWordLengths(FileResource resource, int[] counts){
        int k=0;
        for(String s: resource.words()){
            k=s.length();
            if (k >= counts.length) {  	    	   
                k = counts.length - 1;  	  	
            }
            if(!Character.isLetter(s.charAt(0))){
                k--;
            }
            if(!Character.isLetter(s.charAt(s.length()-1))){
                k--;
            }
            if(k>0)
                counts[k]++;
            
        }
        for(k=0;k<counts.length;k++){
            System.out.println(k + " letter words appear " + counts[k] + " times");
        }
    }
    public int indexOfMax(int[] values){
        int max=0,i,idx=-1;
        for(i=0;i<values.length;i++){
            if(values[i]>max){
                max=values[i];
                idx = i;
            }
        }
        return idx;
    } 
    public void testCountWordLengths(){
        FileResource f = new FileResource("data/manywords.txt");
        int[] counts = new int[31];
        countWordLengths(f,counts);
        int[] values = counts;
        int max = indexOfMax(values);
        System.out.println(max + " most common length");
    }
    
}
