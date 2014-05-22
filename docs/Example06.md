# Overloaded with Ambiguity

Most Java programmers run afoul of the complex invocation and overloading
rules fairly early in their careers. Example 6 ([GitHub][github]) illustrates
the often surprising behaviors that emerge from overloading.

    package kenan.java.playground.example06;
    
    public class Example06 {
      public static void main(String[] args) {
        A a = new A();
        Integer i = new Integer(13);
    
        System.out.println("Example 6");
        System.out.println(" 1: " + a.go(3, i));
        System.out.println(" 2: " + a.go(i, 3));
        // System.out.println(" 2: " + a.go(3, 3));
      }
    }
    
    class A {
      public String go(Integer i1, Integer i2) {
        return "A1";
      }
      public String go(Number i1, double i2) {
        return "A2";
      }
    }

The output is:

    Example 6
     1: A1
     2: A2

Both of the methods are candidates for these calls.

Java starts with strict invocation. If it can find a applicable methods
under strict invocation, it shifts to loose invocation. The important 
distinction in this case is that boxing is only allowed under loose
invocation.

**Item 1**

A2 is selected for #2 because it is applicable by strict invocation


[ex1]: http://www.0xc0deshop.com/2014/05/overriding-instance-methods-in-java.html
[jls8]: http://docs.oracle.com/javase/specs/jls/se8/jls8.pdf
[github]: https://github.com/kevinkenan/java-playground/blob/master/src/main/java/Example06.java