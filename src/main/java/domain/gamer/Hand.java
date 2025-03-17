package domain.gamer;

import domain.calculatestrategy.CalculateStrategy;
import domain.deck.Card;
import java.util.ArrayList;
import java.util.List;

public class Hand {

    private static final int CARD_MAX_SUM = 21;
    private static final int BLACKJACK_CARD_SIZE = 2;

    private final List<Card> cards;
    private final CalculateStrategy calculateStrategy;

    public Hand(final CalculateStrategy calculateStrategy) {
        this.cards = new ArrayList<>();
        this.calculateStrategy = calculateStrategy;
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public int getScoreSum() {
        return calculateStrategy.calculateSum(cards);
    }

    public boolean isBust() {
        return getScoreSum() > CARD_MAX_SUM;
    }

    public boolean isImpossibleDrawCard() {
        return getScoreSum() == CARD_MAX_SUM;
    }

    public boolean isBlackJack() {
        return getScoreSum() == CARD_MAX_SUM && getCards().size() == BLACKJACK_CARD_SIZE;
    }

    public List<Card> getCards() {
        return cards;
    }
}
