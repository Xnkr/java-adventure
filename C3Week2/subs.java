import java.util.*;
/**
 * Write a description of subs here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class subs {
    public void basic(){
        int n,c;
        String s;
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        c = in.nextInt();
        s = in.nextLine();   
        ArrayList<String> subs = new ArrayList<String>();
        int i;
        for(i=0;i<s.length()-n;i++){
            String temp = s.substring(i,i+n);
            if(!subs.contains(temp)){
                subs.add(temp);
            }
        }
        System.out.println(subs.size());
    }
}
