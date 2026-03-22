package domain.game;

import domain.card.Hand;

public class Blackjack extends Finished {

    public Blackjack(Hand hand) {
        super(hand);
    }

    @Override
    public Outcome calculateOutcome(HandState dealerState) {
        return dealerState.outcomeForBlackjack();
    }

    @Override
    public Outcome outcomeForBlackjack() {
        return Outcome.TIE;
    }

    @Override
    public Outcome outcomeForStay(int playerScore) {
        return Outcome.LOSE;
    }
}
