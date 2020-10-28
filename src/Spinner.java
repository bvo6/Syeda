import java.util.NoSuchElementException;
/**
 * Spinner will do the handoffs between bin, air and Syed's hands.
 * CS 310-002.
 * @author Bao Vo
 */ 
public class Spinner {
    
    /**
     * Hand is a inner class which store a single plate.
     */
    private static class Hand {
        /**
         * Instance variable plate.
         */
        private Plate plate;
        
        /**
         * Catch a plate to this hand.
         * @param plate the plate
         * @throw RuntimeException if catching hand is not empty or plate does not exist
         */
        public void catchPlate(Plate plate) {
            if (this.plate != null) {
                throw new RuntimeException("Catching hand not empty");
            }
            if (plate == null) {
                throw new RuntimeException("Can't catch a plate that doesn't exist!");
            }
            this.plate = plate;
        }
        
        /**
         * Toss a plate from this hand.
         * @throw RuntimeException if tossing hand is empty
         * @return the removed plate
         */
        public Plate tossPlate() {
            if (this.plate == null) {
                throw new RuntimeException("Tossing hand was empty");
            }
            Plate temp = this.plate;
            this.plate = null;
            return temp;
        }
        
        /**
         * Check if the hand has plate.
         * @return true of hand has plate
         */
        public boolean hasPlate() {
            //return true if you have a plate, false otherwise
            if (this.plate == null) {
                return false;
            }
            return true;
        }
        
        /**
         * Return a string representation of the plate that hand is holding.
         * @return a string representation of the plate or three spaces if no plate
         */
        public String toString() {
            if (this.plate == null) {
                return "   ";
            }
            return this.plate.toString();   
        }
    }
    
    /**
     * Put a plate from hand 2 and pass it to hand 1.
     */
    public void passPlate() {
        hands[0].catchPlate(hands[1].tossPlate());
    }
    
    /**
     * Put a plate from hand 1 and put it in the bin.
     */
    public void putDownPlate() {
        bin.push(hands[0].tossPlate());
    }
    
    /**
     * Take a plate out of the bin and put it in hand 1.
     * @throw RuntimeException if Bin is empty
     */
    public void pickUpPlate() {
        if (bin.size() == 0) {
            throw new RuntimeException("Can't pickup a plate that doesn't exist!");
        }
        hands[0].catchPlate(bin.pop());
    }
    
    /**
     * Take a plate from hand 1 and put it in the air.
     * @throw RuntimeException if the size of Air has reached its max capacity
     */
    public void spinPlate() {
        if (air.size() == air.MAX_CAPACITY){
            throw new RuntimeException("Too many plates in the air!");
        }
        air.enqueue(hands[0].tossPlate());
    }
    
    /**
     * Take a plate out of the air and put it in hand 2.
     * @throw RuntimeException if Air is empty
     */
    public void catchPlate() {
        if (air.isEmpty()) {
            throw new RuntimeException("Can't catch a plate that doesn't exist!");
        }
        hands[1].catchPlate(air.dequeue());
    }
    
    /**
     * The main method of this class.
     * @param args the command arguments
     */
    public static void main(String[] args) {
        
    }
    /**
     * Declare an air which is a queue for Syed.
     */
    private final Air air = new Air();
    
    /**
     * Declare a bin which is a stack for Syed.
     */
    private final Bin bin = new Bin();
    
    /**
     * Create two hands for Syed.
     */
    private final Hand[] hands = new Hand[2];
    
    
    /**
     * Constructor of Spinners which have two hands and starts with a bin full of plates.
     * @param totalPlates the number of plates in a bin
     */
    public Spinner(int totalPlates) {
        hands[0] = new Hand();
        hands[1] = new Hand();
        
        for(int i = totalPlates; i > 0; i--) {
            this.bin.push(new Plate(i));
        }
    }
    
    /**
     * Return a string representation of Syed in ascii values.
     * @return a string representation of Syed
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        String[] personParts = {
            "   "+air.toString()+"\n",
            "\n",
            "    "+hands[0].toString()+"( )"+hands[1].toString()+"\n",
            "     \\__|__/\n",
            "        |\n",
            "        |\n",
            "       / \\\n",
            "      /   \\\n"
        };
        
        String[] stackParts = this.bin.toString().split("[|]");
        
        int total = (this.bin.size() < personParts.length) ? personParts.length : this.bin.size();
        for(int i = total; i >= 0; i--) {
            sb.append((this.bin.size()-1 < i) ? "   " : stackParts[stackParts.length-i-1]);
            if(i < personParts.length) {
                sb.append(personParts[personParts.length-i-1]);
            }
            else {
                sb.append("\n");
            }
        }
        
        return sb.toString();
    }
}
