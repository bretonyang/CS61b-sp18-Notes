/** An SLList is a list of integers, which hides the terrible 
  * truth of the nakedness within. */
public class SLList {

  private static class IntNode {
    public int item;
    public IntNode next;
    public IntNode(int i, IntNode n) {
      item = i;
      next = n;
    }
  }

  /* The first item (if it exists) is at sentinel.next */
  private IntNode sentinel;
  private int size;

  /** Creates an empty SLList */
  public SLList() {
    sentinel = new IntNode(69, null); // number doesn't matter here
    size = 0;
  }

  /** Creates a list with one item x */
  public SLList(int x) {
    sentinel = new IntNode(69, null); // or simply call this()
    sentinel.next = new IntNode(x, null);
    size = 1;
  }

  /** Creates a list out of an array of integers */
  public SLList(int[] arr) {
    sentinel = new IntNode(69, null);
    size = arr.length;

    IntNode ptr = sentinel;
    for (int n : arr) {
      ptr.next = new IntNode(n, null);
      ptr = ptr.next;
    }
  }

  /** Adds x to the front of the list */
  public void addFirst(int x) {
    size++;
    sentinel.next = new IntNode(x, sentinel.next);
  }

  /** Delete the first node in our list */
  public void deleteFirst() {
    size--;
    sentinel.next = sentinel.next.next;
  }

  /** Returns the first item in the list */
  public int getFirst() {
    return sentinel.next.item;
  }

  /** Adds x to the end of the list */
  public void addLast(int x) {
    size++;
 
    IntNode ptr = sentinel; // Starting at sentinel.

    // Move ptr until it reaches the end of the list.
    while (ptr.next != null) {
      ptr = ptr.next;
    }

    ptr.next = new IntNode(x, null);
  }

  /** Returns the size of the SLList (fast way) */
  public int size() {
    return size;
  }

  

  /** Just a helper function to help us visualize tests */
  public void printList() {
    IntNode ptr = sentinel;
    while (ptr.next != null) {
      ptr = ptr.next;
      System.out.print(" " + ptr.item);
    }
    System.out.println();
  }

  public static void main(String[] args) {
    /* Creates a list of integers, 5, 10, 15 */
    SLList L = new SLList(15);
    L.printList();

    L.addFirst(10);
    L.printList();

    L.addFirst(5);
    L.printList();

    L.addLast(20);
    L.printList();

    L.deleteFirst();
    L.printList();

    System.out.println(L.size()); 

    SLList L2 = new SLList(new int[] {3, 1, 2, -5, 14});
    L2.printList();

    L2.addFirst(69);
    L2.printList();

    L2.deleteFirst();
    L2.printList();
  }

}
