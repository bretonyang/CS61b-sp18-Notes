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

    IntNode ptr = sentinel;
    while (ptr.next != null) {
      ptr = ptr.next;
    }

    ptr.next = new IntNode(x, null);
  }

  /** Returns the size of the SLList (fast way) */
  public int size() {
    return size;
  }

  /** 
   * A Level Exercise 1: 
   *  if 2 numbers in a row are the same, we add them together 
   * and make one large node.
   */
   public void addAdjacent() {
    IntNode ptr = sentinel;

    while (ptr.next != null && ptr.next.next != null) {
      if (ptr.next.item == ptr.next.next.item) {
        ptr.next.item = ptr.next.item + ptr.next.next.item;
        ptr.next.next = ptr.next.next.next;
        size--;
      }
      else {
        ptr = ptr.next;
      }
    }
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
    // TEST
    SLList list1 = new SLList(new int[]{0, 0});
    list1.printList();
    System.out.println(list1.size());

    System.out.println();

    list1.addAdjacent();
    list1.printList();
    System.out.println(list1.size());
  }

}
