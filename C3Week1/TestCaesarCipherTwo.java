import edu.duke.*;
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestCaesarCipherTwo {
    public void simpleTests (){
        FileResource fr = new FileResource("message1.txt");
        String input = fr.asString();
        CaesarCipherTwo cct = new CaesarCipherTwo(17,3);
        String enc = cct.encrypt(input);
        String dec = breakCaesarCipher(enc);
        System.out.println("Enc = " + enc);
        System.out.println("Dec = " + dec);
    }
    public String breakCaesarCipher(String input){
        CaesarBreaker cb = new CaesarBreaker();
        String sub1 = cb.halfOfString(input,0);
        int key1 = cb.getKey(sub1);
        String sub2 = cb.halfOfString(input,1);
        int key2 = cb.getKey(sub2);
        System.out.println(key1 + " " + key2);
        CaesarCipherTwo cct = new CaesarCipherTwo(26-key1, 26-key2);
        return cct.encrypt(input);  
    }
}
