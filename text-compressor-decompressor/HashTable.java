public class HashTable {
    private int numOfItems;
    private int tableSize;
	private int[] table;


// Should we have constructor that initializes to the ASCII 128??

    public HashTable() {
     	this.tableSize = 503;
		this.table = int[tableSize];
        this.numOfItems = 0;
    }
 
    public int size() { 
        return size; 
    }

    public boolean isEmpty() { 
        return numOfItems == 0; 
    }
 
    // This implements hash function to find index
    // for a key
    public short getIndex(String key) {
        short sum = 0;
		for (int i = 0; i < key.length(); i++) {
			sum += key.charAt(i);
		}
        int index = sum % tableSize;
        return index;
    }
 
    // Method to remove a given key
    public V remove(K key)
    {
        // Apply hash function to find index for given key
        int chainIndex = getChainIndex(key);
 
        // Get head of chain
        HashNode<K, V> head = chain.get(chainIndex);
 
        // Search for key in its chain
        HashNode<K, V> prev = null;
        
        while (head != null) {
            // If Key found
            if (head.key.equals(key)) {
                break;
            }
            else {
                prev = head;
                head = head.next;
            }
            
        }
 
        // If key was not there
        if (head == null)
            return null;
 
        // Reduce numOfItems
        numOfItems--;
 
        // Remove key
        if (prev != null)
            prev.next = head.next;
        else
            chain.set(chainIndex, head.next);
 
        return head.value;
    }
 
    // Returns value for a key
    public V get(K key) {
        // Find head of chain for key
        int chainIndex = getChainIndex(key);
        HashNode<K, V> head = chain.get(chainIndex);
 
        // Search key in chain
        while (head != null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }
 
        // If key not found
        return null;
    }
 
    // Adds a key value pair to hash
    public void add(K key, V value) {
        // Find head of chain for given key
        int chainIndex = getChainIndex(key);
        HashNode<K, V> head = chain.get(chainIndex);
 
        // Check if key is already present
        while (head != null)
        {
            if (head.key.equals(key))
            {
                head.value = value;
                return;
            }
            head = head.next;
        }
 
        numOfItems++;
        head = chain.get(chainIndex);
        HashNode<K, V> newNode = new HashNode<K, V>(key, value);
        newNode.next = head;
        chain.set(chainIndex, newNode);
 
        // Rehashing; checking load facotr
        if ((1.0 * numOfItems) / size >= 0.7) {
            ArrayList<HashNode<K, V>> temp = chain;
            chain = new ArrayList<>();
            size = 2 * size;
            numOfItems = 0;
            for (int i = 0; i < size; i++)
                chain.add(null);
 
            for (HashNode<K, V> headNode : temp) {
                while (headNode != null) {
                    add(headNode.key, headNode.value);
                    headNode = headNode.next;
                }
            }
        }
    }
}
