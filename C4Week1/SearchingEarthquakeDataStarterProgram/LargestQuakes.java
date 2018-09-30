package SearchingEarthquakeDataStarterProgram;

import java.util.*;
/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LargestQuakes {
    public void findLargestQuakes(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println(list.size());
        //System.out.println(indexOfLargest(list) + " " + list.get(indexOfLargest(list)).getMagnitude());
        ArrayList<QuakeEntry> res = getLargest(list,50);
        System.out.println(res.size());
        for(QuakeEntry qe: res)
        System.out.println(qe);
    }
    
    public int indexOfLargest(ArrayList<QuakeEntry> quakedata){
        int maxIndex = 0;
        double mag = quakedata.get(0).getMagnitude();
        for(int i = 1; i < quakedata.size(); i++){
            double curr_mag = quakedata.get(i).getMagnitude();
            if(curr_mag > mag){
                mag = curr_mag;
                maxIndex = i;
            }
            
        }
        return maxIndex;
    }
    
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakedata, int howmany){
        ArrayList<QuakeEntry> copy = new ArrayList<>(quakedata);
        ArrayList<QuakeEntry> ret = new ArrayList<>();
        if(quakedata.size() <= howmany){
            return quakedata;
        }
        for(int i =0;i<howmany;i++){
            int idx = indexOfLargest(copy);
            ret.add(copy.get(idx));
            copy.remove(idx);
        }
        return ret;
    }
}
