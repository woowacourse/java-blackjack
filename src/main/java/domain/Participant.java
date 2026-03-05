package domain;

public abstract class Participant {

    private final String name;
    private final Hand hand;

    protected Participant(String name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public boolean isBusted() {
        return hand.scoreSum() >= 21;
    }

    public String name() {
        return name;
    }
}
