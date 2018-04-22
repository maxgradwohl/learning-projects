import java.util.*;
import java.io.*;

public class Decompression {
    private static String fileName;

    public static void main(String[] args) {
        fileName = args[0];
        // creates an input stream
        DataInputStream fileReader = setupFile(fileName);
        DecompDict deDict = new DecompDict();
        // calls decompression for the file
        decompress(fileReader, deDict);
        Scanner input = new Scanner(System.in);
        // asks if user to decompress another file if they want
        while (true) {
            System.out.print("Enter another file to decompress or type quit: ");
            fileName = input.next();
            fileReader = setupFile(fileName);
            deDict = new DecompDict();
            decompress(fileReader, deDict);
        }
    }
   


    /*
     * A method that creates an input stream to read a from a given file
     *
     * @param fileName The name of the file for the input stream
     *
     * @return DataInputStream The input stream for the compressed file
     */
    public static DataInputStream setupFile(String fileName) {
        DataInputStream inStream = null;
        boolean retry = true;
        // while user wants to continue decompressing more files
        while (retry) {
            try {
                // they may enter quit
                if (fileName.equals("quit")) {
                    System.exit(0);
                }
                // continue creating another stream for decompression
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

    

    /*
     * A method that reads in a compressed file and decompress it and writes
     * it to a new file
     *
     * @param in The input stream to the compressed file
     * @param dict The dictionary used for decompression
     *
     * @return void
     */
    public static void decompress(DataInputStream in, DecompDict dict) {
        String endFilename = fileName.substring(0, fileName.length() - 4);
        boolean fileDone = false;
        double elapsedTime = 0;
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(endFilename)); 
            
            long startTime = System.nanoTime();

            short first = in.readShort();
            out.write(dict.getValue(first));
            out.flush();
            short second;
            while (!fileDone) {
                // reading shorts (bytes) from the file
                second = in.readShort();
                // if value is in dictionary
                if (dict.contains(second)) {
                    out.write(dict.getValue(second));
                    dict.addEntry(dict.getValue(first) + dict.getValue(second).charAt(0));
                }
                // if value is not in dictionary 
                else {
                    out.write(dict.getValue(first) + dict.getValue(first).charAt(0));
                    dict.addEntry(dict.getValue(first) + dict.getValue(first).charAt(0));
                }
                out.flush();
                first = second;
            }
            // stops timer and calculates total time
            elapsedTime = (System.nanoTime() - startTime) / 1000000000.0;
            out.close();
        }
        catch (EOFException exc) {
            fileDone = true;
        }
        catch (IOException e) {
            System.out.println("Error reading / writing, returning");
            return;
        }
        // creates the end log file
        createLog(dict, elapsedTime);
    }   
   


    /*
     * Creates a log file filled with stats about the decompression
     * 
     * @param dict The dictionary used for decompression
     * @param elapsedTime The amount of time to do decompression
     *
     * @return void
     */
    public static void createLog(DecompDict dict, double elapsedTime) {
        // grabs filename minus the extension
        String logFile = fileName.substring(0, fileName.length() - 4);
        // creates a file object so that we can know the length in bytes
        File oldf = new File(fileName);
        File newf = new File (logFile);
        double sizeBefore = oldf.length() / 1000.0;
        double sizeAfter = newf.length() / 1000.0;
        double percentFull = dict.loadFactor() * 100.0;
        int totalEntries = dict.numEntries();
        int rehashCount = dict.getRehashCount();
        logFile = logFile.concat(".log");

        // writes stats to corresponding log file
        try{
            BufferedWriter logStream = new BufferedWriter (new FileWriter (new File(logFile)));
            logStream.write("Compression of " + fileName);
            logStream.newLine();
            logStream.write("Compressed from "+ String.format("%.3f", sizeBefore)+" Kilobytes to " + String.format("%.3f", sizeAfter) +" Kilobytes");
            logStream.newLine();
            logStream.write("Compression took " + String.format("%.6f", elapsedTime) + " seconds");
            logStream.newLine();
            logStream.write("The Hash Table is " + String.format("%.3f", percentFull) + "% full");
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
