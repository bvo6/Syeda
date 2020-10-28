/**
 * Queue is an iterface for Air class.
 * CS 310-002.
 * @param <T> generic type
 * @author
 */ 
interface Queue<T> {
    /**
     * Enqueue/Add an object to the queue.
     * @param value the value
     * @return true if the addition is successful
     */
    public boolean enqueue(T value);
    
    /**
     * Dequeue/remove a plate from the list (the first plate).
     * @throw NoSuchElementException if queue is empty
     * @return the removed value/plate
     */
    public T dequeue();
    /**
     * Return the size of queue.
     * @return the size of queue
     */
    public int size();
    
    /**
     * Check if the queue is empty or not.
     * @return true if queue is empty
     */
    public boolean isEmpty();
    
    /**
     * Empty the queue.
     */
    public void clear();
}