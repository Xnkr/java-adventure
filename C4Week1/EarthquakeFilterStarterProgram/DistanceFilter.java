package EarthquakeFilterStarterProgram;


/**
 * Write a description of DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter{
    private Location myLoc;
    private double distMax;
    private String name;
    
    public String getName(){
        return name;
    }
    
    public DistanceFilter(String n,Location loc, double dist){
        myLoc = loc;
        distMax = dist;
        //name = n;
    }
    
    public boolean satisfies(QuakeEntry qe){
        return (qe.getLocation().distanceTo(myLoc) < distMax);
    }

}
