package blackjack.domain.rule;

import blackjack.domain.card.Card;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Score {

    private static final int BUSTED = 0;
    private static final int ACE_NUMBER_GAP = 10;
    private static final int BLACKJACK = 21;
    private static final Map<Integer, Score> SCORE_MATCHER;

    private int score;

    static {
        SCORE_MATCHER = new HashMap<>();
        for (int i = BUSTED; i <= BLACKJACK; i++) {
            SCORE_MATCHER.put(i, new Score(i));
        }
    }

    private Score(int score) {
        this.score = score;
    }

    public static Score from(int score) {
        if (score > BLACKJACK) {
            return SCORE_MATCHER.get(BUSTED);
        }
        return SCORE_MATCHER.get(score);
    }

    public static Score from(List<Card> cards) {
        int result = sumAll(cards);
        result = subtractIfContainingAce(cards, result);
        return Score.from(result);
    }

    public boolean isBusted() {
        return score == BUSTED;
    }

    public boolean isMaxValue() {
        return score == BLACKJACK;
    }

    public int getScore() {
        return score;
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
        if (result > BLACKJACK && card.isAce()) {
            result -= ACE_NUMBER_GAP;
        }
        return result;
    }
}
