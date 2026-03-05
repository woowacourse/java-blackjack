package domain;

public abstract class Participant {

    private final String name;

    protected Participant(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }
}
