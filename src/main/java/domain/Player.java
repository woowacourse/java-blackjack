package domain;

public class Player extends Participant {

    private final Name name;

    public Player(Name name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name.toString();
    }
}
