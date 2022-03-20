package blackjack.domain.state;

import blackjack.domain.game.PlayRecord;

public final class Bust extends Finished {

    Bust(Cards cards) {
        super(cards);
    }

    @Override
    public long revenue(PlayRecord playRecord, long bettingMoney) {
        return -bettingMoney;
    }
}
