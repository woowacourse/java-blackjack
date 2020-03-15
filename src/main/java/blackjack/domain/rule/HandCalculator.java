package blackjack.domain.rule;

import blackjack.domain.card.Card;

import java.util.List;

public class HandCalculator {

    private static final int BUST_THRESHOLD = 21;
    private static final int ACE_NUMBER_GAP = 10;

    public static Score calculate(List<Card> cards) {
        int result = sumAll(cards);
        result = subtractIfContainingAce(cards, result);
        return Score.from(result);
    }

    private static int sumAll(List<Card> cards) {
        return cards.stream()
                .mapToInt(Card::getNumber)
                .sum();
    }

    private static int subtractIfContainingAce(List<Card> cards, int result) {
        for (Card card : cards) {
            result = subtract(result, card);
        }
        return result;
    }

    private static int subtract(int result, Card card) {
        if (result > BUST_THRESHOLD && card.isAce()) {
            result -= ACE_NUMBER_GAP;
        }
        return result;
    }
}
