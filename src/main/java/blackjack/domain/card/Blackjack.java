package blackjack.domain.card;

import blackjack.domain.player.Result;

public class Blackjack extends Finished {
    Blackjack(final Cards cards) {
        super(cards);
    }

    @Override
    public Result play(final Hand other) {
        if (other.cards().isBlackjack()) {
            return Result.PUSH;
        }
        return Result.BLACKJACK_WIN;
    }
}
