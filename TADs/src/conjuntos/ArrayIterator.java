/**
 * ArrayIterator represents an iterator over the elements of an array.
 */


package conjuntos;
import java.util.*;

public class ArrayIterator<T> implements Iterator<T> {
  private int countAI;    // the number of elements in the collection
  private int current;  // the current position in the iteration
  private T[] items;
  
  /**
   * Sets up this iterator using the specified items.
   */
  public ArrayIterator (T[] collection, int size) {
    items = collection;
    countAI = size;
    current = 0;
  }
  
  /**
   * Returns true if this iterator has at least one more element
   * to deliver in the iteration.
   */
  public boolean hasNext() {
    return (current < countAI);
  }
  
  /**
   * Returns the next element in the iteration. If there are no
   * more elements in this iteration, a NoSuchElementException is
   * thrown.
   */
  public T next() {
    T result= null;
    
    if (!hasNext())
      throw new ElementNotFoundException("Set");
    
    result= items[current];
    current++;
    return result;
  }
  
  /**
   * The remove operation is not supported in this collection.
   */
  public void remove() throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }
}
