package EarthquakeFilterStarterProgram;


/**
 * Write a description of MagnitudeFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MagnitudeFilter implements Filter{
    private double minMag, maxMag;
    private String name;
    
    public String getName(){
        return name;
    }
    
    public MagnitudeFilter(String n,double min, double max){
        minMag = min;
        maxMag = max;
        name = n;
    }
    
    public boolean satisfies(QuakeEntry qe){
        return qe.getMagnitude() <= maxMag && qe.getMagnitude() >= minMag;
    }

}
