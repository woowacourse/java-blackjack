package blackjack.domain.state;

import blackjack.domain.game.PlayRecord;

public final class Blackjack extends Finished {

    Blackjack(Cards cards) {
        super(cards);
    }

    @Override
    public long revenue(PlayRecord playRecord, Bet bet) {
        if (playRecord == PlayRecord.BLACKJACK) {
            return bet.multiply(BLACKJACK_MULTIPLIER);
        }
        return bet.multiply(PUSH_MULTIPLIER);
    }
}
