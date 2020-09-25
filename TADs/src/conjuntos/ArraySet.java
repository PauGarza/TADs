/**  
 * ArraySet represents an array implementation of a set.
 */

package conjuntos;
import java.util.*;

public class ArraySet<T> implements SetADT<T> {
  private static Random rand = new Random();
  private final int DEFAULT_CAPACITY = 100;
  private final int NOT_FOUND = -1;
  private int count;  // the current number of elements in the set
  private T[] contents; 
  
  /**
   * Creates an empty set using the default capacity.
   */
  public ArraySet() {
    count = 0;
    contents = (T[])(new Object[DEFAULT_CAPACITY]);
  }
  
  /**
   * Creates an empty set using the specified capacity.
   */
  public ArraySet(int initialCapacity) {
    count = 0;
    contents = (T[])(new Object[initialCapacity]);
  }
  
  /**
   * Adds the specified element to the set if it is not already
   * present. Expands the capacity of the set array if necessary.
   */
  public void add(T element) {

    if (!contains(element)) {
      if(size() == contents.length)
        expandCapacity();
      //Agrega el elemento.
      contents[count]= element;
      count++;
    }
  }
  
  /**
   * Adds the contents of the parameter to this set.
   */
  public void addAll(SetADT<T> set) {
    Iterator<T> itera= set.iterator();
    T element;
    
    while (itera.hasNext()) {
      element= itera.next();    //Recupera un elemento de set.
      this.add(element);
    }
  }
  
  /**
   * Removes a random element from the set and returns it. Throws
   * an EmptyCollectionException if the set is empty.
   */
  public T removeRandom() throws EmptyCollectionException {
    int rn= rand.nextInt(count);    //Genera un entero entre [0, count-1].
    
    if (isEmpty())
      throw new EmptyCollectionException("set");
    
    //Recupera y remueve al elemento.
    T result= contents[rn];
    contents[rn]= contents[count-1];
    contents[count-1]= null;
    count--;
    
    return result;
  }
  
  /**
   * Removes the specified element from the set and returns it.
   * Throws an EmptyCollectionException if the set is empty and a
   * NoSuchElementException if the element is not in the set.
   */
  public T remove (T element) throws EmptyCollectionException,
                                      ElementNotFoundException {
    int search = NOT_FOUND;
    int i= 0;
    
    if (isEmpty())
      throw new EmptyCollectionException("set");
    
    //Recorre el arreglo contents buscando al elemento.
    while (i<size() && search==NOT_FOUND) {
      if (element.equals(contents[i]))
        search= i;
      i++;
    }

    //Lanza la excepción si no está el elemento.
    if (search == NOT_FOUND)
      throw new ElementNotFoundException("set");
    
    //Recupera y remueve al elemento.
    T result= contents[search];
    contents[search]= contents[count-1];
    contents[count-1]= null;
    count--;
    
    return result;
  }
  
  /**
   * Returns a new set that is the union of this set and the
   * parameter.
   */
  public SetADT<T> union (SetADT<T> set) {
    ArraySet<T> both = new ArraySet<T>();
    
    for (int index = 0; index < count; index++)
       both.add (contents[index]);

    Iterator<T> scan = set.iterator();
    while (scan.hasNext())
       both.add (scan.next());

    return both;
 }
  
  public SetADT<T> intersection(SetADT<T> set){
      ArraySet<T> inter = new ArraySet<T>();
      T x;
      Iterator<T> scan = set.iterator();
      while (scan.hasNext()){
          x=scan.next();
          if(this.contains(x))
              inter.add (scan.next());
      }
      return inter;
  }
  
  public SetADT<T> difference(SetADT<T> set){
    ArraySet<T> difer = new ArraySet<T>();
    
    for (int index = 0; index < count; index++)
       difer.add (contents[index]);
    
    Iterator<T> scan = set.iterator();
    while (scan.hasNext())
       difer.remove (scan.next());
     
    return difer;
  }
  
  /**
   * Returns true if this set contains the specified element.
   */
  public boolean contains (T element) {
    int search = NOT_FOUND;
    int i= 0;
    
    //Recorre el arreglo contents buscando al elemento.
    while (i<size() && search==NOT_FOUND) {
      if (element.equals(contents[i]))
        search= i;
      i++;
    }
    return (search != NOT_FOUND);
  }
  
  /**
   * Returns true if this set contains exactly the same elements
   * as the parameter.
   */
  public boolean equals (SetADT<T> set) {
    boolean result = false;
    ArraySet<T> temp1 = new ArraySet<T>();
    ArraySet<T> temp2 = new ArraySet<T>();
    T obj;

    if (size() == set.size()){ 
        temp1.addAll(this);
        temp2.addAll(set);

        Iterator<T> scan = set.iterator();

        while (scan.hasNext()){
            obj = scan.next();   
            if (temp1.contains(obj)){
               temp1.remove(obj);
               temp2.remove(obj);
            }	  
         }
        result = (temp1.isEmpty() && temp2.isEmpty());
      }
      return result;
  }
  
  /**
   * Returns true if this set is empty and false otherwise.
   */
  public boolean isEmpty() {

    return (count == 0);
  }
  
  /**
   * Returns the number of elements currently in this set.
   */
  public int size() {

    return count;
  }
  
  /**
   * Returns an iterator for the elements currently in this set.
   */
  public Iterator<T> iterator() {
    
    return new ArrayIterator(contents,count);
  }
  
  /**
   * Returns a string representation of this set.
   */
  public String toString() {
    String result = "";

    for (int i=0; i<size(); i++)
      result += contents[i].toString() + '\n';
    return result;
  }
  
  /**
   * Creates a new array to store the contents of the set with
   * twice the capacity of the old one.
   */
  private void expandCapacity() {
    T[] larger = (T[])(new Object[contents.length*2]);
    
    for (int index=0; index < contents.length; index++)
      larger[index] = contents[index];
    
    contents = larger;
  }
}

