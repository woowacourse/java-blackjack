package blackjack.state;

import blackjack.domain.Dealer;
import blackjack.domain.User;
import blackjack.domain.card.Card;

public abstract class Finished extends Started {

    public Finished(Cards cards) {
        super(cards);
    }

    @Override
    public State draw(Card card) {
        throw new UnsupportedOperationException("게임이 종료 상태라 카드를 더이상 뽑을 수 없습니다.");
    }

    @Override
    public State stay() {
        throw new UnsupportedOperationException("이미 게임턴이 끝난 상태입니다.");
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public double profit(double money, State dealerState) {
        return money * earningRate(dealerState);
    }

    public abstract double earningRate(State dealerState);
}
