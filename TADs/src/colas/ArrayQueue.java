/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colas;

/**
 *
 * @author pauli
 */
public class ArrayQueue <T> implements QueueADT<T> {
    
    private final int DEFAULT_CAPACITY = 100;
    private int rear, front;
    private T[] queue;
    
    public ArrayQueue(){
        rear = front = 0;
        queue = (T[])(new Object[DEFAULT_CAPACITY]);
   }
    
    public ArrayQueue (int initialCapacity){
        rear = front = 0;
        queue = (T[])(new Object[initialCapacity]);
    }

    public void enqueue (T elemento){
        if (front == rear && queue[front] != null) 
            expandCapacity();
        queue[rear] = elemento;
        rear=(rear+1)%queue.length;
    }
    
    public T dequeue(){
        if (isEmpty()){
            throw new EmptyCollectionException ("queue");
        }else{
            T res = queue[front];
            queue[front] = null;
            front = (front+1)%queue.length;
            
            return res;
        }
    }
    
    public T first(){
        if (isEmpty()){
            throw new EmptyCollectionException ("queue"); 
        }else{
           return queue[front]; 
        }
   }
    
    private void expandCapacity(){
        T[] grande = (T[])(new Object[queue.length*2]);
        
        for (int i=front;i<queue.length;i++)
            grande[i-front] = queue[i];
        if (rear==front)
            for(int i=0;i<rear;i++)
                grande[queue.length-front+i]=queue[i];
        front = 0;
        rear = queue.length;
        queue=grande;
   }
    
    public boolean isEmpty(){
       return (front==rear && queue[front]==null); 
    }
}
