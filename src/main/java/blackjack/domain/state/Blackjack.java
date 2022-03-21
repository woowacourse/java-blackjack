package blackjack.domain.state;

import blackjack.domain.card.ParticipantCards;

public class Blackjack extends Finished {

    private static final double DRAW_RATE = 0;
    private static final double BLACKJACK_RATE = 1.5;

    public Blackjack(ParticipantCards participantCards) {
        super(participantCards);
    }

    @Override
    public boolean isBlackjack() {
        return true;
    }

    @Override
    double earningRate(State dealerState) {
        if (dealerState.isBlackjack()) {
            return DRAW_RATE;
        }
        return BLACKJACK_RATE;
    }
}
