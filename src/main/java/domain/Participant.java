package domain;

import java.util.List;

public abstract class Participant {

    private final String name;
    protected final Hand hand;

    public Participant(String name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    protected abstract boolean isPlayable();

    public abstract boolean isDealer();

    public void draw() {
        if (isPlayable()) {
            hand.drawCard();
        }
    }

    public boolean isBlackJack() {
        return hand.isBlackJack();
    }

    public boolean isBusted() {
        return hand.isBusted();
    }

    public String name() {
        return name;
    }

    public List<Card> cards() {
        return hand.cards();
    }

    public int score() {
        return hand.scoreSum();
    }
}
