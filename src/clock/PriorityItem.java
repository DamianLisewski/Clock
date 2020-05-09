package clock;

/**
 * A wrapper for bundling up an item and its long priority.
 * 
 * @param <T>
 */
public class PriorityItem<T> {

    private final T item;
    private final long priority;
    
    /**
    * constructor for the item of class
    * @param item
    * @param priority
    
     */
    public PriorityItem(T item, long priority) {
        this.item = item;
        this.priority = priority;
    }
    
     /**
    *method used for returning item  
    * @return item
     */
    public T getItem() {
        return item;
    }
    
       /**
    *method used for returning priority  
    * @return priority
     */
    public long getPriority() {
        return priority;
    }

      /**
    *method used for string version of priority item  
    * @return string
     */
    @Override
    public String toString() {
        return "(" + getItem() + ", " + getPriority() + ")";
    }
}
