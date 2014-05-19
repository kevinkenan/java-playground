### Section 2: Overriding (aka Hiding) Static Methods

In Java, you can't override static methods. However, a parent class and its subclasses can have static methods with identical signatures. In that case, the static method in the subclass is said to *hide* the identical method in the parent class. 

Static methods are also called class methods because they don't depend on an actual instance of the class. They are part of the class itself. However, Java allows static methods to be invoked through an instance. While this is discouraged by the language specification [see page 70 of the JLS8](http://docs.oracle.com/javase/specs/jls/se8/jls8.pdf), it is still common, and it can cause confusion because static methods invoked through an instance look like instance methods.

**Item 4**

The output of A2 is not too surprising since we invoked the static method on an A class. This is similar to what we saw in Item 1 above.

**Item 5**

Here we see that static methods are inherited. 

**Item 6**

This result is also not very surprising. You can see that invoking the static `go()` on a B class executes the method defined in the B class. As mentioned above, in Java this is called method hiding. 

In Java, hiding is a different thing than overriding. It seems to me that hiding is one way of implementing overrides. 

In my mind, hiding is what occurs when you override a method without polymorphism. In popular usage, though, override seems to imply polymorphism. It is certainly true in Java. If you 