
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;

public class CharactersInPlay {
    private ArrayList<String> names;
    private ArrayList<Integer> count;
    public CharactersInPlay(){
        names = new ArrayList<>();
        count = new ArrayList<>();
    }
    public void update(String person){
        int index = names.indexOf(person);
        if(index == -1){
            names.add(person);
            count.add(1);
        }
        else{
            int val = count.get(index);
            count.set(index,val+1);
        }
    }
    public void findAllCharacters(){
        names.clear();
        count.clear();
        FileResource resource = new FileResource();
        for(String line: resource.lines()){
            int period = line.indexOf(".");
            if( period != -1){
                String name = line.substring(0,period);
                update(name);
            }
        }
    }
    public void charactersWithNumParts(int num1, int num2){
        findAllCharacters();
        for(int k = 0; k<names.size(); k++){
            if(count.get(k)>=num1 && count.get(k)<=num2){
                System.out.println(names.get(k) + " " + count.get(k));
            }
        }
    }
    public void tester(){
        findAllCharacters();
        for(int k = 0; k<names.size(); k++){
            if(count.get(k)>1){
                System.out.println(names.get(k) + " " + count.get(k));
            }   
        }
    }
    
}
