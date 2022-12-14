# Week 4

- [Week 4](#week-4)
  - [4-1 Intro and Interfaces](#4-1-intro-and-interfaces)
    - [The Desire for Generality](#the-desire-for-generality)
    - [Hypernyms, Hyponyms, and Interface Inheritance](#hypernyms-hyponyms-and-interface-inheritance)
    - [Overriding vs. Overloading](#overriding-vs-overloading)
      - [Code](#code)
    - [Interface Inheritance](#interface-inheritance)
    - [Implementation Inheritance: Default Methods](#implementation-inheritance-default-methods)
    - [Static and Dynamic Type, Dynamic Method Selection](#static-and-dynamic-type-dynamic-method-selection)
    - [More Dynamic Method Selection, Overloading vs. Overriding](#more-dynamic-method-selection-overloading-vs-overriding)
    - [Interface Inheritance vs. Implementation Inheritance](#interface-inheritance-vs-implementation-inheritance)
  - [4-2 Extends, Casting, Higher Order Functions](#4-2-extends-casting-higher-order-functions)
    - [Extends](#extends)
    - [VengefulSLList](#vengefulsllist)
    - [Constructors Are Not Inherited](#constructors-are-not-inherited)
    - [The Object Class](#the-object-class)
    - [Encapsulation](#encapsulation)
    - [Abstraction Barriers](#abstraction-barriers)
    - [My Questions](#my-questions)
    - [How Inheritance Breaks Encapsulation](#how-inheritance-breaks-encapsulation)
      - [Note](#note)
      - [Walkthrough of exercise](#walkthrough-of-exercise)
    - [Type Checking and Casting](#type-checking-and-casting)
      - [Expressions](#expressions)
      - [Casting](#casting)
    - [Higher Order Functions](#higher-order-functions)
    - [Inheritance Cheatsheet](#inheritance-cheatsheet)
  - [4-3 Subtype Polymorphism vs. HoFs](#4-3-subtype-polymorphism-vs-hofs)
    - [Dynamic Method Selection Puzzle](#dynamic-method-selection-puzzle)
    - [Intro](#intro)
    - [Max Function](#max-function)
    - [Interfaces Quiz](#interfaces-quiz)
      - [Sumary](#sumary)
      - [Our comparable Code](#our-comparable-code)
    - [Comparables](#comparables)
    - [Comparator](#comparator)
      - [Hof way](#hof-way)
      - [Java's way](#javas-way)
      - [Summary](#summary)
  - [4-4 Libraries, Abstract Classes, Packages](#4-4-libraries-abstract-classes-packages)
    - [Abstract Data Types (ADTs)](#abstract-data-types-adts)
    - [Java Libraries](#java-libraries)
    - [Java vs. Python](#java-vs-python)
    - [Abstract classes](#abstract-classes)
      - [More about interfaces and abstract classes](#more-about-interfaces-and-abstract-classes)
      - [Weird stuff](#weird-stuff)
      - [When to use abstract classes and when to use interfaces](#when-to-use-abstract-classes-and-when-to-use-interfaces)
    - [Packages](#packages)
      - [Summary](#summary-1)

## 4-1 Intro and Interfaces

### The Desire for Generality

1. ![](./note-img/4-1/5.PNG)
   ![](./note-img/4-1/6.PNG)
   ![](./note-img/4-1/7.PNG)
   ![](./note-img/4-1/8.PNG)
   ![](./note-img/4-1/9.PNG)
   ![](./note-img/4-1/10.PNG)

2. It's nice that Java is smart enough to know how to deal with two of the same methods for different types, but **overloading** has several downsides:

   - It's super repetitive and ugly, because you now have two virtually identical blocks of code.
   - It's more code to maintain, meaning if you want to make a small change to the longest method such as correcting a bug, you need to change it in the method for each type of list.
   - If we want to make more list types, we would have to copy the method for every new list class.

### Hypernyms, Hyponyms, and Interface Inheritance

1. ![](./note-img/4-1/13.PNG)
   ![](./note-img/4-1/14.PNG)
   ![](./note-img/4-1/16.PNG)
   ![](./note-img/4-1/17.PNG)
   ![](./note-img/4-1/18.PNG)

2. We will formalize this relationship in Java: if a SLList is a hyponym of List61B, then the SLList class is a **subclass** of the List61B class and the List61B class is a **superclass** of the SLList class.

   ![](./note-img/4-1/extra-superclass-subclass.PNG)

3. The new List61B is what Java calls an **interface**. It is essentially a contract that specifies what a list must be able to do, but it doesn't provide any implementation for those behaviors.

4. `implements` is essentially a promise. AList is saying "I promise I will have and define all the attributes and behaviors specified in the List61B interface"

### Overriding vs. Overloading

1. ![](./note-img/4-1/20.PNG)
   ![](./note-img/4-1/21.PNG)
   ![](./note-img/4-1/22.PNG)
   ![](./note-img/4-1/23.PNG)

2. **Overriding**: Only happens with inheritance, if a subclass has a method with the exact same signature as in the superclass, we say that the subclass **overrides** the method.

   **Overloading**: Simply happens when methods have the same name but different signatures.

3. It's a good practice to use the `@Override` annotation for overridden methods.

   It is good to note that even if you don???t include this tag, you are still overriding the method. So technically, you don't _have_ to include it. However, including the tag acts as a safeguard for you as the programmer by alerting the compiler that you intend to override this method. The compiler will tell you if something goes wrong in the process.

   Say you want to override the `addLast` method. What if you make a typo and accidentally write `addLsat`? If you don't include the `@Override` tag, then you might not catch the mistake, which could make debugging a more difficult and painful process. Whereas if you include `@Override`, the compiler will stop and prompt you to fix your mistakes before your program even runs.

4. The `@Override` annotation does the these checks: Indicates that a method declaration is intended to override a method declaration in a supertype. If a method is annotated with this annotation type compilers are required to generate an error message unless at least one of the following conditions hold:
   - The method does override or implement a method declared in a supertype.
   - The method has a signature that is override-equivalent to that of any public method declared in `Object`.

#### Code

```java
public interface List61B<Item> {

    /** Inserts X into the back of the list. */
    public void addLast(Item x);

    /** Returns the item from the back of the list. */
    public Item getLast();

    /** Gets the ith item in the list (0 is the front). */
    public Item get(int i);

    /** Returns the number of items in the list. */
    public int size();

    /** Deletes item from back of the list and
     * returns deleted item. */
    public Item removeLast();

    /** Inserts item into given position.
     * Code from discussion #3 */
    public void insert(Item x, int position);

    /** Inserts an item at the front. */
    public void addFirst(Item x);

    /** Gets an item from the front. */
    public Item getFirst();

}
```

```java
/** Array based list.
 *  @author Josh Hug
 */

/* The next item ALWAYS goes in the size position */

public class AList<Item> implements List61B<Item>{
    /* the stored integers */
    private Item[] items;
    private int size;

    private static int RFACTOR = 2;

    /** Creates an empty list. */
    public AList() {
        size = 0;
        items = (Item[]) new Object[100];
    }

    /** Resize our backing array so that it is
     * of the given capacity. */
    private void resize(int capacity) {
        Item[] a = (Item[]) new Object[capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
    }

    /** Inserts X into the back of the list. */
    @Override
    public void addLast(Item x) {
        if (size == items.length) {
            resize(size * RFACTOR);
        }

        items[size] = x;
        size = size + 1;
    }

    /** Returns the item from the back of the list. */
    @Override
    public Item getLast() {
        int lastActualItemIndex = size - 1;
        return items[lastActualItemIndex];
    }

    /** Gets the ith item in the list (0 is the front). */
    @Override
    public Item get(int i) {
        return items[i];
    }

    /** Returns the number of items in the list. */
    @Override
    public int size() {
        return size;
    }

    /** Deletes item from back of the list and
     * returns deleted item. */
    @Override
    public Item removeLast() {
        Item itemToReturn = getLast();
        /* setting to zero not strictly necessary, but
         * it's a good habit (we'll see why soon) */
        items[size - 1] = null;
        size = size - 1;
        return itemToReturn;
    }

    /** Inserts item into given position.
     * Code from discussion #3 */
    @Override
    public void insert(Item x, int position) {
        Item[] newItems = (Item[]) new Object[items.length + 1];

        System.arraycopy(items, 0, newItems, 0, position);
        newItems[position] = x;

        System.arraycopy(items, position, newItems, position + 1, items.length - position);
        items = newItems;
    }

    /** Inserts an item at the front. */
    @Override
    public void addFirst(Item x) {
        insert(x, 0);
    }

    /** Gets an item from the front. */
    @Override
    public Item getFirst() {
        return get(0);
    }

}
```

```java
/* Represent a list of stuff, where all the "list" work is delegated
 * to a naked recursive data structure. */

public class SLList<Blorp> implements List61B<Blorp>{
    public class Node {
        public Blorp item;     /* Equivalent of first */
        public Node next; /* Equivalent of rest */

        public Node(Blorp i, Node h) {
            item = i;
            next = h;
        }
    }

    private Node sentinel;
    private int size;

    /** Creates an empty list. */
    public SLList() {
        size = 0;
        sentinel = new Node(null, null);
    }

    public SLList(Blorp x) {
        size = 1;
        sentinel = new Node(null, null);
        sentinel.next = new Node(x, null);
    }

    /** Adds an item of the front. */
    @Override
    public void addFirst(Blorp x) {
        Node oldFrontNode = sentinel.next;
        Node newNode = new Node(x, oldFrontNode);
        sentinel.next = newNode;
        size += 1;
    }

    /** Gets the front item of the list. */
    @Override
    public Blorp getFirst() {
        return sentinel.next.item;
    }

    /** Puts an item at the back of the list. */
    @Override
    public void addLast(Blorp x) {
        size += 1;

        Node p = sentinel;

        /* Move p until it reaches the end. */
        while (p.next != null) {
            p = p.next;
        }

        p.next = new Node(x, null);
    }

    /** Returns the back node of our list. */
    private Node getLastNode() {
        Node p = sentinel;

        /* Move p until it reaches the end. */
        while (p.next != null) {
            p = p.next;
        }
        return p;
    }

    /** Returns last item */
    @Override
    public Blorp getLast() {
        Node back = getLastNode();
        return back.item;
    }

    /** Deletes and returns last item. */
    @Override
    public Blorp removeLast() {
        Node back = getLastNode();
        if (back == sentinel) {
            return null;
        }

        Node p = sentinel;

        while (p.next != back) {
            p = p.next;
        }
        p.next = null;
        return back.item;
    }

    @Override
    public int size() {
        return size;
    }

    /** Gets the positionth item of the list. */
    @Override
    public Blorp get(int position) {
        if (position == 0) {
            return getFirst();
        }
        Node currentNode = sentinel.next.next;
        while (position > 1 && currentNode.next != null) {
            position -= 1;
            currentNode = currentNode.next;
        }

        return currentNode.item;
    }

    /** Inserts item into given position.
     * Code from discussion #3 */
    @Override
    public void insert(Blorp item, int position) {
        if (sentinel.next == null || position == 0) {
            addFirst(item);
            return;
        }

        Node currentNode = sentinel.next.next;
        while (position > 1 && currentNode.next != null) {
            position -= 1;
            currentNode = currentNode.next;
        }

        Node newNode = new Node(item, currentNode.next);
        currentNode.next = newNode;
    }


    /** TODO: Add a print method that overrides List61B's inefficient print method. */
    public void print() {
        for (Node p = sentinel.next; p != null; p = p.next) {
            System.out.print(p.item + " ");
        }
    }
}
```

```java
public class WordUtils {
    /** Returns the length of the longest word. */
    public static String longest(List61B<String> list) {
        int maxIdx = 0;
        for (int i = 0; i < list.size(); i += 1) {
            String longestString = list.get(maxIdx);
            String thisString = list.get(i);
            if (thisString.length() > longestString.length()) {
                maxIdx = i;
            }
        }
        return list.get(maxIdx);
    }

    public static void main(String[] args) {
        SLList<String> someList = new SLList<>();
        someList.addLast("elk");
        someList.addLast("are");
        someList.addLast("watching");
        System.out.println(longest(someList));
        //someList.print();
    }
}
```

### Interface Inheritance

1. ![](./note-img/4-1/25.PNG)
   ![](./note-img/4-1/26.PNG)
   ![](./note-img/4-1/27.PNG)
   ![](./note-img/4-1/28.PNG)
   ![](./note-img/4-1/29.PNG)
   ![](./note-img/4-1/30.PNG)

2. > NOTE: A memory box can only hold 8byte address for the appropriate type. e.g. `String x` can never hold the 8byte address of a `Dog`. However, super/subclasses have something to do:

3. It's important to note that a variable of a superclass can have memory boxes holding its subclass's pointers. So, it's ok to do

   ```java
   List61B<String> myList = new SLList<String>();
   ```

   Also, when we then call `myList.addFirst("elk");`, this also runs fine, because although the `List61B` interface does NOT have the implementation of `addFirst()`, but the SLList that `myList` is pointing to "overrides" that method.

### Implementation Inheritance: Default Methods

1. ![](./note-img/4-1/32.PNG)
   ![](./note-img/4-1/33.PNG)
   ![](./note-img/4-1/34.PNG)
   ![](./note-img/4-1/35.PNG)
   ![](./note-img/4-1/36.PNG)
   ![](./note-img/4-1/37.PNG)
   ![](./note-img/4-1/38.PNG)

2. In an `interface`, the methods can NOT have a body. So, if we need to achieve **implementation inheritance** (where methods implemented in the superclass is inherited by its subclass), then we'll need to use the keyword `default` in the signature of the method. e.g.:

   ```java
   default public void print() {
      // DO SOMETHING
   }
   ```

3. Note that although we don't have any instance variables to manipulate inside List61B class, but we do have the signature of the methods of its subclass, which allow us to use those methods in the `print()` method.

4. Turns out the `print()` method implemented in `List61B` is not efficient for SLList, since its `get()` method needs to traverse through the whole list until the ith item.

   So, it's efficient for AList, but inefficient for SLList.

### Static and Dynamic Type, Dynamic Method Selection

1. ![](./note-img/4-1/40.PNG)
   ![](./note-img/4-1/41.PNG)
   ![](./note-img/4-1/42.PNG)
   ![](./note-img/4-1/43.PNG)
   ![](./note-img/4-1/44.PNG)
   ![](./note-img/4-1/45.PNG)

2. The term **Method Selection** is actually non-standard, but there's no official term for it, so we'll simply call it method selection.

3. Summary: In Java, variables have a "static type" which is the type given in declaration, and a "dynamic type" which is the type given in initialization during runtime. And the "dynamic type" can be changed many times during runtime.

### More Dynamic Method Selection, Overloading vs. Overriding

1. If the dynamic type Y has a method that **overrides** a method from its compile-time type X ("overriding" means to have the **exact same signature**, even including the parameter type, name, and the return type, but NOT including the modifiers like `public`, `default`), then Y's implementation of that method is used instead.

2. NOTE: **Override** only happens when the signatures are exactly the same.

   e.g. when superclass has `default void flatter(Animal a)` and the subclass has `void flatter(Dog a)`. The difference in the signatures is the parameter type. And since they're actually different signatures, they won't be overridden. Instead, these two methods are **overloaded** and NOT overridden.

3. Based on the above, we came up with how the **Method Selection** works:

   ![](./note-img/4-1/52.PNG)

4. So, in the above example:

   ![](./note-img/4-1/53.PNG)

   First, at compile time, the compiler sees `a.flatter(d)` and searches for a method in `Animal` tha can handle this. If 2 or more methods can handle this, then the most **specific** method is picked. The compiler then "records" the signature `void flatter(Animal a)`.

   Second, when the program runs, the code `a.flatter(d)` have to use the method with the exact same signature `void flatter(Animal a)` recorded by the compiler at compile-time. If some method in `Dog` has the exact same signature, then the program will actually use the one defined in `Dog`. This behavior is called overriding.

5. SUMMARY:

   - **Compile-time**: setup the "static type", and record the signature of the most specific method to this type. (**"overload"** could happen here)

   - **Run-time**: setup the "dynamic type" (might be the same as static type or a subclass type). If its dynamic type is indeed a subclass type and the method is **"override"** in the subclass, then JVM will run that overriding method instead (note that in order to override, methods must have exactly the same signatures). If no override takes place, then simply the original recorded method is run.

6. The fact that "dynamic typing" is checked and "override" mehtods are called only in runtime is intuitive, since at compile we haven't even pass the object yet, then how can JVM know what type the argument is? So, only at runtime when we know the type of the argument can JVM checks for overridden methods.

7. > But note that the **signature** is already decided at compile time, so if the signature is not the same as the overridden method, then JVM will NOT call the override method.

8. "Static type": The type of the object pointer variable. So, the variable pointing to the object will always be of this type, meaning that its pointer points to object of this static type.

   "Dynamic type": The type of the instantiated object that the pointer is currently pointing to.

9. Say there are two methods in the same class

   ```java
   public static void peek(List61B<String> list) {
        System.out.println(list.getLast());
    }
    public static void peek(SLList<String> list) {
        System.out.println(list.getFirst());
    }
   ```

   and you run this code

   ```java
   SLList<String> SP = new SLList<String>();
   List61B<String> LP = SP;
   SP.addLast("elk");

   peek(SP); // calls the 2nd method
   peek(LP); // calls the 1st method
   ```

   The first call to peek() will use the second peek method that takes in an SLList. The second call to peek() will use the first peek method which takes in a List61B. This is because the only distinction between two overloaded methods is the types of the parameters.

   When Java checks to see which method to call, it checks the **static type** and calls the method with the parameter of the same type.

### Interface Inheritance vs. Implementation Inheritance

1. How do we differentiate between "interface inheritance" and "implementation inheritance"? Well, you can use this simple distinction:

   - Interface inheritance (what): Simply tells what the subclasses should be able to do.
     - EX) all lists should be able to print themselves, how they do it is up to them.
   - Implementation inheritance (how): Tells the subclasses how they should behave.
     - EX) Lists should print themselves exactly this way: by getting each element in order and then printing them.

2. When you are creating these hierarchies, remember that the relationship between a subclass and a superclass should be an **_"is-a"_** relationship. AKA Cat should only implement Animal Cat is an Animal. You should not be defining them using a "has-a" relationship. Cat has-a Claw, but Cat definitely should not be implementing Claw.

3. Finally, Implementation inheritance may sound nice and all but there are some drawbacks:
   - It's possible that you overrode a method but forgot you did.
   - It may be hard to resolve conflicts in case two interfaces give conflicting default methods.
   - It encourages overly complex code
   - WORST OF ALL: Breaks encapsulation!!!

---

## 4-2 Extends, Casting, Higher Order Functions

### Extends

Now you've seen how we can use the `implements` keyword to define a hierarchical relationship with interfaces. What if we wanted to define a "hierarchical relationship between classes"?

Suppose we want to build a `RotatingSLList` that has the same functionality as the `SLList` like `addFirst`, `size`, etc., but with an additional `rotateRight` operation to bring the last item to the front of the list.

One way you could do this would be to copy and paste all the methods from `SLList` and write `rotateRight` on top of it all - but then we wouldn't be taking advantage of the power of inheritance! Remember that **inheritance allows subclasses to reuse code from an already defined class**. So let's define our `RotatingSLList` class to inherit from SLList.

We can set up this inheritance relationship in the class header, using the extends keyword like so:

```java
public class RotateSLList<Item> extends SLList<Item>
```

In the same way that `AList` shares an "is-a" relationship with `List61B`, `RotatingSLList` shares an "is-a" relationship `SLList`. The `extends` keyword lets us keep the original functionality of SLList, while enabling us to make modifications and add additional functionality.

![list_subclasses](./note-img/4-2/list_subclasses.png)

**Exercise 4.2.1.** Define the rotateRight method, which takes in an existing list, and rotates every element one spot to the right, moving the last item to the front of the list.

For example, calling `rotateRight` on [5, 9, 15, 22] should return [22, 5, 9, 15].

```java
public void rotateRight() {
    Item x = removeLast();
    addFirst(x);
}
```

You might have noticed that we were able to use methods defined outside of `RotatingSLList`, because we used the extends keyword to inherit them from SLList. That gives rise to the question: What exactly do we inherit?

By using the `extends` keyword, subclasses inherit all **members of the parent class**. "Members" includes:

- All instance and static variables
- All methods
- All nested classes

> Note that "constructors" are not inherited, and `private` members cannot be directly accessed by subclasses.

### VengefulSLList

Notice that when someone calls `removeLast` on an SLList, it throws that value away - never to be seen again. But what if those removed values left and started a massive rebellion against us? In this case, we need to remember what those removed values were so we can hunt them down and terminate them later.

We create a new class, `VengefulSLList`, that remembers all items that have been banished by removeLast.

Like before, we specify in VengefulSLList's class header that it should inherit from SLList.

Now, let's give VengefulSLList a method to print out all of the items that have been removed by a call to the removeLast method, `printLostItems()`. We can do this by adding an instance variable that can keep track of all the deleted items. If we use an SLList to keep track of our items, then we can simply make a call to the print() method to print out all the items.

VengefulSLList's `removeLast` should do exactly the same thing that SLList's does, except with one additional operation - adding the removed item to the `deletedItems` list. In an effort to reuse code, we can override the `removeLast` method to modify it to fit our needs, and call the `removeLast` method defined in the parent class, SLList, using the `super` keyword.

```java
public class VengefulSLList<Item> extends SLList<Item> {
    SLList<Item> deletedItems;

    public VengefulSLList() {
        deletedItems = new SLList<Item>();
    }

    @Override
    public Item removeLast() {
        Item x = super.removeLast();
        deletedItems.addLast(x);
        return x;
    }

    /** Prints deleted items. */
    public void printLostItems() {
        deletedItems.print();
    }
}
```

Notice how we use `super.removeLast()` to call the superclass' method.

### Constructors Are Not Inherited

As we mentioned earlier, subclasses inherit all members of the parent class, which includes instance and static variables, methods, and nested classes, but does NOT include constructors.

While constructors are not inherited, **Java requires that all constructors must start with a call to one of its superclass's constructors**.

To gain some intuition on why that it is, recall that the `extends` keywords defines an "is-a" relationship between a subclass and a parent class. If a VengefulSLList "is-a" SLList, then it follows that every VengefulSLList must be set up like an SLList.

Here's a more in-depth explanation. Let's say we have two classes:

```java
public class Human {...}
```

```java
public class TA extends Human {...}
```

It is logical for TA to extend Human, because all TA's are Human. Thus, we want TA's to inherit the attributes and behaviors of Humans.

If we run the code below:

```java
TA yang = new TA();
```

Then first, a Human must be created. Then, that Human can be given the qualities of a TA. It doesn't make sense for a TA to be constructed without first creating a Human first.

Thus, we can either explicitly make a call to the superclass's constructor, using the `super` keyword:

```java
public VengefulSLList() {
    super();
    deletedItems = new SLList<Item>();
}
```

Or, **if we choose not to, Java will automatically make a call to the superclass's no-argument constructor**.

In this case, adding `super()` has no difference from the constructor we wrote before. It just makes explicit what was done implicitly by Java before. However, if we were to define another constructor in VengefulSLList, Java's implicit call may not be what we intend to call.

Suppose we had a one-argument constructor that took in an item. If we had relied on an implicit call to the superclass's no-argument constructor, super(), the item passed in as an argument wouldn't be placed anywhere!

So, we must **make an explicit call to the correct constructor by passing in the item as a parameter to super**.

```java
public VengefulSLList(Item x) {
    super(x);
    deletedItems = new SLList<Item>();
}
```

### The Object Class

**Every class in Java is a descendant of the `Object` class, or extends the `Object` class**. Even classes that do not have an explicit `extends` in their class still implicitly extend the `Object` class.

For example,

- VengefulSLList `extends` SLList explicitly in its class declaration
- SLList `extends` Object implicitly

This means that since SLList inherits all members of Object, VengefulSLList inherits all members of SLList and Object, transitively. So, what is to be inherited from Object?

As seen in the documentation for the `Object` class, the Object class provides operations that every Object should be able to do - like `.equals(Object obj)`, `.hashCode()`, and `toString()`.

> Note: Interfaces do NOT extend the Object class.

![](./note-img/4-2/Object-Class.PNG)
![](./note-img/4-2/is-a-vs-has-a.PNG)

### Encapsulation

Encapsulation is one of the fundamental principles of object oriented programming, and is one of the approaches that we take as programmers to resist our biggest enemy: _complexity_. Managing complexity is one of the major challenges we must face when writing large programs.

Some of the tools we can use to fight complexity include **hierarchical abstraction** (abstraction barriers!) and a concept known as **"Design for change"**. This revolves around the idea that _programs should be built into modular, interchangeable pieces that can be swapped around without breaking the system_. Additionally, **hiding information** that others don't need is another fundamental approach when managing a large system.

- Hierarchical abstraction.
  - Create **layers of abstraction**, with **clear abstraction barriers**.
- "Design for change".
  - Organize program around objects.
  - Let objects decide how things are done.
  - Hide information others don't need.

The root of encapsulation lies in this notion of hiding information from the outside. One way to look at it is to see how encapsulation is analogous to a Human cell. The internals of a cell may be extremely complex, consisting of chromosomes, mitochondria, ribosomes etc., but yet it is fully **encapsulated into a single module - abstracting away the complexity inside**.

![cell_encapsulated](./note-img/4-2/cell_encapsulated.png)

In computer science terms, **a module can be defined as a set of methods that work together as a whole to perform a task or set of related tasks**. This could be something like a class that represents a list. Now, **if the implementation details of a module are kept internally hidden and the only way to interact with it is through a documented interface, then that module is said to be encapsulated**.

Take the `ArrayDeque` class, for example. The outside world is able to utilize and interact with an `ArrayDeque` through its defined methods, like `addLast` and `removeLast`. However, they need not understand the complex details of how the data structure was implemented in order to be able to use it effectively.

> Managing complexity is the most important thing for building large projects.

### Abstraction Barriers

Ideally, a user should not be able to observe the internal workings of, say, a data structure they are using.

Fortunately, Java makes it easy to enforce abstraction barriers. Using the `private` keyword in Java, it becomes virtually impossible to look inside an object - ensuring that the underlying complexity isn't exposed to the outside world.

And in fact, Java is one of the few languages that actually makes `private` and `protected` forced by the compiler, other languages like Python merely uses a convention.

![caution](./note-img/4-2/caution.PNG)

The above problems are bad examples of using encapsulation, since they're thinking at a low-level abstraction.

![abstraction-barriers](./note-img/4-2/abstraction-barriers.PNG)

### My Questions

Q: How does the JVM search for a method for a class with superclasses? Does it start from the very root or from the class itself?
Ans: For an instance method, it always starts searching in the runtime class of the object calling the method. When Java sees that the method has been overridden in the runtime class, it executes that implementation. Otherwise, it walks up the class hierarchy to find an inherited implementation.

### How Inheritance Breaks Encapsulation

Suppose we had the following two methods in a Dog class. We could have implemented `bark` and `barkMany` like so:

```java
public void bark() {
    System.out.println("bark");
}

public void barkMany(int N) {
    for (int i = 0; i < N; i += 1) {
        bark();
    }
}
```

Or, alternatively, we could have implemented it like so:

```java
public void bark() {
    barkMany(1);
}

public void barkMany(int N) {
    for (int i = 0; i < N; i += 1) {
        System.out.println("bark");
    }
}
```

From a user's perspective, the functionality of either of these implementations is exactly the same. However, observe the effect if we were to define a a subclass of `Dog` called `VerboseDog`, and override its `barkMany` method as such:

```java
@Override
public void barkMany(int N) {
    System.out.println("As a dog, I say: ");
    for (int i = 0; i < N; i += 1) {
        bark();
    }
}
```

**Exercise 4.2.3.** Given a VerboseDog `vd`, what would `vd.barkMany(3)` output, given the first implementation above? The second implementation?

- a: As a dog, I say: bark bark bark
- b: bark bark bark
- c: Something else

As you have seen, using the first implementation, the output is A, while using the second implementation, the program gets caught in an infinite loop. The call to `bark()` will call `barkMany(1)`, which makes a call to `bark()`, repeating the process infinitely many times.

#### Note

The methods are actually stored only in the class defining those methods. When instances calls the method, JVM just lookup into its class for that definition.

The instance methods of an object are stored in its class object (only one copy should exist), they are not "copied" with each new instance, instead, under the hood each instance holds a reference to the method implementation residing in the class object.

See: [stackoverflow: how-are-instance-methods-stored](https://stackoverflow.com/questions/8376953/how-are-instance-methods-stored)

#### Walkthrough of exercise

![walk](./note-img/4-2/walkthrough.PNG)

1. When we call `vd.barkMany(3)`, the dynamic type of `vd` is `VerboseDog`, so JVM will search for the method in `VerboseDog` class and it does find it there.

2. Calling the `barkMany` method, it calls `bark()`, which is equivalently `this.bark()`, where `this` is of dynamic type `VerboseDog`.

3. Calling `this.bark()`, JVM searches for the method in its dynamic type class, `VerboseDog`, but didn't fint it. So, it looks up to its superclasses and finds an implementation in `Dog` and calls it (still calling by `this.bark()`, its just the definition of `bark()` is found at `Dog` class, but the object being called on is still the same)

4. `bark()` then calls `this.barkMany(1)`, where `this` is still just of dynamic type `VerboseDog`.

5. And we get caught in an infinite loop...

### Type Checking and Casting

Before we go into types and casting, let's review dynamic method selection. Recall that dynamic method lookup is the process of determining the method that is executed at runtime based on the dynamic type of the object. Specifically, if a method in SLList is overridden by the VengefulSLList class, then the method that is called at runtime is determined by the run-time type, or dynamic type, of that variable.

**Exercise 4.2.4.** For each line of code below, decide the following:

- Does that line cause a compilation error?
- Which method uses dynamic selection?

![dyna selec exerc](./note-img/4-2/dynamic_selection_exercise.png)

Let's go through this program line by line.

```java
VengefulSLList<Integer> vsl = new VengefulSLList<Integer>(9);
SLList<Integer> sl = vsl;
```

These two lines above compile just fine. Since VengefulSLList "is-an" SLList, it's valid to put an instance of the VengefulSLList class inside an SLList "container".

```java
sl.addLast(50);
sl.removeLast();
```

These lines above also compile. The call to addLast is unambiguous, as VengefulSLList did not override or implement it, so the method executed is in SLList. The removeLast method is overridden by VengefulSLList, however, so we take a look at the dynamic type of sl. Its dynamic type is VengefulSLList, and so dynamic method selection chooses the overridden method in the VengefulSLList class.

```java
sl.printLostItems(); // compile-time error
```

This line above results in a compile-time error. Remember that the **compiler determines whether or not something is valid based on the static type of the object**. Since sl is of static type SLList, and _printLostItems is not defined in the SLList class_, the code will not be allowed to run, even though sl's runtime type is VengefulSLList.

```java
VengefulSLList<Integer> vsl2 = sl; // compile-time error
```

This line above also results in a compile-time error, for a similar reason. In general, the compiler only allows method calls and assignments based on compile-time types. _Since the compiler only sees that the static type of sl is SLList, it will not allow a VengefulSLList "container" to hold it_.

#### Expressions

Like variables as seen above, **expressions using the new keyword also have compile-time types**.

```java
SLList<Integer> sl = new VengefulSLList<Integer>();
```

Above, the compile-time type of the right-hand side of the expression is VengefulSLList. The compiler checks to make sure that VengefulSLList "is-a" SLList, and allows this assignment,

```java
VengefulSLList<Integer> vsl = new SLList<Integer>();
```

Above, the compile-time type of the right-hand side of the expression is SLList. **The compiler checks if SLList "is-a" VengefulSLList, which it is not in all cases, and thus a compilation error results**.

Further, method calls have compile-time types equal to their declared type. Suppose we have this method:

```java
public static Dog maxDog(Dog d1, Dog d2) { ... }
```

Since the **return type of maxDog is Dog, any call to maxDog will have compile-time type Dog**.

```java
Poodle frank = new Poodle("Frank", 5);
Poodle frankJr = new Poodle("Frank Jr.", 15);
```

```java
Dog largerDog = maxDog(frank, frankJr);
Poodle largerPoodle = maxDog(frank, frankJr); //does not compile! RHS has compile-time type Dog
```

Assigning a Dog object to a Poodle variable, like in the SLList case, results in a compilation error. A Poodle "is-a" Dog, but a more general Dog object may not always be a Poodle, even if it clearly is to you and me (we know that frank and frankJr are both Poodles!).

Is there any way around this, when we know for certain that assignment would work?

#### Casting

Java has a special syntax where you can tell the compiler that a specific expression has a specific compile-time type. This is called "casting". **With casting, we can tell the compiler to view an expression as a different compile-time type**.

Looking back at the code that failed above, since we know that `frank` and `frankJr` are both Poodles, we can cast:

```java
Poodle largerPoodle = (Poodle) maxDog(frank, frankJr);
// compiles! RHS has compile-time type Poodle after casting
```

> Caution: Casting is a powerful but dangerous tool. Essentially, casting is telling the compiler not to do its type-checking duties - telling it to trust you and act the way you want it to.

Here's a possible issue that could arise:

```java
Poodle frank = new Poodle("Frank", 5);
Malamute frankSr = new Malamute("Frank Sr.", 100);

Poodle largerPoodle = (Poodle) maxDog(frank, frankSr); // runtime exception!
```

In this case, we compare a Poodle and a Malamute. Without casting, the compiler would normally not allow the call to maxDog to compile, as the right hand side compile-time type would be Dog, not Poodle. However, casting allows this code to pass, and **when maxDog returns the Malamute at runtime, and we try casting a Malamute as a Poodle, we run into a runtime exception** - a `ClassCastException`.

### Higher Order Functions

Taking a little bit of a detour, we are going to introduce higher order functions. **A higher order function is a function that treats other functions as data**. For example, take this Python program `do_twice` that takes in another function as input, and applies it to the input x twice.

```py
def tenX(x):
    return 10*x

def do_twice(f, x):
    return f(f(x))
```

A call to `print(do_twice(tenX, 2))` would apply `tenX` to `2`, and apply `tenX` again to its result, `20`, resulting in `200`. How would we do something like this in Java?

**In old school Java (Java 7 and earlier), memory boxes (variables) could not contain pointers to functions.** What that means is that **we could not write a function that has a "Function" type, as there was simply no type for functions**.

To get around this we can take advantage of interface inheritance. Let's **write an interface that defines any function that takes in an integer and returns an integer - an `IntUnaryFunction`**.

```java
public interface IntUnaryFunction {
    int apply(int x);
}
```

Now we can write a class which `implements` `IntUnaryFunction` to represent a concrete function. Let's make a function that takes in an integer and returns 10 times that integer.

```java
public class TenX implements IntUnaryFunction {
    /* Returns ten times the argument. */
    public int apply(int x) {
        return 10 * x;
    }
}
```

At this point, we've written in Java the Python equivalent of the tenX function. Let's write do_twice now.

```java
public static int do_twice(IntUnaryFunction f, int x) {
    return f.apply(f.apply(x));
}
```

A call to print(do_twice(tenX, 2)) in Java would look like this:

```java
System.out.println(do_twice(new TenX(), 2));
```

Why do we want an interface `IntUnaryFunction`?

Because we might have other methods like `FiveX()`, `TwentyX()`, etc., and when we want to call `do_twice()` with those methods, we can simply refer to them all as of `IntUnaryFunction` class.

![Hof old](./note-img/4-2/HoF-Java7.PNG)
![Hof new](./note-img/4-2/HoF-Java8.PNG)

### Inheritance Cheatsheet

`VengefulSLList extends SLList` means VengefulSLList "is-an" SLList, and inherits all of SLList's members:

- Variables, methods, and nested classes
- Not constructors Subclass constructors must invoke superclass constructor first. The `super` keyword can be used to invoke overridden superclass methods and constructors.

Invocation of overridden methods follows two simple rules:

- Compiler plays it safe and only allows us to do things according to the static type.
- For overridden methods (not overloaded methods), the actual method invoked is based on the dynamic type of the invoking expression
- Can use casting to overrule compiler type checking.

![cheatsheet](./note-img/4-2/cheatsheet.PNG)

---

## 4-3 Subtype Polymorphism vs. HoFs

### Dynamic Method Selection Puzzle

![puzzle-1](./note-img/4-3/puzzle-1.PNG)
![puzzle-2](./note-img/4-3/puzzle-2.PNG)

My ans: 2223

Let's walk through the follwing lines:

```java
Dog dx = ((Dog) o2);
dx.bark();
```

The static type of `o2` is Object, so we use `(Dog)` to cast the static type of the RHS expression into Dog, such that when the compiler checks whether the static type of RHS "is-a" Dog, we can successfully compile.

So when we call `dx.bark()`, since `dx` has static type Dog, so compiler checks whether there's a `bark` method in Dog and if so, register its signature. And at run-time, since `dx` points to `o2` and `o2` has dynamic type `ShowDog`, and also, the method `bark` is overriden in the `ShowDog` class, so the `bark` method in `ShowDog` is called.

```java
((Dog) o2).bark();
```

Again the static type of `o2` is Object, and the static type of `(Dog) o2` is Dog, and there's a method called `bark` in Dog, so compiles fine. At run-time, since the dynamic type of `o2` is ShowDog, so JVM calls the override method `bark` in ShowDog.

```java
Object o3 = (Dog) o2;
o3.bark();
```

Since Object does NOT have `bark` method, this creates a syntax error.

Some more situations:

![hiding](./note-img/4-3/hiding.PNG)

Document about hiding (do note that it's super BAD style): [hiding Doc](https://docs.oracle.com/javase/tutorial/java/IandI/override.html)

### Intro

We've seen how inheritance lets us reuse existing code in a superclass while implementing small modifications by overriding a superclass's methods or writing brand new methods in the subclass. Inheritance also makes it possible to design general data structures and methods using polymorphism.

**Polymorphism**, at its core, means 'many forms'. In Java, polymorphism refers to **how objects can have many forms or types**. In object-oriented programming (OOP), polymorphism relates to **how an object can be regarded as an instance of its own class, an instance of its superclass, an instance of its superclass's superclass, and so on**.

Consider a variable `deque` of static type Deque. A call to `deque.addFirst()` will be determined at the time of execution, depending on the run-time type, or dynamic type, of `deque` when `addFirst` is called. As we saw in the last chapter, Java picks which method to call using dynamic method selection.

Suppose we want to write a python program that prints a string representation of the larger of two objects. There are two approaches to this.

1. Explicit HoF Approach

```py
def print_larger(x, y, compare, stringify):
    if compare(x, y):
        return stringify(x)
    return stringify(y)
```

2. Subtype Polymorphism Approach

```py
def print_larger(x, y):
    if x.largerThan(y):
        return x.str()
    return y.str()
```

Using the explicit HoF approach, you have a common way to print out the larger of two objects.

In contrast, in the subtype polymorphism approach, the object _itself_ makes the choices. The `largerThan` that is called is dependent on what x and y actually are.

### Max Function

Say we want to write a `max` function which takes in any array - regardless of type - and returns the maximum item in the array.

**Exercise 4.3.1.** Your task is to determine how many compilation errors there are in the code below.

> Note: When we pass an argument to a function, the compiler also does type checking, it checks whether the static type of the argument "is-a" parameter's type.

```java
public static Object max(Object[] items) {
    int maxDex = 0;
    for (int i = 0; i < items.length; i += 1) {
        if (items[i] > items[maxDex]) {
            maxDex = i;
        }
    }
    return items[maxDex];
}

public static void main(String[] args) {
    Dog[] dogs = {new Dog("Elyse", 3), new Dog("Sture", 9), new Dog("Benjamin", 15)};
    Dog maxDog = (Dog) max(dogs);
    maxDog.bark();
}
```

There's 1 error:

```java
if (items[i] > items[maxDex]) { /*...*/}
```

This is because the `>` operator does NOT work with Object types.

Instead, one thing we could is define a maxDog function in the Dog class, and give up on writing a "one true max function" that could take in an array of any arbitrary type. We might define something like this:

```java
public static Dog maxDog(Dog[] dogs) {
    if (dogs == null || dogs.length == 0) {
        return null;
    }
    Dog maxDog = dogs[0];
    for (Dog d : dogs) {
        if (d.size > maxDog.size) {
            maxDog = d;
        }
    }
    return maxDog;
}
```

While this would work for now, if we give up on our dream of making a generalized `max` function and let the Dog class define its own `max` function, then **we'd have to do the same for any class we define later** :(. We'd need to write a `maxCat` function, a `maxPenguin` function, a `maxWhale` function, etc., resulting in unnecessary repeated work and a lot of redundant code.

The fundamental issue that gives rise to this is that Objects cannot be compared with `>`. This makes sense, as how could Java know whether it should use the String representation of the object, or the size, or another metric, to make the comparison? In Python or C++, the way that the `>` operator works could be redefined to work in different ways when applied to different types. Unfortunately, Java does not have this capability. Instead, **we turn to interface inheritance to help us out**.

We can **create an interface that guarantees that any implementing class, like Dog, contains a comparison method**, which we'll call `compareTo`.

![compareTo()](./note-img/4-3/dog_comparable.png)

Let's write our interface. We'll specify one method `compareTo`.

```java
public interface OurComparable {
    int compareTo(Object o);
}
```

We will define its behavior like so:

- Return -1 if this < o.
- Return 0 if this equals o.
- Return 1 if this > o.

Now that we've created the `OurComparable` interface, we can require that our Dog class implements the `compareTo` method. First, we change Dog's class header to include `implements OurComparable`, and then we write the `compareTo` method according to its defined behavior above.

**Exercise 4.3.2.** Implement the compareTo method for the Dog class.

```java
public class Dog implements OurComparable {
    private String name;
    private int size;

    public Dog(String n, int s) {
        name = n;
        size = s;
    }

    public void bark() {
        System.out.println(name + " says: bark");
    }

    public int compareTo(Object o) {
        Dog uddaDog = (Dog) o;
        if (this.size < uddaDog.size) {
            return -1;
        } else if (this.size == uddaDog.size) {
            return 0;
        }
        return 1;
    }
}
```

> Notice that since `compareTo` takes in any arbitrary `Object o`, we have to cast the input to a `Dog` to make our comparison using the `size` instance variable.

Now we can generalize the `max` function we defined in exercise 4.3.1 to, instead of taking in any arbitrary array of objects, takes in `OurComparable` objects - which we know for certain all have the `compareTo` method implemented.

```java
public static OurComparable max(OurComparable[] items) {
    int maxDex = 0;
    for (int i = 0; i < items.length; i += 1) {
        int cmp = items[i].compareTo(items[maxDex]);
        if (cmp > 0) {
            maxDex = i;
        }
    }
    return items[maxDex];
}
```

Great! Now our `max` function can take in an array of any `OurComparable` type objects and return the maximum object in the array. Now, this code is admittedly quite long, so we can make it much more succinct by modifying our `compareTo` method's behavior:

- Return negative number if `this` < o.
- Return 0 if `this` equals o.
- Return positive number if `this` > o.

Now, we can just return the difference between the sizes. If my size is 2, and uddaDog's size is 5, `compareTo` would return -3, a negative number indicating that I am smaller.

```java
public int compareTo(Object o) {
    Dog uddaDog = (Dog) o;
    return this.size - uddaDog.size;
}
```

Using inheritance, we were able to generalize our maximization function. What are the benefits to this approach?

- No need for maximization code in every class(i.e. no `Dog.maxDog(Dog[] dogs)` function required
- We have code that operates on multiple types (mostly) gracefully

### Interfaces Quiz

**Exercise 4.3.3.** Given the `Dog` class, `DogLauncher` class, `OurComparable` interface, and the `Maximizer` class, if we omit the `compareTo()` method from the `Dog` class, which file will fail to compile?

```java
public class DogLauncher {
    public static void main(String[] args) {
        ...
        Dog[] dogs = new Dog[]{d1, d2, d3};
        System.out.println(Maximizer.max(dogs));
    }
}

public class Dog implements OurComparable {
    ...
    public int compareTo(Object o) {
        Dog uddaDog = (Dog) o;
        if (this.size < uddaDog.size) {
            return -1;
        } else if (this.size == uddaDog.size) {
            return 0;
        }
        return 1;
    }
    ...
}

public class Maximizer {
    public static OurComparable max(OurComparable[] items) {
        ...
        int cmp = items[i].compareTo(items[maxDex]);
        ...
    }
}
```

In this case, the Dog class fails to compile. By declaring that it `implements OurComparable`, the Dog class makes a claim that it "is-an" `OurComparable`. As a result, the `compiler checks whether the Dog class actually implements the methods specified in OurComparable class`.

What if we were to omit implements OurComparable from the Dog class header? This would cause a compile error in DogLauncher due to this line:

```java
Dog d = (Dog) Maximizer.max(dogs);
```

If Dog does not implement the `OurComparable` interface, then trying to pass in an array of Dogs to Maximizer's `max` function wouldn't be approved by the compiler. `max` only accepts an array of OurComparable objects.

#### Sumary

Note that the `OurComparable` class and `Maximizer` class should ALWAYS compile no matter what other specific class extends or implements them and uses them. This is because `OurComparable` and `Maximizer` works at a **higher level of abstraction**, so when we write these codes, we should NOT even be consider what specific class will use these.

#### Our comparable Code

```java
public interface OurComparable {
    /**
     * Return -1 if this < o; 0 if this = o;
     * 1 if this > o.
     * @param o Object to compare to
     */
    int compareTo(Object o);
}
```

```java
public class Dog implements OurComparable {
    public String name;
    public int size;

    public Dog(String n, int s) {
        name = n;
        size = s;
    }

    @Override
    public int compareTo(Object o) {
        Dog cmpDog = (Dog) o;
        return this.size - cmpDog.size;
    }

    public void bark() {
        System.out.println(name + " says: bark");
    }
}
```

```java
public class Maximizer {
    public static OurComparable max(OurComparable[] items) {
        int maxIdx = 0;
        for (int i = 1; i < items.length; i++) {
            if (items[i].compareTo(items[maxIdx]) > 0) {
                maxIdx = i;
            }
        }
        return items[maxIdx];
    }
}
```

```java
public class DogLauncher {
    public static void main(String[] args) {
        Dog d1 = new Dog("Elyse", 3);
        Dog d2 = new Dog("Sture", 9);
        Dog d3 = new Dog("Benjamin", 15);
        Dog[] dogs = new Dog[]{d1, d2, d3};

        Dog d = (Dog) Maximizer.max(dogs);
        System.out.println(d.name);
    }
}
```

### Comparables

The `OurComparable` interface that we just built works, but it's not perfect. Here are some issues with it:

- Awkward casting to/from Objects
- We made it up:
  - No existing classes implement OurComparable (e.g. String, etc.)
  - No existing classes use OurComparable (e.g. no built-in max function that uses OurComparable)

The solution? **We'll take advantage of an interface that already exists called Comparable**. `Comparable` is already defined by Java and is used by countless libraries.

Comparable looks very similar to the OurComparable interface we made, but with one main difference. Can you spot it?

![comparable_interface](./note-img/4-3/comparable_interface.png)

Notice that `Comparable<T>` means that it takes a **generic type**. This will help us avoid having to cast an object to a specific type!

Now, we will rewrite the `Dog` class to implement the `Comparable` interface, being sure to update the generic type `T` to `Dog`:

```java
public class Dog implements Comparable<Dog> {
    /*...*/

    public int compareTo(Dog uddaDog) {
        return this.size - uddaDog.size;
    }
}
```

Now all that's left is to change each instance of `OurComparable` in the `Maximizer` class to `Comparable`:

```java
public class Maximizer {
    public static Comparable<Dog> max(Comparable<Dog>[] items) {
        int maxIdx = 0;
        for (int i = 1; i < items.length; i++) {
            if (items[i].compareTo((Dog) items[maxIdx]) > 0) {
                maxIdx = i;
            }
        }
        return items[maxIdx];
    }
}
```

Instead of using our personally created interface `OurComparable`, we now use the real, built-in interface, `Comparable`. As a result, **we can take advantage of all the libraries that already exist and use Comparable**.

![Comparable](./note-img/4-3/comparable.png)

Here are some advantages of using a built-in interface like `comparable`:

![advantages of comparable](./note-img/4-3/Comparable_Advantages.PNG)

### Comparator

We've just learned about the `comparable` interface, which imbeds into each `Dog` the ability to compare itself to another Dog. Now, we will introduce a new interface that looks very similar called `Comparator`.

Let's start off by defining some terminology.

- **Natural order** - used to refer to the ordering implied in the `compareTo` method of a particular class.

As an example, the natural ordering of Dogs, as we stated previously, is defined according to the value of size.

What if we'd like to sort Dogs in a different way than their natural ordering, such as by alphabetical order of their name? How can we tell the `max` function that we'd like to find the maximum in a different way?

#### Hof way

Let's first consider this problem at a more abstract way.

If we were to use higher order functions, we could simply pass a different `compare` callback that compares the objects in a different way. For example in python we could write:

```py
def print_larger(x, y, compare, stringfy):
    if compare (x, y):
        return stringfy(x)
    return stringfy(y)
```

Notice how the above code already supports multiple orderings since we could simply pass in different `compare` function.

Now, consider the following subtype polymorphism approach. How can we augment the code to support multiple orderings?

```py
# this is not really valid python, it's just pseudocode
def print_larger(T x, T y, comparator<T>, c):
    if c.compare(x, y):
        return x.str()
    return y.str()
```

There are a few BAD solutions to this problem. For example: write different functions for different orderings and just document which function is for what ordering, or we could pass an extra string to specify which option corresponds to which ordering, etc.

But the most clean way that Java came up with is to pass an extra `comparator` object `c` and it will basically find out how we should compare the objects correspoding to type `T`:

```py
# this is not really valid python, it's just pseudocode
def print_larger(T x, T y, comparator<T>, c):
    if c.compare(x, y):
        return x.str()
    return y.str()
```

#### Java's way

Java's way of doing this is by using `Comparator`'s. Since a comparator is an object, the way we'll use Comparator is by writing a nested class inside Dog that implements the Comparator interface.

But first, what's inside this interface?

```java
public interface Comparator<T> {
    int compare(T o1, T o2);
}
```

This shows that the `Comparator` interface requires that any implementing class implements the `compare` method. The rule for `compare` is just like `compareTo`:

- Return negative number if o1 < o2.
- Return 0 if o1 equals o2.
- Return positive number if o1 > o2.

Let's give Dog a NameComparator. To do this, we can simply defer to String's already defined `compareTo` method.

> Note: to use `comparator` we need to `import` it, which we don't have to do for `comparable`s. Why? It's just because somehow Java designers think that `comparable`s are more important and should be built-in, w/o having to be imported.

Note that we've declared `NameComparator` to be a `static` class. A minor difference, but we do so because we do not need to instantiate a Dog to get a NameComparator. Let's see how this Comparator works in action.

```java
public class Dog implements Comparable<Dog> {
    /* ... */

    public static class NameComparator implements Comparator<Dog> {
        @Override
        public int compare(Dog a, Dog b) {
            return a.name.compareTo(b.name);
        }
    }
}

/* DIFFERENT FILE */

public class DogLauncher {
    public static void main(String[] args) {
        Dog d1 = new Dog("Elyse", 3);
        Dog d2 = new Dog("Sture", 9);

        // We could now use the NameComparator object to compare based on name.
        Dog.NameComparator nameCmp = new Dog.NameComparator();
        if (nameCmp.compare(d1, d3) > 0) {
            d1.bark();
        }
        else {
            d3.bark();
        }
    }
}
```

However, in Java we always try to encapsulate our inner variables and even classes, since we don't want any outer source to be messing around these important data that defines our class. Also, since this inner class is not really a huge class, and is simply a helper class, so we can make it `private`, and use a `getNameComparator` method.

> Note: Does this make any difference? Not really, it just matches how most Java libraries are implemented, and has been a convention in Java.

```java
import java.util.Comparator;

public class Dog implements Comparable<Dog> {
    /* ... */

    public int compareTo(Dog uddaDog) {
        return this.size - uddaDog.size;
    }

    private static class NameComparator implements Comparator<Dog> {
        public int compare(Dog a, Dog b) {
            return a.name.compareTo(b.name);
        }
    }

    public static Comparator<Dog> getNameComparator() {
        return new NameComparator();
    }
}
```

Note that the core idea of this Java way is actually still just passing a differnt function, but since Java can't pass functions, so we instead pass a Comparator object.

![](./note-img/4-3/comparator-1.PNG)
![](./note-img/4-3/comparator-2.PNG)

#### Summary

Note: The core idea to compare based on different orderings is still by passing different functions (or callbacks) to the method that uses these comparators.

But since in Java we can't pass a function (there's just no such thing like a function in other languages, all Java has is methods.), so instead we pass an object, which in turn holds that method we'd like to pass.

So in the code that uses our callback, in other languages like python, we could simply call the function by writing `compare(x, y)`. This is a more "Functional" style.

But Java is NOT a functional language, it's more an OOP language, so we'd wrap up the needed function as a method in an interface, so that we can simply use these comparators in our code and assume that someone else or we will implement these compare methods (e.g. Arrays.sort). do `cmp.compare(x, y)`, where `cmp` is the `Comparator` type parameter.

![](./note-img/4-3/comparator-4.PNG)

To summarize, `interface`s in Java provide us with the ability to make **callbacks**. Sometimes, a function needs the help of another function that might not have been written yet (e.g. `max` needs `compareTo`). A callback function is the helping function (in the scenario, `compareTo`). In some languages, this is accomplished using explicit function passing; **in Java, we wrap the needed function in an `interface`.**

A `Comparable` says, "I want to compare myself to another object". It is imbedded within the object itself, and it defines the natural ordering of a type. A `Comparator`, on the other hand, is more like a third party machine that compares two objects to each other. Since there's only room for one `compareTo` method, if we want multiple ways to compare, we must turn to `Comparator`.

---

## 4-4 Libraries, Abstract Classes, Packages

### Abstract Data Types (ADTs)

We've actually already built 2 ADTs: List61B and Deque. Let's consider Deque now.

![deque](./note-img/4-4/deque.png)
![ADT so far](./note-img/4-4/ADT-sofar.PNG)

We have this interface `deque` that both `ArrayDeque` and `LinkedListDeque` implement. What is the relationship between Deque and its implementing classes? Well, `deque` simply provides a list of methods (behaviors):

```java
public void addFirst(T item);
public void addLast(T item);
public boolean isEmpty();
public int size();
public void printDeque();
public T removeFirst();
public T removeLast();
public T get(int index);
```

These methods are **implemented** by `ArrayDeque` and `LinkedListDeque`.

In Java, Deque is called an `interface`. Conceptually, we call deque an **Abstract data type**. An ADT (like Deque) only comes with behaviors, not any concrete ways to exhibit those behaviors. In this way, it is abstract.

Side Story:

![side story](./note-img/4-4/interface-inheritance.PNG)

> Note: Recall our implementation of `OffByN` class, where we get to pick the value of N we want, which makes it as if we're generating functions.

Some exercises about ADTs.

1. Stack:
   ![stack](./note-img/4-4/stack.PNG)
   Ans: Linked List is a little faster (since never have to resize)

2. A made up example:
   ![grabbag](./note-img/4-4/gragbag.PNG)
   Ans: Array

### Java Libraries

Java has certain built-in Abstract data types that you can use. These are packaged in Java Libraries.

The three most important ADTs come in the java.util library:

- [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html): an ordered collection of items
  - A popular implementation is the [ArrayList](https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html)
- [Set](https://docs.oracle.com/javase/7/docs/api/java/util/Set.html): an unordered collection of strictly unique items (no repeats)
  - A popular implementation is the [HashSet](https://docs.oracle.com/javase/7/docs/api/java/util/HashSet.html)
- [Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html): a collection of key/value pairs. You access the value via the key.
  - A popular implementation is the [HashMap](https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html)

Some lecture slides:

![Collections](./note-img/4-4/Collections.PNG)
![Lists, Sets, and Maps](./note-img/4-4/Java-libraries.PNG)

Finish the exercises below by using the above three ADT's. Reading the documentations linked above will help immensely.

**Exercise 4.4.1** Write a method `getWords` that takes in a `String inputFileName` and puts every word from the input file into a list. Recall how we read words from a file in proj0. (\*Hint: use `In`)

**Exercise 4.4.2** Write a method `countUniqueWords` that takes in a `List<String>` and counts how many unique words there are in the file.

**Exercise 4.4.3** Write a method `collectWordCount` that takes in a `List<String> targets` and a `List<String> words` and finds the number of times each target word appears in the word list.

```java
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

public class DemoCollections {

    /**
     * Returns a lower case version of the string with
     * all characters except letters removed.
     */
    public static String cleanString(String s) {
        return s.toLowerCase().replaceAll("[^a-z]", "");
    }

    /**
     * Gets a list of all words in the file.
     */
    public static List<String> getWords(String inputFilename) {
        List<String> words = new ArrayList<>();
        In in = new In(inputFilename);
        while (!in.isEmpty()) {
            String nextWord = cleanString(in.readString());
            words.add(nextWord);
        }
        return words;
    }

    /**
     * Returns the count of the number of unique words in words.
     */
    public static int countUniqueWords(List<String> words) {
        /* Prof. Josh's solution */
//        Set<String> wordSet = new HashSet<>();
//        for (String word : words) {
//            wordSet.add(word);
//        }

        Set<String> wordSet = new HashSet<>(words);
        return wordSet.size();
    }

    /**
     * Returns a map (a.k.a. dictionary) that tracks the count of all specified
     * target words in words.
     */
    public static Map<String, Integer> collectWordCount(List<String> words, List<String> targets) {
        Map<String, Integer> counts = new HashMap<>();

        /* Initialize counts of targets in the map to 0 */
        for (String t : targets){
            counts.put(t, 0);
        }

        for (String s : words) {
            if (counts.containsKey(s)) {
                counts.put(s, counts.get(s) + 1);
            }
        }
        return counts;
    }

    public static void main(String[] args) {
        List<String> w = getWords("lotteryOfBabylon.txt");
        System.out.println(countUniqueWords(w));

		List<String> targets = new ArrayList<>();
		targets.add("lottery");
		targets.add("the");
		targets.add("babylon");

		System.out.println(collectWordCount(w, targets));
    }
}
```

We used a map because it makes an association between two things. In our case, we need an association between word and number.

These three ADT's all extend from the Collection Interface. The collection interface is super vague. Java says collections "represent a group of objects, known as its elements".

![collection_hierarchy](./note-img/4-4/collection_hierarchy.png)

In the diagram above, the white boxes are interfaces. The blue boxes are concrete classes.

### Java vs. Python

Java is pretty verbose. The java code below looks a lot more cumbersome than the corresponding python code.

![java code](./note-img/4-4/java.png)
![python code](./note-img/4-4/python.png)

![first-class-citizens](./note-img/4-4/first-class-citizens.PNG)
![ADT-implementation](./note-img/4-4/ADT-implementation.PNG)

But, Java has its upsides too! It gives you a lot of choices and freedom. For example, python only has one dictionary type which is declared using curly brackets {}. **With Java, if you want to use an ADT, you can choose what kind of implementation you want**. For example, if you want to use a map, you can choose: a Hashmap? a Treemap? etc.

- Arguably, takes **less time to write programs**, due to features like:

  - Static types (provides type checking and helps guide programmer).
  - Bias towards interface inheritance leading to cleaner subtype polymorphism.
  - Access control modifiers make abstraction barriers more solid.

- **More efficient code**, due to features like:

  - Ability to have more control over engineering tradeoffs.
  - Single valued arrays lead to better performance.

- Basic data structures more closely **resemble underlying hardware**:
  - Would be weird to do ArrayDeque in Python, since there is no need for array resizing. However, in hardware (see 61C), variable length arrays don???t exist.

![factory methods](./note-img/4-4/factory.PNG)

### Abstract classes

![](./note-img/4-4/interface-so-far.PNG)
![](./note-img/4-4/closer-look-interface.PNG)
![](./note-img/4-4/interface-even-more.PNG)
![](./note-img/4-4/interface-summary.PNG)

Next, we introduce the abstract class.

![](./note-img/4-4/intro-abstract.PNG)
![](./note-img/4-4/example.PNG)
![](./note-img/4-4/quiz.PNG)

The answer is 2 (`shred()` and `connectToWifi()`).

> Note: An abstract class does NOT need to implement interface methods. And the unimplemented abstract methods will just be there, waiting for a sub-class to override all of them.

> Note: A `private` varibale in an `abstract` class works the same as in normal classes, i.e., it can only be accessed within the abstract class itself (for example, we can use it in other concrete methods in the abstract class). However, its subclasses that extends it does NOT inherit the private variable.

![](./note-img/4-4/usecase-abstract.PNG)
![](./note-img/4-4/usecase-2.PNG)
![](./note-img/4-4/abstract-vs-interface.PNG)
![](./note-img/4-4/abstract-in-java-lib.PNG)
![](./note-img/4-4/abstract-in-java-lib-1.PNG)
![](./note-img/4-4/whole.PNG)

We've seen **interfaces** that can do a lot of cool things! They allow you to take advantage of interface inheritance and implementation inheritance. As a refresher, these are the qualities of interfaces:

- All methods must be `public`.
- All variables must be `public static final`.
- Can NOT be instantiated.
- All methods are by default abstract unless specified to be `default`.
- Can implement more than 1 interface per class.

We will now introduce a new class that lies somewhere in between interfaces and concrete classes: the **abstract class**. Below are the characteristics of abstract classes:

- Methods can be `public` or `private`.
- Can have any types of variables.
- Can NOT be instantiated.
- Methods are by default concrete unless specified to be `abstract`.
- Can only implement 1 abstract class per class.

Basically, abstract classes can do everything interfaces can do and more.

**When in doubt, try to use interfaces** in order to reduce complexity.

#### More about interfaces and abstract classes

1. [Oracle: abstract classes](https://docs.oracle.com/javase/tutorial/java/IandI/abstract.html)
2. [interface default vs. abstract method](https://stackoverflow.com/questions/19998454/when-to-use-java-8-interface-default-method-vs-abstract-method)

#### Weird stuff

1. Which `bark()` is called?

   ```java
    public class Tree() {
        public int bark() {
            // ...
        }
    }

    public interface Dog {
        default int bark() {
            // ...
        }
    }

    public class Mutant extends Tree implements Dog {
        public static void main(String[] args) {
                (new Mutant()).bark();
        }
    }
   ```

   The one in `Tree` is called.

   > If any class in the hierarchy has a method with same signature, then default methods become irrelevant. A default method cannot override a method from java.lang.Object. The reasoning is very simple, it???s because Object is the base class for all the java classes. So even if we have Object class methods defined as default methods in interfaces, it will be useless because Object class method will always be used. That???s why to avoid confusion, we can???t have default methods that are overriding Object class methods.

#### When to use abstract classes and when to use interfaces

[abstract class vs. interfaces](https://www.devfields.com/abstract-classes-and-interfaces/)

There???s a basic rule that works almost everytime: Use abstract classes if you can make the statement ???A is-a B???. Use interfaces if you can make the statement ???A is-capable-of doing as???, or also, abstract for what a class is, interface for what a class can do.

for example, we can say a triangle is a polygon but it makes no sense to say a triangle is capable of being a polygon.

### Packages

![zen of python](./note-img/4-4/zenofpy.PNG)
![](./note-img/4-4/canonicalization.PNG)
![](./note-img/4-4/packages.PNG)
![](./note-img/4-4/importing.PNG)

> Why is wildcard import bad?
>
> Answer: It can contaminate our namespaces.

![](./note-img/4-4/import-static.PNG)
![](./note-img/4-4/summary.PNG)

Note that `import` in Java is very different from other languages like Python or JavaScript, where in those languages import always means to import a whole file.

But in Java, we do NOT import a whole file, but rather when we say `import ug.joshh.animal.Dog`, it's essentially tells the compiler to replace all `Dog` to `ug.joshh.animal.Dog` in our code.

#### Summary

A namespace is a region that can be used to organize code. Packages are a specific type of namespace that is used to organize classes and interfaces. To use a class from a different package use the following syntax:

```java
package_name.classname.subclassname a = new package_name.classname.subclassname();
```

To make your life easier while typing out code, you can simply import the class following the syntax below:

```java
import package_name.classname.subclassname;
```

Replace the subclassname with a `*` if you want to important everything from the class.
