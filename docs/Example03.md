# The Irrelevancy of References in Static Method Invocation

In Example02 I claimed that the compiler ignores instance references when invoking a static method. Example03 demonstrates the extreme depths of the compiler's apathy.

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

Will this code even compile? The reference `a` is null and we invoke a method on it: `a.go()`. 

It does compile, and the output is:

    Example 3
     1: A1

This behavior is described in section 15.12.4 of the [JLS8](http://docs.oracle.com/javase/specs/jls/se8/jls8.pdf), 
and it should cement the idea that you should always think of static methods as belonging to the class. Feel free to follow the complier's lead and ignore the reference itself and, where possible, invoke static methods through the class itself. This practice will reduce confusion and make programs that are more easily understood.
