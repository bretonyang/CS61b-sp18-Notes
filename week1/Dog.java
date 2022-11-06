public class Dog {
  public int weightInKg;
  public static String binomen = "Canis familiaris";

  // Constructor for Dog.
  public Dog(int w) {
    weightInKg = w;
  }

  public void makeNoise() {
    if (weightInKg < 10) {
      System.out.println("yip!");
    } else if (weightInKg < 30) {
      System.out. println("baark!");
    } else {
      System.out.println("wooooooof!");
    }
  }

  public static Dog maxDog(Dog d1, Dog d2) {
    if (d1.weightInKg > d2.weightInKg) {
      return d1;
    }
    return d2;
  }

  public Dog maxDog(Dog d2) {
    if (this.weightInKg > d2.weightInKg) {
      return this;
    }
    return d2;
  }
}
