# Questions and Exercises: Nested Classes (from https://docs.oracle.com/javase/tutorial/java/javaOO/QandE/nested-questions.html)

Questions
The program Problem.java doesn't compile. What do you need to do to make it compile? Why?

Use the Java API documentation for the Box class (in the javax.swing package) to help you answer the following questions.

What static nested class does Box define?

What inner class does Box define?

What is the superclass of Box's inner class?

Which of Box's nested classes can you use from any class?

How do you create an instance of Box's Filler class?

Exercises
Get the file Class1.java. Compile and run Class1. What is the output?

The following exercises involve modifying the class DataStructure.java, which the section Inner Class Example discusses.

Define a method named print(DataStructureIterator iterator). Invoke this method with an instance of the class EvenIterator so that it performs the same function as the method printEven.

Invoke the method print(DataStructureIterator iterator) so that it prints elements that have an odd index value. Use an anonymous class as the method's argument instead of an instance of the interface DataStructureIterator.

Define a method named print(java.util.function.Function<Integer, Boolean> iterator) that performs the same function as print(DataStructureIterator iterator). Invoke this method with a lambda expression to print elements that have an even index value. Invoke this method again with a lambda expression to print elements that have an odd index value.

Define two methods so that the following two statements print elements that have an even index value and elements that have an odd index value:

DataStructure ds = new DataStructure()
// ...
ds.print(DataStructure::isEvenIndex);
ds.print(DataStructure::isOddIndex);
