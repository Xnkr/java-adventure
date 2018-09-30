package EarthquakeFilterStarterProgram;


/**
 * Write a description of class MinMaxFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MinMagFilter implements Filter
{
    private double magMin; 
    private String name;
    
    public String getName(){
        return name;
    }
    
    public MinMagFilter(String n, double min) { 
        magMin = min;
        name = n;
    } 

    public boolean satisfies(QuakeEntry qe) { 
        return qe.getMagnitude() >= magMin; 
    } 

}
