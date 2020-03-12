package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.rule.CardCalculator;

import java.util.List;

public abstract class Gamer {

    protected Hand hand = new Hand();

    public void draw(Card card) {
        hand.add(card);
    }

    public boolean isBusted() {
        return calculateSum() > CardCalculator.BUST_THRESHOLD;
    }

    public int calculateSum() {
        return hand.calculateSum();
    }

    public abstract String getName();

    public String getCardStatus() {
        return hand.getCardStatus();
    }
}