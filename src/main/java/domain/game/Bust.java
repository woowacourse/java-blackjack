package domain.game;

import domain.card.Hand;

public class Bust extends Finished {

    public Bust(Hand hand) {
        super(hand);
    }

    @Override
    public Outcome calculateOutcome(HandState dealerState) {
        return Outcome.LOSE;
    }

    @Override
    public Outcome outcomeForBlackjack() {
        return Outcome.BLACKJACK_WIN;
    }

    @Override
    public Outcome outcomeForStay(int playerScore) {
        return Outcome.WIN;
    }
}
