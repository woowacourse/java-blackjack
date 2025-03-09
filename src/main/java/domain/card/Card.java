package domain.card;

import java.util.Objects;

public class Card {

    public static final int ACE_HIGH_SCORE = 11;
    public static final int ACE_LOW_SCORE = 1;
    private final CardType type;
    private final CardScore score;

    public Card(CardType type, CardScore score) {
        this.type = type;
        this.score = score;
    }

    public boolean isAce() {
        return getScore() == CardScore.ACE;
    }

    public int calculateAceScore(final int score, final int limit) {
        final int result = limit - score;
        int aceScore = ACE_LOW_SCORE;
        if (result >= ACE_HIGH_SCORE) {
            aceScore = ACE_HIGH_SCORE;
        }
        return aceScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return type == card.type && score == card.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, score);
    }

    public CardScore getScore() {
        return score;
    }

    public CardType getType() {
        return type;
    }
}
