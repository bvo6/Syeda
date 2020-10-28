import java.util.NoSuchElementException;
/**
 * Bin is a stack data structure which will hold plates.
 * @author Bao Vo
 */ 
public class Bin implements Stack<Plate> {
    /**
     * The internal storage list.
     */
    public AttachedList<Plate> internalList;
    
    /**
     * Constructor for the bin class which will initialize all its fields.
     */
    public Bin() {
        internalList = new AttachedList<Plate>();
    }
    
    /**
     * Push a plate to the stack which will add a plate to the top of the stack.
     * @param value the plate
     * @return true if the addition is successful
     */
    @Override public boolean push(Plate value) {
        internalList.add(0, value);
        return true;
    }
    
    /**
     * Pop/remove the top plate of the stack.
     * @throw new NoSuchElementException if Bin is empty
     * @return the removed plate
     */
    @Override public Plate pop() { //throw NoSuchElementException if nothing to pop
        if (internalList.size() == 0) {
            throw new NoSuchElementException("Empty Bin");
        }
        return internalList.remove(0);
    }
    
    /**
     * Return the size of Bin.
     * @return the size of Bin
     */
    @Override public int size() {
        return internalList.size();  
    }
    
    /**
     * Check if the bin is empty or not.
     * @return true if size is 0
     */
    @Override public boolean isEmpty() {
        return internalList.size() == 0;
    }
    
    /**
     * Empty the bin.
     */
    @Override public void clear() {
        internalList.clear();
    }

    /**
     * The main method of this class.
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
        boolean first = true;
        for(Plate p : internalList) {
            if(first) { returnString = returnString+p; first = false; }
            else {returnString = returnString+"|"+p; }
        }
        return returnString;
    }
}
