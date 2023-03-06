package domain.model;

import domain.type.Letter;

public class Score {

    public static final int MAX_VALUE = 21;
    public static final int ACE_SUB_NUMBER = 1;
    private final int value;

    private Score(final int value) {
        this.value = value;
    }

    public static Score of(final Cards cards) {
        final int score = getInitialScore(cards);
        final int aceCount = cards.count(Letter.ACE);
        return new Score(modifyScoreByAce(score, aceCount));
    }

    private static int modifyScoreByAce(int score, final int aceCount) {
        for (int i = 0; i < aceCount; i++) {
            score = changeToAceSub(score);
        }
        return score;
    }

    private static int changeToAceSub(int score) {
        if (score > MAX_VALUE) {
            score -= Letter.ACE.getNumber();
            score += ACE_SUB_NUMBER;
        }
        return score;
    }

    private static int getInitialScore(final Cards cards) {
        return cards.getCards()
            .stream()
            .mapToInt(Card::getNumber)
            .sum();
    }

    public boolean isBust() {
        return value > MAX_VALUE;
    }

    public int getValue() {
        return value;
    }
}
