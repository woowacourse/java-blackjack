package blackjack;

import java.util.List;

public class Cards {

    private final List<String> cards;

    public Cards(String... cards) {
        this.cards = List.of(cards);
    }

    public Score score() {
        if (isBust(totalScore(11))) {
            return new Score(totalScore(1));
        }
        return new Score(totalScore(11));
    }

    private boolean isBust(int score) {
        return score > 21;
    }

    public boolean isBust() {
        return score().isBust();
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

    public Result compare(Cards other) {
        if (hasBust(other)) {
            return judgeBustCase(other);
        }
        return judgeNormalCase(other);
    }

    private boolean hasBust(Cards other) {
        return this.isBust() || other.isBust();
    }

    private Result judgeBustCase(Cards other) {
        if (this.isBust() && other.isBust()) {
            return Result.DRAW;
        }

        if (this.isBust()) {
            return Result.LOSS;
        }

        return Result.WIN;
    }

    private Result judgeNormalCase(Cards other) {
        return score().compare(other.score());
    }
}
