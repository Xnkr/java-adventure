package EarthquakeFilterStarterProgram;


/**
 * Write a description of DepthFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DepthFilter implements Filter{
    private double minDepth, maxDepth;
    private String name;
    
    public String getName(){
        return name;
    }
    
    public DepthFilter(String n,double min, double max){
        minDepth = min;
        maxDepth = max;
        name = n;
    }
    
    public boolean satisfies(QuakeEntry qe){
        return qe.getDepth() <= maxDepth && qe.getDepth() >= minDepth;
    }

}