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