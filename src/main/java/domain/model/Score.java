package domain.model;

import domain.type.Denomination;

public class Score {

    private static final int BLACK_JACK = 21;
    private static final int ACE_SUB_NUMBER = 1;

    private final int value;

    private Score(final int value) {
        this.value = value;
    }

    public static Score of(final Cards cards) {
        final int score = getInitialScore(cards);
        final int count = cards.count(Denomination.ACE);
        return new Score(modifyScoreByAce(score, count));
    }

    private static int getInitialScore(final Cards cards) {
        return cards.getCards()
            .stream()
            .mapToInt(Card::getNumber)
            .sum();
    }

    private static int modifyScoreByAce(int score, final int count) {
        for (int i = 0; i < count; i++) {
            score = changeToAceSub(score);
        }
        return score;
    }

    private static int changeToAceSub(int score) {
        if (score > BLACK_JACK) {
            score -= Denomination.ACE.getNumber();
            score += ACE_SUB_NUMBER;
        }
        return score;
    }

    public boolean isBust() {
        return value > BLACK_JACK;
    }

    public int getValue() {
        return value;
    }
}
