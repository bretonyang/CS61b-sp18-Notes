# Ch.8 Efficient Programming

- [Ch.8 Efficient Programming](#ch8-efficient-programming)
  - [8-1 Encapsulation](#8-1-encapsulation)
    - [Encapsulation](#encapsulation)
    - [ADT's](#adts)
    - [API's](#apis)
    - [Delegation vs Extension](#delegation-vs-extension)
    - [Views](#views)
    - [Takeaway](#takeaway)
  - [8-2 Asymptotics I: An Introduction to Asymptotic Analysis](#8-2-asymptotics-i-an-introduction-to-asymptotic-analysis)
    - [Example of Algorithm Cost](#example-of-algorithm-cost)
    - [Runtime Characterization](#runtime-characterization)

## 8-1 Encapsulation

Efficiency comes in two flavors:

1. Programming cost.

- How long does it take to develop your programs?
- How easy is it to read, modify, and maintain your code?

2. Execution cost.

- How much time does your program take to execute?
- How much memory does your program require?

Today, we will be focusing on how to reduce programming cost. Some helpful Java features discussed in 61B:

- Packages.
  - Good: Keeps code organized (in folders), and makes things package-private.
  - Bad: Too specific (more work to be done, e.g. import)
- Static type checking.
  - Good: Checks for errors early, and speeds up runtime (no need for runtime type checking)
  - Bad: Not too flexible (might need casting), and more verbose code.
- Inheritance (Implementation and interface inheritance).
  - Good: Reuse of code, and allows subtype polymorphism.
  - Bad: “Is-a”, the path of debugging gets annoying, can’t instantiate interfaces, have to implement every method of an interface, and makes code harder to read/understand.

We will explore some new ways in this chapter!

### Encapsulation

We will first define a few terms:

- **Module**: A set of methods that work together as a whole to perform some task or set of related tasks.

- **Encapsulated**: A module is said to be encapsulated if its implementation is completely hidden, and it can be accessed only through a documented interface.

### ADT's

- ADT's (Abstract Data Types) are high-level types that are defined by their **behaviors**, not their implementations.

  e.g., Deque in Proj1 was an ADT that had certain behaviors (addFirst, addLast, etc.). But, the data structures we actually used to implement that ADT was ArrayDeque and LinkedListDeque.

- Some ADT's are actually special cases of other ADT's.

  e.g., Stacks and Queues are just Lists that have even more specific behavior.

### API's

An API(Application Programming Interface) of an ADT is the list of constructors and methods and a short description of each.

API consists of syntactic and semantic specification.

- Compiler verifies that **syntax** is met.
  - AKA, everything specified in the API is present.
- Tests help verify that **semantics** are correct.
  - AKA everything actually works the way it should.
  - Semantic specification usually written out in English (possibly including usage examples). Mathematically precise formal specifications are somewhat possible but not widespread.

**Exercise 8.1.1**:

Write a Stack class using a Linked List as its underlying data structure. You only need to implement a single function: `push(Item x)`. Make sure to make the class generic with "Item" being the generic type!

My ans:

```java
public class MyStack<Item> {
  private LinkedList<Item> list = = new LinkedList<>();

  public void push(Item x) {
    list.add(x);
  }
}
```

Three polular solutions:

1. This solution uses extension. it simply borrow the methods from `LinkedList<Item>` and uses them as its own.

   ```java
    public class ExtensionStack<Item> extends LinkedList<Item> {
      public void push(Item x) {
        add(x);
      }
    }
   ```

2. This approach uses **Delegation**. It creates a Linked List object and calls its methods to accomplish its goal.

   ```java
   public class DelegationStack<Item> {
      private LinkedList<Item> L = new LinkedList<Item>();
      public void push(Item x) {
          L.add(x);
      }
   }
   ```

3. This approach is similar to the previous one, except it can use any class that implements the `List` interface (`LinkedList`, `ArrayList`, etc).

   ```java
   public class StackAdapter<Item> {
      private List L;
      public StackAdapter(List<Item> worker) {
          L = worker;
      }

      public void push(Item x) {
          L.add(x);
      }
   }
   ```

**Warning**: be mindful of the difference between "is-a" and "has-a" relationships.

### Delegation vs Extension

Earlier in the section define that **delegation** is accomplished by passing in a class while **extension** is defined as inheriting (just because it may be hard to notice at first glance).

**Delegation vs Extension**: Right now it may seem that Delegation and Extension are pretty much interchangeable; however, there are some important differences that must be remembered when using them.

**Extension** tends to be used when you know what is going on in the parent class. In other words, you know how the methods are implemented. Additionally, with extension, you are basically saying that the class you are extending from acts similarly to the one that is doing the extending.

**Delegation** is when you do not want to consider your current class to be a version of the class that you are pulling the method from.

### Views

**Views** are an alternative representation of an existed object. Views essentially limit the access that the user has to the underlying object. However, changes done through the views will affect the actual object.

For example, say, you only want a list from index 1 and 4. Then you can use a method `sublist` like this:

```java
List<String> L = new ArrayList<>();
L.add("at"); L.add("ax"); L.add("ban"); ...

List<String> subList = L.sublist(1, 4);
subList.set(0, "jug");
```

Now why is this useful? Well say we want to reverse only part of the list. For example in the below image, we would want to reverse `ax ban bat` in the above list.

![reverse list 1](./note-img/Ch8/reverse_list1.png)

The most intuitive way is to create a method that takes in a list object and the indices which should be reversed. However, this can be a bit painful because we add some extraneous logic.

To get around doing this, we can just create a general reverse function that takes in a list and reverses that list. Because views mutates the underlying object that it represents, we can create a sublist like earlier and reverse the sublist. The end result would actually mutate the actual list and not the copy.

![reverse list 2](./note-img/Ch8/reverse_list2.png)

### Takeaway

- **API**s are hard to design; however, having a coherent design philosophy can make your code much cleaner and easier to deal with.

- **Inheritance** is tempting to use frequently, but it has problems and should be use sparingly, only when you are certain about attributes of your classes (both those being extended and doing the extending).

---

## 8-2 Asymptotics I: An Introduction to Asymptotic Analysis

We can consider the process of writing efficient programs from two different perspectives:

1. Programming Cost (_everything before this section_)
   - How long does it take for you to develop your programs?
   - How easy is it to read or modify your code?
   - How maintainable is your code? (very important — much of the cost comes from maintenance and scalability, not development!)
2. Execution Cost (_everything after this section_)
   - Time complexity: How much time does it take for your program to execute?
   - Space complexity: How much memory does your program require?

### Example of Algorithm Cost

Objective: Determine if a _sorted_ array contains any duplicates.

**Silly Algorithm**: Consider **every** pair, returning true if any match!

**Better Algorithm**: Take advantage of the **sorted** nature of our array.

- We know that if there are duplicates, they must be next to each other.
- Compare neighbors: return true first time you see a match! If no more items, return false.

We can see that the Silly algorithm seems like it’s doing a lot more unnecessary, redundant work than the Better algorithm. But how much more work? How do we actually quantify or determine how efficient a program is? This chapter will provide you the formal techniques and tools to compare the efficiency of various algorithms!

### Runtime Characterization
