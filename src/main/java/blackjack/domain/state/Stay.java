package blackjack.domain.state;

import blackjack.domain.card.ParticipantCards;

public class Stay extends Finished {


    public Stay(ParticipantCards participantCards) {
        super(participantCards);
    }

//    @Override
//    double earningRate(Finished dealerState) {
//        return 0;
//    }

    @Override
    public ParticipantCards participantCards() {
        return participantCards;
    }

//    @Override
//    public State stay() {
//        return null;
//    }

//    @Override
//    public double earningRate(Finished dealerState) {
//        if (dealerState.isBlackjack()) {
//            return -1;
//        }
//        if (!dealerState.isBust() && this.getScore() == dealerState.getScore()) {
//            return 0;
//        }
//        return 1;
//    }

}
