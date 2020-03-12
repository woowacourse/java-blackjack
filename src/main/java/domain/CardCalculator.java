package domain;

import domain.card.Card;

import java.util.List;

public class CardCalculator {
    private static final int SUM_WITH_ACE = 6;
    private static final int STANDARD_ACE_ELEVEN = 11;
    private static final int BLACK_JACK = 21;
    private static final int DEALER_STANDARD_ADDITIONAL_CARD = 16;
    private static final int SUM_CONTAIN_ACE = 10;

    private CardCalculator() {
    }

    public static boolean isUnderSixteen(List<Card> cards) {
        return isPresentAceAndUnderSumWithAce(cards) || isNotPresentAceAndUnderSixteen(cards);
    }

    private static boolean isNotPresentAceAndUnderSixteen(List<Card> cards) {
        return cards.stream()
                .noneMatch(Card::isAce) && calculateCards(cards) <= DEALER_STANDARD_ADDITIONAL_CARD;
    }

    private static boolean isPresentAceAndUnderSumWithAce(List<Card> cards) {
        return cards.stream()
                .anyMatch(Card::isAce) && calculateCards(cards) <= SUM_WITH_ACE;
    }

    public static boolean isBlackJack(List<Card> cards) {
        int sum = calculateCards(cards);

        if (cards.stream().anyMatch(Card::isAce) && sum == STANDARD_ACE_ELEVEN) {
            return true;
        }
        return sum == BLACK_JACK;
    }

    private static int calculateCards(List<Card> cards) {
        return (int) cards.stream()
                .mapToLong(Card::getCardNumber)
                .sum();
    }

    public static int calculateContainAce(List<Card> cards) {
        int playerCardSum = calculateCards(cards);

        if (cards.stream()
                .anyMatch(Card::isAce)
                && playerCardSum + SUM_CONTAIN_ACE <= BLACK_JACK) {
            playerCardSum += SUM_CONTAIN_ACE;
        }

        return playerCardSum;
    }
}
