package domain;

import java.util.List;

public abstract class Participant {

    protected static final int BLACK_JACK_SCORE = 21;
    protected static final int BLACK_JACK_CARD_SIZE = 2;

    protected final Name name;
    protected final Hand hand;

    Participant(String name) {
        this.name = new Name(name);
        this.hand = new Hand();
    }

    abstract boolean isHittable();

    public void initHand(List<Card> cards) {
        this.hand.add(cards);
    }

    public void hit(Card card) {
        this.hand.add(card);
    }

    public boolean isBust() {
        return getTotalScore() > BLACK_JACK_SCORE;
    }

    public boolean isBlackJack() {
        return getTotalScore() == BLACK_JACK_SCORE && hand.getSize() == BLACK_JACK_CARD_SIZE;
    }

    public int getTotalScore() {
        return hand.calculateScore();
    }

    public Hand getHand() {
        return hand;
    }

    public String getName() {
        return name.name();
    }
}
