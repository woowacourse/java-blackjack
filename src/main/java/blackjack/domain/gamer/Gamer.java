package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.rule.CardCalculator;

import java.util.List;

public abstract class Gamer implements Comparable<Gamer> {

    protected Hand hand = new Hand();
    private int score = 0;

    public void draw(Card card) {
        hand.add(card);
        score = hand.calculateSum();
    }

    public boolean isBusted() {
        return calculateSum() > CardCalculator.BUST_THRESHOLD;
    }

    public int calculateSum() {
        return hand.calculateSum();
    }

    public abstract boolean canDrawCard();

    public abstract String getName();

    public List<Card> getHand() {
        return hand.getCardStatus();
    }

    @Override
    public int compareTo(Gamer o) {
        return Integer.compare(this.score, o.score);
    }
}