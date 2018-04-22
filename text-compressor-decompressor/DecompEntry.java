public class DecompEntry {
    // private data fields
    private short code;
    private String value;
    
    //constructor
    public DecompEntry(short code, String value) {
        this.code = code;
        this.value = value;
    }

    /*
     * Returns the code of a decompression dictionary entry
     *
     * @return short The code
     */
    public short getCode() {
        return this.code;
    }
    
    /*
     * Returns the value of a decompression dictionary entry
     *
     * @return String The string value 
     */
    public String getValue() {
        return this.value;
    }

    /*
     * Sets the code for a decompression dictionary entry
     *
     * @param newCode The new code to be set
     * 
     * @return void
     */
    public void setCode(short newCode) {
        this.code = newCode;
    }

    /*
     * Sets the value for a decompression dictionary entry
     *
     * @param newValue The new value to be set
     *
     * @return void
     */
    public void setValue(String newValue) {
        this.value = newValue;
    }
}
