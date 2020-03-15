package blackjack.domain.rule;

import blackjack.domain.card.Card;

import java.util.List;

public class CardCalculator {

    public static final int BUST_THRESHOLD = 21;
    private static final int ACE_NUMBER_GAP = 10;

    public static int calculate(List<Card> cards) {
        int result = sumAll(cards);
        return subtractIfContainingAce(cards, result);
    }

    private static int sumAll(List<Card> cards) {
        return cards.stream()
                .mapToInt(Card::getCardNumber)
                .sum();
    }

    private static int subtractIfContainingAce(List<Card> cards, int result) {
        for (Card card : cards) {
            if (result > BUST_THRESHOLD && card.isAce()) {
                result -= ACE_NUMBER_GAP;
            }
        }
        return result;
    }
}