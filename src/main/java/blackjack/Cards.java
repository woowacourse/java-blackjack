package blackjack;

import java.util.List;

public class Cards {

    private final List<String> cards;

    public Cards(String... cards) {
        this.cards = List.of(cards);
    }

    public int score() {
        if (isBust(totalScore(11))) {
            return totalScore(1);
        }
        return totalScore(11);
    }

    private boolean isBust(int score) {
        return score > 21;
    }

    public boolean isBust() {
        return score() > 21;
    }

    private int totalScore(int aceValue) {
        int score = 0;
        for (String s : cards) {
            String value = s.substring(0, 1);
            score += number(value, aceValue);
        }
        return score;
    }

    private int number(String value, int aceValue) {
        if (value.equals("J") || value.equals("Q") || value.equals("K")) {
            return 10;
        }
        if (value.equals("A")) {
            return aceValue;
        }
        return Integer.parseInt(value);
    }
}
