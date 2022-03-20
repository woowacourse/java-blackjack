package blackjack_statepattern.state;

import blackjack_statepattern.card.Card;
import blackjack_statepattern.card.Cards;

public abstract class Finished implements State {
    protected static final int WIN_RATE = 1;
    protected static final int LOSE_RATE = -1;
    protected static final int DRAW_RATE = 0;
    protected static final double BLACKJACK_WIN_RATE = 1.5;

    private final Cards cards;

    public Finished(Cards cards) {
        this.cards = cards;
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
    public int score() {
        return cards().computeScore();
    }

    @Override
    public Cards cards() {
        return cards;
    }

    @Override
    public double profit(State state, final double money) {
        return money * computeEarningRate(state);
    }

    protected abstract double computeEarningRate(State state);

}
