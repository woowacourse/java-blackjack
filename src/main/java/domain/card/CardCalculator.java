package domain.card;

import java.util.List;

public class CardCalculator {
    private static final int BLACK_JACK = 21;
    private static final int DEALER_STANDARD_ADDITIONAL_CARD = 16;
    private static final int SUM_WHEN_ACE_ELEVEN = 10;

    private CardCalculator() {
    }

    public static boolean isUnderSixteen(List<Card> cards) {
        return sumCardDeck(cards) <= DEALER_STANDARD_ADDITIONAL_CARD;
    }


    public static boolean isBlackJack(List<Card> cards) {
        int sum = sumCardDeck(cards);

        return sum == BLACK_JACK;
    }

    public static boolean isUnderBlackJack(List<Card> cards) {
        int sum = sumCardDeck(cards);

        return sum < BLACK_JACK;
    }

    public static boolean isMoreThanBlackJack(List<Card> cards) {
        return !isUnderBlackJack(cards);
    }

    public static int sumCardDeck(List<Card> cards) {
        int playerCardSum = sumCardDeckNotDeterminedAce(cards);

        if (cards.stream()
                .anyMatch(Card::isAce)
                && playerCardSum + SUM_WHEN_ACE_ELEVEN <= BLACK_JACK) {
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
