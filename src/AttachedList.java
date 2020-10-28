import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
/**
 * AttachedList is a generic linked list that implements Java's List interface.
 * CS 310-002.
 * @author Bao Vo
 * @param <T> generic type
 */ 
public class AttachedList<T> implements List<T> {
    /**
     * Node is a private node class that is used to create a linked list.
     * @param <T> generic type
     */
    private static class Node<T> {
        /**
         * The value of the node.
         */
        T value;
        /**
         * The next node.
         */
        Node<T> next;
        /**
         * Constructor for the Node class which is used to initialize its field.
         * @param value value of the current node.
         */
        public Node (T value) {
            this.value = value;
            this.next = null;
        }
    }
    
    /**
     * The head of the linked list.
     */
    private Node<T> head = null;
    
    /**
     * The node is used to track the current Node in the linked list.
     */
    private Node<T> currentNode;
    
    /**
     * The current size of the list.
     */
    private int size;
    
    /**
     * Constructor for the AttachedList class which is used to initialize all the fields.
     */
    public AttachedList() {
        /*
         * The current node will be the head since the list is empty.
         * Size is initialized to 0.
         */
        this.currentNode = head;
        this.size = 0;
    }
    
    /**
     * Return the size of the list.
     * @return the size of the list
     */
    @Override
    public int size() {
        return size;
    }
    
    /**
     * Check if the list is empty or not.
     * @return true if list is empty, false if not empty
     */
    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }
    
    /**
     * This method is used to find the first occurence of a given object in the list.
     * @param o the object
     * @return -1 if list is empty or object is not found, otherwise return the index of the given object
     */
    @Override
    public int indexOf(Object o) {
        if (size == 0) {
            return -1;
        }
        // Create a temporary node and set it the the first node in the list.
        Node<T> temp = head;
        // Initialize index to 0 which will be used to find the index of object.
        int index = 0;
        /*
         * Loop through each node in the list.
         * If object is found return index immidiately, otherwise increment index and set temp to the next node.
         */
        while (temp != null) {
            if ((o == null ? temp.value == null : o.equals(temp.value))) {
                return index;
            }
            index++;
            temp = temp.next;
        }
        return -1;
    }
    
    /**
     * Check if the list contains the given object.
     * @param o the object to be searched
     * @return true if object is found, false otherwise
     */
    @Override
    public boolean contains(Object o) {
        /*
         * Create a temporary node and set it to the head.
         * Loop through the list and check if object is found, if yes, return immediately, otherwise set temp to the next node.
         */
        Node<T> temp = head;
        if (this.size() == 0) {
            return false;
        }
        while (temp != null) {
            if ((o == null ? temp.value == null : o.equals(temp.value))) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }
    
    /**
     * Append an object to the end of the list.
     */
    @Override
    public boolean add(T e) {
        /*
         * Check if the list is empty.
         * if yes, the head (first node) will be assigned to hold the given element.
         * CurrentNode will also be the head, increment size by one.
         */
        if (this.head == null) {
            this.head = new Node<T>(e);
            this.currentNode = head;
            this.size++;
            return true;
            /*
             * Otherwise, create a new node to point to the current node.
             * Set the current node with the new element.
             * The next node (which orginally points to the old current node) will be the new current node.
             * Increment size by one.
             */
        } else {
            Node<T> node = this.currentNode;
            currentNode = new Node<T>(e);
            node.next = currentNode;
            this.size++;
            return true;
        }
    }
    
    /**
     * Add an element to a specific index in the list.
     * @param index the index of the element
     * @param element the element
     * @throw IndexOutOfBoundsException for invalid index
     */
    @Override
    public void add(int index, T element) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        /*
         * Check if the index is 0 which means the element will be added to the first node of the list.
         * If yes, create a temp node with the new element value, and the next node of this temp points to the first node.
         * Create a node which points to the first node, shift right every node from the first node of the list.
         * Then, set the first node to the new node with the new element value, increment size by 1.
         */
        if (index == 0) {
            Node<T> temp = new Node<T>(element);
            Node<T> node = head;
            temp.next = head; 
            for (int i = 0; i < size; i++) {
                node = node.next;
            }
            head = temp;
            size++;
            /*
             * Check if the index = size (add the new element at the end).
             * if yes, create a new node with the new element and the next node will point to the next current node.
             * Set the next current node to the new node, and the current node to the new node, increment size by one.
             */
        } else if (index == size) {
            Node<T> temp = new Node<T>(element);
            temp.next = currentNode.next;
            currentNode.next = temp;
            currentNode = temp;
            size++;
            /*
             * If the index in the range of size.
             * Create a temp node and set it to the first node of the list.
             * Loop through the list untill the node temp reaches the node before the given index.
             * Then create a new node with the new element, the next node will point to the node at the given index.
             * Set the temp next (pointing to the node at the given index) to this new node.
             * Increment size by one.
             */
        } else {
            Node<T> temp = head; 
            int count = 0;
            while (temp != null && count < index - 1) {
                temp = temp.next;
                count++;
            }
            Node<T> node = new Node<T>(element);
            node.next = temp.next;
            temp.next = node;
            this.size++;
        }
    }
    
    /**
     * Remove an element at a given index in the list.
     * @param index the index
     * @throw IndexOutOfBoundsException for invalid index
     * @return the removed value at the given index
     */
    @Override
    public T remove(int index) {
        if (index >= size || index < 0 || size == 0) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        // Create a temp node to hold the value of the element that needs to be removed.
        Node<T> remove = null;
        /*
         * Check if the index is 0 which will remove the first node of the list.
         * The remove node will points to the first node (to hold its value before the removal)
         * Unlink the first node of the list by setting that node to the next node after.
         * Decrement size by one
         */
        if (index == 0) {
            remove = head;
            head = head.next;
            size--;
            /*
             * Otherwise, if index is not 0.
             * Create a temp node and set it to the first node of the list.
             * Loop through the list untill it reaches the node before the index.
             * Set the remove node to the node at the given index (hold its value).
             * Unlink the node at the given index by setting that node to the next node after.
             * Decrement size by one.
             */
        } else {
            Node<T> temp = head;
            for (int i = 0; i < index - 1; i++) {
                temp = temp.next;
            }
            remove = temp.next;
            temp.next = temp.next.next;
            size--;
        }
        return remove.value;
    }
    
    /**
     * Remove the first occurence of the given object in the list.
     * @param o the object that needs to be removed
     * @return true if the removal successful, false if failed.
     */
    @Override
    public boolean remove(Object o) {
        /*
         * Create 2 temporary nodes which point to the first node of the list, the other points to the next node.
         */
        Node<T> temp = head;
        Node<T> nextTemp = temp.next;
        /*
         * Loop through the list, and check the next node after the current temp node matches the object or not
         * If yes, unlink that node by setting that node to the next node after.
         */
        while (temp != null) {
            // Check if the object matches the head (first node), if yes set the head to the next node (unlink the first node).
            // Decrement size by one and return.
            if ((o == null ? temp.value == null : o.equals(temp.value))) {
                head = nextTemp;
                size--;
                return true;
            }
            if ((o == null ? temp.value == null : o.equals(nextTemp.value))) {
                temp.next = temp.next.next;
                size--;
                return true;
            }
            temp = temp.next;
            nextTemp = temp.next;
        }
        return false;
    }
    
    /**
     * Empty the list by setting the head to null and set size to 0.
     */
    @Override
    public void clear() {
        head = null;
        size = 0;
    }
    
    /**
     * Get the value of the given index in the list.
     * @param index the index of the element
     * @throw IndexOutOfBoundsException for invalid index
     * @return the value at the given index
     */
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        /* Check if the index is 0 (the first node of the list).
         * Return the value of the first node.
         */ 
        if (index == 0) {
            return head.value;
        }
        /*
         * Create a temp node, set it to the first node.
         * Loop through the list untill the index. 
         * Return the value.
         */
        Node<T> temp = head;
        int count = 0;
        while (temp != null && count != index) {
            count++;
            temp = temp.next;
        }
        return temp.value;
    }
    
    /**
     * Set/Replace the element at the given index.
     * @param index the index of the element
     * @throw IndexOutOfBoundsException for invalid index
     * @return the old value at the index
     */
    @Override
    public T set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        /*
         * Create a temp node to hold the value to be replaced.
         * Check if index is 0 (replace the first node).
         * Assign the value of the first node to the temp node.
         * Then, set the value of the first node to the given element.
         */
        Node<T> temp = new Node<T>(null);
        if (index == 0) {
            temp.value = head.value;
            head.value = element;
            return temp.value;
        }
        /*
         * Otherwise, create a temp node that points to the first node.
         * Loop through the list untill the 1 node before the index.
         * Set the temp node to the value of index (hold the value to return).
         * Then set the value of index to the new element, exit the loop.
         */
        Node<T> node = head;
        for (int i = 0; i < index; i++) {
            if (i == index - 1) {
                temp.value = node.next.value;
                node.next.value = element;
                break;
            }
            node = node.next;
        }
        return temp.value;    
    }
    /**
     * Remove/Slice from the range of indexes and return this slice as a new AttachedList.
     * @param fromIndex start from this index 
     * @param toIndex end at this index
     * @throw IndexOutOfBoundsException for invalid index
     * @return the slice as a new AttachedList
     */
    public AttachedList<T> slice(int fromIndex, int toIndex) {
        // Check for valid index.
        if (fromIndex < 0 || fromIndex >= size || toIndex >= size || fromIndex > toIndex || toIndex < 0) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        /*
         * Create a new list and a temp node that points to the head of the list.
         * Create a variable count to keep track of the indexes and a condition to check if the loop reaches the given fromIndex.
         */
        AttachedList<T> list = new AttachedList<T>();
        Node<T> temp = head;
        int count = 0;
        boolean condition = false;
        // Loop through the list.
        while (temp != null) {
            // This inner loop will iterate when the value of count reaches the value of fromIndex.
            while (count >= fromIndex  && temp != null) {
                // If yes, append the value from the given index to the new list.
                list.add(temp.value);
                size--;
                // Check if the value of count = toIndex which meets the condition (exit the inner loop).
                if (count == toIndex) {
                    condition = true;
                    break;
                }
                // Increment count for each iteration and remove the current node (while count is within the fromIndex).
                count++;
                temp = temp.next;     
            }
            // Check if the condition is met, exit the outer loop.
            if (condition == true) {
                break;
            }
            // Increment count for each iteration and move to the next node untill the condition is met or count reaches the fromIndex.
            count++;
            temp = temp.next;
        }
        return list;
    }
    
    /**
     * Return a copy of the list with elements reversed.
     * @return a copy of the new AttachedList with elements reversed
     */
    public AttachedList<T> reverseCopy() {
        /*
         * Create a temp node that points to the first node of list.
         * Create a new list to hold the elements.
         * Loop through the list (start from the end (size - 1)).
         * Use the get() method to get the value of list at that index and append to the new list.
         * Increment the size of new list by 1.
         */
        Node<T> temp = head;
        AttachedList<T> list = new AttachedList<T>();
        for (int i = size - 1; i >= 0; i--) {
            list.add(this.get(i));
        }
        return list;
    }
    
    /**
     * Convert a 2D list into a 1D list which will be returned as a new AttachedList.
     * @param <E> generic type of a 2D list
     * @param packedList the 2D list
     * @return a new 1D AttachedList 
     */
    public static <E> AttachedList<E> flatten(AttachedList<AttachedList<E>> packedList) {
        /*
         * Create a new list which will hold values from the 2D list.
         * Create a variable getSize that hold the size of the given packedList (size of lists).
         */
        AttachedList<E> list = new AttachedList<E>();
        int getSize = packedList.size();
        // Check if the packedList is empty, if yes, return an empty new list.
        if (getSize == 0) {
            return list;
        }
        /*
         * Loop through each sublist in the packedList.
         * Create a temp list and assign it to the current sublist of packList.
         * Create a temp node and set it to the first node of the current sublist.
         * Append every node of the current sublist into the 1D new AttachedList (list).
         */
        for (int i = 0; i < getSize; i++) {
            AttachedList<E> getList = packedList.get(i);
            Node<E> temp = getList.head;
            while (temp != null) {
                list.add(temp.value);
                temp = temp.next;
            }
        }
        return list;
    }
    
    /**
     * Convert a 1D list into a 2D list of values that hold sequential values together.
     * @param <E> the 1D list with sequential values
     * @param flatList the 1D list
     * @return a 2D of values that each holds sequential values together
     */
    public static <E> AttachedList<AttachedList<E>> pack(AttachedList<E> flatList) {
        /*
         * Create a new 2D AttachedList that will hold different values in different sublists.
         */
        AttachedList<AttachedList<E>> list = new AttachedList<AttachedList<E>>();
        // Check if the flatList is empty or not, if yes, return a new empty list.
        if (flatList.size() == 0) {
            return list;
        }
        // Creata a temp value which will be used to compare the current value in the list.
        E value = null;
        /*
         * Loop through the list and create a current value of the list with the temp value.
         * Check if the current value matches the temp value or not.
         * If yes, it will skip the condition (if statement) and add the value to the current sublist of list.
         * If not, create a new sublist of list and set the current value to the temp value, then add the value to the current sublist.
         */
        for (int i = 0; i < flatList.size(); i++){
            E currValue = flatList.get(i);
            if (currValue != value) {
                list.add(new AttachedList<E>());
                value = currValue;
            }
            list.get(list.size() - 1).add(currValue);
        }
        return list;
    }
    
    /**
     * Returns an iterator over the elements in this list in proper sequence.
     * @return a new iterator which will be used to check for the next object/node
     */
    @Override
    public Iterator<T> iterator() {
        //this method is outlined for you... just fill out next() and hasNext()
        //NO ADDITIONAL ANYTHING (METHODS, VARIABLES, ETC.) INSIDE THE ANONYMOUS CLASS
        //You may NOT override remove() or any other iterator methods
        return new Iterator<T>() {
            //starts at the head
            private Node<T> current = head;
            /*
             * Returns true if the iteration has more elements.
             * @return true if the iteration has more elements, false otherwise
             */
            @Override
            public boolean hasNext() {
                return current != null;
            }
            
            /*
             * Assign a current node to the next node.
             * Check if if the iteration has no more elements.
             * @throw NoSuchElementException for no more elements
             * @return value of current
             */
            @Override
            public T next() {
                if (hasNext() == false) {
                    throw new NoSuchElementException("No more elements");
                }
                T data = current.value;
                current = current.next;
                return data;
            }
        };
    }
    
    /**
     * Return a string representation of the object.
     * @return a string representation of the object
     */
    public String toString() {
        return super.toString();
    }
    
    /** 
     * The main method of this class.
     * @param args the command arguments
     */
    public static void main(String[] args) {
    }
    
    // --------------------------------------------------------
    // DO NOT EDIT ANYTHING BELOW THIS LINE (except to add JavaDocs)
    // --------------------------------------------------------
    /**
     * Returns an array containing all of the elements in this list in proper sequence (from first to last element).
     * @return an array of items in the list
     */
    @Override
    public Object[] toArray() {
        Object[] items = new Object[this.size()];
        int i = 0;
        for(T val : this) {
            items[i++] = val;
        }
        return items;
    }
    
    /**
     * Returns an array containing all of the elements in this list in proper sequence.
     * @param a the given array
     * @return a new array of the given array
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        return (T[]) this.toArray();
    }
    /**
     * Returns true if this list contains all of the elements of the specified collection.
     * @param c collection to be checked for containment in this list
     * @return true if this list contains all of the elements of the specified collection
     */
    @Override public boolean containsAll(Collection<?> c) { throw new UnsupportedOperationException("Not supported."); }
    
    /**
     * Appends all of the elements in the specified collection to the end of this list.
     * @param c collection containing elements to be added to this list
     * @return true if this list changed as a result of the call
     */
    @Override public boolean addAll(Collection<? extends T> c) { throw new UnsupportedOperationException("Not supported."); }
    
    /**
     * Inserts all of the elements in the specified collection into this list at the specified position.
     * @param index index at which to insert the first element from the specified collection
     * @param c - collection containing elements to be added to this list
     * @return true if this list changed as a result of the call
     */
    @Override public boolean addAll(int index, Collection<? extends T> c) { throw new UnsupportedOperationException("Not supported."); }
    
    /**
     * Removes from this list all of its elements that are contained in the specified collection.
     * @param c - collection containing elements to be removed from this list
     * @return true if this list changed as a result of the call
     */
    @Override public boolean removeAll(Collection<?> c) { throw new UnsupportedOperationException("Not supported."); }
    
    /**
     * Retains only the elements in this list that are contained in the specified collection.
     * @param c - collection containing elements to be retained in this list
     * @return true if this list changed as a result of the call
     */
    @Override public boolean retainAll(Collection<?> c) { throw new UnsupportedOperationException("Not supported."); }
    
    /**
     * Returns the index of the last occurrence of the specified element in this list.
     * @param o - element to search for
     * @return the index of the last occurrence
     */
    @Override public int lastIndexOf(Object o) { throw new UnsupportedOperationException("Not supported."); }
    
    /**
     * Returns a list iterator over the elements in this list.
     * @return a list iterator over the elements in this list
     */
    @Override public ListIterator<T> listIterator() { throw new UnsupportedOperationException("Not supported."); }
    
    /**
     * Returns a list iterator over the elements in this list.
     * @param index - index of the first element to be returned from the list iterator
     * @return a list iterator over the elements in this list
     */
    @Override public ListIterator<T> listIterator(int index) { throw new UnsupportedOperationException("Not supported."); }
    
    /**
     * Returns a view of the portion of this list between the specified fromIndex, inclusive, and toIndex, exclusive.
     * @param fromIndex - low endpoint (inclusive) of the subList
     * @param toIndex - high endpoint (exclusive) of the subList
     * @return a view of the specified range within this list
     */
    @Override public List<T> subList(int fromIndex, int toIndex) { throw new UnsupportedOperationException("Not supported."); }
}