package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.winning.WinningResult;

public class Blackjack extends Start {
    public Blackjack(Cards cards) {
        super(cards);
        if (!cards.isBlackjack()) {
            throw new IllegalArgumentException("블랙잭이 아닙니다.");
        }
    }

    @Override
    public State draw(Card card) {
        throw new IllegalStateException("블랙잭이므로 카드를 더 뽑을 수 없습니다.");
    }

    @Override
    public State stay() {
        return this;
    }

    @Override
    public double profit(double bettingMoney) {
        return bettingMoney * 1.5 - bettingMoney;
    }

    @Override
    public WinningResult decide(State state) {
        if (!state.isFinished()) {
            throw new IllegalArgumentException("끝난 상태와 승패를 결정할 수 있습니다.");
        }
        if (state instanceof Blackjack) {
            return WinningResult.DRAW;
        }
        return WinningResult.WIN;
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
