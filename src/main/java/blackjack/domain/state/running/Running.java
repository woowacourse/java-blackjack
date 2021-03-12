package blackjack.domain.state.running;

import blackjack.domain.Money;
import blackjack.domain.card.Cards;
import blackjack.domain.state.Started;
import blackjack.domain.state.State;
import blackjack.domain.state.finished.Stay;

public abstract class Running extends Started {

    public Running(Cards cards) {
        super(cards);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public Money profit(Money money) {
        throw new IllegalArgumentException("[ERROR] 게임이 아직 안끝났습니다.");
    }

    @Override
    public State stay() {
        return new Stay(cards);
    }
}
