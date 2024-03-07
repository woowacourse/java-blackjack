package domain;

import java.util.List;

public class Gamer {
    Name name;
    Hand hand;

    public Gamer(final Name name) {
        this.name = name;
        this.hand = new Hand();
    }

    public void hit(final Card card) {
        hand.add(card);
    };

    public int calculateTotalScore() {
        return hand.sum();
    }

    public boolean isBust() {
        return hand.isOverBlackJack();
    }

    public List<Card> getHand() {
        return hand.getCards();
    }

    public Name getName() {
        return name;
    }
}
