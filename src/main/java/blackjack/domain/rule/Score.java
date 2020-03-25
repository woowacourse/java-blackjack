package blackjack.domain.rule;

import blackjack.domain.card.Card;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Score {

    private static final int BUSTED = 0;
    private static final int BLACKJACK_THRESHOLD = 21;

    public static final Map<Integer, Score> SCORES;

    private int score;

    static {
        SCORES = new HashMap<>();

        for (int i = 0; i <= BLACKJACK_THRESHOLD; i++) {
            SCORES.put(i, new Score(i));
        }
    }

    private Score(int score) {
        this.score = score;
    }

    public static Score calculateScore(List<Card> cards) {
        int score = HandCalculator.calculate(cards);

        if (score > BLACKJACK_THRESHOLD) {
            return SCORES.get(BUSTED);
        }
        return SCORES.get(score);
    }

    public boolean isBusted() {
        return score == BUSTED;
    }

    public boolean isBlackJack() {
        return score == BLACKJACK_THRESHOLD;
    }

    public int getNumber() {
        return score;
    }
}