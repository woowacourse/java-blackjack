package domain.card;

import domain.card.CardType.Score;

public class Card {

    public static final int ACE_HIGH_SCORE = 11;
    public static final int ACE_LOW_SCORE = 1;
    private final CardType type;

    public Card(CardType type) {
        this.type = type;
    }

    public Score getScore() {
        return type.getScore();
    }

    public CardType getType() {
        return type;
    }

    public boolean isAce() {
        return type.getScore().getValue() == ACE_LOW_SCORE;
    }

    public int calculateAceScore(final int score, final int limit) {
        final int result = limit - score;
        int aceScore = ACE_LOW_SCORE;
        if (result >= ACE_HIGH_SCORE) {
            aceScore = ACE_HIGH_SCORE;
        }
        return aceScore;
    }
}
