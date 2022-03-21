package blackjack.domain.state;

import blackjack.domain.card.ParticipantCards;

public class Stay extends Finished {

    private static final int LOSE_RATE = -1;
    private static final int DRAW_RATE = 0;
    private static final int WIN_RATE = 1;

    public Stay(ParticipantCards participantCards) {
        super(participantCards);
    }

    @Override
    public ParticipantCards getParticipantCards() {
        return participantCards;
    }


    @Override
    public double earningRate(State dealerState) {
        if (!dealerState.isBust() && this.getScore() == dealerState.getScore()) {
            return DRAW_RATE;
        }
        if (dealerState.isBust() || !dealerState.isBust() && this.getScore() > dealerState.getScore()) {
            return WIN_RATE;
        }
        return LOSE_RATE;
    }

}
