import edu.duke.*;
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestCaesarCipher {
    public void simpleTests(){
        FileResource fr = new FileResource("message1.txt");
        String input = fr.asString();
        OOCaesarCipher cc = new OOCaesarCipher(18);
        String enc = cc.encrypt(input);
        String dec = breakCaesarCipher(enc);
        System.out.println("Enc = " + enc);
        System.out.println("Dec = " + dec);
    }
    public String breakCaesarCipher(String input){
        CaesarBreaker cb = new CaesarBreaker();
        int[] freqs = cb.countLetters(input);
        int maxDex = cb.maxIndex(freqs);
        int dkey = maxDex - 4;
        if(maxDex < 4){
            dkey = 26 - (4-maxDex);
        }
        OOCaesarCipher cc = new OOCaesarCipher(26-dkey);
        String dec = cc.encrypt(input);
        return dec;        
    }
}
