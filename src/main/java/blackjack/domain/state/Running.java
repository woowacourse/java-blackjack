package blackjack.domain.state;

import blackjack.domain.card.CardHand;
import blackjack.domain.game.ResultStatus;

public abstract class Running extends Started {

    Running(final CardHand cardHand) {
        super(cardHand);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public double calculateEarningRate(final ResultStatus resultStatus) {
        throw new UnsupportedOperationException("아직 게임이 종료되지 않아 수익률을 계산할 수 없습니다.");
    }
}
