public class SpeedTestAList {
  public static void main(String[] args) {
    AList myList = new AList();
    for (int i = 0; i < 100000; i++) {
      myList.addLast(i);
    }
  }
}
