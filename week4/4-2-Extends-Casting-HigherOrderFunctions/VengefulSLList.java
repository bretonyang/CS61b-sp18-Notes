public class VengefulSLList<Item> extends SLList<Item> {
    private SLList<Item> deletedItems;

    public VengefulSLList() {
        super();
        deletedItems = new SLList<>();
    }

    public VengefulSLList(Item x) {
        super(x);
        deletedItems = new SLList<>();
    }

    public void printLostItems() {
        deletedItems.print();
    }

    @Override
    public Item removeLast() {
        Item x = super.removeLast();
        deletedItems.addFirst(x);
        return x;
    }

    public static void main(String[] args) {
        VengefulSLList<Integer> vList = new VengefulSLList<>(9);

        vList.addLast(1);
        vList.addLast(5);
        vList.addLast(10);
        vList.addLast(13);

        vList.removeLast();
        vList.removeLast();

        System.out.print("The removed are: ");
        vList.printLostItems();
    }
}
