import java.util.*;
import java.io.*;

/* Compression holds the main method for compressing a file
 *
 *
 * No Compression objects are created, just one static variable
 * String filename - used for file manipulation
 */
public class Compression {
    private static String fileName;

    public static void main(String[] args) {
        fileName = args[0];
        DataInputStream fileReader = setupFile(fileName);
        Dictionary compressDict = new Dictionary();
        compress(fileReader, compressDict);
    }
    
    /* setupFile is used for opening up the input file and also allows the
     * user to retry when a bad filename is entered. 
     *
     * @param fileName - static variable for managing input file
     *
     * @return a DataInputStream that reads specified file
     */
    public static DataInputStream setupFile(String fileName) {    
        DataInputStream inStream = null;
        boolean retry = true;
        while (retry) {
            try {
                if (fileName.equals("quit")) {
                    System.exit(0);
                }
                inStream = new DataInputStream(new FileInputStream(fileName));
                retry = false;
            }
            catch (FileNotFoundException e) {
                retry = true;
                System.out.println("Can't find file, try again: ");
            }
        }
        return inStream;
    }

    public static void compress(DataInputStream input, Dictionary dict) {
        String zzzFile = fileName + ".zzz";
        String prefix = "";
        DataOutputStream outStr = null;
        
        //start timer here
        long startTime = System.nanoTime();
        KeyEntry tempEntry = new KeyEntry((short)-1, 'a');
        boolean fileDone = false;
        try {
            outStr = new DataOutputStream(new FileOutputStream(zzzFile));
            prefix += (char)input.readByte();
            tempEntry.setEndChar(prefix.charAt(0));

            while (!fileDone) {
                if (dict.needsRehash()) {
                    Dictionary newDict = new Dictionary(dict);
                    dict = newDict;
                }
                tempEntry.setPreCode((short)-1);
                while (dict.contains(tempEntry)) {
                    prefix += (char)input.readByte();
                    tempEntry.setPreCode(dict.findEntry(tempEntry).getFinalCode());
                    tempEntry.setEndChar(prefix.charAt(prefix.length() - 1));
                }
                outStr.writeShort(tempEntry.getPreCode());
                dict.addEntry(tempEntry);
                prefix = prefix.substring(prefix.length() - 1);
            }
        }
        catch (EOFException e) {
            try {
                outStr.writeShort(dict.findEntry(tempEntry).getFinalCode());
                outStr.close();
            }
            catch (IOException ex) {
                System.out.println("Error finalizing printing");
            }
            fileDone = true;
        }
        catch (IOException exc) {
            System.out.println("Error reading / writing, returning");
            return;
        }
    

    //end timer and grab lengths
        double elapsedTime = (System.nanoTime() - startTime) / 1000000000.0;
        createLog(dict, elapsedTime); 
    }
    //this creates a log file and is ready to write to it.. not sure what it
    //needs to write
    public static void createLog(Dictionary dict, double elapsedTime) {
        File oldf = new File(fileName);
        File zzzf = new File(fileName.concat(".zzz"));
        double sizeBefore = oldf.length() / 1000.0;
        double sizeAfter = zzzf.length() / 1000.0;
        double percentFull = ((double)dict.getNumEntries() / dict.getSize()) * 100.0; 
        double avgList = dict.avgListSize();
        int longList = dict.getLongestList();
        int totalEntries = dict.getNumEntries();
        int rehashCount = dict.getRehashCount();

        String logFile = fileName.concat(".zzz.log");
        
        try{
            BufferedWriter logStream = new BufferedWriter (new FileWriter (new File(logFile)));
            logStream.write("Compression of " + fileName);
            logStream.newLine();
            logStream.write("Compressed from "+ String.format("%.3f", sizeBefore)+" Kilobytes to "+ String.format("%.3f", sizeAfter) +" Kilobytes");
            logStream.newLine();
            logStream.write("Compression took " + String.format("%.6f", elapsedTime) + " seconds");
            logStream.newLine();
            logStream.write("The Hash Table is " + String.format("%.3f", percentFull) + "% full");
            logStream.newLine();
            logStream.write("The average linked list is " + String.format("%.3f", avgList) + " elements long");
            logStream.newLine();
            logStream.write("The longest linked list contains " + longList + " elements");
            logStream.newLine();
            logStream.write("The dictionary contains " + totalEntries + " total entries");
            logStream.newLine();
            logStream.write("The table was rehashed " + rehashCount + " times");
            logStream.close();
        }
        catch (IOException exc) {
            System.out.println("Error writing to log");
            System.exit(1);
        }
        catch (Exception e) {
            System.out.println("Something Went Wrong");
            System.exit(1);
        }

    }

}
