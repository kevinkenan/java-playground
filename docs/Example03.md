# The Irrelevancy of References in Static Method Invocation

In [Example02](http://www.0xc0deshop.com/2014/05/hiding-static-methods-in-java.html) I claimed that the compiler ignores instance references when invoking a static method. Example03 (also on [GitHub](https://github.com/kevinkenan/java-playground/blob/master/src/main/java/Example03.java)) demonstrates the extreme depths of the compiler's apathy.

    package kenan.java.playground.example03;
    
    public class Example03 {
      public static void main(String[] args) {
      	System.out.println("Example 3");
        A a = null;
        System.out.println(" 1: " + a.go());
      }
    }
    
    class A {
      public static String go() {
        return "A1";
      }
    }

Will this code even run? The reference `a` is null and we invoke a method on it: `a.go()`. Won't it throw a `NullPointerException`?

It does run, no exceptions are thrown, and the output is:

    Example 3
     1: A1

This behavior is described in section 15.12.4 of [JLS8](http://docs.oracle.com/javase/specs/jls/se8/jls8.pdf), 
and it cements the idea that you should always think of static methods as belonging to the class even when they are invoked through a reference. When you're reading code that invokes static methods on reference, follow the complier's lead: ignore the reference. Pay attention only to the reference's type.

Also, as mentioned in [Example02](http://www.0xc0deshop.com/2014/05/hiding-static-methods-in-java.html), where possible, invoke static methods through the class itself. That is, do this: `A.go()`; not this: `a.go()`. This practice will reduce confusion and produce programs that are more easily understood.
