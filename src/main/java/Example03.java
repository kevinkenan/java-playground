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