public class Exercise {
  public static void main(String[] args) {
    Dog smallDog = new Dog(5);
    Dog mediumDog = new Dog(25);
    Dog hugeDog = new Dog(150);

    Dog[] dogs = new Dog[4];
    dogs[0] = smallDog;
    dogs[1] = hugeDog;
    dogs[2] = new Dog(130);

    for (int i = 0; i < dogs.length; i++) {
      Dog.maxDog(dogs[i], mediumDog).makeNoise();
    }
  }

  public static class Dog {
    public int size;

    public Dog(int s) {
      size = s;
    }

    public void makeNoise() {
      if (size < 10) {
        System.out.println("yip");
      } else if (size < 30) {
        System.out.println("bark bark");
      } else {
        System.out.println("woof woof woof");
      }
    }

    public static Dog maxDog(Dog d1, Dog d2) {
      if (d1.size > d2.size) {
        return d1;
      }
      return d2;
    }
  }
}