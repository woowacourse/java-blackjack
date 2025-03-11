package domain.card;

public class Card {

    private static final int ACE_HIGH_SCORE = 11;
    private static final int ACE_LOW_SCORE = 1;
    private final CardType type;
    private final CardScore score;

    public Card(CardType type, final CardScore score) {
        this.type = type;
        this.score = score;
    }

    public CardScore getScore() {
        return score;
    }

    public CardType getType() {
        return type;
    }

    public boolean isAce() {
        return score == CardScore.ACE;
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
