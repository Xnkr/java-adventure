
/**
 * Write a description of CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class CaesarBreaker {
      public int[] countLetters(String message){
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for(int k=0;k<message.length();k++){
                char ch = Character.toLowerCase(message.charAt(k));
                int dex = alph.indexOf(ch);
                if(dex!=-1){
                    counts[dex] += 1;
                }
            }
            return counts;
    }
    
    public int maxIndex(int[] vals){
        int maxDex = 0;
        for(int k=0;k<vals.length; k++){
            if(vals[k] > vals[maxDex]){
                maxDex = k;
            }
        }
        return maxDex;
    }
    
    public String decrypt(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if(maxDex < 4){
            dkey = 26 - (4-maxDex);
        }
        return cc.encrypt(encrypted,26-dkey);
    }
    
    public String halfOfString(String message, int start){
        StringBuilder sub = new StringBuilder();
        int k=0;
        char ch;
        for(k=start;k<message.length();k+=2){
            ch=message.charAt(k);
            sub.append(ch);
        }
        return sub.toString();
    }
    
    public int getKey(String s){
        int[] freqs = countLetters(s);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if(maxDex < 4){
            dkey = 26 - (4-maxDex);
        }
        return dkey;
    }
    
    public void decryptTwoKeys(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        String sub1 = halfOfString(encrypted,0);
        int key1 = getKey(sub1);
        String sub2 = halfOfString(encrypted,1);
        int key2 = getKey(sub2);
        String decrypted = cc.encryptTwoKeys(encrypted, 26-key1, 26-key2);
        System.out.println("Keys = " + key1 + " , " + key2 + " Message = " + decrypted);
    }
    
    public void decryptFile(){
        FileResource f = new FileResource("data/mysteryTwoKeysQuiz.txt");
        decryptTwoKeys(f.asString());
    }
        
    public void testDecrypt(){
       String message = decrypt("ggggggggg");
       System.out.println(message);
    }
}
