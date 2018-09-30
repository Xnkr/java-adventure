
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         // complete constructor
         records = new ArrayList<>();
     }
        
     public void readFile(String filename) {
         FileResource f = new FileResource(filename);
         for(String s: f.lines()){
             records.add(WebLogParser.parseEntry(s));
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs(){
         ArrayList<String> uniqueIps = new ArrayList<>();
         for(LogEntry le : records){
             String s = le.getIpAddress();
             if(!uniqueIps.contains(s)){
                 uniqueIps.add(s);
                }
         }
         return uniqueIps.size();
     }
     
     public void printAllHigherThanNum(int num){
         for(LogEntry le : records){
             int status = le.getStatusCode();
             if(status > num){
                 System.out.println(le);               
             }
         }
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday){
         ArrayList<String> uniqueIPday = new ArrayList<String>();
         for(LogEntry le: records){
             String d = le.getAccessTime().toString();
             if(d.contains(someday)){
                 String s = le.getIpAddress();
                 if(!uniqueIPday.contains(s)){
                     uniqueIPday.add(s);
                }
             }
         }
         return uniqueIPday;
     }
     
     public String trimToFormat(String d){
         int idx = d.indexOf(" ");
         idx +=1;
         int end = d.indexOf(" ",idx);
         end = d.indexOf(" ",end+1);
         d = d.substring(idx,end);
         return d;         
     }
     
     public int countUniqueIPsInRange(int low, int high){
         int count = 0;
         ArrayList<String> uniqueIP = new ArrayList<String>();
         for(LogEntry le : records){
             int status = le.getStatusCode();
             if(status >= low && status <= high){
                String s = le.getIpAddress();
                 if(!uniqueIP.contains(s)){
                     uniqueIP.add(s);
                     count+=1;
                }            
             }
         }
         return count;
     }
     
     public HashMap<String,Integer> countVisitsPerIP(){
         HashMap<String,Integer> ip = new HashMap<String,Integer>();
         for(LogEntry le:records){
            String s = le.getIpAddress();
            if(!ip.keySet().contains(s)){
                ip.put(s,1);
            }
            else{
                ip.put(s,ip.get(s)+1);
            }
         }
         return ip;
     }
        
     public int mostNumberVisitsByIP(HashMap<String,Integer> ip){
         int count= 0;
         for(String s: ip.keySet()){
             if(count < ip.get(s)){
                 count = ip.get(s);
                }
            }
         return count;
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String,Integer> ip){
          int maxCount = mostNumberVisitsByIP(ip);
          ArrayList<String> ipVisit = new ArrayList<>();
          for(String s: ip.keySet()){
              if(maxCount == ip.get(s)){
                  ipVisit.add(s);
                }
            }
            return ipVisit;
     }
     
     public HashMap<String,ArrayList<String>> iPsForDays(){
         HashMap<String,ArrayList<String>> ipToDay = new HashMap<String,ArrayList<String>>();
         ArrayList<String> ips = new ArrayList<>();
         for(LogEntry le: records){
             String d = le.getAccessTime().toString();
             d = trimToFormat(d);
             if(!ipToDay.keySet().contains(d)){
                 ipToDay.put(d,new ArrayList<String>());
                 ips = ipToDay.get(d);
                 ips.add(le.getIpAddress());
                 ipToDay.put(d,ips);
             }
             else{
                 ips = ipToDay.get(d);
                 ips.add(le.getIpAddress());
                 ipToDay.put(d,ips);
             }
         }
         return ipToDay;
     }
     
     public String dayWithMostIPVisits(HashMap<String,ArrayList<String>> ipToDay){
         String day= "";
         int max = 0;
         for(String s: ipToDay.keySet()){
             if(max < ipToDay.get(s).size()){
                 max = ipToDay.get(s).size();
                 day = s;
                }
            }
         return day;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String,ArrayList<String>> ipOnDay,String day){
         ArrayList<String> ipMostVisit = new ArrayList<>();
         ArrayList<String> allIP = new ArrayList<>();
         allIP = ipOnDay.get(day);
         HashMap<String,Integer> ip = new HashMap<String,Integer>();
         if(allIP!=null){
         for(String s: allIP){
            if(!ip.keySet().contains(s)){
                ip.put(s,1);
            }
            else{
                ip.put(s,ip.get(s)+1);
            }
         }}
         ipMostVisit = iPsMostVisits(ip);
         return ipMostVisit;
     }
}
