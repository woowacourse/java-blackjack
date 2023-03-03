package player;

import java.util.List;

import card.Card;

abstract class Participant {
    public static final int BUST_SCORE = 22;
    protected final Name name;
    protected final Hand hand;

    public Participant(Name name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public void hit(Card card) {
        hand.add(card);
    }

    public int calculateScore() {
        return hand.calculateScore();
    }

    public List<Card> showCards() {
        return hand.getCards();
    }

    public boolean isBust() {
        return hand.calculateScore() >= BUST_SCORE;
    }

    public Name getName() {
        return name;
    }

    abstract void win();

    abstract void lose();

    abstract void tie();

}
