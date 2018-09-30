
/**
 * Write a description of SumDigit here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SumDigit {
    public int Sumdig(int a){
        if(a>0)
            return (a%10+Sumdig(a/10));
        else
            return 0;
        }
}
