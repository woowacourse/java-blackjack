package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;

import java.util.List;

public class ScoreCalculator {

    private static final int BUST_STANDARD = 21;
    private static final int ACE_DIFFERENCE = 10;

    public static int cardSum(List<Card> cards) {
        int sum = cards.stream()
                .mapToInt(Card::getNumber)
                .sum();

        if (sum > BUST_STANDARD) {
            sum = adjustSum(sum, cards);
        }

        return sum;
    }

    private static int adjustSum(int sum, List<Card> cards) {
        int aceCount = getAceCount(cards);

        while (sum > BUST_STANDARD && aceCount > 0) {
            sum -= ACE_DIFFERENCE;
            aceCount -= 1;
        }
        return sum;
    }

    private static int getAceCount(List<Card> cards) {
        return (int) cards.stream()
                .filter(card -> card.getNumber() == CardNumber.ACE.getNumber())
                .count();
    }
}
