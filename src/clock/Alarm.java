package clock;

/**
 * Minimal "alarm" class.
 *
 * At the moment a alarm object just holds their name, but in a more realistic
 * system, there would obviously be more.
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
    *method used for string version of alarm class  
    * @return string
     */
    @Override
    public String toString() {
        return getName();
    }
}
