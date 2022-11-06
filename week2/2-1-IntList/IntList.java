public class IntList {
  public int first;
  public IntList rest;

  public IntList(int f, IntList r) {
    first = f;
    rest = r;
  }

  /** Return the size of the list using recursion */
  public int size() {
    if (rest == null) {
      return 1;
    }
    return 1 + rest.size();
  }

  /** Return the size of the List using iteration */
  public int iterativeSize() {
    IntList p = this;
    int totalSize = 0;
    while (p != null) {
      totalSize++;
      p = p.rest;
    }
    return totalSize;
  }

  /** Returns the ith item of this list using recursion */
  public int get(int i) {
    if (i == 0) {
      return first;
    }
  
    return rest.get(i - 1);
  }

  /** 
   * Convert IntList to String format 
   * NOTE: This is NOT included in sp18 lecture. Instead, 
   * it's from the sp16 version. 
   */
  public String toString() {
    if (rest == null) {
      return Integer.toString(first);
    }
    return Integer.toString(first) + " " + rest.toString();
  }

  /** 
   * Returns an new IntList identical to L, but with
   * each element incremented by x. L is not allowed
   * to change. 
   */
  public static IntList incrList(IntList L, int x) {
    // Using recursion, but iteration also works. 
    // Also, other base cases are possible
    if (L == null) {
      return null;
    }
    return new IntList(L.first + x, incrList(L.rest, x));

    /*
     * Josh Hug prof's solution:
     * You can do the two lines on one line, but he've opted
     * for two lines for maximum pedagogical clarity. 
     */
    // IntList incrementedList = new IntList(L.first + x, null);
    // incrementedList.rest = incrList(L.rest, x);
    // return incrementedList;
  }

  /** 
   * Returns an IntList identical to L, but with
   * each element incremented by x. Not allowed to use
   * the 'new' keyword. 
   */
  public static IntList dincrList(IntList L, int x) {
    /* 
     * Using recursion, but iteration also works. 
     * Also, other base cases are possible.
     */ 
    if (L == null) {
      return null;
    }
    
    L.first += x;
    dincrList(L.rest, x); // Don't care about return value here

    return L;
  }

  public static void main(String[] args) {
    IntList L = new IntList(15, null);
    L = new IntList(10, L);
    L = new IntList(5, L);

    System.out.println(L.size());
    System.out.println(L.iterativeSize());
    System.out.println(L.get(0));

    IntList L2 = incrList(L, 3);
    IntList L3 = dincrList(L, 3);

    System.out.println("Testing for memory locations ...");
    /* 
     * toString() method returns the String representation of 
     * the object. If you print any object, Java compiler 
     * internally invokes the toString() method on the object.
     * 
     * If we didn't write our own toString() here, then Java will 
     * print the address of these objects. 
     */
    System.out.println(L);
    System.out.println(L2);
    System.out.println(L3); 

    System.out.println("Testing for incrList() ...");
    System.out.println(L2.size());
    System.out.println(L2.get(0));
    System.out.println(L2.get(1));
    System.out.println(L2.get(2));
    System.out.println(L2.toString());

    System.out.println("Testing for dincrList() ...");
    System.out.println(L3.size());
    System.out.println(L3.get(0));
    System.out.println(L3.get(1));
    System.out.println(L3.get(2));
    System.out.println(L3.toString());
  }
}
