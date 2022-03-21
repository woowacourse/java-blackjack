package blackjack.domain.state;

import blackjack.domain.card.ParticipantCards;

public class Bust extends Finished{

    public Bust(ParticipantCards participantCards) {
        super(participantCards);
    }

//    @Override
//    double earningRate(Finished dealerState) {
//        return 0;
//    }
//
//    public double earningRate() {
//        return -1;
//    }

    @Override
    public boolean isBust() {
        return true;
    }
}
