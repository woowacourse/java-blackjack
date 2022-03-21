package blackjack.domain.state.finished;

import blackjack.domain.cards.Cards;
import blackjack.domain.cards.card.Card;
import blackjack.domain.state.Init;
import blackjack.domain.state.State;

public abstract class Finished extends Init {
    static final float BLACKJACK_RATE = 1.5f;
    static final int WIN_RATE = 1;
    static final int LOSE_RATE = -1;
    static final int TIE_RATE = 0;

    public Finished(Cards cards) {
        super(cards);
    }

    @Override
    public State draw(Card card) {
        throw new IllegalStateException("더 이상 카드를 받을 수 없습니다.");
    }

    @Override
    public State stay() {
        return this;
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    public abstract boolean isBust();

    public abstract boolean isBlackjack();

    @Override
    public int profit(int money, Finished state) {
        return (int) (money * earningRate(state));
    }

    public abstract double earningRate(Finished state);
}
