package blackjack.domain;

import blackjack.domain.card.Card;
import java.util.List;

public class Rule {

    private static final int BLACKJACK_NUMBER = 21;
    private static final int ACE_POINT_DIFFERENCE = 10;

    public int sumPoint(List<Card> cards) {
        int aceCount = countAce(cards);

        if (aceCount > 0) {
            return sumWithAce(aceCount, cards);
        }

        return cards.stream()
                .mapToInt(Card::getPoint)
                .sum();
    }

    private int sumWithAce(int aceCount, List<Card> cards) {
        int sum = cards.stream()
                .mapToInt(Card::getPoint)
                .sum();
        return calculateWithAce(sum, aceCount);
    }

    private int calculateWithAce(int sum, int aceCount) {
        if (aceCount == 0) {
            return sum;
        }

        if (sum + ACE_POINT_DIFFERENCE <= BLACKJACK_NUMBER) {
            return calculateWithAce(sum + ACE_POINT_DIFFERENCE, aceCount - 1);
        }

        return sum;
    }

    private int countAce(List<Card> cards) {
        return Math.toIntExact(cards.stream()
                .filter(Card::isAce).count());
    }
}
