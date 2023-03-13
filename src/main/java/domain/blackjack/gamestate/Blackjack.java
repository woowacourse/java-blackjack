package domain.blackjack.gamestate;

import domain.blackjack.Result;
import domain.card.Card;
import domain.card.Cards;

public class Blackjack extends GameState {
    Blackjack(Cards cards) {
        super(cards);
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
        if (otherState instanceof Blackjack) {
            return Result.DRAW;
        }

        return Result.WIN;
    }

    @Override
    public double getEarningRate() {
        return 1.5;
    }
}
