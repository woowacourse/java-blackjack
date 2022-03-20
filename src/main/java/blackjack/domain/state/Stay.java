package blackjack.domain.state;

import blackjack.domain.game.PlayRecord;

final class Stay extends Finished {

    Stay(Cards cards) {
        super(cards);
    }

    @Override
    public long revenue(PlayRecord playRecord, Bet bet) {
        if (playRecord == PlayRecord.WIN) {
            return bet.multiply(WIN_MULTIPLIER);
        }

        if (playRecord == PlayRecord.LOSS) {
            return bet.multiply(LOSE_MULTIPLIER);
        }

        return bet.multiply(PUSH_MULTIPLIER);
    }
}
