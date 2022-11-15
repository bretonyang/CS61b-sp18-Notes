public interface List61B<Item> {

    /** Inserts X into the back of the list. */
    void addLast(Item x);

    /** Returns the item from the back of the list. */
    Item getLast();

    /** Gets the ith item in the list (0 is the front). */
    Item get(int i);

    /** Returns the number of items in the list. */
    int size();

    /** Deletes item from back of the list and
     * returns deleted item. */
    Item removeLast();

    /** Inserts item into given position.
     * Code from discussion #3 */
    void insert(Item x, int position);

    /** Inserts an item at the front. */
    void addFirst(Item x);

    /** Gets an item from the front. */
    Item getFirst();

    /**
     * Prints out the entire list.
     */
    default void print() {
        for (int i = 0, n = size(); i < n; i++) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }

}
