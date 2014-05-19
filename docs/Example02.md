# Hiding Static Methods in Java

In Java, you can't override static methods. However, a parent class and its subclasses can have static methods with identical signatures. In that case, the static method in the subclass is said to *hide* the identical method in the parent class. Overriding and hiding are both examples of what *Java Puzzlers* refers to as *name reuse.* 

Static methods are also called class methods because they don't depend on an actual instance of the class. You invoke a static method directly on the class: `A.myStaticMethod()`. You don't need an instance of A to access `myStaticMethod().`

However, Java also allows static methods to be invoked through an instance. While this is discouraged by the language specification (see [JLS8 8.5](http://docs.oracle.com/javase/specs/jls/se8/jls8.pdf) on page 70), it is still common, and it causes confusion because static methods invoked through an instance look like instance methods--which are invoked dynamically.

The code in Example02 (on [GitHub](https://github.com/kevinkenan/java-playground/blob/master/src/main/java/Example01.java))
is identical to the code in Example01 except the `go()` methods are now static. In this example I am invoking the static methods through instances, contrary to the recommendations in the language spec. This is to better illustrate the difference in behavior when compared to Example01. 

    package kenan.java.playground.example02;
    
    public class Example02 {
      public static void main(String[] args) {
        A a = new A();
        B b = new B();
        A x = b;
    
        System.out.println("Example 2");
        System.out.println(" 1: " + a.go());
        System.out.println(" 2: " + b.go());
        System.out.println(" 3: " + x.go());
      }
    }
    
    class A {
      public static String go() {
        return "A1";
      }
    }
    
    class B extends A {
      public static String go() {
        return "B1";
      }
    }

The output of this program, is slightly different:

    Example 2
     1: A1
     2: B1
     3: A1

**Item 1**

The output of A1 is not too surprising since we invoked the static method on an instance of an A class.

**Item 2**

This result is also not very surprising. You can see that invoking the static `go()` on an instance of a B class executes the method defined in the B class. This is called method hiding; the implementation of `go()` in B hides the implementation in A.

**Item 3**

Here we see the difference between instance methods and static methods. In Example01, the output of item 3 was B1. When `go()` is static, the output is A1. This difference is because static methods invoked through a reference are resolved based on the declared type of the reference. Since `x` is declared as a reference to an A, the implementation of `go()` in A is used. 

If you refrain from invoking static methods on instances, this complexity is reduced. Invocations such as `A.go()` or `B.go()` are fairly unambiguous. In fact, the Java compiler pretty much ignores instance references when invoking static methods, and only pays attention to the declared type of the reference. Example03 shows this explicitly. If the compiler can resolve references to their types, then you should be able to do it in most cases when writing the code.

Check out Puzzle 48 in *Java Puzzlers* for more information on this behavior. In particular, the authors take it one step further than I do. Their advice: "Do not hide static methods." 
