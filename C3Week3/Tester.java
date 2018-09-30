
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer log = new LogAnalyzer();
        log.readFile("short-test_log");
        log.printAll();
    }
    
    public void testUniqueIP(){
        LogAnalyzer log = new LogAnalyzer();
        log.readFile("weblog2_log");
        System.out.println(log.countUniqueIPs());
    }
    
    public void testPrintAllHigherThanNum(){
        LogAnalyzer log = new LogAnalyzer();
        log.readFile("weblog1_log");
        log.printAllHigherThanNum(400);
    }
    
    public void testUniqueIPVisitsOnDay(){
        LogAnalyzer log = new LogAnalyzer();
        log.readFile("weblog2_log");
        ArrayList<String> uniqueIPday = log.uniqueIPVisitsOnDay("Sep 27");
        System.out.println(uniqueIPday.size());
    }
    
    public void testCountUniqueIPsInRange(){
        LogAnalyzer log = new LogAnalyzer();
        log.readFile("weblog2_log");
        System.out.println(log.countUniqueIPsInRange(200,299));
    }
    
    public void testCountingVisits(){
        LogAnalyzer log = new LogAnalyzer();
        log.readFile("weblog2_log");
        
        HashMap<String, Integer> ip = log.countVisitsPerIP();
        System.out.println("countVisitsPerIP " +ip);
        
        int count = log.mostNumberVisitsByIP(ip);
        System.out.println("mostNumberVisitsByIP "+count);
        
        ArrayList<String> IPsMostVisits = log.iPsMostVisits(ip);
        System.out.println("iPsMostVisits "+IPsMostVisits);
        
        HashMap<String,ArrayList<String>> IPsForDays = log.iPsForDays();
        System.out.println("iPsForDays "+IPsForDays);
        
        String DayWithMostIPVisits = log.dayWithMostIPVisits(IPsForDays);
        System.out.println("dayWithMostIPVisits "+DayWithMostIPVisits);
        
        ArrayList<String> IPsWithMostVisitsOnDay = log.iPsWithMostVisitsOnDay(IPsForDays,"Sep 29");
        System.out.println("iPsWithMostVisitsOnDay "+IPsWithMostVisitsOnDay);
    }
}
