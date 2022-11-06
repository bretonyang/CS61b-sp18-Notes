public class RotatingSLList<Item> extends SLList<Item> {

    /** Rotates List to the right. */
    public void rotateRight() {
        Item x = removeLast();
        addFirst(x);
    }

    public static void main(String[] args) {
        RotatingSLList<Integer> rList = new RotatingSLList<>();

        rList.addLast(10);
        rList.addLast(11);
        rList.addLast(12);
        rList.addLast(13);

        rList.rotateRight();
        rList.print();
    }

}
