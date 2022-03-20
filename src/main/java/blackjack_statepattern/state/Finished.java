package blackjack_statepattern.state;

import blackjack_statepattern.card.Card;
import blackjack_statepattern.card.Cards;

public abstract class Finished extends Started {
    protected static final int WIN_RATE = 1;
    protected static final int LOSE_RATE = -1;
    protected static final int DRAW_RATE = 0;
    protected static final double BLACKJACK_WIN_RATE = 1.5;


    public Finished(Cards cards) {
        super(cards);
    }

    @Override
    public final State draw(final Card card) {
        throw new IllegalArgumentException("[ERROR] 턴이 종료되어 더이상 카드를 받을 수 없습니다.");
    }

    @Override
    public final State stay() {
        throw new IllegalArgumentException("[ERROR] 턴이 종료되어 스테이할 수 없습니다.");
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public final boolean isFinished() {
        return true;
    }

    @Override
    public double profit(State state, final double money) {
        return money * earningRate(state);
    }

    protected abstract double earningRate(State state);

}
