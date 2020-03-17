package domains.user;

import domains.card.Deck;

public class User {
    protected Hands hands;
    protected boolean burst = false;
    protected boolean blackJack = false;

    public void hit(Deck deck) {
        hands.draw(deck);
        if (hands.isBurst()) {
            this.burst = true;
        }
    }

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

    protected boolean isBlackJack() {
        return blackJack;
    }
}
