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
        int score = getInitialScore(cards);
        if (score > BLACK_JACK && cards.contains(Letter.ACE)) {
            score -= Letter.ACE.getNumber();
            score += ACE_SUB_NUMBER;
        }
        return new Score(score);
    }

    private static int getInitialScore(final Cards cards) {
        return cards.getCards()
            .stream()
            .mapToInt(Card::getNumber)
            .sum();
    }

    public int getValue() {
        return value;
    }
}
