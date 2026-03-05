package domain;

public abstract class Participant {
    private static final int BUST_NUMBER = 21;

    private final String name;
    protected final Hand hand;

    protected Participant(String name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public boolean isBusted() {
        return hand.scoreSum() >= BUST_NUMBER;
    }

    protected abstract boolean isPlaying();

    public String name() {
        return name;
    }
}
