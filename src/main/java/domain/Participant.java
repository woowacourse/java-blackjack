package domain;

import dto.GameStatus;

public abstract class Participant {

    private final String name;
    protected final Hand hand;

    public Participant(String name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    protected abstract boolean isPlayable();

    public abstract boolean isDealer();

    public abstract GameStatus status();

    public void draw() {
        if (isPlayable()) {
            hand.drawCard();
        }
    }

    public boolean isBusted() {
        return hand.isBusted();
    }

    public int scoreSum() {
        return hand.scoreSum();
    }
    public String name() {
        return name;
    }
}
