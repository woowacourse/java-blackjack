package blackjack.domain.state;

import blackjack.domain.bet.Betting;
import blackjack.domain.game.PlayingCards;

public class Stay extends Finished {

    private double earningRate = DEFAULT_RATE;

    public Stay(final PlayingCards playingCards, final Betting betting) {
        super(playingCards);
        this.betting = betting;
    }

    @Override
    public void bet(final String betting) {
        throw new IllegalStateException("Stay 상태일 때는 bet 을 실행할 수 없습니다.");
    }

    @Override
    public void decideRate(final double rate) {
        earningRate = rate;
    }

    @Override
    double earningRate() {
        return earningRate;
    }
}
