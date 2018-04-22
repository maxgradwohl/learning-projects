/* LinkedList is used for separate chaining in the Dictionary hash table
 * 
 * INSTANCE VARIABLES
 * KeyEntry head - first entry of a list, allows KeyEntry access
 * int numOfItems - keeps track of how many KeyEntry objects are in a list
 */
public class LinkedList {
	private KeyEntry head;
    private int numOfItems;

	public LinkedList() {
		head = null;
        numOfItems = 0;
	}
	
    public KeyEntry getHead() {
        return head;
    }
	public boolean isEmpty() {
		return head == null;
	}
    public int length() {
        return this.numOfItems;
    }

	public void push(KeyEntry toPush) {
        if (head == null) { // Special case - list was empty
            head = toPush;
            head.setNext(null);
        }
        else {
            toPush.setNext(head);
            head = toPush;
        }
        numOfItems++;
    }
    
    public boolean containsInList(KeyEntry toFind) {
        KeyEntry cur = head;
        while (cur != null) {
            if (cur.equals(toFind)) {
                return true;
            }
            cur = cur.getNext();
        }
        return false;
    }

    public KeyEntry getEntry(KeyEntry toFind) {
        KeyEntry cur = head;
        while (cur != null) {
            if (cur.equals(toFind)) {
                return cur;
            }
            cur = cur.getNext();
        }
        return null;
    }

    public KeyEntry locate(int posi) throws IndexOutOfBoundsException {
        KeyEntry cur = head;
        if (posi > numOfItems) {
            throw new IndexOutOfBoundsException("Index Out Bounds");
        }
        while (posi >= 1) {
            cur = cur.getNext();
            posi--;
        }
        return cur;
    }
}
