package model.hand;

import java.util.List;
import model.deck.Card;

public final class SoftHand extends ParticipantHand {

    public SoftHand(final List<Card> cards) {
        super(cards);
    }

    public SoftHand() {
        super();
    }

    @Override
    public Score calculateDefaultScore() {
        return Score.calculateDefault(cards);
    }

    @Override
    public Score calculateFinalScore() {
        Score defaultScore = Score.calculateDefault(cards);
        Score maxScore = defaultScore.calculateMax();
        if (maxScore.isBurst()) {
            return defaultScore;
        }
        return maxScore;
    }

    @Override
    public ParticipantHand cloneToSoftHand() {
        return this;
    }
}
