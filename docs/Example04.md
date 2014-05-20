# The Irreversibility of Overrides and Uncovering Hidden Fields

Once a method has been overridden in a subclass, that method's behavior is no longer directly available to the user of the subclass. See Item 3 in [Example 1](http://www.0xc0deshop.com/2014/05/overriding-instance-methods-in-java.html) for an example. Sure, the subclass can use `super` to invoke the overridden method, but that mechanism isn't available through a reference to the subclass. Overriding is irreversible. 

Hiding is a different story. Java allows you to hide fields, nested classes, and static methods. Hiding static methods was discouraged in [Example 2, Hiding Static Methods in Java](http://www.0xc0deshop.com/2014/05/hiding-static-methods-in-java.html), so let's use instance fields here.

The program below contains a simple hierarchy of three classes. Each contains an instance field `s`. 

    package kenan.java.playground.example04;
    
    public class Example04 {
      public static void main(String[] args) {
        C c = new C();
    
        System.out.println("Example 4");
        System.out.println(" 1: " + c.s);
        System.out.println(" 2: " + ((B)c).s);
        System.out.println(" 3: " + ((A)c).s);
      }
    }
    
    class A {
      public String s = "A1";
    }
    class B extends A {
      public String s = "B1";
    }
    class C extends B {
      public String s = "C1";
    }

The output is:

    Example 4
     1: C1
     2: B1
     3: A1

Output 1 clearly shows that the `s` in `C` hides the `s` in its superclasses. Outputs 2 and 3 show that to access the hidden fields in the superclasses, you simply cast the reference to the appropriate type. 

This is a key difference between hiding and overriding. Overriding is forever. Hidden items can be uncovered. All it takes is a cast. 