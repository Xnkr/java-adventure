import edu.duke.*;
/**
 * Write a description of OOCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class OOCaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    public OOCaesarCipher (int key){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
        mainKey = key;
    }
    public String encrypt(String input){
        StringBuilder encrypted = new StringBuilder(input);
        for(int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            int idx;
            idx = alphabet.indexOf(currChar);
            if(idx != -1){
                char newChar = shiftedAlphabet.charAt(idx);
                encrypted.setCharAt(i, newChar);
                
            }
        }
        return encrypted.toString();
    }
    public String decrypt(String input){
        OOCaesarCipher  cc = new OOCaesarCipher(26-mainKey);
        String msg = cc.encrypt(input);
        return msg;
    }
}
