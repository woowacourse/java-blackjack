package blackjack.domain.machine;

import blackjack.domain.card.Card;
import java.util.List;

public class Score {
    public static final int MAX_SCORE = 21;
    private static final int INIT_A_NUMBER = 1;
    private static final int NEW_A_NUMBER = 11;
    private static final int CONDITION_HIT = 16;

    private final int sum;

    private Score(int sum) {
        this.sum = sum;
    }

    public static Score from(List<Card> hand) {
        int sum = calculate(hand);

        if (!isContainA(hand)) {
            return new Score(sum);
        }

        return new Score(scoreWithA(sum));
    }

    public static int calculate(List<Card> hand) {
        return hand.stream()
                .mapToInt(Card::getNumber)
                .sum();
    }

    private static boolean isContainA(List<Card> hand) {
        int count = (int) hand.stream()
                .filter(myCard -> myCard.getNumber() == INIT_A_NUMBER)
                .count();

        return count > 0;
    }

    private static int scoreWithA(int sum) {
        if (sum + (NEW_A_NUMBER - INIT_A_NUMBER) <= MAX_SCORE) {
            return sum + NEW_A_NUMBER - INIT_A_NUMBER;
        }

        return sum;
    }

    public boolean isHit() {
        return sum <= CONDITION_HIT;
    }

    public boolean isBust() {
        return sum > MAX_SCORE;
    }

    public boolean isMax() {
        return sum == MAX_SCORE;
    }

    public int getSum() {
        return sum;
    }
}
