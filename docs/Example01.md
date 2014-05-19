

I'm re-reading Joshua Block (*Effective Java* and *Java Puzzlers*) and thought
I'd engrave some of my thoughts and notes here and on GitHub for future
reference. If you haven't read *Effective Java* or *Java Puzzlers*, you should
stop reading for a moment and order them right away. These books shine a
bright light into some rather dark corners of Java and provide insight into
Java programming that's worth more than a dozen certifications.

This post is inspired by Item 41 in *Effective Java* and Chapter 8 of *Java
Puzzlers.* I say inspired by because I'm not reproducing the code examples or
sticking to exactly the same topics as the book. These notes simply record
some of my thoughts while reading.

The program in Example01 (available on 
[GitHub](https://github.com/kevinkenan/java-playground/blob/master/src/Example01.java)), 
consists of two classes, A and B. B is a sublcass of A. B overrides several 
methods inherited from A. Here's the code:

    package kenan.java.playground.example01;
    
    public class Example01 {
      public static void main(String[] args) {
        A a = new A();
        B b = new B();
        A x = b;
    
        System.out.println("Example 1");
        System.out.println(" 1: " + a.go());
        System.out.println(" 2: " + b.go());
        System.out.println(" 3: " + x.go());
      }
    }
    
    class A {
      public String go() {
        return "A1";
      }
    }
    
    class B extends A {
      public String go() {
        return "B1";
      }
    }

When we run this code we get:

    Example 1
     1: A1
     2: B1
     3: B1

This output is terribly interesting, but let's walk through it quickly to
make sure sure it's clear.

**Item 1**

This is basic method invocation on an object. 

**Item 2**

Here we see that Java does support overriding. The method `go()` is
implemented in A and then it is re-implemented in B. Invoking `go()` on a B
objects executes the implementation in B. Which isn't too surprising.

**Item 3**

Finally something a little more interesting. Take a moment to think about this
situation. The variable `x` is of type A, but it actually references a B
object. The complier allows this because B is a subclass of A. These sorts of
discrepancies between declared and actual types often produce interesting or
unexpected behavior in Java. 

Logically, `x.go()` could execute the implementation defined in A or B. Since 
`x` is of type A, then it is easy to imagine that A1 would be the output, 
such as we saw with Item 1. But the output is B1. This is because overriding 
instance methods in Java is done dynamically at runtime. The JVM decides 
during execution what the actual type of the object is and selects the
shallowest method with the appropriate signature. This is an example of 
polymorphism. 



