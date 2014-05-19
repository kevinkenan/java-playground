package kenan.java.playground.example02;

public class Example02 {
    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        A x = b;

        System.out.println("Example 2");

        System.out.print(" 1: " );
        a.go2();

        System.out.print(" 2: " );
        b.go2();

        System.out.print(" 3: " );
        x.go2();
    }
}

class A {
    public static void go2() {
        System.out.println("A2");
    }
}

class B extends A {
    public static void go2() {
        System.out.println("B2");
    }
}