package EarthquakeFilterStarterProgram;


/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter{
    private String where, phrase;
    private String name;
    
    public String getName(){
        return name;
    }
    
    public PhraseFilter(String n, String wh, String ph){
        where = wh;
        phrase = ph;
        name = n;
    }
    
    public boolean satisfies(QuakeEntry qe){
        switch(where){
                case "any":
                    if(qe.getInfo().contains(phrase)){
                        return true;
                    }
                    break;
                case "start":
                    if(qe.getInfo().startsWith(phrase)){
                        return true;
                    }
                    break;
                case "end":
                    if(qe.getInfo().endsWith(phrase)){
                        return true;
                    }
                    break;
        }
        return false;
    }

}
