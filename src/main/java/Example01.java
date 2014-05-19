package kenan.java.playground.example01;

public class Example01 {
    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        A x = b;

        // ------------------------------------------------
        System.out.println("Example Set 1");

        System.out.print(" 1: " );
        a.go1();

        System.out.print(" 2: " );
        b.go1();

        System.out.print(" 3: " );
        x.go1();

        // ------------------------------------------------
        System.out.println("Example Set 2");

        System.out.print(" 1: " );
        a.go2();

        System.out.print(" 2: " );
        b.go2();

        System.out.print(" 3: " );
        x.go2();

        // ------------------------------------------------
        System.out.println("Example Set 3");

        System.out.print(" 1: " );
        a.go3(a);

        System.out.print(" 2: " );
        x.go3(b);

        System.out.print(" 3: " );
        x.go3(x);
    }
}

class A {
    // Example Set 1 Methods
    public void go1() {
        System.out.println("A1");
    }
    // Example Set 2 Methods
    public static void go2() {
        System.out.println("A2");
    }
    // Example Set 3 Methods
    public void go3(A a) {
        System.out.println("A3a");
    }
    public void go3(B b) {
        System.out.println("A3b");
    }

}

class B extends A {
    // Example Set 1 Methods11
    public void go1() {
        System.out.println("B1");
    }
    // Example Set 2 Methods
    public static void go2() {
        System.out.println("B2");
    }
    // Example Set 3 Methods
    public void go3(A a) {
        System.out.println("B3a");
    }
    public void go3(B b) {
        System.out.println("B3b");
    }
}