package blackjack.domain.state.running;

import blackjack.domain.cards.Cards;
import blackjack.domain.state.Init;

public abstract class Running extends Init {
    public Running(Cards cards) {
        super(cards);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public double profit(double money) {
        throw new IllegalStateException("아직 수익을 계산할 수 없습니다.");
    }
}
