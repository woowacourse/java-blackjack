package blackjack.domain.state;

import blackjack.domain.game.PlayRecord;

public final class Blackjack extends Finished {

    Blackjack(Cards cards) {
        super(cards);
    }

    @Override
    public long revenue(PlayRecord playRecord, long bettingMoney) {
        if (playRecord == PlayRecord.BLACKJACK) {
            return (long)(bettingMoney * 1.5);
        }
        return 0;
    }
}
