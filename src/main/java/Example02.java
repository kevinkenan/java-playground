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