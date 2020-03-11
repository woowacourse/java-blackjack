package domain;

import java.util.List;

public class CardCalculator {
    private static final int SUM_WITH_ACE = 6;

    private CardCalculator() {
    }

    public static boolean isUnderSixteen(Player dealer) {
        List<Card> cards = dealer.getCard();
        if (cards.stream()
                .anyMatch(Card::isAce) && dealer.sumCardNumber() <= SUM_WITH_ACE) {
            return true;
        }
        return false;
    }
}
