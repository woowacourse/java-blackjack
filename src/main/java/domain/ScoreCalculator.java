package domain;

import java.util.List;

public class ScoreCalculator {
    // TODO 계산 로직 리팩토링 필요
    public static int calculate(List<Card> cards) {
        int sum = 0;
        int aceCount = 0;
        for (Card card : cards) {
            sum += card.getCardNumber().getValue();
            if (card.getCardNumber().equals(CardNumber.ACE)) {
                aceCount++;
            }
        }

        for (int i = 0; i < aceCount; i++) {
            if (sum + 10 <= 21) {
                sum += 10;
            }
        }
        return sum;
    }
}
