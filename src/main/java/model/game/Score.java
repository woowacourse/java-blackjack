package model.game;

import model.card.Card;
import model.card.Cards;

public class Score {

    private static final int ACE_SCORE_HIGH = 11;
    private static final int ACE_SCORE_LOW = 1;
    private static final int MIN_SCORE_FOR_ACE_HIGH = 11;
    private static final int BLACKJACK_SCORE = 21;

    private final int value;

    private Score(int value) {
        this.value = value;
    }

    public static Score from(Cards cards) {
        int totalScore = calculateTotalScore(cards);
        return new Score(totalScore);
    }

    private static int calculateTotalScore(Cards cards) {
        int totalScore = cards.calculateTotalScore();
        while (totalScore <= MIN_SCORE_FOR_ACE_HIGH && hasAce(cards)) {
            totalScore += ACE_SCORE_HIGH - ACE_SCORE_LOW;
        }
        return totalScore;
    }

    private static boolean hasAce(Cards cards) {
        return cards.getCards()
            .stream()
            .anyMatch(Card::isAce);
    }

    public boolean isNotBurst() {
        return !isBurst();
    }

    public boolean isBurst() {
        return value > BLACKJACK_SCORE;
    }

    public int getValue() {
        return value;
    }
}
