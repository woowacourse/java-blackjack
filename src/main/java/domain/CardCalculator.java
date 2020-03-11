package domain;

import java.util.List;

public class CardCalculator {
    private static final int SUM_WITH_ACE = 6;
    private static final int STANDARD_ACE_ELEVEN = 11;
    private static final int BLACK_JACK = 21;

    private CardCalculator() {
    }

    public static boolean isUnderSixteen(Player dealer) {
        List<Card> cards = dealer.getCard();
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
}
