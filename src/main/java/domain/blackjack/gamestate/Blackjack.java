package domain.blackjack.gamestate;

import domain.blackjack.Result;
import domain.card.Card;
import domain.card.Cards;

public class Blackjack extends GameState {
    private static final double BLACKJACK_EARN_RATE = 1.5;

    public Blackjack(Cards cards, HandState handState) {
        super(cards, handState);
    }

    @Override
    public GameState receive(Card card) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isAbleToReceiveCard() {
        return false;
    }

    @Override
    public Result competeToOtherState(GameState otherState) {
        if (handState.isEqualState(otherState.handState)) {
            return Result.DRAW;
        }

        return Result.WIN;
    }

    @Override
    public double getEarningRate() {
        return BLACKJACK_EARN_RATE;
    }
}
