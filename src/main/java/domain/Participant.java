package domain;

import java.util.List;

public class Participant {
    private static final int BLACK_JACK_COUNT = 21;
    protected Hands hands;
    private Name name;

    public Participant(Name name) {
        this.hands = new Hands();
        this.name = name;
    }

    public void receiveCard(Card card) {
        hands.receive(card);
    }

    public int getCardCount() {
        return hands.getCardCount();
    }

    public Name getName() {
        return name;
    }

    public int getScore() {
        return hands.calculateScore();
    }

    public List<Card> getCards() {
        return hands.getValue();
    }

    public boolean canHit() {
        return hands.calculateScore() <= BLACK_JACK_COUNT;
    }
}
