package domain;

import java.util.List;

abstract class Gamer {
    Name name;
    Hand hand;

    public Gamer(Decks decks) {
        this.hand = new Hand();
        for (int i = 0; i < 2; i++) {
            hand.add(decks.draw());
        }
    }

    abstract void hit(final Decks decks);

    public int calculateTotalScore() {
        return hand.sum();
    }

    public boolean isBust() {
        return hand.isOverBlackJack();
    }

    public List<Card> getHand() {
        return hand.getCards();
    }
}
