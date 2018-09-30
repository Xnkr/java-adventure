
/**
 * Write a description of MiniP here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class MiniP {
    public void printNames () {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + rec.get(0) +
                           " Gender " + rec.get(1) +
                           " Num Born " + rec.get(2));
            }
        }
    }
    
    public int getRank(int year, String name, String gender){
        boolean found = false;
        String filename = "data/yob"+year+".csv";
        FileResource fr = new FileResource(filename);
        //FileResource fr = new FileResource();
        int rank = 0;
         for (CSVRecord rec : fr.getCSVParser(false)) {
            if(rec.get(1).equals(gender)){
                rank++;
                if(rec.get(0).equals(name)){
                    found = true;
                    break;
                }
            }
        }
        if(found)
            return rank;
        else
            return -1;        
    }
    
    public String getName(int year, int rank, String gender){
        boolean found = false;
        String filename = "data/yob"+year+".csv";
        FileResource fr = new FileResource(filename);
        //FileResource fr = new FileResource();
        int currentRank = 0;
        String nameOfBaby=null;
         for (CSVRecord rec : fr.getCSVParser(false)) {
            if(rec.get(1).equals(gender)){
                currentRank++;
                if(currentRank == rank){
                    found = true;
                    nameOfBaby = rec.get(0);
                    break;
                }
            }
        }
        if(found)
            return nameOfBaby;
        else
            return "NO NAME";
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender){
        String filenameOld = "data/yob"+year+".csv";
        FileResource frOld = new FileResource(filenameOld);
        String filenameNew = "data/yob"+newYear+".csv";
        FileResource frNew = new FileResource(filenameNew);
        int oldRank =0, newRank = 0;
        String nameOfBaby = null;
        for(CSVRecord rec : frOld.getCSVParser(false)){
            if(rec.get(1).equals(gender)){
                oldRank++;
                if(rec.get(0).equals(name)){
                    break;
                }
            }
        }
        for(CSVRecord rec : frNew.getCSVParser(false)){
            if(rec.get(1).equals(gender)){
                newRank++;
                if(newRank == oldRank){
                    nameOfBaby = rec.get(0);
                    break;
                }
            }
        }
        System.out.println(name + " born in " + year + " would be " + nameOfBaby +" if she was born in " +newYear+".");
    }
    
    public int yearOfHighestRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int allRank = 0 ,fileRank = 0;
        boolean found = false, fileFound = false;
        for(File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            fileRank = 0;
            fileFound = false;
            for(CSVRecord rec: fr.getCSVParser(false)){
                if(rec.get(1).equals(gender)){
                fileRank++;
                if(rec.get(0).equals(name)){
                    found = true;
                    fileFound = true;
                    break;
                }
            }
            }
            if(allRank!=0 && fileFound){ 
                if(fileRank < allRank){
                    allRank = fileRank;
                    System.out.println("Year = " + f.getName() + " Rank = " + allRank); 
                }
            }
            else if(fileFound){
                allRank = fileRank;
                }
       }
       if(found){
            System.out.println(allRank);
            return allRank;
        }
       else
            return -1;
    }
    
    public double getAverageRank(String name, String gender){
        boolean found=false, fileFound = false ;
        int sumOfRanks = 0,fileRank = 0, fileCount = 0;
        double avg = 0;
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            fileRank = 0;
            fileFound = false;
            for(CSVRecord rec: fr.getCSVParser(false)){
                if(rec.get(1).equals(gender)){
                fileRank++;
                if(rec.get(0).equals(name)){
                    found = true;
                    fileFound = true;
                    fileCount++;
                    break;
                }
            }
         }
         if(sumOfRanks!=0 && fileFound){ 
              sumOfRanks += fileRank;               
            }
         else if (sumOfRanks == 0 && fileFound){
              sumOfRanks = fileRank;
            }
       }
       if(found){
           avg = ((float)sumOfRanks)/fileCount;
           return avg;
        }
        else{
            return -1;
        }
       
    }
    
    public int getTotalBirthsRankedHigher(String name,String gender,int year){
        boolean found = false;
        String filename = "data/yob"+year+".csv";
        FileResource fr = new FileResource(filename);
        //FileResource fr = new FileResource();
        int totalBirths = 0;
        int rank = 0;
         for (CSVRecord rec : fr.getCSVParser(false)) {
            if(rec.get(1).equals(gender)){
                rank++;
                if(rec.get(0).equals(name)){
                    found = true;
                    break;
                }
                int numBorn = Integer.parseInt(rec.get(2));
                totalBirths += numBorn;
            }
        }
        if(found)
            return totalBirths;
        else
            return -1;      
    }
        
    public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalNames = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        StorageResource uniqueBoys = new StorageResource();
        StorageResource uniqueGirls = new StorageResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
                if(uniqueBoys == null){
                    uniqueBoys.add(rec.get(0));
                }
                else if(!uniqueBoys.contains(rec.get(0))){
                    uniqueBoys.add(rec.get(0));
                }
                    
            }
            else {
                totalGirls += numBorn;
                if(uniqueGirls == null){
                    uniqueGirls.add(rec.get(0));
                }
                else if(!uniqueGirls.contains(rec.get(0))){
                    uniqueGirls.add(rec.get(0));
                }
            }
            totalNames++;
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("female girls = " + totalGirls);
        System.out.println("male boys = " + totalBoys);
        System.out.println("total names = " + totalNames);
        System.out.println("total unique boys = " + uniqueBoys.size());
        System.out.println("total unique Girls = " + uniqueGirls.size());
    }

    public void testTotalBirths () {
        FileResource fr = new FileResource();
        //FileResource fr = new FileResource("data/yob2014.csv");
        totalBirths(fr);
    }
}
