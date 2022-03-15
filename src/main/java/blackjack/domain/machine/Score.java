package blackjack.domain.machine;

import java.util.List;

public class Score {
    private static final int INIT_A_NUMBER = 1;
    private static final int NEW_A_NUMBER = 11;

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

    private static int calculate(List<Card> hand) {
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
        if (sum <= NEW_A_NUMBER) {
            return sum + NEW_A_NUMBER - INIT_A_NUMBER;
        }

        return sum;
    }

    public int getSum() {
        return sum;
    }
}
