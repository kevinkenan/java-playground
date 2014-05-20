package kenan.java.playground.example04;

public class Example04 {
  public static void main(String[] args) {
    C c = new C();

    System.out.println("Example 4");
    System.out.println(" 1: " + c.s);
    System.out.println(" 2: " + ((B)c).s);
    System.out.println(" 3: " + ((A)c).s);
  }
}

class A {
  public String s = "A1";
}
class B extends A {
  public String s = "B1";
}
class C extends B {
  public String s = "C1";
}