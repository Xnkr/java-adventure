package EarthquakeFilterStarterProgram;

import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        Filter f = new MagnitudeFilter("mag",3.5 , 4.5); 
        ArrayList<QuakeEntry> m45  = filter(list, f); 
        f = new DepthFilter("dep", -55000.0 , -20000.0);
        ArrayList<QuakeEntry> d  = filter(m45, f); 
//         Filter f = new DistanceFilter("Location",new Location (39.7392, -104.9903),1000*1000); 
//         ArrayList<QuakeEntry> m45  = filter(list, f); 
//         f = new PhraseFilter("Phrase","end","a");
//         ArrayList<QuakeEntry> d  = filter(m45, f); 
        for (QuakeEntry qe: d) { 
            System.out.println(qe);
        } 
        System.out.println(d.size());
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }
    
    
    public void testMAF(){
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter("Mag",1.0 , 4.0));
        maf.addFilter(new DepthFilter("Dep",-180000.0 , -30000.0));
        maf.addFilter(new PhraseFilter("ph","any","o"));
        ArrayList<QuakeEntry> d  = filter(list, maf); 
        for (QuakeEntry qe: d) { 
            System.out.println(qe);
        } 
        System.out.println(d.size());
    }
    
    public void testMatchAllFilter2 (){
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter("mag",0.0 , 5.0 ));
        maf.addFilter(new DistanceFilter("Dis",new Location(55.7308, 9.1153),3000*1000));
        maf.addFilter(new PhraseFilter("ph","any","e"));
        ArrayList<QuakeEntry> d  = filter(list, maf); 
        for (QuakeEntry qe: d) { 
            System.out.println(qe);
        } 
        System.out.println(d.size());
        System.out.println(maf.getName());
    }

}
