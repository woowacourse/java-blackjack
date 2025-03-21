package domain.card;

public record Card(CardType type, CardScore score) {

    private static final int ACE_HIGH_SCORE = 11;
    private static final int ACE_LOW_SCORE = 1;

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
