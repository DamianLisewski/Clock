package clock;

/**
 * Minimal "person" class.
 *
 * At the moment a Person object just holds their name, but in a more realistic
 * system, there would obviously be more.
 */
public class Alarm {

    protected String name;

    public Alarm(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
