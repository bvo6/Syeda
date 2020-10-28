import java.util.NoSuchElementException;
/**
 * Air class is a Queue which holds plates.
 * CS 310-002.
 * @author Bao Vo
 */ 
public class Air implements Queue<Plate> {
    /**
     * Maximum allowed items in the air.
     */ 
    public static final int MAX_CAPACITY = 13;
    
    /**
     * Internal storage for Queue.
     */
    public AttachedList<Plate> internalList;
    
    /**
     * Constructor for the Air class which will be used to initialize its field.
     */
    public Air() {
        internalList = new AttachedList<Plate>();
    }
    
    /**
     * Enqueue a plate from the queue which will add a plate to the end of the list.
     * @param value the new plate
     * @return true if the addition is successful, false if the size has reached the max capacity
     */
    public boolean enqueue(Plate value) {
        if (internalList.size() == MAX_CAPACITY) {
            return false;
        }
        internalList.add(value);
        return true;
    }
    
    /**
     * Dequeue/remove a plate from the list (the first plate).
     * @throw NoSuchElementException if queue is empty
     * @return the removed value/plate
     */
    public Plate dequeue() {
        if (internalList.isEmpty()) {
            throw new NoSuchElementException("Nothing to deque");
        }
        return internalList.remove(0);
    }
    
    /**
     * Return the size of the queue.
     * @return the size of the queue
     */
    public int size() {
        return internalList.size();
    }
    
    /**
     * Check if the queue is empty or not.
     * @return true if queue is empty
     */
    public boolean isEmpty() {
        return internalList.isEmpty();
    }
    
    /**
     * Empty the queue.
     */
    public void clear() {
        internalList.clear();
    }
    
    /**
     * The main method of Air class.
     * @param args the command arguments
     */
    public static void main(String[] args) {

    }
    
    /**
     * Return a string representation of plate.
     * @return a string representation of plate
     */
    public String toString() {
        String returnString = "";
        for(Plate p : internalList) {
            returnString = p+returnString;
        }
        return returnString;
    }
}
