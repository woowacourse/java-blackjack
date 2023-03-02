package domain.model;

import domain.type.Letter;

public class Score {

    public static final int BLACK_JACK = 21;
    public static final int ACE_SUB_NUMBER = 1;
    private final int value;

    private Score(final int value) {
        this.value = value;
    }

    public static Score of(final Cards cards) {
        final int score = getInitialScore(cards);
        final int count = cards.count(Letter.ACE);
        return new Score(modifyScoreByAce(score, count));
    }

    private static int modifyScoreByAce(int score, final int count) {
        for (int i = 0; i < count; i++) {
            if (score > BLACK_JACK) {
                score -= Letter.ACE.getNumber();
                score += ACE_SUB_NUMBER;
                continue;
            }
            break;
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
        return value > BLACK_JACK;
    }

    public int getValue() {
        return value;
    }
}
