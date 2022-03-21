package blackjack.domain.state;

import blackjack.domain.card.ParticipantCards;

public class Blackjack extends Finished{

    public Blackjack(ParticipantCards participantCards) {
        super(participantCards);
    }

    @Override
    public boolean isBlackjack() {
        return true;
    }

//    @Override
//    double earningRate(Finished dealerState) {
//        if (dealerState.isBlackjack()) {
//            return 0;
//        }
//        return 1.5;
//    }
}
