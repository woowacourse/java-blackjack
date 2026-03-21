package domain.game;

import domain.card.Hand;

public class Stay extends Finished {

    public Stay(Hand hand) {
        super(hand);
    }

    @Override
    public Outcome calculateOutcome(HandState dealerState) {
        return dealerState.outcomeForStay(score());
    }

    @Override
    public Outcome outcomeForBlackjack() {
        return Outcome.BLACKJACK_WIN;
    }

    @Override
    public Outcome outcomeForStay(int playerScore) {
        if (playerScore > score()) {
            return Outcome.WIN;
        }
        if (playerScore == score()) {
            return Outcome.TIE;
        }
        return Outcome.LOSE;
    }
}