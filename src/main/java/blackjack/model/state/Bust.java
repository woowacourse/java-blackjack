package blackjack.model.state;

import blackjack.model.cards.Cards;
import blackjack.model.results.Result;

public class Bust extends Finished {
    public Bust(Cards cards) {
        super(cards);
    }

    @Override
    public Result determineResult(State otherState) {
        return Result.LOSE;
    }
}
