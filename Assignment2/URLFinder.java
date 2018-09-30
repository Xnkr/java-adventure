/**
 * Prints out all links within the HTML source of a web page.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;

public class URLFinder {
    public StorageResource findURLs(String url) {
        URLResource page = new URLResource(url);
        String source = page.asString();
        StorageResource store = new StorageResource();
        int start = 0;
        while (true) {
            int index = source.indexOf("href=", start);
            if (index == -1) {
                break;
            }
            int firstQuote = index+6; // after href="
            int endQuote = source.indexOf("\"", firstQuote);
            String sub = source.substring(firstQuote, endQuote);
            if (sub.startsWith("http")) {
                store.add(sub);
            }
            start = endQuote + 1;
        }
        return store;
    }
    
    public int dotFinder (String link){
        int start = 0,dot=0;
        while(true){
            int tag = link.indexOf(".",start);
            if(tag == -1){
                break;
            }
            else{
                dot++;
                start = tag + 1;
            }
        }
        return dot;
    }
    
    public void testURL() {
        StorageResource s1 = findURLs("http://www.dukelearntoprogram.com/course2/data/newyorktimes.html");
        int count = 0;
        for(String link: s1.data()){
            /*if(link.indexOf(".com") != -1){
                count++;
                System.out.println(link);
            }
            if(link.endsWith(".com/")){
                count++;
                System.out.println(link);
            }
            if(link.endsWith(".com")){
                count++;
                System.out.println(link);
            }*/
            count = count + dotFinder(link);
       }
        System.out.println("size = " + s1.size());
        System.out.println("count = " + count);
    }
}
