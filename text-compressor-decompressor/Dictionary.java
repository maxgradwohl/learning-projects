/* Dictionary implements our hashing via separate chaining (LinkedList)
 *
 * INSTANCE VARIABLES
 * LinkedList[] list - an array of LinkedLists, our hash table
 * short numEntries - total KeyEntry objects, used as the code for compressing
 * int dictSize - keeps track of hash table size
 * 
 * Two static variables
 * int[] primes - used for rehashing, new table sizes
 * rehashCount - how many times table has been rehashed / resized
 */
public class Dictionary {
	private LinkedList[] list;
    private short numEntries;
    private int dictSize;
    private static int[] primes = new int[]{1009, 2017, 4027, 8059, 16127, 32237, 64577};
    private static int rehashCount = 0;

    /* Default Constructor
     * Initializes Dictionary with base size of 503 and sets up hash table
     * Also initializes dictionary with ASCII table values
     */
    public Dictionary() {
     	dictSize = 503;
		list = new LinkedList[dictSize];
        numEntries = 0;
        // Initialize LinkedList for each hash table entry
        for (int i = 0; i < dictSize; i++) {
            list[i] = new LinkedList();    
        }
        for (int i = 0; i < 128; i++) {
            KeyEntry temp = new KeyEntry((short)-1, (char)i);
            addEntry(temp);
        }
    }

    /* Rehashing Constructor
     * Makes a new dictionary and copies over elements from old one
     * Table size is next prime in primes arry
     *
     * @param oldDict dictionary that is getting too full
     * @return a new dictionary with a larger tablesize
     */
    public Dictionary(Dictionary oldDict) {
        this.numEntries = oldDict.numEntries;
        this.dictSize = primes[rehashCount];
        this.list = new LinkedList[this.dictSize];
        
        for (int i = 0; i < this.dictSize; i++) {
            list[i] = new LinkedList();
        }
        for (int i = 0; i < oldDict.getSize(); i++) {
            KeyEntry temp = oldDict.list[i].getHead();
            while (temp != null) {
                int posi = getIndex(temp.getPreCode(), temp.getEndChar());
                list[posi].push(temp);
                temp = temp.getNext();
            }
        } 
        rehashCount++;
    }
 
    public int getSize() { 
        return dictSize; 
    }
    public int getNumEntries() {
        return numEntries;
    }

    public boolean needsRehash() {
        double factor = (double)numEntries / (double)dictSize;
        if (factor > 0.66)
            return true;
        else
            return false;
    }

    public int getIndex(short preCode, char endChar) {
        int combo = Math.abs(preCode) + (endChar * 2531);
        int index = combo % dictSize;
        return index;
    }

    public void addEntry(KeyEntry toAdd) {
        int posi = getIndex(toAdd.getPreCode(), toAdd.getEndChar());
        short code = numEntries;
        toAdd.setFinalCode(code);
        list[posi].push(toAdd);
        System.out.println("Total Entries: " + numEntries);
        numEntries++;
    }

    public boolean contains(KeyEntry toFind) {
        int checkIndex = getIndex(toFind.getPreCode(), toFind.getEndChar());
        boolean found = false;
        found = list[checkIndex].containsInList(toFind);
        // since we are pushing to the top, first node found will have
        // longest prefix because it will be the newest so just return when
        // found
        if (found == true) {
            return found;
        }
        return false;
    }

    public KeyEntry findEntry(KeyEntry toFind) {
        KeyEntry toReturn = null;
        int checkIndex = getIndex(toFind.getPreCode(), toFind.getEndChar());
        toReturn = list[checkIndex].getEntry(toFind);
        return toReturn;
    }

    public int getRehashCount() {
        return rehashCount;
    }

    public int getLongestList() {
        int longestList = 0;
        for (int i = 0; i < dictSize; i++) {
            if (list[i].length() > longestList) {
              longestList = list[i].length();  
            } 
        }
        return longestList;
    }

    public double avgListSize() {
        double numList = 0;
        for (int i = 0; i < this.dictSize; i++) {
            if (!list[i].isEmpty()) {
                numList++;
            }
        }
        double avgListSize = (double) this.numEntries / numList;
        return avgListSize;
    }
}
