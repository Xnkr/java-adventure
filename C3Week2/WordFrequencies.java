import edu.duke.*;
import java.util.*;

/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordFrequencies {
        private ArrayList<String> myWords;
        private ArrayList<Integer> myFreqs;
        public WordFrequencies(){
            myWords = new ArrayList<>();
            myFreqs = new ArrayList<>();
        }
        public void findUnique(){
            myWords.clear();
            myFreqs.clear();
            FileResource f = new FileResource();
            for(String s: f.words()){
                s = s.toLowerCase();
                int index = myWords.indexOf(s);
                if(index == -1){
                    myWords.add(s);
                    myFreqs.add(1);
                }
                else{
                    int val = myFreqs.get(index);
                    myFreqs.set(index,val+1);
                }
            }
        }
        public int findIndexOfMax(){
            int maxValue = 0;
            int maxIndex = 0;
            for(int k = 0; k<myFreqs.size(); k++){
                if(myFreqs.get(k) > maxValue){
                    maxValue = myFreqs.get(k);
                    maxIndex = k;
                }
                
            }
            return maxIndex;
        }
        public void tester(){
            findUnique();
            
            for(int k=0; k<myWords.size(); k++){
                System.out.println(myFreqs.get(k) + " " + myWords.get(k));
            }
            System.out.println("High occurance: \t" + myWords.get(findIndexOfMax()) + " " + myFreqs.get(findIndexOfMax()));
            System.out.println("Unique: " + myWords.size());
        }
}
