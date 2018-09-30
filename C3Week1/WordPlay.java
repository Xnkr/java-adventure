
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordPlay {
    public boolean isVowel (char ch){
        ch = Character.toLowerCase(ch);
        if(ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u')
            return true;
        
        else
            return false;
    }
    
    public String replaceVowels (String phrase, char ch){
        StringBuilder s = new StringBuilder(phrase);
        int i=0;
        for(i =0; i < s.length(); i++){
            char currChar = s.charAt(i);
            if(isVowel(currChar)){
                s.setCharAt(i,ch);
            }
        }
        return s.toString();
    }
    
    public String emphasize (String phrase, char ch){
        StringBuilder s = new StringBuilder(phrase);
        int i;
        for(i=0;i<s.length();i++){
            char currChar = s.charAt(i);
            currChar = Character.toLowerCase(currChar);
            if(currChar == ch){
                if(i%2 == 0){
                    s.setCharAt(i,'*');
                }
                else{
                    s.setCharAt(i,'+');
                }
            }
        }
        return s.toString();
    }
}
