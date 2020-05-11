package clock;

import java.util.Calendar;
import java.util.Observable;
//import java.util.GregorianCalendar;

public class Model extends Observable {
    
    //initialise variables for calander values
    int hour = 0;
    int minute = 0;
    int second = 0;
    int oldSecond = 0;
    
    
       /**
    *constructor
    * calls update method
     */
    public Model() {
        update();
    }
    
      /**
    *method used for updating model
    * uses current time and date values through the calender    
     */
    public void update() {
        Calendar date = Calendar.getInstance();
        hour = date.get(Calendar.HOUR);
        minute = date.get(Calendar.MINUTE);
        oldSecond = second;
        second = date.get(Calendar.SECOND);
        if (oldSecond != second) {
            setChanged();
            notifyObservers();
        }
    }
}