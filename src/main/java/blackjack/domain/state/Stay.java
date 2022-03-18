package blackjack.domain.state;

import blackjack.domain.bet.Betting;
import blackjack.domain.game.PlayingCards;

public class Stay extends Finished {

    // TODO: 딜러와 비교하여 값 변경
    private static final double PROFIT_RATE = 1;

    public Stay(final PlayingCards playingCards, final Betting betting) {
        super(playingCards);
        this.betting = betting;
    }

    @Override
    public void bet(final String betting) {
        throw new IllegalStateException("Stay 상태일 때는 bet 을 실행할 수 없습니다.");
    }

    @Override
    double earningRate() {
        return PROFIT_RATE;
    }
}
