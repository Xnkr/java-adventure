import edu.duke.*;
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipherTwo {
    private String alphabet,shiftedAlphabet1,shiftedAlphabet2;
    private int mainKey1,mainKey2;
    public CaesarCipherTwo(int key1, int key2){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        mainKey1 = key1;
        mainKey2 = key2;
        shiftedAlphabet1 = alphabet.substring(key1)+ alphabet.substring(0,key1);
        shiftedAlphabet2 = alphabet.substring(key2)+ alphabet.substring(0,key2);
    }
    public String encrypt(String input){
        StringBuilder encrypted = new StringBuilder(input);
        boolean key=true;
        
        for(int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            int idx;
            idx = alphabet.indexOf(currChar);
            if(idx != -1){
                if(key){
                        char newChar = shiftedAlphabet1.charAt(idx);
                        encrypted.setCharAt(i, newChar);
                        key=false;
                }
                else{
                        char newChar = shiftedAlphabet2.charAt(idx);
                        encrypted.setCharAt(i, newChar);
                        key=true;
                }
            }
            else
                key = !key;
        }
        return encrypted.toString();
    }
    public String decrypt(String input){
        CaesarCipherTwo cct = new CaesarCipherTwo(26-mainKey1,26-mainKey2);
        return cct.encrypt(input);
    }
}
