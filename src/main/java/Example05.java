package kenan.java.playground.example05;

public class Example05 {
    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        A x = b;

        System.out.println("Example 3");

        System.out.print(" 1: " );
        a.go3(a);

        System.out.print(" 2: " );
        x.go3(b);

        System.out.print(" 3: " );
        x.go3(x);
    }
}

class A {
    public void go3(A a) {
        System.out.println("A3a");
    }
    public void go3(B b) {
        System.out.println("A3b");
    }

}

class B extends A {
    public void go3(A a) {
        System.out.println("B3a");
    }
    public void go3(B b) {
        System.out.println("B3b");
    }
}