package domain.calculatestrategy;

import domain.deck.Card;
import java.util.List;

public class DealerStrategy implements CalculateStrategy {

    private final static int ACE_ADDITIONAL_NUMBER = 10;

    @Override
    public int calculateSum(final List<Card> cards) {
        final int sumOfRank = getSumOfRank(cards);
        if (hasAce(cards)) {
            return sumOfRank + ACE_ADDITIONAL_NUMBER;
        }
        return sumOfRank;
    }

    private int getSumOfRank(final List<Card> cards) {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    private boolean hasAce(final List<Card> cards) {
        return cards.stream()
                .anyMatch(Card::isAce);
    }
}
