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