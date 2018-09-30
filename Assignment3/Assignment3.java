import edu.duke.*;
import org.apache.commons.csv.*;

public class Assignment3 {
    public void countryInfo(CSVParser parser, String country) {
        int found = 0;
        for (CSVRecord record : parser) {
            String countryName = record.get("Country");            
            if (countryName.equals(country)) {
                found = 1;
                String exports = record.get("Exports");
                System.out.print(countryName + " : " + exports + " : ");
                String value = record.get("Value (dollars)");
                System.out.println(value);
            }
        }
        if(found != 1)
            System.out.println("NOT FOUND");
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        for (CSVRecord record : parser) {
            String exports = record.get("Exports");            
            if (exports.contains(exportItem1) && exports.contains(exportItem2)) {
                String countryName = record.get("Country");
                System.out.println(countryName);
            }
        }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem){
        int count = 0;
        for (CSVRecord record : parser) {
            String exports = record.get("Exports");            
            if (exports.contains(exportItem)) {
                count++;
            }
        }
        return count;
    }
    
    public void bigExporters(CSVParser parser, String  amount){
        for (CSVRecord record : parser) {
            String value = record.get("Value (dollars)");       
            int valueLen = value.length();
            int amountLen = amount.length();
            if (valueLen > amountLen) {
                String countryName = record.get("Country");   
                System.out.println(countryName + " : " + value);
            }
        }
    }
    
    public void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        countryInfo(parser, "Nauru");
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser, "cotton", "flowers");
        parser = fr.getCSVParser();
        int count = numberOfExporters(parser,"cocoa");
        System.out.println("Number of exporters : " + count);
        parser = fr.getCSVParser();
        bigExporters(parser,"$999,999,999,999");
    }
}