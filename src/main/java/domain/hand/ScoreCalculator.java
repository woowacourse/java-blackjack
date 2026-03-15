package domain.hand;

import domain.card.Card;
import domain.card.CardNumber;

import java.util.List;

public class ScoreCalculator {
    private static final int ACE_BONUS = 10;
    private static final int BUST_LIMIT_SCORE = 21;

    public static int calculate(List<Card> cards) {
        int baseSum = calculateBaseSum(cards);
        int aceCount = countAce(cards);
        return applyAceLogic(baseSum, aceCount);
    }


    private static int calculateBaseSum(List<Card> cards) {
        int sum = 0;
        for (Card card : cards) {
            sum += card.getCardNumber().getValue();
        }
        return sum;
    }

    private static int countAce(List<Card> cards) {
        return (int) cards.stream()
                .map(Card::getCardNumber)
                .filter(cardNumber -> cardNumber == CardNumber.ACE)
                .count();
    }

    private static int applyAceLogic(int baseSum, int aceCount) {
        int score = baseSum;
        for (int i = 0; i < aceCount; i++) {
            score = addAceBonus(score);
        }
        return score;
    }

    private static int addAceBonus(int score) {
        if (score + ACE_BONUS <= BUST_LIMIT_SCORE) {
            return score + ACE_BONUS;
        }
        return score;
    }
}
