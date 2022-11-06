/** Demonstrates declaration of a method in Java */
public class LargerDemo {
  /**
   * Returns the Larger of x and y.
   * 
   * @param x an integer
   * @param y another integer to compare with x
   * @return the larger of x and y
   */
  public static int larger(int x, int y) {
    if (x > y) {
      return x;
    }
    return y;
  }

  public static void main(String[] args) {
    System.out.println(larger(-5, 15));
  }
}
