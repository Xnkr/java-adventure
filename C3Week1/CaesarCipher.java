import edu.duke.*;

public class CaesarCipher {
    public String encrypt(String input, int key) {

        StringBuilder encrypted = new StringBuilder(input);
        boolean upper; 
        String uAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lAlphabet = uAlphabet.toLowerCase();
        String uShiftedAlphabet = uAlphabet.substring(key)+uAlphabet.substring(0,key);
        String lShiftedAlphabet = uShiftedAlphabet.toLowerCase();

        for(int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            int idx;
            if(Character.isUpperCase(currChar)){
                idx = uAlphabet.indexOf(currChar);
                upper=true;
            }
            else{
                idx = lAlphabet.indexOf(currChar);
                upper=false;
            }
            
            if(idx != -1){
                if(upper){
                char newChar = uShiftedAlphabet.charAt(idx);
                encrypted.setCharAt(i, newChar);
                }
                else{
                    char newChar = lShiftedAlphabet.charAt(idx);
                    encrypted.setCharAt(i, newChar);
                }
            }

        }

        return encrypted.toString();
    }
    public String encryptTwoKeys(String input, int key1, int key2) {

        StringBuilder encrypted = new StringBuilder(input);
        boolean upper,key=true; 
        String uAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lAlphabet = uAlphabet.toLowerCase();
        String uShiftedAlphabetKey1 = uAlphabet.substring(key1)+uAlphabet.substring(0,key1);
        String lShiftedAlphabetKey1 = uShiftedAlphabetKey1.toLowerCase();        
        String uShiftedAlphabetKey2 = uAlphabet.substring(key2)+uAlphabet.substring(0,key2);
        String lShiftedAlphabetKey2 = uShiftedAlphabetKey2.toLowerCase();

        for(int i = 0; i < encrypted.length(); i++) {
            if(key){
                char currChar = encrypted.charAt(i);
                int idx;
                if(Character.isUpperCase(currChar)){
                    idx = uAlphabet.indexOf(currChar);
                    upper=true;
                }
                else{
                    idx = lAlphabet.indexOf(currChar);
                    upper=false;
                }
                
                if(idx != -1){
                    if(upper){
                        char newChar = uShiftedAlphabetKey1.charAt(idx);
                        encrypted.setCharAt(i, newChar);
                    }
                    else{
                        char newChar = lShiftedAlphabetKey1.charAt(idx);
                        encrypted.setCharAt(i, newChar);
                    }
                }
                key=false;
            }
            else{
                char currChar = encrypted.charAt(i);
                int idx;
                if(Character.isUpperCase(currChar)){
                    idx = uAlphabet.indexOf(currChar);
                    upper=true;
                }
                else{
                    idx = lAlphabet.indexOf(currChar);
                    upper=false;
                }
                
                if(idx != -1){
                    if(upper){
                        char newChar = uShiftedAlphabetKey2.charAt(idx);
                        encrypted.setCharAt(i, newChar);
                    }
                    else{
                        char newChar = lShiftedAlphabetKey2.charAt(idx);
                        encrypted.setCharAt(i, newChar);
                    }
                }
                key=true;
            }
        }

        return encrypted.toString();
    }
    public void testCaesar() {
        int key = 15;
        FileResource fr = new FileResource();
        String message = //fr.asString(); 
        "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        String encrypted = encrypt(message, key);
        // encrypted = encryptTwoKeys(message, 8,21);
        System.out.println(encrypted);
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println(decrypted);
    }
}

