public class Example01 {
    public static void main(String[] args) {
        B b = new B();
        A a = new A();
        A x = b;

        // Basic overridden methods
        System.out.print("1: " );
        a.go();

        System.out.print("2: " );
        b.go();

        System.out.print("3: " );
        x.go();

        // Overridden static methods
        System.out.println("------");

        System.out.print("4: " );
        a.go("");

        System.out.print("5: " );
        b.go("");

        System.out.print("6: " );
        x.go("");

        // Overriding (actual type) vs overloading (declared type)
        System.out.println("------");

        System.out.print("7: " );
        a.go(a);

        System.out.print("8: " );
        x.go(b);

        System.out.print("9: " );
        x.go(x);
    }
}

class A {
    public void go() {
        System.out.println("A1");
    }
    public static void go(String s) {
        System.out.println("A2");
    }
    public void go(A a) {
        System.out.println("A3");
    }
    public void go(B b) {
        System.out.println("A4");
    }
    public void go(int i) {
        System.out.println("A5");
    }
    public void go(A a, int i) {
        go(a);
    }
}

class B extends A {
    public void go() {
        System.out.println("B1");
    }
    public static void go(String s) {
        System.out.println("B2");
    }
    public void go(A a) {
        System.out.println("B3");
    }
    public void go(B b) {
        System.out.println("B4");
    }
    public void go(B b, int i) {
        go(b);
    }
}