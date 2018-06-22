public class Tester {
    public static void main(String[] args) {
        StackReference testStack = new StackReference();
        
        for (int i = 0; i < 40; i++) {
            testStack.push((Integer)i);
        }
        Node curr = testStack.getTop();
        for (int j = 0; j < 40; j++) {
            System.out.println(curr.getItem());
            curr = curr.getNext();
        }
    }
}
