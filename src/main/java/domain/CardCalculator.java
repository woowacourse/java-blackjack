package domain;

import java.util.List;

public class CardCalculator {
    private static final int SUM_WITH_ACE = 6;
    private static final int STANDARD_ACE_ELEVEN = 11;
    private static final int BLACK_JACK = 21;
    private static final int DEALER_STANDARD_ADDITIONAL_CARD = 16;
    public static final int SUM_CONTAIN_ACE = 10;

    private CardCalculator() {
    }

    public static boolean isUnderSixteen(Player dealer) {
        List<Card> cards = dealer.getCard();
        return isPresentAceAndUnderSumWithAce(dealer, cards) || isNotPresentAceAndUnderSixteen(dealer, cards);
    }

    private static boolean isNotPresentAceAndUnderSixteen(Player dealer, List<Card> cards) {
        return cards.stream()
                .noneMatch(Card::isAce) && dealer.sumCardNumber() <= DEALER_STANDARD_ADDITIONAL_CARD;
    }

    private static boolean isPresentAceAndUnderSumWithAce(Player dealer, List<Card> cards) {
        return cards.stream()
                .anyMatch(Card::isAce) && dealer.sumCardNumber() <= SUM_WITH_ACE;
    }

    public static boolean isBlackJack(Player player) {
        int sum = player.sumCardNumber();

        if (player.getCard().stream().anyMatch(Card::isAce) && sum == STANDARD_ACE_ELEVEN) {
            return true;
        }
        return sum == BLACK_JACK;
    }

    public static int calculateCards(List<Card> cards) {
        return (int) cards.stream()
                .mapToLong(Card::getCardNumber)
                .sum();
    }

    public static int calculateContainAce(Player player) {
        int playerCardSum = player.sumCardNumber();
        if (player.getCard().stream()
                .anyMatch(Card::isAce)
                && player.sumCardNumber() + SUM_CONTAIN_ACE <= BLACK_JACK) {
            playerCardSum += SUM_CONTAIN_ACE;
        }
        return playerCardSum;
    }
}
