package model.card;

import java.util.List;

public class Score {

    private static final int ACE_SCORE_HIGH = 11;
    private static final int ACE_SCORE_LOW = 1;
    private static final int MIN_SCORE_FOR_ACE_HIGH = 11;
    private static final int TWENTY_ONE = 21;

    private final int value;

    private Score(int value) {
        this.value = value;
    }

    public static Score from(List<Card> cards) {
        int score = calculateScore(cards);
        return new Score(score);
    }

    private static int calculateScore(List<Card> cards) {
        int score = sumScore(cards);
        while (score <= MIN_SCORE_FOR_ACE_HIGH && hasAce(cards)) {
            score += ACE_SCORE_HIGH - ACE_SCORE_LOW;
        }
        return score;
    }

    private static int sumScore(List<Card> cards) {
        return cards.stream()
            .mapToInt(Card::getScore)
            .sum();
    }

    public static boolean hasAce(List<Card> cards) {
        return cards.stream()
            .anyMatch(Card::isAce);
    }

    public boolean is21() {
        return value == TWENTY_ONE;
    }

    public boolean isOver21() {
        return value > TWENTY_ONE;
    }

    public int getValue() {
        return value;
    }
}
