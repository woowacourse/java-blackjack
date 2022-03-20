package blackjack.domain.state;

import blackjack.domain.game.Betting;
import blackjack.domain.game.PlayRecord;

public final class Blackjack extends Finished {

    Blackjack(Cards cards) {
        super(cards);
    }

    @Override
    public long revenue(PlayRecord playRecord, Betting betting) {
        if (playRecord == PlayRecord.BLACKJACK) {
            return betting.multiply(BLACKJACK_MULTIPLIER);
        }
        return betting.multiply(PUSH_MULTIPLIER);
    }
}
