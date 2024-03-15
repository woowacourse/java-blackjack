package domain.participant;

import domain.card.Card;
import domain.card.Cards;

import java.util.List;

public class Dealer extends Cards {

    private static final int MIN_SCORE = 16;

    public Dealer(List<Card> cards) {
        super(cards);
    }

    @Override
    public boolean canDraw() {
        return bestSumOfCardScore() <= MIN_SCORE;
    }

    public Card getFirstCard() {
        return cards.get(0);
    }

    @Override
    public String toString() {
        return "Dealer{" +
                "cards=" + cards +
                '}';
    }
}
