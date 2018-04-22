/* This class defines the KeyEntry which we use for compressing files
 * The only methods in this class are getters and setters
 * Plus a simple constructor and equals method
 *
 * INSTANCE VARIABLES
 * short preCode - the code used for a previous entry
 * char endChar - the new character that makes a new entry necsesary
 * short finalCode - the code used by this specific entry
 * KeyEntry next - links Nodes in LinkedList
 */

public class KeyEntry {
    private short preCode;
    private char endChar;
    private short finalCode;
    private KeyEntry next;
    
    // Constructor sets default end code to -1 so it can be manually set later
    public KeyEntry(short preCode, char endChar) {
        this.preCode = preCode;
        this.endChar = endChar;
        this.finalCode = -1;
        this.next = null;
    }


    /* Returns the precode for a given entry
     *
     * @return short The precode
     */
    public short getPreCode() {
        return preCode;
    }

    /* Returns the end char for a given entry
     *
     * @return char The end char
     */
    public char getEndChar() {
        return endChar;
    }

    /* Returns the final code for a given entry
     *
     * @return short The final code
     */
    public short getFinalCode() {
        return finalCode;
    }
    
    /* Returns the next entry for a given entry
     *
     * @return KeyEntry The next entry
     */
    public KeyEntry getNext() {
        return next;
    }

    /* Sets precode for a given entry
     *
     * @param code The code for the precode
     *
     * @return void
     */
    public void setPreCode(short code) {
        this.preCode = code;
    }

    /* Sets end char for a given entry
     *
     * @param newChar The char for the end char
     *
     * @return void
     */
    public void setEndChar(char newChar) {
        this.endChar = newChar;
    }

    /* Sets the final code for a given entry
     *
     * @param newCode The code for the final code
     *
     * @return void
     */
    public void setFinalCode(short newCode) {
        this.finalCode = newCode;
    }

    /* Sets next entry for a given entry
     *
     * @param newNext The new next KeyEntry
     *
     * @return void
     */
    public void setNext(KeyEntry newNext) {
        this.next = newNext;
    }

    /* Compares two KeyEntry objects, returns true if they are equal
     *
     * @param compare KeyEntry to compare to a given key entry
     *
     * @return boolean Returns if entries are equal or not
    */
    public boolean equals(KeyEntry compare) {
        if ((preCode == compare.getPreCode()) && (endChar == compare.getEndChar()))
            return true;
        else
            return false;
    }
}
