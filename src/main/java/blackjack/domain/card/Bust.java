package blackjack.domain.card;

import blackjack.domain.player.Result;

public class Bust extends Finished {
    Bust(final Cards cards) {
        super(cards);
    }

    @Override
    public Result play(final Hand other) {
        return Result.LOSE;
    }
}
