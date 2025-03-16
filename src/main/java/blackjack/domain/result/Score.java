package blackjack.domain.result;

import blackjack.domain.game.Participant;

public class Score {
    private static final int BUSTED_STANDARD_SCORE_VALUE = 21;
    private static final int BLACKJACK_STANDARD_CARD_COUNT = 2;
    private static final int BLACKJACK_STANDARD_SCORE_VALUE = 21;

    private final int scoreValue;
    private final int cardCount;

    public Score(Participant participant) {
        this.scoreValue = participant.getOptimisticValue();
        this.cardCount = participant.getCards().size();
    }

    public boolean isBusted() {
        return scoreValue > BUSTED_STANDARD_SCORE_VALUE;
    }

    public boolean isBlackJack() {
        return cardCount == BLACKJACK_STANDARD_CARD_COUNT & scoreValue == BLACKJACK_STANDARD_SCORE_VALUE;
    }

    public int getScoreValue() {
        return scoreValue;
    }
}
