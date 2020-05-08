package clock;

/**
 * Minimal "person" class.
 *
 * At the moment a Person object just holds their name, but in a more realistic
 * system, there would obviously be more.
 */
public class Alarm {

    protected String name;
    protected long alarm;

    public Alarm(String name , long alarm) {
        this.name = name;
        this.alarm = alarm;
    }

    public String getName() {
        return name;
    }
    
      public long getAlarm() {
        return alarm;
    }

    @Override
    public String toString() {
        return getName();
    }
}
