package model.hand;

import java.util.List;
import model.deck.Card;
import model.deck.CardRank;

public final class SoftHand extends ParticipantHand {
    private static final int BURST_SCORE_LIMIT = 21;

    public SoftHand(final List<Card> cards) {
        super(cards);
    }

    public SoftHand() {
        super();
    }

    @Override
    public int calculateFinalScore() {
        int defaultScore = calculateDefaultScore();
        int maxScore = convertOneAceToMaxValueFrom(defaultScore);
        if (maxScore <= BURST_SCORE_LIMIT) {
            return maxScore;
        }
        return defaultScore;
    }

    @Override
    public ParticipantHand cloneToSoftHand() {
        return this;
    }

    private int convertOneAceToMaxValueFrom(final int score) {
        return score - CardRank.ACE.getDefaultValue() + CardRank.ACE.getMaxValue();
    }
}
