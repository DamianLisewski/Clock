package clock;

/**
 * Minimal "alarm" class.
 *
 * At the moment a alarm object just holds their name, but in a more realistic
 * system, there would obviously be more.
 * @author Damian Lisewski 17002750 
 * @version v1
 * Date : 07/05/2020
 */
public class Alarm {

    protected String name;
    protected long alarm;

     /**
    *constructor  
    * @param name
    *@param  alarm
     */
    public Alarm(String name , long alarm) {
        this.name = name;
        this.alarm = alarm;
    }

      /**
    *return alarm name
    *@return name
     */
    public String getName() {
        return name;
    }
    
     /**
    *return alarm time
    *@return alarm
     */
      public long getAlarm() {
        return alarm;
    }
      
      
          /**
    *edit alarm method
     * @param name
     * @param alarm
    *
     */
      public void editAlarm(String name, long alarm) {
        this.name = name;
        this.alarm = alarm;
    
    }
      
        /**
    *method used for string version of alarm class  
    * @return string
     */
    @Override
    public String toString() {
        return getName();
    }
}
