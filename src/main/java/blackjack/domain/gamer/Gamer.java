package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;

import java.util.List;

public abstract class Gamer {

    Hand hand = new Hand();

    public void draw(Card card) {
        hand.add(card);
    }

    public boolean isBusted() {
        return hand.isBusted();
    }

    public int calculateScore() {
        return hand.calculate();
    }

    public abstract String getName();

    public List<Card> getHand() {
        return hand.getHand();
    }
}