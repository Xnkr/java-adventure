package EarthquakeFilterStarterProgram;

import java.util.ArrayList;
/**
 * Write a description of MatchAllFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MatchAllFilter implements Filter{
    private ArrayList<Filter> maf;
    private ArrayList<String> names;
    
    public String getName(){
        return names.toString();
    }
    public MatchAllFilter(){
        maf = new ArrayList<Filter>();
        names = new ArrayList<>();
    }
    
    public void addFilter(Filter f){
        maf.add(f);
        names.add(f.getName());
    }
    
    public boolean satisfies(QuakeEntry qe){
        for(Filter f: maf){
            if(!f.satisfies(qe)){
                return false;
            }
        }
        return true;
    }
}
