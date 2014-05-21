# Overriding Overloaded Methods

This example explores what happens when you override overloaded methods. The interesting behavior in the program below ([GitHub][github]) comes into play when the example introduces differences between declared and actual types. 


    package kenan.java.playground.example05;
    
    public class Example05 {
      public static void main(String[] args) {
        A a = new A();
        B b = new B();
        A x = b;
    
        System.out.println("Example 5");
        System.out.println(" 1: " + a.go(a));
        System.out.println(" 2: " + x.go(b));
        System.out.println(" 3: " + x.go(x));
      }
    }
    
    class A {
      public String go(A a) {
        return "A1";
      }
      public String go(B b) {
        return "A2";
      }
    }
    class B extends A {
      public String go(A a) {
        return "B1";
      }
      public String go(B b) {
        return "B2";
      }
    }

The output is:

    Example 5
     1: A1
     2: B2
     3: B1

**Item 1**

Shows that Java will select the overloaded method that exactly matches the parameter list. Not very surprising.

**Item 2**

As discussed in [Example 1][ex1], the actual type of a reference is used when methods are overridden. Since `x` is a reference to a `B` object, only the `B` implementations are available in selecting which overloaded method to invoke. Of those, Java again selects the overloaded method that exactly matches the parameter. If you understand how overriding works this should not be very surprising.

**Item 3**

This one may be a little tricky as the dual use of `x` is misleading. The method invocation `x.go(...)` uses the same logic as in Item 2 above: since `x` is a reference to a `B`, the methods in `B` are the available candidates. At this point it is easy to just continue on with the idea that `x` is a `B` which could lead to the mistaken conclusion that the output should be B2. 

The key to understanding the behavior that produces B1, is to understand that Java uses the declared types of the parameters to determine the signature of the method to invoke. Then it uses the actual type of the reference to invoke the method that best matches the signature. The details of this entire process are many and complex, but Section 8.4.9 of [JLS8][jls8] gives an overview and points to the labyrinth of rules that govern overloading and method invocation.

Applying that knowledge to the example above, you can see that since the declared type of parameter `x` is `A`, the method that best matches the signature is one that accepts `A` as its only parameter. There are two of these methods: one in `A` and one in the subclass `B`. Since these methods are overridden, the actual type of the invoking reference is used (and will be determined dynamically at runtime). In this case, the actual type of reference `x` is `B`. So the conclusion is that the `go(A a)` method in class `B` is the method that will be invoked. 

In most cases, the declared type is the relevant type in Java. When overriding comes into play, though, the actual type is used to determine invocation dynamically. So in the expression `x.go(x)`, the first `x` is treated like a `B` (because of overriding) and the second is treated as usual like an `A`.

[ex1]: http://www.0xc0deshop.com/2014/05/overriding-instance-methods-in-java.html
[jls8]: http://docs.oracle.com/javase/specs/jls/se8/jls8.pdf
[github]: https://github.com/kevinkenan/java-playground/blob/master/src/main/java/Example05.java