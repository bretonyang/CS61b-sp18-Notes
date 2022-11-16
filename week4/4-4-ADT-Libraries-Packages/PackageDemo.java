/* Program to demonstrate use of import */
import breton.animal.Dog;

public class PackageDemo {
    public static void main(String[] args) {
        Dog d = new Dog("Corgi", 3);
        System.out.println(d.getName());
    }
}
