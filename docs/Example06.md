# Overloaded with Ambiguity

Most Java programmers run afoul of the complex invocation and overloading
rules fairly early in their careers. Example 6 ([GitHub][github]) illustrates
the often surprising behaviors that emerge from overloading--especially when
combined with implicit conversions and autoboxing.

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

The output is:

    Example 5
     1: A1
     2: A2

It might not be apparent from the code, but both versions of the method `go`
in class `A` are applicable to both method calls. That is, if either of the
`go` methods did not exist--`go` was not overloaded--both method calls would
still execute without error.

To see this for yourself, change the name of the `go` method that returns A1
to something like `noGo`. Recompile and run. Both `a.go(3, i)` and `a.go(i, 3)` 
will return A2. Do the same to the `go` that returns A2, and you'll see that
both calls will return A1. 

Since both methods defined in `A` are applicable to to both method invocations,
why does overloading cause different behavior? Why is 
`go(Integer i1, Integer i2)` more applicable to `a.go(3, i)` while 
`go(Number i1, double i2)` is more applicable to `a.go(i, 3)`? All it takes is
boxing `3` in both cases to return A1.

One reason for the observed behavior is that Java's algorithm for matching 
method invocations to method definitions proceeds in three phases. If matches
are found in an early phase, the search concludes with that phase and does not 
continue on to the later phases. See Section 15.12.2 in [JLS8][jls8] for 
the details.

In this case, only the first two phases are relevant (the third phase applies
to varagrs). The first phase uses what Java calls *strict invocation.* During
strict  invocation, Java will widen a reference but it will not autobox
primitive types. The second phase uses *loose invocation* and allows boxing
conversions. [JLS8 5.3][jls8] discusses these rules.

With that background in mind, let's look at the results of the program.

**Item 1** 

The method invocation `a.go(3, i)` will not match either method definition in
`A` under strict invocation. The 3 must be converted to a reference type in
order for it to match either definition and such conversions are not permitted
in phase 1.

When Java moves on to phase 2, it notes that it can easily box 3 to an 
Integer. This makes `go(Integer i1, Integer i2)` a match. Voila!

Wait, not so fast: `go(Number i1, double i2)` is also a match in phase 2.
The first argument in the method invocation, `3`, needs to be boxed and widened
to become a Number, and the second argument needs to be unboxed and widened to
become a double. These operations are all allowed in phase 2.

Both methods are applicable to the invocation. What is Java to do?

Once Java has determined which methods are applicable, it then selects the
most specific of those methods. The rules for this selection are a bit hairy.
Read [JLS8][jls8] 15.12.2.5 for the *specifics* (I couldn't resist). This is
an area of the JLS on which I need to spend a bit more time, but here's my
understanding of what is going on.

To determine the most specific method, Java compares the types of each
method's parameters. When comparing two methods the most specific method is
the method in which each parameter is a subtype of the corresponding parameter
of the other method. The rules defining a subtype are covered in [JLS8][jls8]
4.10.

To be a little formal: If `m1` is a method with parameters
`T1, T2, T3,..., Tn` and `m2` is a method with parameters
`S1, S2, S3,..., Sn`, then we say `m2` is more specific than `m1` if 
each `Si` is a subtype of `Ti`. (Note that a type is a subtype of itself.

So far, this is pretty straightforward. Things get interesting when we mix
primitive types with reference types. Because Java allows autoboxing,
primitive types can "match" reference types. This occurred in our example
above. However, boxing and unboxing are only applied during the phases to
determine which methods are applicable. Once Java has determined the set of
applicable methods, it no longer considers boxing or unboxing. It only looks
at the parameter types.

This means that when selecting the most specific method, Java will on occasion
need to determine if a primitive type parameter is more specific than a
reference type parameter. To resolve this question, Java considers the
arguments in the actual method invocation. The more specific parameter is the
one which is a supertype of the argument used in the invocation.

So when comparing two methods the most specific method is the method in which
each parameter is either a subtype of the corresponding parameter of the other
method or, failing that, is a supertype of of the corresponding argument.

*Rule for Selecting the Most Specific Method*

>  If `M` is a method invocation with arguments
>  `A1, A2, A3,..., An` and `m1` a method with parameters
>  `T1, T2, T3,..., Tn` and `m2` is a method with parameters
>  `S1, S2, S3,..., Tn`, then we say `m2` is more specific than `m1` if 
>  for each `Si` one of the following conditions is true:
>  
>    1. If `Si` and `Ti` are both reference types or both primitive types,
>       then `Si` is a subtype of `Ti` 
>    2. If `Si` or `Ti` is a reference type and the other is a primitive
>       type, then `Ai` is a subtype of `Si` 

The rules that identify applicable methods guarantee that one of these
conditions will be true. This doesn't cover *all* of the rules in the JLS8
for determining specificity, just the parts that are relevant to this example.

The first parameters in our example case are `Integer` in the first method and
`Number` in the second method. Since `Integer` is more specific than `Number`,
the first method is considered more specific for the first parameter per the
first condition of the *Rule* stated above.

The second parameters are `Integer` in the first method and `double` in the
second method. Since one is primitive and the other a reference type, the
second condition of the *Rule* applies. Since the second argument is an
`Integer`, and an `Integer` is a subtype of itself, the first method is
considered more specific for the second parameter.

Since the first method is considered more specific than the second method for
each parameter, the first method is considered the most specific. Hence, the
first result is A1.

**Item 2**

This case is much easier. During the first phase of determining applicable
methods, boxing is not permitted, but widening is. Both parameters of the
invocation `a.go(i, 3)` can be widened to match the parameters of the second
method `go(Number, double)`. Matching the first method would require a boxing
operation which isn't permitted in the first phase. 

So in this case, Java never moves on to the second phase. Since only one
method was identified as applicable, there's also no need to determine the
specificity of methods. So the second result is A2.

See, I said it was easier.

**Item 3**

If we had included

    System.out.println(" 3: " + a.go(3, 3));

as a 3rd invocation, the compiler would have complained that the "reference to 
go is ambiguous." Hopefully this makes sense given the above discussion. The
invocation requires boxing to match, and both methods will match with some
widening thrown in. 

The determination on which is more specific starts okay. The first parameter
of the first method is more specific according the first condition of the *Rule.*
However, the second parameter of the second method is more specific according
to the second condition of the *Rule.* We don't have a most specific method so the
compiler throws the "ambiguous" error.

**Summary**

This was a long post. Longer than I thought it would be. And I might have the
exact rule by which the compiler determines the most specific method wrong. It
is certainly incomplete. Chapter 15 of the JLS8 should come with a "here be
dragons" warning. I certainly feel that the language spec is a bit ambiguous
about this topic, but I'm probably just misreading some obvious point.
Eventually someone will point it out to me and I'll have a "Doh!" moment.

The important take-away from this example is that overloading is tricky. If
you're programming an API, avoid overloading or at least minimize it.
*Effective Java* recommends that you never have more than one overloaded
method with the same number of parameters. Seems like good advice to me. It
will certainly eliminate uncertainty about which method will actually be
executed.


[ex1]: http://www.0xc0deshop.com/2014/05/overriding-instance-methods-in-java.html
[jls8]: http://docs.oracle.com/javase/specs/jls/se8/jls8.pdf
[github]: https://github.com/kevinkenan/java-playground/blob/master/src/main/java/Example06.java