package domain.card;

import java.util.List;

public class CardCalculator {
    private static final int WINNING_COUNT = 21;
    private static final int SUM_WHEN_ACE_ELEVEN = 10;

    private CardCalculator() {
    }

    public static int sumCardDeck(List<Card> cards) {
        int playerCardSum = sumCardDeckNotDeterminedAce(cards);

        if (cards.stream()
                .anyMatch(Card::isAce)
                && playerCardSum + SUM_WHEN_ACE_ELEVEN <= WINNING_COUNT) {
            playerCardSum += SUM_WHEN_ACE_ELEVEN;
        }

        return playerCardSum;
    }

    private static int sumCardDeckNotDeterminedAce(List<Card> cards) {
        return (int) cards.stream()
                .mapToLong(Card::getCardNumber)
                .sum();
    }
}
