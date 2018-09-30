/**
 * Find the highest (hottest) temperature in any number of files of CSV weather data chosen by the user.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class Assgn3 {
    public CSVRecord coldestHourInFile(CSVParser parser) {
        //start with largestSoFar as nothing
        CSVRecord smallestSoFar = null;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            // use method to compare two records
            smallestSoFar = getSmallerOfTwo(currentRow, smallestSoFar);
        }
        //The largestSoFar is the answer
        return smallestSoFar;
    }

    public void testColdestInDay () {
        FileResource fr = new FileResource();
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        System.out.println("coldest temperature was " + smallest.get("TemperatureF") +
                   " at " + smallest.get("DateUTC"));
    }

    public CSVRecord coldestInManyDays() {
        CSVRecord smallestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        // iterate over files
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            // use method to get largest in file.
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            // use method to compare two records
            smallestSoFar = getSmallerOfTwo(currentRow, smallestSoFar);
        }
        //The largestSoFar is the answer
        return smallestSoFar;
    }

    public CSVRecord getSmallerOfTwo (CSVRecord currentRow, CSVRecord smallestSoFar) {
        //If largestSoFar is nothing
        if (smallestSoFar == null) {
            smallestSoFar = currentRow;
        }
        //Otherwise
        else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
            //Check if currentRow’s temperature > largestSoFar’s
               
           if ((currentTemp != -9999) && (currentTemp < smallestTemp)) {
                //If so update largestSoFar to currentRow
                smallestSoFar = currentRow;
            }
        }
        return smallestSoFar;
	}
	
	public CSVRecord getSmallerHumOfTwo (CSVRecord currentRow, CSVRecord smallestSoFar) {
        //If largestSoFar is nothing
        if (smallestSoFar == null) {
            smallestSoFar = currentRow;
        }
        //Otherwise
        else {
            if(!currentRow.get("Humidity").equals("N/A")){
            double currentHum = Double.parseDouble(currentRow.get("Humidity"));
            double smallestHum = Double.parseDouble(smallestSoFar.get("Humidity"));
            //Check if currentRow’s temperature > largestSoFar’s
               
               if ((currentHum != -9999) && (currentHum < smallestHum)) {
                //If so update largestSoFar to currentRow
                        smallestSoFar = currentRow;
                    }
           }
        }
        return smallestSoFar;
	}
	
	public CSVRecord lowestHumidityInFile (CSVParser parser){
	   CSVRecord smallestSoFar = null;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            // use method to compare two records
            smallestSoFar = getSmallerHumOfTwo(currentRow, smallestSoFar);
        }
        //The largestSoFar is the answer
        return smallestSoFar;
	   
	   
	   }
	   
	public double averageTemperatureInFile(CSVParser parser){
	    double avg, total=0;
	    int n=0;
	    for (CSVRecord currentRow : parser) {
	             double temp = Double.parseDouble(currentRow.get("TemperatureF"));
	             if(total == 0)
	               total = temp;
	             else
	               total = total + temp;
	             n++;	               
	       }
	       avg = total/n;
	       return avg;
	   }
	   
	public void testAverageTemperatureInFile(){
	   	   FileResource fr = new FileResource();
           CSVParser parser = fr.getCSVParser();
           double average = averageTemperatureInFile(parser);
           System.out.println("Average temperature in file is :" + average);
	   }
	   
	public void testLowestHumidityInFile(){
	   	   FileResource fr = new FileResource();
           CSVParser parser = fr.getCSVParser();
           CSVRecord smallest = lowestHumidityInFile(parser);
           System.out.println("Lowest Humidity on was :" + smallest.get("Humidity") + " at " + smallest.get("DateUTC"));
	   }
	
	public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
	    double avg, total=0;
	    int n=0;
	       for (CSVRecord currentRow : parser) {
            // use method to compare two records
            double humidity = Double.parseDouble(currentRow.get("Humidity"));
            if(humidity >= value){
                double temp = Double.parseDouble(currentRow.get("TemperatureF"));
                if(total == 0)
	               total = temp;
	             else
	               total = total + temp;
	             n++;	   
	        }
	        
        }
	    if(n>0){
	        avg = total/n;
	       return avg;
	   }else
	       return -9999;
	   }
	   
	public void testAverageTemperatureWithHighHumidityInFile(){
	       FileResource fr = new FileResource();
           CSVParser parser = fr.getCSVParser();
           double average = averageTemperatureWithHighHumidityInFile(parser,80);
           if(average != -9999)
           System.out.println("Average temperature in file is :" + average);
           else
           System.out.println("No temperatures with that humidity");
	   }
	   
	public String fileWithColdestTemperature(){
	    CSVRecord smallestSoFar = null;
	    String fileName = null;
        DirectoryResource dr = new DirectoryResource();
        // iterate over files
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            // use method to get largest in file.
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            // use method to compare two records
            smallestSoFar = getSmallerOfTwo(currentRow, smallestSoFar);
            if(smallestSoFar.get("DateUTC").equals(currentRow.get("DateUTC"))){
                fileName = f.getName();
            }
        }
        //The largestSoFar is the answer
        return fileName;
	}
	
	public void testFileWithColdestTemperature(){
	    String fileName = fileWithColdestTemperature();
	    String filePath = "data/2014/"+fileName;
	    FileResource fr = new FileResource(filePath);
	    System.out.println("Coldest day was in the file: " + fileName);
	    CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature on that day was :" + smallest.get("TemperatureF"));
        
	}

	public void testSmallestInManyDays () {
		CSVRecord smallest = coldestInManyDays();
		System.out.println("coldest temperature was " + smallest.get("TemperatureF") +
				   " at " + smallest.get("DateUTC"));
	}
	
	public CSVRecord coldestHumInManyDays() {
        CSVRecord smallestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        // iterate over files
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            // use method to get largest in file.
            CSVParser parser = fr.getCSVParser();
            CSVRecord currentRow = lowestHumidityInFile(parser);
            // use method to compare two records
            smallestSoFar = getSmallerHumOfTwo(currentRow, smallestSoFar);
        }
        //The largestSoFar is the answer
        return smallestSoFar;
    }
	
	public void testSmallestHumInManyDays () {
		CSVRecord smallest = coldestHumInManyDays();
		System.out.println("Lowest Humidity was " + smallest.get("Humidity") +
				   " at " + smallest.get("DateUTC"));
	}
}
