package kenan.java.playground.example06;

public class Example06 {
  public static void main(String[] args) {
    A a = new A();
    B b = new B();
    C c = new C();
    Integer i = new Integer(13);

    System.out.println("Example 5");
    System.out.println(" 1: " + a.go(3, i));
    System.out.println(" 2: " + a.go(i, 3));
    System.out.println(" 3: " + b.go(3, i));
    System.out.println(" 4: " + b.go(i, 3));
    System.out.println(" 5: " + c.go(3, i));
    System.out.println(" 6: " + c.go(i, 3));
    //System.out.println(" 2: " + a.go(new Double(2.0), new Double(3.0)));
  }
}

class A {
  public String go(Integer i1, Integer i2) {
    return "A1";
  }
}
class B {
  public String go(Number i1, double i2) {
    return "A2";
  }
}
class C {
  public String go(Integer i1, Integer i2) {
    return "A1";
  }
  public String go(Number i1, double i2) {
    return "A2";
  }
}