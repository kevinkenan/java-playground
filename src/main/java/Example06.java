package kenan.java.playground.example06;

public class Example06 {
  public static void main(String[] args) {
    A a = new A();
    Integer i = new Integer(13);

    System.out.println("Example 5");
    System.out.println(" 1: " + a.go(3, i)); 
    System.out.println(" 2: " + a.go(i, 3));
  }
}

class A {
  // First Method:
  public String go(Integer i1, Integer i2) {
    return "A1";
  }
  // Second Method:
  public String go(Number i1, double i2) {
    return "A2";
  }
}