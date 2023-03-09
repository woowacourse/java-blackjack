package domain;

import java.util.List;

public abstract class Participant {

    protected final Hand hand;
    private final Name name;

    Participant(Name name) {
        this.name = name;
        this.hand = new Hand();
    }

    abstract boolean isDrawable();

    public void receiveCard(Card card) {
        hand.add(card);
    }

    public int score() {
        return hand.calculateScore();
    }

    public abstract List<Card> initialHand();

    public List<Card> hand() {
        return hand.getCards();
    }

    public String name() {
        return name.value();
    }
}
