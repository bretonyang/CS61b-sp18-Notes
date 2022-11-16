/* Program to demonstrate use of package */
package breton.animal;

public class Dog {
    private String name;
    private double size;

    public Dog(String name, double size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public double getSize() {
        return size;
    }
}
