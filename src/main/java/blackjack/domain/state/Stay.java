package blackjack.domain.state;

import blackjack.domain.game.PlayRecord;

final class Stay extends Finished {

    Stay(Cards cards) {
        super(cards);
    }

    @Override
    public long revenue(PlayRecord playRecord, long bettingMoney) {
        if (playRecord == PlayRecord.WIN) {
            return bettingMoney;
        }

        if (playRecord == PlayRecord.LOSS) {
            return -bettingMoney;
        }

        return 0;
    }
}
