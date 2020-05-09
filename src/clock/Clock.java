package clock;

/**
    
    *main runnable class
    * deals with the instanciation of the model ,view and controller, as well as the priority queue used for the alarm
     */
public class Clock {
    
    public static void main(String[] args) {
        Model model = new Model();
        View view = new View(model);
        model.addObserver(view);
        Controller controller = new Controller(model, view);
        PriorityQueue<Alarm> q;
          q = new SortedArrayPriorityQueue<>(5);
                
          
          
        
    }
}
