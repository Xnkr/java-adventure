import java.util.*;
import edu.duke.*;
import java.io.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for(i=whichSlice;i<message.length();i+=totalSlices){
            sb.append(message.charAt(i));
        }
        return sb.toString();
    }
    
    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        String slice = "";
        for(int i = 0;i<klength;i++){
            slice = sliceString(encrypted, i, klength);
            key[i]=cc.getKey(slice);
        } 
        return key;    
    }
    
    public int countWords(String message, HashSet dictionary){
        int count = 0;
        for(String s: message.split("\\W+")){
            if(dictionary.contains(s.toLowerCase())){
                count++;
            }
        }
        return count;
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary){        
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        int maxCount = 0,maxIdx = 0;
        char mostCommon = '0';
        for(String word: dictionary){
            for(int k=0; k < word.length(); k++){
                int dex = alph.indexOf(Character.toLowerCase(word.charAt(k)));
                if (dex != -1){
                    counts[dex] += 1;
                }
            }    
        }
        for(int i=0;i<counts.length;i++){
            if(counts[i] > maxCount){
                maxCount = counts[i];
                maxIdx = i;
            }
        }
        mostCommon = alph.charAt(maxIdx);
        return mostCommon;
    }    
    
    public String breakForLanguage(String encrypted, HashSet dictionary){
        int keyLength, maxCount = 0, maxKey = 1;
        String decrypted = "",dec;
        int numWords =0;
        char mostCommon = mostCommonCharIn(dictionary);
        for(keyLength = 1;keyLength<=100;keyLength++){
            numWords = 0;
            int[] key = tryKeyLength(encrypted,keyLength,mostCommon);
            VigenereCipher vc = new VigenereCipher(key);
            dec = vc.decrypt(encrypted);
            for(String s: dec.split("\\W+"))
                numWords++;
            int count = countWords(dec, dictionary);
            if(count > maxCount){
                maxCount = count;
                decrypted = dec;
                maxKey = keyLength;
            }
        }
        System.out.println(maxKey + " score: " + maxCount*100/numWords);
        int[] maxLK = tryKeyLength(encrypted,maxKey,mostCommon);
        System.out.println(Arrays.toString(maxLK) + " mostCommon: "+ mostCommon);
        return decrypted;
    }
    
    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages){
        for(String s: languages.keySet()){
            System.out.println(s);
            String dec = breakForLanguage(encrypted,languages.get(s));
            System.out.println(dec);
        }
    }
       
    public HashSet readDictionary(FileResource fr){
        HashSet set = new HashSet<String>();
        for(String line: fr.lines()){
            set.add(line.toLowerCase());            
        }
        return set;
    }

    public void breakVigenere () {
        FileResource f = new FileResource("secretmessage3.txt");
        String enc = f.asString();
        //String dec = breakForLanguage(enc,words);
        DirectoryResource d = new DirectoryResource();
        HashSet<String> dic = new HashSet<String>();
        HashMap<String, HashSet<String>> languages = new HashMap<String,HashSet<String>>();
        languages.clear();
        for(File fl : d.selectedFiles()){
            FileResource dicFile = new FileResource(fl);
            dic = readDictionary(dicFile);
            String fileName = fl.getName();
            languages.put(fileName,dic);
            System.out.println("Appended "+ fileName);
        }
        breakForAllLangs(enc, languages);
        //System.out.println(dec);
    }
    
    public void testTryKeyLength(){
        FileResource f = new FileResource("secretmessage1.txt");
        String enc = f.asString();
        int[] key = tryKeyLength(enc,4,'e');
        for(int i=0;i<key.length;i++)
            System.out.println(key[i]);
    }    
}
