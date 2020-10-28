/**
 * Plate class will create plates with letters for Syeda to hold.
 * @author
 */ 
public class Plate {
    /**
     * The number of the plate.
     */
    private int number;
    /**
     * Constructor of the plate.
     * @param number of the plate
     */
    public Plate(int number) {
        this.number = number;
    }
    
    /**
     * Return a string representaion of plate.
     * @return a string representation of plate
     */
    public String toString() {
        return "("+((char)(this.number+96))+")";
    }
    
    /**
     * Return the number of plate.
     * @return the number of plate
     */
    public int getNumber() {
        return this.number;
    }
}
