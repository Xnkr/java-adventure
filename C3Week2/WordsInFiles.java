import edu.duke.*;
import java.util.*;
import java.io.*;
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordsInFiles {
    private HashMap<String, ArrayList<String>> myMap;
    public WordsInFiles(){
        myMap = new HashMap<String, ArrayList<String>>();
    }
    private void addWordsFromFile(File f){
        FileResource res = new FileResource(f);
        for(String s: res.words()){
            if(!myMap.keySet().contains(s)){
                myMap.put(s, new ArrayList<String>());
                myMap.get(s).add(f.getName());
            }
            else{
                int idx = myMap.get(s).indexOf(f.getName());
                if(idx == -1){
                    myMap.get(s).add(f.getName());
                }
            }
        }       
    }
    private void buildWordFileMap(){
        myMap.clear();
        DirectoryResource res = new DirectoryResource();
        for(File f: res.selectedFiles()){
            addWordsFromFile(f);
        }
    }
    private int maxNumber(){
        int maxNumber=0;
        for(String s: myMap.keySet()){
            if( maxNumber < myMap.get(s).size()){
                maxNumber = myMap.get(s).size();
            }
        }
        return maxNumber;
    }
    private ArrayList<String> wordsInNumFiles(int number){
        ArrayList<String> words = new ArrayList<>();
        for(String s: myMap.keySet()){
            if( number == myMap.get(s).size()){
                words.add(s);
            }
        }
        return words;
    }
    private void printFilesIn(String word){
        if(myMap.keySet().contains(word)){
            for(int k = 0; k < myMap.get(word).size(); k++){
                System.out.println(myMap.get(word).get(k));
            }
        }
        else{
            System.out.println("******");
        }        
    }
    public void tester(){
        buildWordFileMap();
        System.out.println("Max number: "+ maxNumber());
        ArrayList<String> words = wordsInNumFiles(4);
        System.out.println(words.size());
        for(int k = 0; k<words.size(); k++){
//            System.out.println(words.get(k));                
        }
        printFilesIn("tree");
    }
}
