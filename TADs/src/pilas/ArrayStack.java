
package pilas;

public class ArrayStack<T> implements StackADT<T> {
  /** Constant to represent the default capacity of the array.
   */
  private final int DEFAULT_CAPACITY = 100;

  /** Represents both the number of elements and the next
   * available position in the array.
   */
  private int top;

  /** Array of generic elements to represent the stack.
   */
  private T[] stack;

   /** Creates an empty stack using the default capacity.
    */
  public ArrayStack() {
    top = 0;
    stack = (T[])(new Object[DEFAULT_CAPACITY]);
  }

  /** Creates an empty stack using the specified capacity.
   */
  public ArrayStack (int initialCapacity) {
    top = 0;
    stack = (T[])(new Object[initialCapacity]);
  }

  /** push: adds the specified element to the top of this stack, expanding
    * the capacity of the stack array if necessary.
    */
  public void push (T element) {
    if (top == stack.length)
      expandCapacity();
    stack[top]= element;
    top++;
  }
  
  /** pop: removes the element at the top of this stack and returns a
   * reference to it. Throws an EmptyCollectionException if the stack
   * is empty.
   */
  public T pop() throws EmptyCollectionException {
    T result;
    
    if (isEmpty())
      throw new EmptyCollectionException("Stack");
    result= stack[top-1];
    stack[top-1]= null;
    top--;
    return result;
  }
  
  /** peek: returns a reference to the element at the top of this stack.
   * The element is not removed from the stack.  Throws an
   * EmptyCollectionException if the stack is empty.
   */
  public T peek() throws EmptyCollectionException {
    if (isEmpty())
      throw new EmptyCollectionException("Stack");
    return stack[top-1];
  }
  
  /** isEmpty: returns true if this stack is empty and false otherwise.
   */
  public boolean isEmpty() {
    return (top == 0);
  }
  
  /**
   * Returns a string representation of this stack.
   */
  public String toString() {
    String result = "";

    for (int scan=0; scan < top; scan++)
      result = result + stack[scan].toString() + "\n";

    return result;
  }

  /** Creates a new array to store the contents of this stack with
   * twice the capacity of the old one.
   */
  private void expandCapacity() {
    T[] larger = (T[])(new Object[stack.length*2]);

    for (int index=0; index < stack.length; index++)
      larger[index] = stack[index];

    stack = larger;
  }
}
