package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.ParticipantCards;

public abstract class Finished extends Started{

    public Finished(ParticipantCards participantCards) {
        super(participantCards);
    }

    public int getScore() {
        return participantCards.calculateScore();
    }

    @Override
    public State draw(Card card) {
        throw new IllegalStateException();
    }

    @Override
    public State stay() {
        throw new IllegalStateException();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

//    @Override
//    public double profit(double money, Finished dealerState) {
//        return earningRate(dealerState) * money;
//    }
//
//    abstract double earningRate(Finished dealerState);
}
