/**
 * Demonstrates higher order functions (HoF) in Java.
 */
public class HoFDemo {
    public static int doTwice(IntUnaryFunction f, int x) {
        return f.apply(f.apply(x));
    }

    public static void main(String[] args) {
        int a = doTwice(new TenX(), 2);
        System.out.println(a);
    }
}
