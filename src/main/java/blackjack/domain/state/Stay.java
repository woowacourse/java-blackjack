package blackjack.domain.state;

import blackjack.domain.game.PlayRecord;

final class Stay extends Finished {

    Stay(Cards cards) {
        super(cards);
    }

    @Override
    public long revenue(PlayRecord playRecord, Betting betting) {
        if (playRecord == PlayRecord.WIN) {
            return betting.multiply(WIN_MULTIPLIER);
        }

        if (playRecord == PlayRecord.LOSS) {
            return betting.multiply(LOSE_MULTIPLIER);
        }

        return betting.multiply(PUSH_MULTIPLIER);
    }
}
