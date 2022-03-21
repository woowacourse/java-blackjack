package blackjack.domain.state;

import blackjack.domain.card.ParticipantCards;

public class Bust extends Finished{

    private static final int BUST_RATE = -1;

    public Bust(ParticipantCards participantCards) {
        super(participantCards);
    }

    @Override
    double earningRate(State dealerState) {
        return BUST_RATE;
    }


    @Override
    public boolean isBust() {
        return true;
    }
}
