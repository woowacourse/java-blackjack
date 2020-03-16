package domains.user;

import domains.card.Deck;

public abstract class User {
    protected Hands hands;
    protected boolean burst = false;

    abstract void hit(Deck deck);

    public int handSize() {
        return hands.size();
    }

    public int score() {
        return hands.score();
    }

    public String getHandsWords() {
        return hands.toString();
    }

    public boolean isBurst() {
        return burst;
    }
}
