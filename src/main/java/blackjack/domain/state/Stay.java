package blackjack.domain.state;

import blackjack.domain.game.PlayRecord;

final class Stay extends Finished {

    Stay(Cards cards) {
        super(cards);
    }

    @Override
    public long revenue(PlayRecord playRecord, long bettingMoney) {
        if (playRecord == PlayRecord.WIN) {
            return bettingMoney * WIN_MULTIPLIER;
        }

        if (playRecord == PlayRecord.LOSS) {
            return bettingMoney * LOSE_MULTIPLIER;
        }

        return PUSH_REVENUE;
    }
}
