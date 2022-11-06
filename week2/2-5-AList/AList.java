/** Array based list. */

//         0 1  2 3 4 5 6 
// items: [6 9 -1 2 0 0 0 ...]
// size: 4

/* Invaraints:
1. position of next item to be inserted is always size.
2. size is always the number of items in the AList. 
3. The last item in the list is always at position size - 1.
*/

public class AList<Item> {

  private Item[] items; 
  private int size;
  
  /** Creates an empty list. */
  public AList() {
    items = (Item[]) new Object[100];
    size = 0;
  }

  /** Resizes the underlying array to the target capacity */
  private void resize(int capacity) {
    Item[] newItems = (Item[]) new Object[capacity];
    System.arraycopy(items, 0, newItems, 0, size);
    items = newItems;
  }

  /** Inserts X into the back of the list. */
  public void addLast(Item x) {
    if (size == items.length) {
      resize(size * 2);
    }
    
    items[size] = x;
    size++;
  }

  /** Returns the item from the back of the list. */
  public Item getLast() {
    return items[size - 1];
  }

  /** Gets the ith item in the list (0 is the front). */
  public Item get(int i) {
    return items[i];
  }

  /** Returns the number of items in the list. */
  public int size() {
    return size;
  }

  /** Deletes item from back of the list and
  * returns deleted item. */
  public Item removeLast() {
    Item returnItem = getLast();
    items[size - 1] = null; // to avoid loitering
    size--;
    return returnItem;
  }

}
