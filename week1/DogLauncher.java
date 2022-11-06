public class DogLauncher {
  public static void main(String[] args) {
    Dog d = new Dog(25);
    Dog d2 = new Dog(100);

    // Here, we don't want the instantiated dogs to do the judgement
    // Instead, we want the "god" version of Dogs to decide.
    Dog bigger = Dog.maxDog(d, d2);
    bigger.makeNoise();

    // non-static version
    Dog bigger2 = d.maxDog(d2);
    bigger2.makeNoise();

    // static variables
    System.out.println(d.binomen); // BAD style, DON'T do this
    System.out.println(d2.binomen); // BAD style, DON'T do this
    System.out.println(Dog.binomen); // GOOD
  }
}
