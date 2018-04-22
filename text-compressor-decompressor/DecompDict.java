public class DecompDict {
    // private data fields
    private DecompEntry[] list;
    private short numEntries;
    private int dictSize;
    private int rehashCount;

    // constructor - intializes acii values
    public DecompDict() {
        this.dictSize = 400;
        this.list = new DecompEntry[dictSize];
        this.numEntries = 0;
        this.rehashCount = 0;
        
        for (int i = 0; i < 128; i++) {
            String value = Character.toString((char)i);
            addEntry(value);
        }
    }

    /*
     * Adds an entry to the decompression dictionary. Will double dictionary
     * size if load factor becomes too great.
     *
     * @param value The value that needs a dictionary entry
     *
     * @return void
     */
    public void addEntry(String value) {
        if ((double)(numEntries / dictSize) > 0.66) {
            list = doubleSize();
        }
        list[numEntries] = new DecompEntry(numEntries, value);
        numEntries++;
    }

    /*
     * Checks if the dictionary contains a code.
     *
     * @param code The code to be searched
     *
     * @return boolean Returns if a code is in the dictionary
     */
    public boolean contains(short code) {
        return list[code] != null;
    }


    /*
     * Gets the value of the corresponding code.
     *
     * @param code The code to be searched
     *
     * @return String The value for the code
     */
    public String getValue(short code) {
        return list[code].getValue();
    }

    /*
     * Doubles the size of the decompression dictionary if the load factor
     * becomes to great to keep efficiency.
     *
     * @return DecompEntry Returns the new doubled dictionary
     */
    public DecompEntry[] doubleSize() {
        dictSize = dictSize * 2;
        DecompEntry[] newList = new DecompEntry[dictSize];
        for (int i = 0; i < numEntries; i++) {
            newList[i] = new DecompEntry(list[i].getCode(), list[i].getValue());
        }
        rehashCount += 1;
        return newList;
    }
   
    /*
     * Returns the number of times the dictionary has been doubled
     *
     * @return int The count of doubling
     */
    public int getRehashCount() {
        return rehashCount;
    }


    /*
     * The number of entries in the decompression dictionary
     *
     * @return short The number of entries in the dictionary
     */
    public short numEntries() {
        return this.numEntries;
    }
   

    /*
     * Calculates the load factor for your dictionary
     *
     * @return double The load factor as a decimal
     */
    public double loadFactor() {
        double loadFactor = (double)this.numEntries / (double)this.dictSize;
        return loadFactor;
    }    
}
