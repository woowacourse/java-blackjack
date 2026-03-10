package domain;

public final class Player extends Participant {
    private final Name name;
    private Outcome outcome;

    public Player(Name name) {
        super();
        this.name = name;
    }

    public Player(String name) {
        this(new Name(name));
    }

    public Outcome getOutcome() {
        return outcome;
    }

    public void setOutcome(Outcome outcome) {
        this.outcome = outcome;
    }

    public String getName() {
        return name.getName();
    }
}
