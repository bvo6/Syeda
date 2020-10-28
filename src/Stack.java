/**
 * Stack is an iterface for Bin class.
 * CS 310-002.
 * @param <T> generic type
 * @author
 */ 
interface Stack<T> {
    
    /**
     * Push a plate to the stack which will add a plate to the top of the stack.
     * @param value the plate
     * @return true if the addition is successful
     */
    public boolean push(T value);
    
    /**
     * Pop/remove the top plate of the stack.
     * @throw new NoSuchElementException if Bin is empty
     * @return the removed plate
     */
    public T pop();
    
    /**
     * Return the size of Bin.
     * @return the size of Bin
     */
    public int size();
    
    /**
     * Check if the bin is empty or not.
     * @return true if size is 0
     */
    public boolean isEmpty();
    
    /**
     * Empty the bin.
     */
    public void clear();
}