package blackjack.model.state;

import blackjack.model.cards.Cards;
import blackjack.model.results.Result;

public class BlackJack extends Finished {
    public BlackJack(Cards cards) {
        super(cards);
    }

    @Override
    public Result determineResult(State otherState) {
        if (otherState.isBlackJack()) {
            return Result.PUSH;
        }
        return Result.WIN_BY_BLACKJACK;
    }
}
